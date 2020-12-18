/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MANUEL
 */
@Entity
@Table(name = "producto_pedido_venta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductoPedidoVenta.findAll", query = "SELECT p FROM ProductoPedidoVenta p")
    , @NamedQuery(name = "ProductoPedidoVenta.findByCantidad", query = "SELECT p FROM ProductoPedidoVenta p WHERE p.cantidad = :cantidad")
    , @NamedQuery(name = "ProductoPedidoVenta.findByTotal", query = "SELECT p FROM ProductoPedidoVenta p WHERE p.total = :total")
    , @NamedQuery(name = "ProductoPedidoVenta.findByProductoId", query = "SELECT p FROM ProductoPedidoVenta p WHERE p.productoPedidoVentaPK.productoId = :productoId")
    , @NamedQuery(name = "ProductoPedidoVenta.findByPedidoDeVentaId", query = "SELECT p FROM ProductoPedidoVenta p WHERE p.productoPedidoVentaPK.pedidoDeVentaId = :pedidoDeVentaId")})
public class ProductoPedidoVenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProductoPedidoVentaPK productoPedidoVentaPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cantidad")
    private Double cantidad;
    @Column(name = "total")
    private Double total;
    @JoinColumn(name = "producto_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Producto producto;
    @JoinColumn(name = "pedido_de_venta_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PedidoDeVenta pedidoDeVenta;

    public ProductoPedidoVenta() {
    }

    public ProductoPedidoVenta(ProductoPedidoVentaPK productoPedidoVentaPK) {
        this.productoPedidoVentaPK = productoPedidoVentaPK;
    }

    public ProductoPedidoVenta(int productoId, int pedidoDeVentaId) {
        this.productoPedidoVentaPK = new ProductoPedidoVentaPK(productoId, pedidoDeVentaId);
    }

    public ProductoPedidoVentaPK getProductoPedidoVentaPK() {
        return productoPedidoVentaPK;
    }

    public void setProductoPedidoVentaPK(ProductoPedidoVentaPK productoPedidoVentaPK) {
        this.productoPedidoVentaPK = productoPedidoVentaPK;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public PedidoDeVenta getPedidoDeVenta() {
        return pedidoDeVenta;
    }

    public void setPedidoDeVenta(PedidoDeVenta pedidoDeVenta) {
        this.pedidoDeVenta = pedidoDeVenta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productoPedidoVentaPK != null ? productoPedidoVentaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductoPedidoVenta)) {
            return false;
        }
        ProductoPedidoVenta other = (ProductoPedidoVenta) object;
        if ((this.productoPedidoVentaPK == null && other.productoPedidoVentaPK != null) || (this.productoPedidoVentaPK != null && !this.productoPedidoVentaPK.equals(other.productoPedidoVentaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.ProductoPedidoVenta[ productoPedidoVentaPK=" + productoPedidoVentaPK + " ]";
    }
    
}
