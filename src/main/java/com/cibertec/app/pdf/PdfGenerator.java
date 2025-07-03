package com.cibertec.app.pdf;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import com.cibertec.app.entity.Venta;
import java.util.List;


import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;


public class PdfGenerator {

	public void generate(List<Venta> ventaLista, HttpServletResponse response) throws IOException, DocumentException {
		
		Document documento = new Document(PageSize.A4);
		
		PdfWriter.getInstance(documento, response.getOutputStream());
		
		documento.open();
		
		Font fontTitulo = FontFactory.getFont(FontFactory.HELVETICA);
		
		fontTitulo.setSize(20);
		
		Paragraph titulo = new Paragraph("Listado de Ventas", fontTitulo);
		
		titulo.setAlignment(Paragraph.ALIGN_CENTER);
		
		documento.add(titulo);
		
		PdfPTable pdfTabla = new PdfPTable(7);
		
		pdfTabla.setWidthPercentage(100f);
		pdfTabla.setWidths(new int[] {2, 4, 5, 5, 5, 3, 4});
		pdfTabla.setSpacingBefore(3);
		
		PdfPCell celda = new PdfPCell();
		
		celda.setBackgroundColor(new CMYKColor(0, 0, 0, 255));
		celda.setPadding(5);
		
		Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
		fuente.setColor(CMYKColor.WHITE);
		
		celda.setPhrase(new Phrase("ID", fuente));
		pdfTabla.addCell(celda);
		celda.setPhrase(new Phrase("Fecha", fuente));
		pdfTabla.addCell(celda);
		celda.setPhrase(new Phrase("Cliente", fuente));
		pdfTabla.addCell(celda);
		celda.setPhrase(new Phrase("Producto", fuente));
		pdfTabla.addCell(celda);
		celda.setPhrase(new Phrase("Proveedor", fuente));
		pdfTabla.addCell(celda);
		celda.setPhrase(new Phrase("Cantidad", fuente));
		pdfTabla.addCell(celda);
		celda.setPhrase(new Phrase("Total", fuente));
		pdfTabla.addCell(celda);
		
		for (Venta venta : ventaLista) {
			pdfTabla.addCell(String.valueOf(venta.getIdventa()));
			pdfTabla.addCell(venta.getFecha().toString());
			pdfTabla.addCell(venta.getCliente().getNombre());
			pdfTabla.addCell(venta.getProducto().getNombre());
			pdfTabla.addCell(venta.getProveedor().getNombre());
			pdfTabla.addCell(String.valueOf(venta.getCantidad()));
			pdfTabla.addCell(String.valueOf(venta.getTotal()));
		}
		
		documento.add(pdfTabla);
		
		documento.close();
		
		
		

	}
}
