package output;


import java.io.IOException;




public class DocumentWriterFactory {

	private WordWriter wordWriter;
	
	private ExcelWriter exelWriter;
	
	private WriterDecorator writedecorator;
	
	
	private String typefilewrite=null;

	public DocumentWriterFactory() {
		
		
	}



	public DocumentWriter createWriter(String typefile, String path, String encodetype) throws IOException {
		if(path!=null && path!="") {
		
	
	
		if(path.endsWith(".docx") && encodetype.equals("None")) {
			 wordWriter=new WordWriter(path);
			 typefilewrite=".docx";
			 return wordWriter;
		
		}
		else if (path.endsWith(".xlsx") && encodetype.equals("None")) 
		{	 typefilewrite=".xlsx";
			 exelWriter=new ExcelWriter(path);
			 return exelWriter;
		}
		else if(path.endsWith(".docx") && encodetype.equals("Rot13")) {
				wordWriter=new WordWriter(path);
				typefilewrite=".docx";
				writedecorator=new WriterRot13Decorator(wordWriter);
				return writedecorator; 
			    
		}
		else if(path.endsWith(".docx") && encodetype.equals("Atbash")) {
				wordWriter=new WordWriter(path);
				typefilewrite=".docx";
				writedecorator=new WriterAtbashDecorator(wordWriter); //writerAtbashDecorator
				return writedecorator;
			
		}else if(path.endsWith(".xlsx") && encodetype.equals("Rot13")) {
		   exelWriter =new ExcelWriter(path);
		   typefilewrite=".xlsx";
		   writedecorator=new WriterRot13Decorator(exelWriter);	
		   return  writedecorator;
		}
		else if(path.endsWith(".xlsx") && encodetype.equals("Atbash")) {
			  exelWriter=new ExcelWriter(path);
			  typefilewrite=".xlsx";
			  writedecorator=new WriterAtbashDecorator(exelWriter);
			  return  writedecorator;
		}else {
			System.out.println("Error filetype or path\nProgram closing.....");
			 
			return null;
		//	System.exit(0);
		}
		
	}else {
		System.out.println("Error NULL filetype or NULL path or"+ " "+"\nProgram closing.....");
		 
		return null;
		
		
	}
		//return null;
		
		
	}	
		
	
	public String getFileType_Write() {
		return typefilewrite;
	}
}
