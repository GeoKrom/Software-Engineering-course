package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Document;

public class NewDocument implements ActionListener {

	
	private Document document;
	private ReplayManager replayManager;
	
	
	
	
	public NewDocument(Document doc) {
		
	}


	public void setDocument(Document doc) {
		this.document=doc;
	}
	
	
	public void setReplayManager(ReplayManager replaymanager) {
		
		this.replayManager=replaymanager;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		

	}

}
