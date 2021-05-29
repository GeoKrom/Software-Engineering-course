package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Document;

public class DocumentToSpeech implements ActionListener{

	private Document document;
	private DocumentToSpeech copy;
	
	
	public DocumentToSpeech() {
		
	}
	public DocumentToSpeech(Document doc) {
		this.document=doc;
	}
	
	public DocumentToSpeech(DocumentToSpeech docto) {
		this.copy=new DocumentToSpeech();
		document =new Document(docto.getDoc());
		this.copy.setDocument(document.getDocument());
		document=this.copy.getDoc();

	}

	
	public void setDocument(Document doc) {
		this.document=new Document(doc).getDocument();
	}
	
	@Override
	public void actionPerformed(ActionEvent doctospec) {
		
		this.document.playContents();
		
}

	public Document getDoc() {
		return document;
		
	}


	public DocumentToSpeech getDocumentToSpeechDocument() {
		
		return copy;
	}

	

}