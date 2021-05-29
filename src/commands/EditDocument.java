package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import model.Document;

public class EditDocument implements ActionListener {

	private Document document;
	private EditDocument copy;
	private String str;

	public EditDocument() {
	
		
	}
	
	public EditDocument(EditDocument doc) {
		this.copy=new EditDocument();
		document=new Document(doc.getDocument());
		this.copy.setDocument(document.getDocument());
		document=this.copy.getDocument();
		str=doc.getStr();
		this.copy.setStr(str);
		
	}
	public EditDocument(Document doc,String str) {
		this.document=doc;
		this.str=str;
		
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	
	public EditDocument getEditDocument() {
		return copy;
	}
	
	public Document getDocument() {
		return document;
	}

	public void setDocument(Document doc) {
		this.document=new Document(doc).getDocument();
	}
	
	@Override
	public void actionPerformed(ActionEvent edit) {
		
		this.document.edit(this.str);
		
	}

}
