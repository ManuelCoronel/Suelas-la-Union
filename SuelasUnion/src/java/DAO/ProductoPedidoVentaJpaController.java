/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.NonexistentEntityException;
import DAO.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Producto;
import DTO.PedidoDeVenta;
import DTO.ProductoPedidoVenta;
import DTO.ProductoPedidoVentaPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author MANUEL
 */
public class ProductoPedidoVentaJpaController implements Serializable {
  Conexion con = Conexion.getConexion();
    public ProductoPedidoVentaJpaController() {
        this.emf = con.getBd();
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProductoPedidoVenta productoPedidoVenta) throws PreexistingEntityException, Exception {
        if (productoPedidoVenta.getProductoPedidoVentaPK() == null) {
            productoPedidoVenta.setProductoPedidoVentaPK(new ProductoPedidoVentaPK());
        }
        productoPedidoVenta.getProductoPedidoVentaPK().setProductoId(productoPedidoVenta.getProducto().getId());
        productoPedidoVenta.getProductoPedidoVentaPK().setPedidoDeVentaId(productoPedidoVenta.getPedidoDeVenta().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto producto = productoPedidoVenta.getProducto();
            if (producto != null) {
                producto = em.getReference(producto.getClass(), producto.getId());
                productoPedidoVenta.setProducto(producto);
            }
            PedidoDeVenta pedidoDeVenta = productoPedidoVenta.getPedidoDeVenta();
            if (pedidoDeVenta != null) {
                pedidoDeVenta = em.getReference(pedidoDeVenta.getClass(), pedidoDeVenta.getId());
                productoPedidoVenta.setPedidoDeVenta(pedidoDeVenta);
            }
            em.persist(productoPedidoVenta);
            if (producto != null) {
                producto.getProductoPedidoVentaList().add(productoPedidoVenta);
                producto = em.merge(producto);
            }
            if (pedidoDeVenta != null) {
                pedidoDeVenta.getProductoPedidoVentaList().add(productoPedidoVenta);
                pedidoDeVenta = em.merge(pedidoDeVenta);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProductoPedidoVenta(productoPedidoVenta.getProductoPedidoVentaPK()) != null) {
                throw new PreexistingEntityException("ProductoPedidoVenta " + productoPedidoVenta + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProductoPedidoVenta productoPedidoVenta) throws NonexistentEntityException, Exception {
        productoPedidoVenta.getProductoPedidoVentaPK().setProductoId(productoPedidoVenta.getProducto().getId());
        productoPedidoVenta.getProductoPedidoVentaPK().setPedidoDeVentaId(productoPedidoVenta.getPedidoDeVenta().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProductoPedidoVenta persistentProductoPedidoVenta = em.find(ProductoPedidoVenta.class, productoPedidoVenta.getProductoPedidoVentaPK());
            Producto productoOld = persistentProductoPedidoVenta.getProducto();
            Producto productoNew = productoPedidoVenta.getProducto();
            PedidoDeVenta pedidoDeVentaOld = persistentProductoPedidoVenta.getPedidoDeVenta();
            PedidoDeVenta pedidoDeVentaNew = productoPedidoVenta.getPedidoDeVenta();
            if (productoNew != null) {
                productoNew = em.getReference(productoNew.getClass(), productoNew.getId());
                productoPedidoVenta.setProducto(productoNew);
            }
            if (pedidoDeVentaNew != null) {
                pedidoDeVentaNew = em.getReference(pedidoDeVentaNew.getClass(), pedidoDeVentaNew.getId());
                productoPedidoVenta.setPedidoDeVenta(pedidoDeVentaNew);
            }
            productoPedidoVenta = em.merge(productoPedidoVenta);
            if (productoOld != null && !productoOld.equals(productoNew)) {
                productoOld.getProductoPedidoVentaList().remove(productoPedidoVenta);
                productoOld = em.merge(productoOld);
            }
            if (productoNew != null && !productoNew.equals(productoOld)) {
                productoNew.getProductoPedidoVentaList().add(productoPedidoVenta);
                productoNew = em.merge(productoNew);
            }
            if (pedidoDeVentaOld != null && !pedidoDeVentaOld.equals(pedidoDeVentaNew)) {
                pedidoDeVentaOld.getProductoPedidoVentaList().remove(productoPedidoVenta);
                pedidoDeVentaOld = em.merge(pedidoDeVentaOld);
            }
            if (pedidoDeVentaNew != null && !pedidoDeVentaNew.equals(pedidoDeVentaOld)) {
                pedidoDeVentaNew.getProductoPedidoVentaList().add(productoPedidoVenta);
                pedidoDeVentaNew = em.merge(pedidoDeVentaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ProductoPedidoVentaPK id = productoPedidoVenta.getProductoPedidoVentaPK();
                if (findProductoPedidoVenta(id) == null) {
                    throw new NonexistentEntityException("The productoPedidoVenta with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ProductoPedidoVentaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ProductoPedidoVenta productoPedidoVenta;
            try {
                productoPedidoVenta = em.getReference(ProductoPedidoVenta.class, id);
                productoPedidoVenta.getProductoPedidoVentaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productoPedidoVenta with id " + id + " no longer exists.", enfe);
            }
            Producto producto = productoPedidoVenta.getProducto();
            if (producto != null) {
                producto.getProductoPedidoVentaList().remove(productoPedidoVenta);
                producto = em.merge(producto);
            }
            PedidoDeVenta pedidoDeVenta = productoPedidoVenta.getPedidoDeVenta();
            if (pedidoDeVenta != null) {
                pedidoDeVenta.getProductoPedidoVentaList().remove(productoPedidoVenta);
                pedidoDeVenta = em.merge(pedidoDeVenta);
            }
            em.remove(productoPedidoVenta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ProductoPedidoVenta> findProductoPedidoVentaEntities() {
        return findProductoPedidoVentaEntities(true, -1, -1);
    }

    public List<ProductoPedidoVenta> findProductoPedidoVentaEntities(int maxResults, int firstResult) {
        return findProductoPedidoVentaEntities(false, maxResults, firstResult);
    }

    private List<ProductoPedidoVenta> findProductoPedidoVentaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProductoPedidoVenta.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ProductoPedidoVenta findProductoPedidoVenta(ProductoPedidoVentaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProductoPedidoVenta.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductoPedidoVentaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProductoPedidoVenta> rt = cq.from(ProductoPedidoVenta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
