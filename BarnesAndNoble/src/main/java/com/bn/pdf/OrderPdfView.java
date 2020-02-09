package com.bn.pdf;

import java.awt.Color;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.bn.dao.OrderDetailsDAO;
import com.bn.pojo.Customer;
import com.bn.pojo.CustomerOrderDetails;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class OrderPdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Font font_helvetica_14_bold_blue = new Font(Font.TIMES_ROMAN, 14, Font.BOLD, Color.BLUE);
		Font font_courier_16_italic_red = new Font(Font.TIMES_ROMAN, 16, Font.ITALIC, Color.RED);
		Font font_courier_16_bold_black = new Font(Font.TIMES_ROMAN, 16, Font.BOLD, Color.BLACK);

		HttpSession session = request.getSession();
		long userId = (Long) session.getAttribute("userId");
		String username = (String) session.getAttribute("username");
		Customer customer = (Customer) session.getAttribute("customer");

		OrderDetailsDAO orDet = new OrderDetailsDAO();
		List odList = orDet.listDetails(userId);

		Paragraph p1 = new Paragraph("Barnes & Noble - Your Order History", font_courier_16_bold_black);
		document.add(p1);

		Paragraph p2 = new Paragraph("Customer Id: " + String.valueOf(userId), font_courier_16_italic_red);
		document.add(p2);

		Paragraph p3 = new Paragraph("Customer Name: " + customer.getFirstName() + " " + customer.getLastName(), font_courier_16_italic_red);
		document.add(p3);

		Iterator orderIterator = odList.iterator();
		while (orderIterator.hasNext()) {
			CustomerOrderDetails order = (CustomerOrderDetails) orderIterator.next();

			Paragraph para = new Paragraph("Order Date: " + order.getOrderId().getOrderDate(), font_helvetica_14_bold_blue);

			PdfPTable tablehead = new PdfPTable(4); // 6 columns.

			PdfPCell cell1 = new PdfPCell(new Paragraph("Order Id"));
			PdfPCell cell3 = new PdfPCell(new Paragraph("Book Title"));
			PdfPCell cell4 = new PdfPCell(new Paragraph("Author"));
			PdfPCell cell5 = new PdfPCell(new Paragraph("Price"));

			tablehead.addCell(cell1);
			tablehead.addCell(cell3);
			tablehead.addCell(cell4);
			tablehead.addCell(cell5);

			PdfPTable tablebody = new PdfPTable(4); 

			PdfPCell cell6 = new PdfPCell(new Paragraph(String.valueOf(order.getOrderId().getOrderId())));
			PdfPCell cell8 = new PdfPCell(new Paragraph(order.getBookName()));
			PdfPCell cell9 = new PdfPCell(new Paragraph(order.getBookAuthor()));
			PdfPCell cell10 = new PdfPCell(new Paragraph(String.valueOf("$"+order.getPrice())));

			tablebody.addCell(cell6);
			tablebody.addCell(cell8);
			tablebody.addCell(cell9);
			tablebody.addCell(cell10);

			tablehead.setSpacingBefore(10f);
			tablebody.setSpacingAfter(20f);

			document.add(para);
			document.add(tablehead);
			document.add(tablebody);

		}

	}

}
