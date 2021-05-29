package TestsJunit;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.awt.FontFormatException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.junit.Test;

import model.Document;

public class TestEncode {
	
	private Document doc;
	
	

	//TestRot13
	@Test
	public final void testOpenEncodedWordRot13() throws IOException {
		String path="C:\\Users\\Hacknet\\eclipse-workspace\\myy803-main.zip_expanded\\myy803-main\\Project2021\\test_files\\bla.docx";
		String path_write="C:\\Users\\Hacknet\\eclipse-workspace\\myy803-main.zip_expanded\\myy803-main\\Project2021\\test_files\\encodedRot13.docx";
		String typecode ="None";
		try {
			doc=new Document(null,path,typecode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			doc.open(doc.getFiletype(),doc.getPath(),doc.getTypecode());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			
			doc.save(null,path_write,"Rot13");
		} catch (IOException | FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		assertEquals(isEqWord(path_write,"C:\\Users\\Hacknet\\eclipse-workspace\\myy803-main.zip_expanded\\myy803-main\\Project2021\\test_files\\originalRot13.docx"),true);
		assertEquals(doc.getPath_write(),path_write);
		assertEquals(doc.getTypecode_write(),"Rot13");
		assertEquals(doc.getFiletype_write(),".docx");
		
		
		
	}
	
	@Test
	public final void testOpenEncodedWordRot13Null() {
		String path="C:\\Users\\Hacknet\\eclipse-workspace\\myy803-main.zip_expanded\\myy803-main\\Project2021\\test_files\\bla.docx";
		String path_write="C:\\\\Users\\\\Hacknet\\\\eclipse-workspace\\\\myy803-main.zip_expanded\\\\myy803-main\\\\Project2021\\\\test_files\\\\encodedRot13.docx";
		String typecode ="None";
		try {
			doc=new Document(null,path,typecode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			doc.open(doc.getFiletype(),doc.getPath(),doc.getTypecode());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<String> lines2 =new ArrayList<String>();
		lines2.add("Lampros vlahopoulos panwraia tsami giwrgos krommudas");
		lines2.add(" Texnologia logismikou");
		lines2.add("2021");
		lines2.add("Final report");
		try {
			doc.save(null,null,"Rot13");
		} catch (IOException | FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(doc.getContents(),lines2);
		assertEquals(doc.getPath_write(),"");
		assertEquals(doc.getTypecode_write(),"");
		assertEquals(doc.getFiletype_write(),"");
		
		
		
	}
	
	

	
	@Test
	public final void testOpenEncodedExelRot13() throws IOException {
		String path="C:\\Users\\Hacknet\\eclipse-workspace\\myy803-main.zip_expanded\\myy803-main\\Project2021\\test_files\\test.xlsx";
		String path_write="C:\\Users\\Hacknet\\eclipse-workspace\\myy803-main.zip_expanded\\myy803-main\\Project2021\\test_files\\encodedRot13Exel.xlsx";
		String typecode ="None";
		try {
			doc=new Document(null,path,typecode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			doc.open(doc.getFiletype(),doc.getPath(),doc.getTypecode());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			doc.save(doc.getFiletype(),path_write,"Rot13");
		} catch (IOException | FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		assertEquals(isEqExel(path_write,"C:\\Users\\Hacknet\\eclipse-workspace\\myy803-main.zip_expanded\\myy803-main\\Project2021\\test_files\\originalEncodedRot13.xlsx"),true);
		assertEquals(doc.getPath_write(),path_write);
		assertEquals(doc.getTypecode_write(),"Rot13");
		assertEquals(doc.getFiletype_write(),".xlsx");
		
		
		
	}
	
	@Test
	public final void testOpenEncodedExelRot13Null() throws IOException {
		String path="C:\\Users\\Hacknet\\eclipse-workspace\\myy803-main.zip_expanded\\myy803-main\\Project2021\\test_files\\test.xlsx";
		
		String typecode ="None";
		try {
			doc=new Document(null,path,typecode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			doc.open(doc.getFiletype(),doc.getPath(),doc.getTypecode());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			doc.save(null,null,null);
		} catch (IOException | FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<String> lines=new ArrayList<String>();
		lines.add("lampros\t");
		lines.add("kwstas\t");
		lines.add("10\t");
		
		lines.add("\n");
		lines.add("mhstos\t");
		lines.add("nikos\t");
		lines.add("20\t");
		lines.add("\n");
		
		assertEquals(doc.getContents(),lines);
		assertEquals(doc.getPath_write(),"");
		assertEquals(doc.getTypecode_write(),"");
		assertEquals(doc.getFiletype_write(),"");
		
		
		
	}
	
	
	//Atbash
	
		@Test
		public final void testOpenEncodedWordAtbash() throws IOException {
			String path="C:\\Users\\Hacknet\\eclipse-workspace\\myy803-main.zip_expanded\\myy803-main\\Project2021\\test_files\\bla.docx";
			String path_write="C:\\Users\\Hacknet\\eclipse-workspace\\myy803-main.zip_expanded\\myy803-main\\Project2021\\test_files\\encodedAtbash.docx";
			String typecode ="None";
			try {
				doc=new Document(null,path,typecode);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				doc.open(doc.getFiletype(),doc.getPath(),doc.getTypecode());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				
				doc.save(null,path_write,"Atbash");
			} catch (IOException | FontFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
			assertEquals(isEqWord(path_write,"C:\\Users\\Hacknet\\eclipse-workspace\\myy803-main.zip_expanded\\myy803-main\\Project2021\\test_files\\originalAtbash.docx"),true);
			assertEquals(doc.getPath_write(),path_write);
			assertEquals(doc.getTypecode_write(),"Atbash");
			assertEquals(doc.getFiletype_write(),".docx");
			
			
			
		}
	
		@Test
		public final void testOpenEncodedWordAtbashNull() throws IOException {
			String path="C:\\Users\\Hacknet\\eclipse-workspace\\myy803-main.zip_expanded\\myy803-main\\Project2021\\test_files\\bla.docx";
		
			String typecode ="None";
			try {
				doc=new Document(null,path,typecode);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				doc.open(doc.getFiletype(),doc.getPath(),doc.getTypecode());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				
				doc.save(null,null,null);
			} catch (IOException | FontFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ArrayList<String> lines2 =new ArrayList<String>();
			lines2.add("Lampros vlahopoulos panwraia tsami giwrgos krommudas");
			lines2.add(" Texnologia logismikou");
			lines2.add("2021");
			lines2.add("Final report");
		
			assertEquals(doc.getContents(),lines2);
			assertEquals(doc.getPath_write(),"");
			assertEquals(doc.getTypecode_write(),"");
			assertEquals(doc.getFiletype_write(),"");
			
			
			
		}
	
		@Test
		public final void testOpenEncodedExelAtbash() throws IOException {
			String path="C:\\Users\\Hacknet\\eclipse-workspace\\myy803-main.zip_expanded\\myy803-main\\Project2021\\test_files\\test.xlsx";
			String path_write="C:\\Users\\Hacknet\\eclipse-workspace\\myy803-main.zip_expanded\\myy803-main\\Project2021\\test_files\\encodedAtbashXl.xlsx";
			String typecode ="None";
			try {
				doc=new Document(null,path,typecode);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				doc.open(doc.getFiletype(),doc.getPath(),doc.getTypecode());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				
				doc.save(null,path_write,"Atbash");
			} catch (IOException | FontFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
			assertEquals(isEqExel(path_write,"C:\\Users\\Hacknet\\eclipse-workspace\\myy803-main.zip_expanded\\myy803-main\\Project2021\\test_files\\originalAtbashXl.xlsx"),true);
			assertEquals(doc.getPath_write(),path_write);
			assertEquals(doc.getTypecode_write(),"Atbash");
			assertEquals(doc.getFiletype_write(),".xlsx");
			
			
			
		}
	
		@Test
		public final void testOpenEncodedExelAtbashNull() throws IOException {
			String path="C:\\Users\\Hacknet\\eclipse-workspace\\myy803-main.zip_expanded\\myy803-main\\Project2021\\test_files\\test.xlsx";
		
			String typecode ="None";
			try {
				doc=new Document(null,path,typecode);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				doc.open(doc.getFiletype(),doc.getPath(),doc.getTypecode());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				
				doc.save(null,null,null);
			} catch (IOException | FontFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ArrayList<String> list =new ArrayList<String>();
			list.add("lampros\t");
			list.add("kwstas\t");
			list.add("10\t");
			list.add("\n");
			list.add("mhstos\t");
			list.add("nikos\t");
			list.add("20\t");
			list.add("\n");
			
			assertEquals(doc.getContents(),list);
			assertEquals(doc.getPath_write(),"");
			assertEquals(doc.getTypecode_write(),"");
			assertEquals(doc.getFiletype_write(),"");
			
			
			
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
	         
	public static boolean isEqExel(String a, String b) throws IOException {
		
        Workbook workbook = WorkbookFactory.create(new File(a));
        Workbook workbook2 = WorkbookFactory.create(new File(b));
    
      
        Sheet sheet = workbook.getSheetAt(0);
        Sheet sheet2 = workbook2.getSheetAt(0);
    
        DataFormatter dataFormatter = new DataFormatter();
       
     
       
        Iterator<Row> rowIterator = sheet.rowIterator();
        Iterator<Row> rowIterator2 = sheet2.rowIterator();
       
        while (rowIterator.hasNext() && rowIterator2.hasNext()) {
            Row row = rowIterator.next();
            Row row2=rowIterator2.next();
         
      
            Iterator<Cell> cellIterator = row.cellIterator();
            Iterator<Cell> cellIterator2 = row2.cellIterator();
            
            while (cellIterator.hasNext() && cellIterator2.hasNext()) {
                Cell cell = cellIterator.next();
                Cell cell2 =cellIterator2.next();
               
                String cellValue = dataFormatter.formatCellValue(cell);
                String cellValue2 = dataFormatter.formatCellValue(cell2);
              
              
                if(!cellValue.equals(cellValue2)) {
                	
                	workbook.close();
                    workbook2.close();
                	return false;
                
                }
           
                
   
     
	}
            if(cellIterator.hasNext() || cellIterator2.hasNext()) {
             	 workbook.close();
             	 workbook2.close();
             	return false;
             } 
		
	} 
     if(rowIterator.hasNext() || rowIterator2.hasNext()) {
   	 workbook.close();
   	 workbook2.close();
   	return false;
   }
   workbook.close();
   workbook2.close();
  
		return true;
	}

	
}
