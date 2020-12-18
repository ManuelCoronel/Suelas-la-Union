/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author MANUEL
 */
@Entity
@Table(name = "devolucion_cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DevolucionCliente.findAll", query = "SELECT d FROM DevolucionCliente d")
    , @NamedQuery(name = "DevolucionCliente.findById", query = "SELECT d FROM DevolucionCliente d WHERE d.id = :id")
    , @NamedQuery(name = "DevolucionCliente.findByDescripcion", query = "SELECT d FROM DevolucionCliente d WHERE d.descripcion = :descripcion")
    , @NamedQuery(name = "DevolucionCliente.findByFechaInicio", query = "SELECT d FROM DevolucionCliente d WHERE d.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "DevolucionCliente.findByFechaFin", query = "SELECT d FROM DevolucionCliente d WHERE d.fechaFin = :fechaFin")})
public class DevolucionCliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @JoinColumn(name = "pedido_de_venta_id_pedido_de_venta", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PedidoDeVenta pedidoDeVentaIdPedidoDeVenta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "devolucionCliente")
    private List<DevolucionClienteProducto> devolucionClienteProductoList;

    public DevolucionCliente() {
    }

    public DevolucionCliente(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public PedidoDeVenta getPedidoDeVentaIdPedidoDeVenta() {
        return pedidoDeVentaIdPedidoDeVenta;
    }

    public void setPedidoDeVentaIdPedidoDeVenta(PedidoDeVenta pedidoDeVentaIdPedidoDeVenta) {
        this.pedidoDeVentaIdPedidoDeVenta = pedidoDeVentaIdPedidoDeVenta;
    }

    @XmlTransient
    public List<DevolucionClienteProducto> getDevolucionClienteProductoList() {
        return devolucionClienteProductoList;
    }

    public void setDevolucionClienteProductoList(List<DevolucionClienteProducto> devolucionClienteProductoList) {
        this.devolucionClienteProductoList = devolucionClienteProductoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DevolucionCliente)) {
            return false;
        }
        DevolucionCliente other = (DevolucionCliente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.DevolucionCliente[ id=" + id + " ]";
    }
    
}
