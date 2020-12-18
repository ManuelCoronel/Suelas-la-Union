/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.IllegalOrphanException;
import DAO.exceptions.NonexistentEntityException;
import DAO.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Producto;
import java.util.ArrayList;
import java.util.List;
import DTO.PedidoProveedor;
import DTO.Proveedor;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author MANUEL
 */
public class ProveedorJpaController implements Serializable {
  Conexion con = Conexion.getConexion();
    public ProveedorJpaController() {
        this.emf = con.getBd();
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Proveedor proveedor) throws PreexistingEntityException, Exception {
        if (proveedor.getProductoList() == null) {
            proveedor.setProductoList(new ArrayList<Producto>());
        }
        if (proveedor.getPedidoProveedorList() == null) {
            proveedor.setPedidoProveedorList(new ArrayList<PedidoProveedor>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Producto> attachedProductoList = new ArrayList<Producto>();
            for (Producto productoListProductoToAttach : proveedor.getProductoList()) {
                productoListProductoToAttach = em.getReference(productoListProductoToAttach.getClass(), productoListProductoToAttach.getId());
                attachedProductoList.add(productoListProductoToAttach);
            }
            proveedor.setProductoList(attachedProductoList);
            List<PedidoProveedor> attachedPedidoProveedorList = new ArrayList<PedidoProveedor>();
            for (PedidoProveedor pedidoProveedorListPedidoProveedorToAttach : proveedor.getPedidoProveedorList()) {
                pedidoProveedorListPedidoProveedorToAttach = em.getReference(pedidoProveedorListPedidoProveedorToAttach.getClass(), pedidoProveedorListPedidoProveedorToAttach.getId());
                attachedPedidoProveedorList.add(pedidoProveedorListPedidoProveedorToAttach);
            }
            proveedor.setPedidoProveedorList(attachedPedidoProveedorList);
            em.persist(proveedor);
            for (Producto productoListProducto : proveedor.getProductoList()) {
                Proveedor oldProveedorCedulaOfProductoListProducto = productoListProducto.getProveedorCedula();
                productoListProducto.setProveedorCedula(proveedor);
                productoListProducto = em.merge(productoListProducto);
                if (oldProveedorCedulaOfProductoListProducto != null) {
                    oldProveedorCedulaOfProductoListProducto.getProductoList().remove(productoListProducto);
                    oldProveedorCedulaOfProductoListProducto = em.merge(oldProveedorCedulaOfProductoListProducto);
                }
            }
            for (PedidoProveedor pedidoProveedorListPedidoProveedor : proveedor.getPedidoProveedorList()) {
                Proveedor oldProveedorCedulaOfPedidoProveedorListPedidoProveedor = pedidoProveedorListPedidoProveedor.getProveedorCedula();
                pedidoProveedorListPedidoProveedor.setProveedorCedula(proveedor);
                pedidoProveedorListPedidoProveedor = em.merge(pedidoProveedorListPedidoProveedor);
                if (oldProveedorCedulaOfPedidoProveedorListPedidoProveedor != null) {
                    oldProveedorCedulaOfPedidoProveedorListPedidoProveedor.getPedidoProveedorList().remove(pedidoProveedorListPedidoProveedor);
                    oldProveedorCedulaOfPedidoProveedorListPedidoProveedor = em.merge(oldProveedorCedulaOfPedidoProveedorListPedidoProveedor);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProveedor(proveedor.getCedula()) != null) {
                throw new PreexistingEntityException("Proveedor " + proveedor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Proveedor proveedor) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedor persistentProveedor = em.find(Proveedor.class, proveedor.getCedula());
            List<Producto> productoListOld = persistentProveedor.getProductoList();
            List<Producto> productoListNew = proveedor.getProductoList();
            List<PedidoProveedor> pedidoProveedorListOld = persistentProveedor.getPedidoProveedorList();
            List<PedidoProveedor> pedidoProveedorListNew = proveedor.getPedidoProveedorList();
            List<String> illegalOrphanMessages = null;
            for (Producto productoListOldProducto : productoListOld) {
                if (!productoListNew.contains(productoListOldProducto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Producto " + productoListOldProducto + " since its proveedorCedula field is not nullable.");
                }
            }
            for (PedidoProveedor pedidoProveedorListOldPedidoProveedor : pedidoProveedorListOld) {
                if (!pedidoProveedorListNew.contains(pedidoProveedorListOldPedidoProveedor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PedidoProveedor " + pedidoProveedorListOldPedidoProveedor + " since its proveedorCedula field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Producto> attachedProductoListNew = new ArrayList<Producto>();
            for (Producto productoListNewProductoToAttach : productoListNew) {
                productoListNewProductoToAttach = em.getReference(productoListNewProductoToAttach.getClass(), productoListNewProductoToAttach.getId());
                attachedProductoListNew.add(productoListNewProductoToAttach);
            }
            productoListNew = attachedProductoListNew;
            proveedor.setProductoList(productoListNew);
            List<PedidoProveedor> attachedPedidoProveedorListNew = new ArrayList<PedidoProveedor>();
            for (PedidoProveedor pedidoProveedorListNewPedidoProveedorToAttach : pedidoProveedorListNew) {
                pedidoProveedorListNewPedidoProveedorToAttach = em.getReference(pedidoProveedorListNewPedidoProveedorToAttach.getClass(), pedidoProveedorListNewPedidoProveedorToAttach.getId());
                attachedPedidoProveedorListNew.add(pedidoProveedorListNewPedidoProveedorToAttach);
            }
            pedidoProveedorListNew = attachedPedidoProveedorListNew;
            proveedor.setPedidoProveedorList(pedidoProveedorListNew);
            proveedor = em.merge(proveedor);
            for (Producto productoListNewProducto : productoListNew) {
                if (!productoListOld.contains(productoListNewProducto)) {
                    Proveedor oldProveedorCedulaOfProductoListNewProducto = productoListNewProducto.getProveedorCedula();
                    productoListNewProducto.setProveedorCedula(proveedor);
                    productoListNewProducto = em.merge(productoListNewProducto);
                    if (oldProveedorCedulaOfProductoListNewProducto != null && !oldProveedorCedulaOfProductoListNewProducto.equals(proveedor)) {
                        oldProveedorCedulaOfProductoListNewProducto.getProductoList().remove(productoListNewProducto);
                        oldProveedorCedulaOfProductoListNewProducto = em.merge(oldProveedorCedulaOfProductoListNewProducto);
                    }
                }
            }
            for (PedidoProveedor pedidoProveedorListNewPedidoProveedor : pedidoProveedorListNew) {
                if (!pedidoProveedorListOld.contains(pedidoProveedorListNewPedidoProveedor)) {
                    Proveedor oldProveedorCedulaOfPedidoProveedorListNewPedidoProveedor = pedidoProveedorListNewPedidoProveedor.getProveedorCedula();
                    pedidoProveedorListNewPedidoProveedor.setProveedorCedula(proveedor);
                    pedidoProveedorListNewPedidoProveedor = em.merge(pedidoProveedorListNewPedidoProveedor);
                    if (oldProveedorCedulaOfPedidoProveedorListNewPedidoProveedor != null && !oldProveedorCedulaOfPedidoProveedorListNewPedidoProveedor.equals(proveedor)) {
                        oldProveedorCedulaOfPedidoProveedorListNewPedidoProveedor.getPedidoProveedorList().remove(pedidoProveedorListNewPedidoProveedor);
                        oldProveedorCedulaOfPedidoProveedorListNewPedidoProveedor = em.merge(oldProveedorCedulaOfPedidoProveedorListNewPedidoProveedor);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = proveedor.getCedula();
                if (findProveedor(id) == null) {
                    throw new NonexistentEntityException("The proveedor with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedor proveedor;
            try {
                proveedor = em.getReference(Proveedor.class, id);
                proveedor.getCedula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proveedor with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Producto> productoListOrphanCheck = proveedor.getProductoList();
            for (Producto productoListOrphanCheckProducto : productoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Proveedor (" + proveedor + ") cannot be destroyed since the Producto " + productoListOrphanCheckProducto + " in its productoList field has a non-nullable proveedorCedula field.");
            }
            List<PedidoProveedor> pedidoProveedorListOrphanCheck = proveedor.getPedidoProveedorList();
            for (PedidoProveedor pedidoProveedorListOrphanCheckPedidoProveedor : pedidoProveedorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Proveedor (" + proveedor + ") cannot be destroyed since the PedidoProveedor " + pedidoProveedorListOrphanCheckPedidoProveedor + " in its pedidoProveedorList field has a non-nullable proveedorCedula field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(proveedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Proveedor> findProveedorEntities() {
        return findProveedorEntities(true, -1, -1);
    }

    public List<Proveedor> findProveedorEntities(int maxResults, int firstResult) {
        return findProveedorEntities(false, maxResults, firstResult);
    }

    private List<Proveedor> findProveedorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Proveedor.class));
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

    public Proveedor findProveedor(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Proveedor.class, id);
        } finally {
            em.close();
        }
    }

    public int getProveedorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Proveedor> rt = cq.from(Proveedor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
