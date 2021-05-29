package TestsJunit;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import model.Document;

public class Testdecode {

	private static Document doc;
	
	
	
	//TestRot13
	@Test
	public final void testOpenDecodedWordRot13() throws IOException {
		String path="C:\\Users\\Hacknet\\eclipse-workspace\\myy803-main.zip_expanded\\myy803-main\\Project2021\\test_files\\testWordDecodeRot13.docx";
		String typecode ="Rot13";
		doc=new Document(null,path,typecode);
		doc.open(doc.getFiletype(),doc.getPath(),doc.getTypecode());
		ArrayList<String> lines =new ArrayList<String>();
		lines.add("Lampros vlahopoulos Texnologia Logismikou ");
		lines.add("2021 ");
		lines.add("TestRot13");

		assertEquals(doc.getContents(),lines);
		assertEquals(doc.getPath(),path);
		assertEquals(doc.getTypecode(),typecode);
		assertEquals(doc.getFiletype(),".docx");
		
		
	}
	
	@Test
	public final void testOpenDecodedWordRot13V_2() throws IOException {
		String path="C:\\Users\\Hacknet\\eclipse-workspace\\myy803-main.zip_expanded\\myy803-main\\Project2021\\test_files\\testWordDecodeRot13.docx";
		String typecode ="Atbash";
		doc=new Document(null,path,typecode);
		doc.open(doc.getFiletype(),doc.getPath(),doc.getTypecode());
		ArrayList<String> lines =new ArrayList<String>();
		lines.add("Lampros vlahopoulos Texnologia Logismikou ");
		lines.add("2021 ");
		lines.add("TestRot13");
	
		
		assertNotEquals("should not equals",doc.getContents(),lines);
		assertEquals(doc.getPath(),path);
		assertEquals(doc.getTypecode(),typecode);
		assertEquals(doc.getFiletype(),".docx");
		
		
	    typecode ="None";
		doc=new Document(null,path,typecode);
		doc.open(doc.getFiletype(),doc.getPath(),doc.getTypecode());
		lines =new ArrayList<String>();
		lines.add("Lampros vlahopoulos Texnologia Logismikou ");
		lines.add("2021 ");
		lines.add("TestRot13");
		assertNotEquals("Should be not equal",doc.getContents(),lines);
		assertEquals(doc.getPath(),path);
		assertEquals(doc.getTypecode(),typecode);
		assertEquals(doc.getFiletype(),".docx");
		
	}
	
	@Test
	public final void testOpenDecodedWordRot13Null() throws IOException {
		String path="C:\\Users\\Hacknet\\eclipse-workspace\\myy803-main.zip_expanded\\myy803-main\\Project2021\\test_files\\testWordDecodeRot13.docx";
		String typecode =null;
		doc=new Document(null,path,null);
		doc.open(doc.getFiletype(),doc.getPath(),doc.getTypecode());
		ArrayList<String> lines =new ArrayList<String>();
		
		assertEquals(doc.getContents(),lines);
		assertEquals(doc.getPath(),path);
		assertEquals(doc.getTypecode(),"");
		assertEquals(doc.getFiletype(),"");
		
		
	}
	

	@Test
	public final void testOpenDecodedExelRot13() throws IOException {
		String path="C:\\Users\\Hacknet\\eclipse-workspace\\myy803-main.zip_expanded\\myy803-main\\Project2021\\test_files\\testDecode.xlsx";
		String typecode ="Rot13";
		doc=new Document(null,path,typecode);
		doc.open(doc.getFiletype(),doc.getPath(),doc.getTypecode());
		ArrayList<String> lines =new ArrayList<String>();
		lines.add("lampros\t");
		lines.add("mhtsos\t");
		lines.add("giwrgos\t");
		lines.add("panwraia\t");
		lines.add("\n");
		lines.add("10\t");
		lines.add("20\t");
		lines.add("30\t");
		lines.add("40\t");
		lines.add("\n");
		
		assertEquals(doc.getContents(),lines);
		assertEquals(doc.getPath(),path);
		assertEquals(doc.getTypecode(),typecode);
		assertEquals(doc.getFiletype(),".xlsx");
		
		
	}
	
	@Test
	public final void testOpenDecodedExelRot13V_2() throws IOException {
		String path="C:\\Users\\Hacknet\\eclipse-workspace\\myy803-main.zip_expanded\\myy803-main\\Project2021\\test_files\\testDecode.xlsx";
		String typecode ="Atbash";
		doc=new Document(null,path,typecode);
		doc.open(doc.getFiletype(),doc.getPath(),doc.getTypecode());
		ArrayList<String> lines =new ArrayList<String>();
		lines.add("lampros\t");
		lines.add("mhtsos\t");
		lines.add("giwrgos\t");
		lines.add("panwraia\t");
		lines.add("\n");
		lines.add("10\t");
		lines.add("20\t");
		lines.add("30\t");
		lines.add("40\t");
		lines.add("\n");
		assertNotEquals("should not equals",doc.getContents(),lines);
		assertEquals(doc.getPath(),path);
		assertEquals(doc.getTypecode(),typecode);
		assertEquals(doc.getFiletype(),".xlsx");
		
		
	    typecode ="None";
		doc=new Document(null,path,typecode);
		doc.open(doc.getFiletype(),doc.getPath(),doc.getTypecode());
		lines =new ArrayList<String>();
		lines.add("lampros\t");
		lines.add("mhtsos\t");
		lines.add("giwrgos\t");
		lines.add("panwraia\t");
		lines.add("\n");
		lines.add("10\t");
		lines.add("20\t");
		lines.add("30\t");
		lines.add("40\t");
		lines.add("\n");
		assertNotEquals("Should be not equal",doc.getContents(),lines);
		assertEquals(doc.getPath(),path);
		assertEquals(doc.getTypecode(),typecode);
		assertEquals(doc.getFiletype(),".xlsx");
		
	}
	@Test
	public final void testOpenDecodedExelRot13Null() throws IOException {
		String path="C:\\Users\\Hacknet\\eclipse-workspace\\myy803-main.zip_expanded\\myy803-main\\Project2021\\test_files\\testDecode.xlsx";
		String typecode =null;
		doc=new Document(null,path,null);
		doc.open(doc.getFiletype(),doc.getPath(),doc.getTypecode());
		ArrayList<String> lines =new ArrayList<String>();
		
		assertEquals(doc.getContents(),lines);
		assertEquals(doc.getPath(),path);
		assertEquals(doc.getTypecode(),"");
		assertEquals(doc.getFiletype(),"");
		
		
	}
	
	////////////////////////////
	
	
	//TestAtbash
	
	@Test
	public final void testOpenDecodedWordAtBash() throws IOException {
		String path="C:\\Users\\Hacknet\\eclipse-workspace\\myy803-main.zip_expanded\\myy803-main\\Project2021\\test_files\\testWordDecodeAtbash.docx";
		String typecode ="Atbash";
		doc=new Document(null,path,typecode);
		doc.open(doc.getFiletype(),doc.getPath(),doc.getTypecode());
		ArrayList<String> lines =new ArrayList<String>();
		lines.add("Lampros vlahopoulos Texnologia Logismikou");
		lines.add("2021");
		lines.add("TestAtbash");
	
		System.out.println(doc.getContents());
		System.out.println(lines);
		assertEquals(doc.getContents(),lines);
		assertEquals(doc.getPath(),path);
		assertEquals(doc.getTypecode(),typecode);
		assertEquals(doc.getFiletype(),".docx");
		
		
	}
	
	@Test
	public final void testOpenDecodedWordAtbashV_2() throws IOException {
		String path="C:\\Users\\Hacknet\\eclipse-workspace\\myy803-main.zip_expanded\\myy803-main\\Project2021\\test_files\\testWordDecodeAtbash.docx";
		String typecode ="Rot13";
		doc=new Document(null,path,typecode);
		doc.open(doc.getFiletype(),doc.getPath(),doc.getTypecode());
		ArrayList<String> lines =new ArrayList<String>();
		lines.add("Lampros vlahopoulos Texnologia Logismikou");
		lines.add("2021");
		lines.add("TestAtbash");
	
		
		assertNotEquals("should not equals",doc.getContents(),lines);
		assertEquals(doc.getPath(),path);
		assertEquals(doc.getTypecode(),typecode);
		assertEquals(doc.getFiletype(),".docx");
		
		
	    typecode ="None";
		doc=new Document(null,path,typecode);
		doc.open(doc.getFiletype(),doc.getPath(),doc.getTypecode());
		lines =new ArrayList<String>();
		lines.add("Lampros vlahopoulos Texnologia Logismikou");
		lines.add("2021");
		lines.add("TestAtbash");
		assertNotEquals("Should be not equal",doc.getContents(),lines);
		assertEquals(doc.getPath(),path);
		assertEquals(doc.getTypecode(),typecode);
		assertEquals(doc.getFiletype(),".docx");
		
	}
	
	@Test
	public final void testOpenDecodedWordAtbashNull() throws IOException {
		String path="C:\\Users\\Hacknet\\eclipse-workspace\\myy803-main.zip_expanded\\myy803-main\\Project2021\\test_files\\testWordDecodeAtbash.docx";
		String typecode =null;
		doc=new Document(null,path,null);
		doc.open(doc.getFiletype(),doc.getPath(),doc.getTypecode());
		ArrayList<String> lines =new ArrayList<String>();
		
		assertEquals(doc.getContents(),lines);
		assertEquals(doc.getPath(),path);
		assertEquals(doc.getTypecode(),"");
		assertEquals(doc.getFiletype(),"");
		
		
	}
	
	@Test
	public final void testOpenDecodedExelAtBash() throws IOException {
		String path="C:\\Users\\Hacknet\\eclipse-workspace\\myy803-main.zip_expanded\\myy803-main\\Project2021\\test_files\\testDecodeAt.xlsx";
		String typecode ="Atbash";
		doc=new Document(null,path,typecode);
		doc.open(doc.getFiletype(),doc.getPath(),doc.getTypecode());
		ArrayList<String> lines =new ArrayList<String>();
		lines.add("lampros\t");
		lines.add("mhtsos\t");
		lines.add("giwrgos\t");
		lines.add("panwraia\t");
		lines.add("\n");
		lines.add("10\t");
		lines.add("20\t");
		lines.add("30\t");
		lines.add("40\t");
		lines.add("\n");
		
		assertEquals(doc.getContents(),lines);
		assertEquals(doc.getPath(),path);
		assertEquals(doc.getTypecode(),typecode);
		assertEquals(doc.getFiletype(),".xlsx");
		
		
	}
	@Test
	public final void testOpenDecodedExelAtbash_2() throws IOException {
		String path="C:\\Users\\Hacknet\\eclipse-workspace\\myy803-main.zip_expanded\\myy803-main\\Project2021\\test_files\\testDecodeAt.xlsx";
		String typecode ="Rot13";
		doc=new Document(null,path,typecode);
		doc.open(doc.getFiletype(),doc.getPath(),doc.getTypecode());
		ArrayList<String> lines =new ArrayList<String>();
		lines.add("lampros\t");
		lines.add("mhtsos\t");
		lines.add("giwrgos\t");
		lines.add("panwraia\t");
		lines.add("\n");
		lines.add("10\t");
		lines.add("20\t");
		lines.add("30\t");
		lines.add("40\t");
		lines.add("\n");
		assertNotEquals("should not equals",doc.getContents(),lines);
		assertEquals(doc.getPath(),path);
		assertEquals(doc.getTypecode(),typecode);
		assertEquals(doc.getFiletype(),".xlsx");
		
		
	    typecode ="None";
		doc=new Document(null,path,typecode);
		doc.open(doc.getFiletype(),doc.getPath(),doc.getTypecode());
		lines =new ArrayList<String>();
		lines.add("lampros\t");
		lines.add("mhtsos\t");
		lines.add("giwrgos\t");
		lines.add("panwraia\t");
		lines.add("\n");
		lines.add("10\t");
		lines.add("20\t");
		lines.add("30\t");
		lines.add("40\t");
		lines.add("\n");
		assertNotEquals("Should be not equal",doc.getContents(),lines);
		assertEquals(doc.getPath(),path);
		assertEquals(doc.getTypecode(),typecode);
		assertEquals(doc.getFiletype(),".xlsx");
		
	}
	@Test
	public final void testOpenDecodedExelAtbashNull() throws IOException {
		String path="C:\\Users\\Hacknet\\eclipse-workspace\\myy803-main.zip_expanded\\myy803-main\\Project2021\\test_files\\testDecodeAt.xlsx";
		String typecode =null;
		doc=new Document(null,path,null);
		doc.open(doc.getFiletype(),doc.getPath(),doc.getTypecode());
		ArrayList<String> lines =new ArrayList<String>();
		assertEquals(doc.getContents(),lines);
		assertEquals(doc.getPath(),path);
		assertEquals(doc.getTypecode(),"");
		assertEquals(doc.getFiletype(),"");
		
		
	}
	
}

