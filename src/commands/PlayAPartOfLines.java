package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Document;

public class PlayAPartOfLines implements ActionListener {

	private Document document;
	private PlayAPartOfLines copy;
	private String  lines;
	
	
	public PlayAPartOfLines() {
		
	}
	

	public PlayAPartOfLines(Document doc,String lines) {
		
		this.document=doc;
		this.lines=lines;

	}
	public PlayAPartOfLines(PlayAPartOfLines play) {
		copy=new PlayAPartOfLines();
		document=new Document(play.getDocument());
		this.copy.setDocument(document.getDocument());
		document=this.copy.getDocument();
		lines=play.getLines();
		this.copy.setLines(lines);
	}

	

	public Document getDocument() {
		return document;
	}
	
	public void setDocument(Document document) {
		this.document =new Document( document).getDocument();
	}
	
	public PlayAPartOfLines getCopy() {
		return copy;
	}
	
	public void setCopy(PlayAPartOfLines copy) {
		this.copy = copy;
	}
	
	public String getLines() {
		return lines;
	}
	
	public void setLines(String lines) {
		this.lines = lines;
	}
	
	@Override
	public void actionPerformed(ActionEvent playpart) {
		
		this.document.playLine(this.lines);

	}
	public PlayAPartOfLines getPlayAPartOfLinesDocument() {
		
		return copy;
	}

}
