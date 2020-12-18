/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NEGOCIO;

import DAO.PedidoDeVentaJpaController;
import DTO.ProductoPedidoVenta;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.Font;
import java.io.*;
import java.util.*;

public class GeneratePDF {

    private int id;

    public GeneratePDF() {
    }

    public GeneratePDF(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static void main(String[] args) throws FileNotFoundException, DocumentException, BadElementException, IOException {
        // LLAMADA AL METODO PARA CREAR EL PDF
        GeneratePDF pdf = new GeneratePDF();
        pdf.setId(2);
        pdf.crearPDF(pdf.getId());
    }

    public void crearPDF(int id) throws FileNotFoundException, DocumentException, BadElementException, IOException {
        // Se crea el documento
        PedidoDeVentaJpaController p = new PedidoDeVentaJpaController();

        List<ProductoPedidoVenta> s = (List<ProductoPedidoVenta>) (p.findPedidoDeVenta(id).getProductoPedidoVentaList());
        Document documento = new Document();
        Image logo = Image.getInstance("web/vistas/images/icon/logo.png");
        Image separador = Image.getInstance("web/vistas/images/icon/separador.png");
        // El OutPutStream para el fichero donde crearemos el PDF
        String nombreArchivo = "factura" + id + ".pdf";
        FileOutputStream ficheroPDF = new FileOutputStream(nombreArchivo);
        // Se asocia el documento de OutPutStream
        PdfWriter.getInstance(documento, ficheroPDF);
        // Se abre el documento
        documento.open();
        // Parrafo
        Paragraph titulo = new Paragraph("Factura #" + id + "\n",
                FontFactory.getFont("arial",
                        22,
                        Font.BOLD
                )
        );
        titulo.setAlignment(1);
        Paragraph blank = new Paragraph("\n");

        // Añadimos el titulo al documento
        documento.add(titulo);
        //modificando imagen
        logo.scaleAbsoluteHeight(100);
        logo.scaleAbsoluteWidth(100);
        logo.setAlignment(1);
        //agregando imagen
        documento.add(logo);
        separador.scaleAbsoluteWidth(550);
        separador.setAlignment(1);
        documento.add(separador);
        String datosEmpresa
                = "DATOS DE LA EMPRESA\n"
                + "Cl. 10 #9-90 "
                + "Cúcuta, Norte de Santander, Colombia\n"
                + "+57 311 4586406\n"
                + "supersuelaslaunion@gmail.com\n\n"
                + "DATOS DEL CLIENTE\n"
                + (p.findPedidoDeVenta(id).getClienteCedula().getNombre() + " " + " - CC " + p.findPedidoDeVenta(id).getClienteCedula().getCedula()) + "\n\n";
        Paragraph empresa = new Paragraph(datosEmpresa);
        documento.add(empresa);
        // Creamos una tabla
        // producto, cantidad, precioUnitario, precio, total, codigoPedido
        PdfPTable tabla = new PdfPTable(4);
        tabla.addCell(new Paragraph("PRODUCTO", FontFactory.getFont("arial",
                13,
                Font.BOLD
        )));
        tabla.addCell(new Paragraph("CANTIDAD", FontFactory.getFont("arial",
                13,
                Font.BOLD
        )));
        tabla.addCell(new Paragraph("PRECIO C/U", FontFactory.getFont("arial",
                13,
                Font.BOLD
        )));
        tabla.addCell(new Paragraph("VALOR", FontFactory.getFont("arial",
                13,
                Font.BOLD
        )));
        double total = 0;
        for (ProductoPedidoVenta pro : s) {
            tabla.addCell("" + nombreProducto(pro, pro.getProducto().getTipoProducto()));
            tabla.addCell("" + pro.getCantidad());
            tabla.addCell("" + pro.getProducto().getPrecio());
            tabla.addCell("" + (pro.getCantidad() * pro.getProducto().getPrecio()));
            total += pro.getCantidad() * pro.getProducto().getPrecio();
        }

        // Añadimos la tabla al documento
        tabla.setHorizontalAlignment(1);

        documento.add(tabla);
        documento.add(blank);
        Paragraph valorTotal = new Paragraph("VALOR TOTAL DE LA FACTURA: \n$ " + total + " COP\n\n",
                FontFactory.getFont("arial",
                        16,
                        Font.BOLD
                )
        );
        valorTotal.setAlignment(2);
        documento.add(valorTotal);
        String atendido = "Fue atentido por: " + (p.findPedidoDeVenta(id).getPersonalId().getNombre()) + " " + (p.findPedidoDeVenta(id).getPersonalId().getApellido() + "\nTipo de Entrega: " + (p.findPedidoDeVenta(id).getTipoEntregaId().getDescripcion()));
        java.util.Date fecha = new Date();
        Paragraph atendidoDoc = new Paragraph(atendido + "\n\n");
        Paragraph hora = new Paragraph(fecha + "");
        hora.setAlignment(1);
        documento.add(atendidoDoc);
        documento.add(hora);
        // Se cierra el documento
        documento.close();
    }

    public String nombreProducto(ProductoPedidoVenta pro, String tipo) {
        String nombreProducto = "";
        if (tipo.equals("1")) {
            nombreProducto = "Plantilla" + " " + pro.getProducto().getPlantilla().getModelo();
        }
        if (tipo.equals("2")) {
            nombreProducto = "Suela PVC" + " " + pro.getProducto().getSuela().getModelo();
        }
        if (tipo.equals("3")) {
            nombreProducto = "Suela Expanso" + " " + pro.getProducto().getSuela().getModelo();
        }
        if (tipo.equals("4")) {
            nombreProducto = "Tira" + " " + pro.getProducto().getTira().getModelo();
        }
        if (tipo.equals("5")) {
            nombreProducto = "Salpa";
        }
        return nombreProducto;
    }
}
