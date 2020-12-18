/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author MANUEL
 */
@Embeddable
public class ProductoPedidoVentaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "producto_id")
    private int productoId;
    @Basic(optional = false)
    @Column(name = "pedido_de_venta_id")
    private int pedidoDeVentaId;

    public ProductoPedidoVentaPK() {
    }

    public ProductoPedidoVentaPK(int productoId, int pedidoDeVentaId) {
        this.productoId = productoId;
        this.pedidoDeVentaId = pedidoDeVentaId;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public int getPedidoDeVentaId() {
        return pedidoDeVentaId;
    }

    public void setPedidoDeVentaId(int pedidoDeVentaId) {
        this.pedidoDeVentaId = pedidoDeVentaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) productoId;
        hash += (int) pedidoDeVentaId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductoPedidoVentaPK)) {
            return false;
        }
        ProductoPedidoVentaPK other = (ProductoPedidoVentaPK) object;
        if (this.productoId != other.productoId) {
            return false;
        }
        if (this.pedidoDeVentaId != other.pedidoDeVentaId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.ProductoPedidoVentaPK[ productoId=" + productoId + ", pedidoDeVentaId=" + pedidoDeVentaId + " ]";
    }
    
}
