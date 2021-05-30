package TestsJunit;

import static org.junit.Assert.assertEquals;

import java.awt.FontFormatException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
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
public class TestSave {

	private static Document doc;
	
	
	@Test
	public final void testSaveWord() throws IOException, FontFormatException
	{
		
		doc=new Document(null,null,null);
		
		ArrayList<String> list=new ArrayList<String>();
		list.add("lampros vlahopoulos"+"\n"+"kwstas vlahopoulos mpampas 4hnei arnia"+"\n"+"END OF PROGRAM");
		
		String path="C:\\Users\\Hacknet\\eclipse-workspace\\myy803-main.zip_expanded\\myy803-main\\Project2021\\test_files\\lam.docx";
		String typecode="None";
		doc.setContents(list);
		doc.save(null,path,typecode);
		assertEquals(isEqWord(path,"C:\\Users\\Hacknet\\eclipse-workspace\\myy803-main.zip_expanded\\myy803-main\\Project2021\\test_files\\testEqu.docx"),true);
		assertEquals(doc.getContents(),list);
		assertEquals(doc.getPath_write(),path);
		assertEquals(doc.getTypecode_write(),typecode);
		assertEquals(doc.getFiletype_write(),".docx");
		
		
	}
	
	@Test
	public final void testSaveWordNull() throws IOException, FontFormatException
	{
		
		doc=new Document(null,null,null);
		ArrayList<String> list=new ArrayList<String>();
		doc.save(null,null,null);
		assertEquals(doc.getContents(),list);
		assertEquals(doc.getPath_write(),"");
		assertEquals(doc.getFiletype_write(),"");
		assertEquals(doc.getTypecode_write(),"");
	}
	
	
	@Test
	public final void testSaveEcel() throws IOException, FontFormatException
	{
		
		doc=new Document(null,null,null);
		
		ArrayList<String> list=new ArrayList<String>();
		list.add("lampros\t"+"10\t\n"+"kwstas\t");
		
		String path="C:\\\\Users\\\\Hacknet\\\\eclipse-workspace\\\\myy803-main.zip_expanded\\\\myy803-main\\\\Project2021\\\\test_files\\\\lam13.xlsx";
		String typecode="None";
		doc.setContents(list);
		doc.save(null,path,typecode);
		assertEquals(isEqExel(path,"C:\\\\Users\\\\Hacknet\\\\eclipse-workspace\\\\myy803-main.zip_expanded\\\\myy803-main\\\\Project2021\\\\test_files\\\\XL.xlsx"),true);
		assertEquals(doc.getContents(),list);
		assertEquals(doc.getPath_write(),path);
		assertEquals(doc.getTypecode_write(),typecode);
		assertEquals(doc.getFiletype_write(),".xlsx");
		
		
	}
	
	@Test
	public final void testSaveEcelNull() throws IOException, FontFormatException
	{
		
		doc=new Document(null,null,null);
		doc.save(null,null,null);
		ArrayList<String> list =new ArrayList<String>();
		assertEquals(doc.getContents(),list);
		assertEquals(doc.getPath_write(),"");
		assertEquals(doc.getTypecode_write(),"");
		assertEquals(doc.getFiletype_write(),"");
		
		
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
              
                System.out.println(cellValue + "  " +cellValue2);
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
