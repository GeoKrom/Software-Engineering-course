package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import model.Document;

public class OpenDocument implements ActionListener{

	private OpenDocument copy;
	private Document document;
	
	
	public OpenDocument() {

	}
	
	public OpenDocument(OpenDocument doc) {
		this.copy=new OpenDocument();
		document=new Document(doc.getDocument());
		this.copy.setDocument(document.getDocument());
		document=this.copy.getDocument();
	}


	public OpenDocument(Document document) {
		this.document=document;
		
	}
	
	
	public Document getDocument() {
		return document;
	}

	public OpenDocument getOpenDocument() {
		return copy;
	}
	

	public void setDocument(Document doc) {
		this.document=new Document(doc).getDocument();
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent openfile) {
		
		try {
			this.document.open(document.getFiletype(),document.getPath(),document.getTypecode());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}
