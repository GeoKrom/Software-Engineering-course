package commands;

import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.IllegalFormatException;



import model.Document;

public class SaveDocument implements ActionListener {

	
	private Document document;
	private SaveDocument copy;
	
	
	public SaveDocument() {
	
	}

	public SaveDocument(SaveDocument doc) {
		copy=new SaveDocument();
		document=new Document(doc.getDocument());
		this.copy.setDocument(document.getDocument());
		document=this.copy.getDocument();
	}
	
	public SaveDocument(Document doc) {
		
		this.document=doc;
		
	}
	
	public SaveDocument getSaveDocument() {
		return copy;
	}

	

	public void setDocument(Document doc) {
		this.document=new Document(doc).getDocument();
	}
	
	public Document getDocument() {
		return document;
	}
	

	
	@Override
	public void actionPerformed(ActionEvent save) {
		
		try {
			this.document.save(this.document.getFiletype_write(),this.document.getPath_write(), this.document.getTypecode_write());
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (IllegalFormatException e) {
			
			e.printStackTrace();
		} catch (FontFormatException e) {
			
			e.printStackTrace();
		}
		
	}

	
	
}
