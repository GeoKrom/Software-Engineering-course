package TestsJunit;
import static org.junit.Assert.*;

import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.junit.Test;

import commands.CommandsFactory;
import model.Document;
import model.FakeTTSFacade;

public class ‘otalœperation‘est {

	
	
	private static Document doc;
	
	
	@Test
	public final void test1() throws IOException {
		//openWord
		String path="C:\\\\Users\\\\Hacknet\\\\eclipse-workspace\\\\myy803-main.zip_expanded\\\\myy803-main\\\\Project2021\\\\test_files\\\\bla.docx";
		doc=new Document(null,path,"None");
		CommandsFactory comf =new CommandsFactory(doc);
		ActionListener op= comf.createCommand("openDocument");
		op.actionPerformed(null);
		ArrayList<String> lines =new ArrayList<String>();
		lines.add("Lampros vlahopoulos panwraia tsami giwrgos krommudas");
		lines.add(" Texnologia logismikou");
		lines.add("2021" );
		lines.add("Final report");
		assertEquals(doc.getContents(),lines);
		assertEquals(doc.getPath(),path);
		assertEquals(doc.getTypecode(),"None");
		assertEquals(doc.getFiletype(),".docx");
		
		
		//openExel
		path="C:\\Users\\Hacknet\\eclipse-workspace\\myy803-main.zip_expanded\\myy803-main\\Project2021\\test_files\\test.xlsx";
		String typecode ="None";
		doc=new Document(null,path,typecode);
		comf =new CommandsFactory(doc);
		op= comf.createCommand("openDocument");
		op.actionPerformed(null);
		
		lines =new ArrayList<String>();
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
		
		
		//word open with Rot13 decode
		path="C:\\Users\\Hacknet\\eclipse-workspace\\myy803-main.zip_expanded\\myy803-main\\Project2021\\test_files\\testWordDecodeRot13.docx";
		typecode ="Rot13";
		doc=new Document(null,path,typecode);
		comf =new CommandsFactory(doc);
		op= comf.createCommand("openDocument");
		op.actionPerformed(null);
		lines =new ArrayList<String>();
		lines.add("Lampros vlahopoulos Texnologia Logismikou ");
		lines.add("2021 ");
		lines.add("TestRot13");
	
		assertEquals(doc.getContents(),lines);
		assertEquals(doc.getPath(),path);
		assertEquals(doc.getTypecode(),typecode);
		assertEquals(doc.getFiletype(),".docx");
		
		
		//save word
		doc=new Document(null,null,null);
		ArrayList<String> list=new ArrayList<String>();
		list.add("lampros vlahopoulos"+"\n"+"kwstas vlahopoulos mpampas 4hnei arnia"+"\n"+"END OF PROGRAM");
		path="C:\\Users\\Hacknet\\eclipse-workspace\\myy803-main.zip_expanded\\myy803-main\\Project2021\\test_files\\lam.docx";
		typecode="None";
		doc.setContents(list);
		doc.setPath_write(path);
		doc.setTypecode_write(typecode);
		comf =new CommandsFactory(doc);
		ActionListener sv= comf.createCommand("saveDocument");
		sv.actionPerformed(null);
		assertEquals(isEqWord(path,"C:\\Users\\Hacknet\\eclipse-workspace\\myy803-main.zip_expanded\\myy803-main\\Project2021\\test_files\\testEqu.docx"),true);
		assertEquals(doc.getContents(),list);
		assertEquals(doc.getPath_write(),path);
		assertEquals(doc.getTypecode_write(),typecode);
		assertEquals(doc.getFiletype_write(),".docx");
		
		
		
		
		//open_edit_exel
		path="C:\\Users\\Hacknet\\eclipse-workspace\\myy803-main.zip_expanded\\myy803-main\\Project2021\\test_files\\test.xlsx";
		String Typecode ="None";
		doc=new Document(null,path,Typecode);
	
		lines =new ArrayList<String>();
		lines.add("lampros"+"\t");
		lines.add("kwstas"+"\t");
		lines.add("10"+"\t" );
		lines.add("\n");
		lines.add("mhstos"+"\t");
		lines.add("nikos"+"\t");
		lines.add("20"+"\t");
		lines.add("\n");
	
		comf =new CommandsFactory(doc);
		op= comf.createCommand("openDocument");
		op.actionPerformed(null);
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
		
		comf =new CommandsFactory(doc,input);
		ActionListener ed= comf.createCommand("editDocument");
		ed.actionPerformed(null);
		
		assertEquals(doc.getContents(),list_input);
		
		
	
		
	}
	
	
	
	@Test
	public  void testAll() throws IOException {
		
		
		String path="C:\\\\Users\\\\Hacknet\\\\eclipse-workspace\\\\myy803-main.zip_expanded\\\\myy803-main\\\\Project2021\\\\test_files\\\\bla.docx";
		doc=new Document(null,path,"None");
		CommandsFactory comf =new CommandsFactory(doc);
		ActionListener op= comf.createCommand("openDocument");
		op.actionPerformed(null);
		ArrayList<String> lines =new ArrayList<String>();
		lines.add("Lampros vlahopoulos panwraia tsami giwrgos krommudas");
		lines.add(" Texnologia logismikou");
		lines.add("2021" );
		lines.add("Final report");
		assertEquals(doc.getContents(),lines);
		assertEquals(doc.getPath(),path);
		assertEquals(doc.getTypecode(),"None");
		assertEquals(doc.getFiletype(),".docx");
		
		FakeTTSFacade fake=new FakeTTSFacade();
		doc.setAudioManager(fake);
		comf =new CommandsFactory(doc);
		ActionListener playall= comf.createCommand("DocumentToSpeech");
		playall.actionPerformed(null);
		assertEquals(((FakeTTSFacade)doc.getAudiomanager()).getContentToPlay(),doc.getContents());
	}
	
	
	
	
	
	
	public static boolean isEqWord(String a,String b) throws IOException 
	 {
			try {
				FileInputStream file =new FileInputStream(a);
				FileInputStream file2 =new FileInputStream(b);
				
				XWPFDocument xwpfDocument = new XWPFDocument(file);
				XWPFDocument xwpfDocument2 = new XWPFDocument(file2);
				List<XWPFParagraph> paragraphList =xwpfDocument.getParagraphs();
				List<XWPFParagraph> paragraphLis2t =xwpfDocument2.getParagraphs();
				
			if(paragraphList.size()!=paragraphLis2t.size()) {
				return false;
			}
				
				for(int i=0; i<paragraphList.size(); i++) {
					if(!paragraphList.get(i).equals(paragraphList.get(i))) {
						return false;
					}else {
						continue;
					}
			
				}
			}	catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
			
			
			return true;
	        }
	         
	    

}

