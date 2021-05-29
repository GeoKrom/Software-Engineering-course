package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Document;

public class Record implements ActionListener {

	private Document document;

	public Record(Document doc) {
		this.document=doc;
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		//this.document.record(arg0);
	}

}
