package TestsJunit;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import model.Document;

public class TestOpen {
	
	
	private static Document doc;
	
	
	@Test
	public final void testOpenWord() throws IOException {
		String path="C:\\\\Users\\\\Hacknet\\\\eclipse-workspace\\\\myy803-main.zip_expanded\\\\myy803-main\\\\Project2021\\\\test_files\\\\bla.docx";
		String typecode ="None";
		doc=new Document(null,path,typecode);
		doc.open(doc.getFiletype(),doc.getPath(),doc.getTypecode());
		ArrayList<String> lines =new ArrayList<String>();
		lines.add("Lampros vlahopoulos panwraia tsami giwrgos krommudas");
		lines.add(" Texnologia logismikou");
		lines.add("2021" );
		lines.add("Final report");
		assertEquals(doc.getContents(),lines);
		assertEquals(doc.getPath(),path);
		assertEquals(doc.getTypecode(),typecode);
		assertEquals(doc.getFiletype(),".docx");
		
		
	}
	
	@Test
	public final void testOpenNULL() throws IOException {
		
		doc=new Document(null,null,null);
		doc.open(doc.getFiletype(),doc.getPath(),doc.getTypecode());
		ArrayList<String> l =new ArrayList<String>();
		assertEquals(doc.getContents(),l);
		assertEquals(doc.getPath(),"");
		assertEquals(doc.getTypecode(),"");
		assertEquals(doc.getFiletype(),"");
	}
	
	@Test
	public final void testOpenNULL2() throws IOException {
		String path="C:\\Users\\Hacknet\\eclipse-workspace\\myy803-main.zip_expanded\\myy803-main\\Project2021\\test_files\\bla.docx";
		
		doc=new Document(null,path,null);
		doc.open(doc.getFiletype(),doc.getPath(),doc.getTypecode());
		ArrayList<String> l =new ArrayList<String>();
		assertEquals(doc.getContents(),l);
		assertEquals(doc.getPath(),path);
		assertEquals(doc.getTypecode(),"");
		assertEquals(doc.getFiletype(),"");
	}
	@Test
	public final void testOpenNULL3() throws IOException {
		String path="C:\\Users\\Hacknet\\eclipse-workspace\\myy803-main.zip_expanded\\myy803-main\\Project2021\\test_files\\bla.docx";
		
		doc=new Document(null,path,"");
		doc.open(doc.getFiletype(),doc.getPath(),doc.getTypecode());
		ArrayList<String> l =new ArrayList<String>();
		assertEquals(doc.getContents(),l);
		assertEquals(doc.getPath(),path);
		assertEquals(doc.getTypecode(),"");
		assertEquals(doc.getFiletype(),"");
	}
	@Test
	public final void testOpenNULL4() throws IOException {
		
		doc=new Document(null,"",null);
		doc.open(doc.getFiletype(),doc.getPath(),doc.getTypecode());
		ArrayList<String> l =new ArrayList<String>();
		assertEquals(doc.getContents(),l);
		assertEquals(doc.getPath(),"");
		assertEquals(doc.getTypecode(),"");
		assertEquals(doc.getFiletype(),"");
	}

	@Test
	public final void testOpenBlank() throws IOException {
		
		doc=new Document("","","");
		doc.open(doc.getFiletype(),doc.getPath(),doc.getTypecode());
		ArrayList<String> l =new ArrayList<String>();
		assertEquals(doc.getContents(),l);
		assertEquals(doc.getPath(),"");
		assertEquals(doc.getTypecode(),"");
		assertEquals(doc.getFiletype(),"");
	}
	
	@Test
	public final void testOpenExel() throws IOException {
		String path="C:\\Users\\Hacknet\\eclipse-workspace\\myy803-main.zip_expanded\\myy803-main\\Project2021\\test_files\\test.xlsx";
		String typecode ="None";
		doc=new Document(null,path,typecode);
		doc.open(doc.getFiletype(),doc.getPath(),doc.getTypecode());
		ArrayList<String> lines =new ArrayList<String>();
		lines.add("lampros"+"\t");
		lines.add("kwstas"+"\t");
		lines.add("10"+"\t" );
		lines.add("\n");
		lines.add("mhstos"+"\t");
		lines.add("nikos"+"\t");
		lines.add("20"+"\t");
		lines.add("\n");
		assertEquals(doc.getContents(),lines);
		assertEquals(doc.getPath(),path);
		assertEquals(doc.getTypecode(),"None");
		assertEquals(doc.getFiletype(),".xlsx");
		
		
	}
	
	
	

}
