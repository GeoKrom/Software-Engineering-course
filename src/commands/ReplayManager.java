package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import model.Document;

public class ReplayManager implements ActionListener {

	private Document document;
	private ArrayList<ActionListener> list_actions;
	private ActionEvent actionEvent;
	

	
	public ReplayManager(Document doc,ActionEvent arg0) {
		this.document=doc;
		this.list_actions=new ArrayList<ActionListener>();
		this.actionEvent=arg0;
	}
	
	public Document getDocument() {
		return document;
	}

	public ArrayList<ActionListener> getList_actions() {
		return list_actions;
	}

	public void setList_actions(ArrayList<ActionListener> list_actions) {
		this.list_actions = list_actions;
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		document.replay(this.list_actions,this.actionEvent);

	}

	
	public void addcommand(ActionListener openfile) {
		
		this.list_actions.add(openfile);
		
	}
}
