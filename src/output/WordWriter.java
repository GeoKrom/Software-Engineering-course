package output;

import java.io.File;
import java.io.FileOutputStream;
//import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;



public  class WordWriter implements DocumentWriter {

	
	private String path;
	
	
	public WordWriter(String path) {
		
		this.path = path;
		
	}

	@Override
	public void write(ArrayList<String> list) {
		System.out.println(list);
		try {
			XWPFDocument word =new XWPFDocument();
			
			FileOutputStream output =new FileOutputStream(new File(this.path)); 
		
			//for(String e:list) {
			for(String e:list) {  ///
				
			
			//String[] ar=list.get(0).split("\n");
			String[] ar=e.split("\n");
			
			for(int i=0; i<ar.length; i++) {
				XWPFParagraph par=word.createParagraph();
				XWPFRun run=par.createRun();
				run.setText(ar[i]);
				System.out.println(ar[i]);
						
				}
			} 
			
			
			word.write(output);
			output.close();
			
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
	
	}



	
}
