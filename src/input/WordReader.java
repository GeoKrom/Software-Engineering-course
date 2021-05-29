package input;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public class WordReader implements DocumentReader {


	private String path;
	private XWPFDocument xwpfDocument;
	
	
	public WordReader(String path) {
		
		this.path=path;
		
	}
	
	public ArrayList<String> read() throws IOException {
		ArrayList<String> lines=new ArrayList<String>();
		try {
			FileInputStream file =new FileInputStream(path);
			
			xwpfDocument = new XWPFDocument(file);
			List<XWPFParagraph> paragraphList =xwpfDocument.getParagraphs();
			
		
			for(XWPFParagraph paragraph:paragraphList) {
				
				lines.add(paragraph.getText());
				
			}
		
		}catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		
		
		return lines;
	}

	
	
	

}
