package input;

import java.io.IOException;


public class DocumentReaderFactory {

	private WordReader wordReader;
	
	private ExcelReader exelReader;
	private ReaderAtbashDecorator readerAtbashDecorator;
	private ReaderRot13Decorator readerRot13Decorator;
	
	private String type=null;
	public DocumentReaderFactory() {
		
	}
	
	public DocumentReader createReader(String typefile, String path, String encodetype) throws IOException  {
		if(encodetype!=null && !encodetype.equals("") && path!=null){
		
		if (path.endsWith(".docx") && (encodetype.equals("None"))) {
			type=".docx";
			 wordReader=new WordReader(path);
			 return wordReader;
		}
		else if (path.endsWith(".docx") && encodetype.equals("Rot13")) {
			type=".docx";
			wordReader=new WordReader(path);
			return  readerRot13Decorator=new ReaderRot13Decorator(wordReader);
		}
		else if (path.endsWith(".docx") && encodetype.equals("Atbash")) {
			type=".docx";
			wordReader=new WordReader(path);
			return  readerAtbashDecorator=new ReaderAtbashDecorator(wordReader);
		}
		else if (path.endsWith(".xlsx") && encodetype.equals("None")) {
			type=".xlsx";
			 exelReader=new ExcelReader(path);
			 return exelReader;
		}
		else if (path.endsWith(".xlsx") && encodetype.equals("Rot13")) {
			type=".xlsx";
			exelReader=new ExcelReader(path);
			return  readerRot13Decorator=new ReaderRot13Decorator(exelReader);
		}
		else if (path.endsWith(".xlsx") && encodetype.equals("Atbash")) {
			type=".xlsx";
			exelReader=new ExcelReader(path);
			return  readerAtbashDecorator=new ReaderAtbashDecorator(exelReader);
		}
		else {
			System.out.println("Error end of file .xlsx or docx or wrong encryption type!!");
			
			return null;
		}
	
	}else {
		
		System.out.println("Error:EncodeType was null empty or file path was null or empty" +" ");
		
		return null;
	}
	
	
	
	}

	public String getfiletype() {
		
		return type;
	}
	
	
	
}
