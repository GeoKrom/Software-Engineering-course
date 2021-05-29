package model;

import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;


import input.DocumentReader;
import input.DocumentReaderFactory;
import output.DocumentWriter;
import output.DocumentWriterFactory;

public class Document {

	private ArrayList<String> contents;
	private DocumentReader documentReader;
	private DocumentReaderFactory docReaderFactory;
	private DocumentWriter documentWriter;
	private DocumentWriterFactory docWriterFactory;
	private TextToSpeechFactory api;
	private TextToSpeechApi audiomanager;
	private String filetype;
	private String path;
	private String typecode;
	private String filetype_write;
	private String path_write;
	private String typecode_write;
	private ArrayList<String> contents_write=new ArrayList<String>();
	private static Document document;
	private ArrayList<Integer> settings_sound=new ArrayList<Integer>();
	
	
	
	public Document(String filetype,String path,String typecode) throws IOException {
		if( path==null ||typecode==null) {
			if( path==null) {
				this.path="";
				this.typecode=typecode;
			}
			if(typecode==null) {
				this.typecode="";
				this.path=path;
			}
			this.filetype="";
			
			this.contents=new ArrayList<String>();
		}else {
			this.filetype=filetype;
			this.path=path;
			this.typecode=typecode;
			this.contents=new ArrayList<String>();
			this.docReaderFactory=new DocumentReaderFactory();
			
			
			this.api=new TextToSpeechFactory();
		    this.audiomanager=  /*(TTSFacade)*/ this.api.createAPI("FreeTTS");
		
		}
	
	}
	public Document() {
		
	}
	
	public Document(Document currDoc) {
		document=new Document();
	
		
		document.setContents( currDoc.getContents());
		document.setDocumentReader(currDoc.getDocumentReader());
		document.setDocReaderFactory(currDoc.getDocReaderFactory());
		document.setDocumentWriter(currDoc.getDocumentWriter());
		document.setDocWriterFactory(currDoc.getDocWriterFactory());
		document.setAudioManager(currDoc.getAudiomanager());
		document.setApi(currDoc.getApi());
		document.setFiletype(currDoc.getFiletype());
		document.setPath(currDoc.getPath());
		document.setTypecode(currDoc.getTypecode());
		document.setFiletype_write(currDoc.getFiletype_write());
		document.setPath_write(currDoc.getPath_write());
		document.setTypecode_write(currDoc.getTypecode_write());
		document.setContents_write(currDoc.getContents_write());
		//document.setSoundSettings(currDoc.getSettings_sound());
		this.settings_sound=new ArrayList<Integer>();
		for(Integer a:currDoc.getSettings_sound()) {
			
			this.settings_sound.add(a);
		}
	}
	
	public ArrayList<Integer> getSettings_sound() {
		return settings_sound;
	}
	

	public Document getDocument() {
		return document;
	}
	
	
	public void setContents(ArrayList<String> contents) {
		this.contents=new ArrayList<String>();
		for(String e:contents) {
			this.contents.add(e);
		}
		
	}

	public void setAudioManager(TextToSpeechApi facade) {
		
		this.audiomanager= facade;
		
	}
	
	public void setDocReaderFactory(DocumentReaderFactory doc) {
		this.docReaderFactory=doc;
		
	}
	
	
	public void open(String a,String b, String c) throws IOException {
		
		if( b==null ||c==null) {
			if( b==null) {
				this.path="";
				this.typecode=c;
			}
			if(c==null) {
				this.typecode="";
				this.path=b;
			}
			this.filetype=a;
		
		}
		
			if(this.docReaderFactory!=null) {
				this.filetype=a;
				this.path=b;
				this.typecode=c;
				this.documentReader=this.docReaderFactory.createReader(a,b,c);
				if(this.documentReader!=null) {
					this.contents=this.documentReader.read();
					this.filetype=this.docReaderFactory.getfiletype();
				}else {
					this.filetype="";
				}
				
				
			
			}
	
		}
		
	public void playContents(){
	
		if(this.contents!=null &&this.audiomanager!=null) {
			
		
			for(String e:this.contents) {
				this.audiomanager.play(e);
			}
		}else {
			System.out.println("Contents null or '' i cant play the text or audiomanager is null");
			this.audiomanager.play("");
			
		}
	   
	}
	
	public void playLine(String part) {
	
			if(part!=null &&this.audiomanager!=null) {
				this.audiomanager.play(part);
			}
			else {
				System.out.println("Error null audiomanager or selected string");
				this.audiomanager.play("");
			}
	}
	
	
	public void edit(String newcontents) {
		
		if(newcontents!=null) {
			this.contents.clear();
			this.contents.add(newcontents);
		}else {
			System.out.println("Error null String for Edit");
			
		}
		
	}

	public void save(String a, String b , String c) throws IOException, FontFormatException {
		this.filetype_write=a;
		this.path_write=b;
		this.typecode_write=c;
		
		docWriterFactory=new DocumentWriterFactory();
		this.documentWriter= docWriterFactory.createWriter(this.filetype_write,this.path_write,this.typecode_write); //this.contents_write
		
		
		
		if(this.documentWriter!=null) {
			
			this.documentWriter.write(/*this.contents_write*/ this.contents);
			this.filetype_write=docWriterFactory.getFileType_Write();
		}else {
			this.filetype_write="";
			this.path_write="";
			this.typecode_write="";
			System.out.println("Document writer is Null");
		}
		
	
	}
	
	
   public DocumentWriter getDocumentWriter() {
		return this.documentWriter;
	}



   public void setSoundSettings(ArrayList<Integer> settings_sound) {
	   this.settings_sound=new ArrayList<Integer>();
	   for(Integer a:settings_sound) {
			
			this.settings_sound.add(a);
		}
	   	
	   	if(this.settings_sound!=null && this.settings_sound.size()==3) {
	   		this.audiomanager.setPinch(this.settings_sound.get(0));
			this.audiomanager.setRate(this.settings_sound.get(1));
			this.audiomanager.setVolume(this.settings_sound.get(2));
	   	}else {
	   		System.out.println("Error null list or wrong size");
	   		
	   	}
		
		
	}

    public void replay(ArrayList<ActionListener> list_actions,ActionEvent a) {
    	for(ActionListener  e :list_actions) {
    		e.actionPerformed(a);
    	}
    	
	}
    
  

	public /*TTSFacade*/TextToSpeechApi getAudiomanager() {
		return this.audiomanager;
	}


	public String getFiletype() {
		return filetype;
	}


	public String getPath() {
		return path;
	}


	public String getTypecode() {
		return typecode;
	}


	public ArrayList<String> getContents() {
		return this.contents;
	}



	public String getFiletype_write() {
		return filetype_write;
	}



	public void setFiletype_write(String filetype_write) {
		this.filetype_write = filetype_write;
	}



	public String getPath_write() {
		return path_write;
	}



	public void setPath_write(String path_write) {
		this.path_write = path_write;
	}



	public String getTypecode_write() {
		return typecode_write;
	}



	public void setTypecode_write(String typecode_write) {
		this.typecode_write = typecode_write;
	}


	public DocumentReader getDocumentReader() {
		
		return this.documentReader;
	}
	

	public DocumentWriterFactory getDocWriterFactory() {
		return docWriterFactory;
	}


	public void setDocWriterFactory(DocumentWriterFactory docWriterFactory) {
		this.docWriterFactory = docWriterFactory;
	}


	public TextToSpeechFactory getApi() {
		return api;
	}


	public void setApi(TextToSpeechFactory api) {
		this.api = api;
	}


	public ArrayList<String> getContents_write() {
		return contents_write;
	}


	public void setContents_write(ArrayList<String> contents_write) {
		this.contents_write = contents_write;
	}


	public DocumentReaderFactory getDocReaderFactory() {
		return docReaderFactory;
	}


	public void setDocumentReader(DocumentReader documentReader) {
		this.documentReader = documentReader;
	}


	public void setDocumentWriter(DocumentWriter documentWriter) {
		this.documentWriter = documentWriter;
	}


	


	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public void setTypecode(String typecode) {
		this.typecode = typecode;
	}




	
	
}
