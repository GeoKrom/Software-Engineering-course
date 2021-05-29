package TestsJunit;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.*;
import model.Document;

public class TestEdit {

	private Document doc;
	
	
	
	@Test
	public final void testWordEdit() throws IOException {
		
		String path="C:\\Users\\Hacknet\\eclipse-workspace\\myy803-main.zip_expanded\\myy803-main\\Project2021\\test_files\\bla.docx";
		String Typecode ="None";
		doc=new Document(null,path,Typecode);
		ArrayList<String> lines =new ArrayList<String>();
		lines.add("Lampros vlahopoulos panwraia tsami giwrgos krommudas");
		lines.add(" Texnologia logismikou");
		lines.add("2021" );
		lines.add("Final report");
		
		doc.open(doc.getFiletype(), doc.getPath(),doc.getTypecode());
		assertEquals(doc.getContents(),lines);
		ArrayList<String> list_input=new ArrayList<String>();
		
		String input="lampros vlahopoulos antoula  2021";
		list_input.add(input);
		
		doc.edit(input);
		assertEquals(doc.getContents(),list_input);
		
		
	}

	@Test
	public final void testEdit2() throws IOException {
		
		String path="C:\\Users\\Hacknet\\eclipse-workspace\\myy803-main.zip_expanded\\myy803-main\\Project2021\\test_files\\bla.docx";
		String Typecode ="None";
		doc=new Document(null,path,Typecode);
		ArrayList<String> lines =new ArrayList<String>();
		lines.add("Lampros vlahopoulos panwraia tsami giwrgos krommudas");
		lines.add(" Texnologia logismikou");
		lines.add("2021" );
		lines.add("Final report");
		
		doc.open(doc.getFiletype(), doc.getPath(),doc.getTypecode());
		assertEquals(doc.getContents(),lines);
		ArrayList<String> list_input=new ArrayList<String>();
		
		String input=null;
		list_input.add(input);
		
		doc.edit(input);
		assertEquals(doc.getContents(),lines);
		
		
	}
	
	
	// ADD FOR ECEL   TESTS !!!!!!!!!
	

	@Test
	public final void testExelEdit() throws IOException {
		
		String path="C:\\Users\\Hacknet\\eclipse-workspace\\myy803-main.zip_expanded\\myy803-main\\Project2021\\test_files\\test.xlsx";
		String Typecode ="None";
		doc=new Document(null,path,Typecode);
		ArrayList<String> lines =new ArrayList<String>();
		lines.add("lampros"+"\t");
		lines.add("kwstas"+"\t");
		lines.add("10"+"\t" );
		lines.add("\n");
		lines.add("mhstos"+"\t");
		lines.add("nikos"+"\t");
		lines.add("20"+"\t");
		lines.add("\n");
		
		doc.open(doc.getFiletype(), doc.getPath(),doc.getTypecode());
		
		assertEquals(doc.getContents(),lines);
		
		ArrayList<String> list_input=new ArrayList<String>();
		
		String input="lampros\tkwstas\t"+"\n"+"nikos\tgiwrgos\t"+"\n";
		
		list_input.add("lampros\t");
		list_input.add("kwstas\t");
		list_input.add("\n");
		list_input.add("nikos\t");
		list_input.add("giwrgos\t\n");
		String a="";
		for(String e:list_input) {
			a+=e;
		}
		list_input.clear();
		
		list_input.add(a);
		
		doc.edit(input);
		
		assertEquals(doc.getContents(),list_input);
		
		
	}

	@Test
	public final void testExelEditNull() throws IOException {
		
		String path="C:\\Users\\Hacknet\\eclipse-workspace\\myy803-main.zip_expanded\\myy803-main\\Project2021\\test_files\\test.xlsx";
		String Typecode ="None";
		doc=new Document(null,path,Typecode);
		ArrayList<String> lines =new ArrayList<String>();
		lines.add("lampros"+"\t");
		lines.add("kwstas"+"\t");
		lines.add("10"+"\t" );
		lines.add("\n");
		lines.add("mhstos"+"\t");
		lines.add("nikos"+"\t");
		lines.add("20"+"\t");
		lines.add("\n");
		
		doc.open(doc.getFiletype(), doc.getPath(),doc.getTypecode());
		
		assertEquals(doc.getContents(),lines);
		
		ArrayList<String> list_input=new ArrayList<String>();
		
		
		
		list_input.add("");
		
		doc.edit(null);
		
		assertEquals(doc.getContents(),lines);
		
		
	}
}
