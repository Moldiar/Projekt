package mpr.proj;

import java.io.FileOutputStream;

import mpr.proj.pedigree.Horse;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;

import com.itextpdf.text.Element;
import com.itextpdf.text.Font;

import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public abstract class PDFExport {

	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
			Font.BOLD);

	public static void exportPDF(Horse horse, int depth) {
		try {
			System.out.println("Creating File...");
			Document document = new Document(PageSize.A4.rotate());
			PdfWriter.getInstance(document, new FileOutputStream(horse.getName()+ "_ancestors.pdf"));
			document.open();
			document.addTitle("Genealogy Tree");
			Anchor anchor = new Anchor("The Genealogy of the horse "+ horse.getName(), catFont);
			Chapter catPart = new Chapter(new Paragraph(anchor), 1);
			createTable(catPart, horse, depth);
			document.add(catPart);
			document.close();
			System.out.println("Exported to file: " + horse.getName()+ "_ancestors.pdf");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void downloadParents(Horse horse, int depth, PdfPTable table) {
		PdfPCell cell;
		String name;
		if (horse != null) {
			name = horse.getName();
		} else {
			name = "Unknown";
		}
		cell = new PdfPCell(new Phrase(name));
		cell.setRowspan((int) Math.pow(2, depth));
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.addCell(cell);

		if (horse.getSire() != null && depth > 0) {
			downloadParents(horse.getSire(), depth - 1, table);
		} else if (horse.getSire() == null && depth > 0) {
			downloadParents(new Horse(), depth - 1, table);
		}
		if (horse.getDam() != null && depth > 0) {
			downloadParents(horse.getDam(), depth - 1, table);
		} else if (horse.getDam() == null && depth > 0) {
			downloadParents(new Horse(), depth - 1, table);
		}
	}

	private static void createTable(Section subCatPart, Horse horse, int depth)
			throws BadElementException {
		PdfPTable table = new PdfPTable(depth + 1);
		downloadParents(horse, depth, table);
		subCatPart.add(table);
	}

}