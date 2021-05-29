package output;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.ArrayList;

//import org.apache.commons.lang3.math.NumberUtils;
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter implements DocumentWriter {

	private String path;
	

	public ExcelWriter(String path) {
		
		this.path=path;
	}

	@Override
	public void write(ArrayList<String> list){
		
		XSSFWorkbook wb= new XSSFWorkbook();
			
		XSSFSheet sheet =wb.createSheet("page 1");	
		
		String a="";
		for(String e:list) {
			a+=e;
			
		}
		
		String ar[]=a.split("\n");
		for(int i=0; i<ar.length; i++) {
			String[] ar1=ar[i].split("\t");
			
			Row row =sheet.createRow(i);
			for(int j=0; j<ar1.length; j++) {
				 
				Cell cell =row.createCell(j); 
				
				cell.setCellValue(ar1[j]);
				
			}
		}
	
		try {
			wb.write(new FileOutputStream(this.path));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			wb.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	

	}

