/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.IllegalOrphanException;
import DAO.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Proveedor;
import DTO.ProductoPedidoProveedor;
import java.util.ArrayList;
import java.util.List;
import DTO.DevolucionProveedor;
import DTO.PedidoProveedor;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author MANUEL
 */
public class PedidoProveedorJpaController implements Serializable {
  Conexion con = Conexion.getConexion();
    public PedidoProveedorJpaController() {
        this.emf = con.getBd();
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PedidoProveedor pedidoProveedor) {
        if (pedidoProveedor.getProductoPedidoProveedorList() == null) {
            pedidoProveedor.setProductoPedidoProveedorList(new ArrayList<ProductoPedidoProveedor>());
        }
        if (pedidoProveedor.getDevolucionProveedorList() == null) {
            pedidoProveedor.setDevolucionProveedorList(new ArrayList<DevolucionProveedor>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedor proveedorCedula = pedidoProveedor.getProveedorCedula();
            if (proveedorCedula != null) {
                proveedorCedula = em.getReference(proveedorCedula.getClass(), proveedorCedula.getCedula());
                pedidoProveedor.setProveedorCedula(proveedorCedula);
            }
            List<ProductoPedidoProveedor> attachedProductoPedidoProveedorList = new ArrayList<ProductoPedidoProveedor>();
            for (ProductoPedidoProveedor productoPedidoProveedorListProductoPedidoProveedorToAttach : pedidoProveedor.getProductoPedidoProveedorList()) {
                productoPedidoProveedorListProductoPedidoProveedorToAttach = em.getReference(productoPedidoProveedorListProductoPedidoProveedorToAttach.getClass(), productoPedidoProveedorListProductoPedidoProveedorToAttach.getProductoPedidoProveedorPK());
                attachedProductoPedidoProveedorList.add(productoPedidoProveedorListProductoPedidoProveedorToAttach);
            }
            pedidoProveedor.setProductoPedidoProveedorList(attachedProductoPedidoProveedorList);
            List<DevolucionProveedor> attachedDevolucionProveedorList = new ArrayList<DevolucionProveedor>();
            for (DevolucionProveedor devolucionProveedorListDevolucionProveedorToAttach : pedidoProveedor.getDevolucionProveedorList()) {
                devolucionProveedorListDevolucionProveedorToAttach = em.getReference(devolucionProveedorListDevolucionProveedorToAttach.getClass(), devolucionProveedorListDevolucionProveedorToAttach.getId());
                attachedDevolucionProveedorList.add(devolucionProveedorListDevolucionProveedorToAttach);
            }
            pedidoProveedor.setDevolucionProveedorList(attachedDevolucionProveedorList);
            em.persist(pedidoProveedor);
            if (proveedorCedula != null) {
                proveedorCedula.getPedidoProveedorList().add(pedidoProveedor);
                proveedorCedula = em.merge(proveedorCedula);
            }
            for (ProductoPedidoProveedor productoPedidoProveedorListProductoPedidoProveedor : pedidoProveedor.getProductoPedidoProveedorList()) {
                PedidoProveedor oldPedidoProveedorOfProductoPedidoProveedorListProductoPedidoProveedor = productoPedidoProveedorListProductoPedidoProveedor.getPedidoProveedor();
                productoPedidoProveedorListProductoPedidoProveedor.setPedidoProveedor(pedidoProveedor);
                productoPedidoProveedorListProductoPedidoProveedor = em.merge(productoPedidoProveedorListProductoPedidoProveedor);
                if (oldPedidoProveedorOfProductoPedidoProveedorListProductoPedidoProveedor != null) {
                    oldPedidoProveedorOfProductoPedidoProveedorListProductoPedidoProveedor.getProductoPedidoProveedorList().remove(productoPedidoProveedorListProductoPedidoProveedor);
                    oldPedidoProveedorOfProductoPedidoProveedorListProductoPedidoProveedor = em.merge(oldPedidoProveedorOfProductoPedidoProveedorListProductoPedidoProveedor);
                }
            }
            for (DevolucionProveedor devolucionProveedorListDevolucionProveedor : pedidoProveedor.getDevolucionProveedorList()) {
                PedidoProveedor oldPedidoProveedorIdOfDevolucionProveedorListDevolucionProveedor = devolucionProveedorListDevolucionProveedor.getPedidoProveedorId();
                devolucionProveedorListDevolucionProveedor.setPedidoProveedorId(pedidoProveedor);
                devolucionProveedorListDevolucionProveedor = em.merge(devolucionProveedorListDevolucionProveedor);
                if (oldPedidoProveedorIdOfDevolucionProveedorListDevolucionProveedor != null) {
                    oldPedidoProveedorIdOfDevolucionProveedorListDevolucionProveedor.getDevolucionProveedorList().remove(devolucionProveedorListDevolucionProveedor);
                    oldPedidoProveedorIdOfDevolucionProveedorListDevolucionProveedor = em.merge(oldPedidoProveedorIdOfDevolucionProveedorListDevolucionProveedor);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PedidoProveedor pedidoProveedor) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PedidoProveedor persistentPedidoProveedor = em.find(PedidoProveedor.class, pedidoProveedor.getId());
            Proveedor proveedorCedulaOld = persistentPedidoProveedor.getProveedorCedula();
            Proveedor proveedorCedulaNew = pedidoProveedor.getProveedorCedula();
            List<ProductoPedidoProveedor> productoPedidoProveedorListOld = persistentPedidoProveedor.getProductoPedidoProveedorList();
            List<ProductoPedidoProveedor> productoPedidoProveedorListNew = pedidoProveedor.getProductoPedidoProveedorList();
            List<DevolucionProveedor> devolucionProveedorListOld = persistentPedidoProveedor.getDevolucionProveedorList();
            List<DevolucionProveedor> devolucionProveedorListNew = pedidoProveedor.getDevolucionProveedorList();
            List<String> illegalOrphanMessages = null;
            for (ProductoPedidoProveedor productoPedidoProveedorListOldProductoPedidoProveedor : productoPedidoProveedorListOld) {
                if (!productoPedidoProveedorListNew.contains(productoPedidoProveedorListOldProductoPedidoProveedor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProductoPedidoProveedor " + productoPedidoProveedorListOldProductoPedidoProveedor + " since its pedidoProveedor field is not nullable.");
                }
            }
            for (DevolucionProveedor devolucionProveedorListOldDevolucionProveedor : devolucionProveedorListOld) {
                if (!devolucionProveedorListNew.contains(devolucionProveedorListOldDevolucionProveedor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DevolucionProveedor " + devolucionProveedorListOldDevolucionProveedor + " since its pedidoProveedorId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (proveedorCedulaNew != null) {
                proveedorCedulaNew = em.getReference(proveedorCedulaNew.getClass(), proveedorCedulaNew.getCedula());
                pedidoProveedor.setProveedorCedula(proveedorCedulaNew);
            }
            List<ProductoPedidoProveedor> attachedProductoPedidoProveedorListNew = new ArrayList<ProductoPedidoProveedor>();
            for (ProductoPedidoProveedor productoPedidoProveedorListNewProductoPedidoProveedorToAttach : productoPedidoProveedorListNew) {
                productoPedidoProveedorListNewProductoPedidoProveedorToAttach = em.getReference(productoPedidoProveedorListNewProductoPedidoProveedorToAttach.getClass(), productoPedidoProveedorListNewProductoPedidoProveedorToAttach.getProductoPedidoProveedorPK());
                attachedProductoPedidoProveedorListNew.add(productoPedidoProveedorListNewProductoPedidoProveedorToAttach);
            }
            productoPedidoProveedorListNew = attachedProductoPedidoProveedorListNew;
            pedidoProveedor.setProductoPedidoProveedorList(productoPedidoProveedorListNew);
            List<DevolucionProveedor> attachedDevolucionProveedorListNew = new ArrayList<DevolucionProveedor>();
            for (DevolucionProveedor devolucionProveedorListNewDevolucionProveedorToAttach : devolucionProveedorListNew) {
                devolucionProveedorListNewDevolucionProveedorToAttach = em.getReference(devolucionProveedorListNewDevolucionProveedorToAttach.getClass(), devolucionProveedorListNewDevolucionProveedorToAttach.getId());
                attachedDevolucionProveedorListNew.add(devolucionProveedorListNewDevolucionProveedorToAttach);
            }
            devolucionProveedorListNew = attachedDevolucionProveedorListNew;
            pedidoProveedor.setDevolucionProveedorList(devolucionProveedorListNew);
            pedidoProveedor = em.merge(pedidoProveedor);
            if (proveedorCedulaOld != null && !proveedorCedulaOld.equals(proveedorCedulaNew)) {
                proveedorCedulaOld.getPedidoProveedorList().remove(pedidoProveedor);
                proveedorCedulaOld = em.merge(proveedorCedulaOld);
            }
            if (proveedorCedulaNew != null && !proveedorCedulaNew.equals(proveedorCedulaOld)) {
                proveedorCedulaNew.getPedidoProveedorList().add(pedidoProveedor);
                proveedorCedulaNew = em.merge(proveedorCedulaNew);
            }
            for (ProductoPedidoProveedor productoPedidoProveedorListNewProductoPedidoProveedor : productoPedidoProveedorListNew) {
                if (!productoPedidoProveedorListOld.contains(productoPedidoProveedorListNewProductoPedidoProveedor)) {
                    PedidoProveedor oldPedidoProveedorOfProductoPedidoProveedorListNewProductoPedidoProveedor = productoPedidoProveedorListNewProductoPedidoProveedor.getPedidoProveedor();
                    productoPedidoProveedorListNewProductoPedidoProveedor.setPedidoProveedor(pedidoProveedor);
                    productoPedidoProveedorListNewProductoPedidoProveedor = em.merge(productoPedidoProveedorListNewProductoPedidoProveedor);
                    if (oldPedidoProveedorOfProductoPedidoProveedorListNewProductoPedidoProveedor != null && !oldPedidoProveedorOfProductoPedidoProveedorListNewProductoPedidoProveedor.equals(pedidoProveedor)) {
                        oldPedidoProveedorOfProductoPedidoProveedorListNewProductoPedidoProveedor.getProductoPedidoProveedorList().remove(productoPedidoProveedorListNewProductoPedidoProveedor);
                        oldPedidoProveedorOfProductoPedidoProveedorListNewProductoPedidoProveedor = em.merge(oldPedidoProveedorOfProductoPedidoProveedorListNewProductoPedidoProveedor);
                    }
                }
            }
            for (DevolucionProveedor devolucionProveedorListNewDevolucionProveedor : devolucionProveedorListNew) {
                if (!devolucionProveedorListOld.contains(devolucionProveedorListNewDevolucionProveedor)) {
                    PedidoProveedor oldPedidoProveedorIdOfDevolucionProveedorListNewDevolucionProveedor = devolucionProveedorListNewDevolucionProveedor.getPedidoProveedorId();
                    devolucionProveedorListNewDevolucionProveedor.setPedidoProveedorId(pedidoProveedor);
                    devolucionProveedorListNewDevolucionProveedor = em.merge(devolucionProveedorListNewDevolucionProveedor);
                    if (oldPedidoProveedorIdOfDevolucionProveedorListNewDevolucionProveedor != null && !oldPedidoProveedorIdOfDevolucionProveedorListNewDevolucionProveedor.equals(pedidoProveedor)) {
                        oldPedidoProveedorIdOfDevolucionProveedorListNewDevolucionProveedor.getDevolucionProveedorList().remove(devolucionProveedorListNewDevolucionProveedor);
                        oldPedidoProveedorIdOfDevolucionProveedorListNewDevolucionProveedor = em.merge(oldPedidoProveedorIdOfDevolucionProveedorListNewDevolucionProveedor);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pedidoProveedor.getId();
                if (findPedidoProveedor(id) == null) {
                    throw new NonexistentEntityException("The pedidoProveedor with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PedidoProveedor pedidoProveedor;
            try {
                pedidoProveedor = em.getReference(PedidoProveedor.class, id);
                pedidoProveedor.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pedidoProveedor with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<ProductoPedidoProveedor> productoPedidoProveedorListOrphanCheck = pedidoProveedor.getProductoPedidoProveedorList();
            for (ProductoPedidoProveedor productoPedidoProveedorListOrphanCheckProductoPedidoProveedor : productoPedidoProveedorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PedidoProveedor (" + pedidoProveedor + ") cannot be destroyed since the ProductoPedidoProveedor " + productoPedidoProveedorListOrphanCheckProductoPedidoProveedor + " in its productoPedidoProveedorList field has a non-nullable pedidoProveedor field.");
            }
            List<DevolucionProveedor> devolucionProveedorListOrphanCheck = pedidoProveedor.getDevolucionProveedorList();
            for (DevolucionProveedor devolucionProveedorListOrphanCheckDevolucionProveedor : devolucionProveedorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PedidoProveedor (" + pedidoProveedor + ") cannot be destroyed since the DevolucionProveedor " + devolucionProveedorListOrphanCheckDevolucionProveedor + " in its devolucionProveedorList field has a non-nullable pedidoProveedorId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Proveedor proveedorCedula = pedidoProveedor.getProveedorCedula();
            if (proveedorCedula != null) {
                proveedorCedula.getPedidoProveedorList().remove(pedidoProveedor);
                proveedorCedula = em.merge(proveedorCedula);
            }
            em.remove(pedidoProveedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PedidoProveedor> findPedidoProveedorEntities() {
        return findPedidoProveedorEntities(true, -1, -1);
    }

    public List<PedidoProveedor> findPedidoProveedorEntities(int maxResults, int firstResult) {
        return findPedidoProveedorEntities(false, maxResults, firstResult);
    }

    private List<PedidoProveedor> findPedidoProveedorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PedidoProveedor.class));
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

    public PedidoProveedor findPedidoProveedor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PedidoProveedor.class, id);
        } finally {
            em.close();
        }
    }

    public int getPedidoProveedorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PedidoProveedor> rt = cq.from(PedidoProveedor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
