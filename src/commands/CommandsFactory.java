package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.Document;

//import model.Document;

public class CommandsFactory {

	private OpenDocument openDocument;
	private EditDocument editDocument;
	private SaveDocument saveDocument;
	private DocumentToSpeech docToSpeech;
	private PlayAPartOfLines playAPartOfLines;
	private SoundSettings soundSettings;
	private ReplayManager replaymanager;
	private ActionEvent actionEvent;
	private Document doc;
	private String part;
	private ArrayList<Integer> sound_par=new ArrayList<Integer>();
	
	
	
	 public CommandsFactory(Document doc, ActionEvent arg0) {
		this.actionEvent=arg0;
		this.doc = new Document(doc).getDocument();
		
	}
	
	 
	 public CommandsFactory(Document dc) {
		
		this.doc=dc;
	
	}

	
	 public CommandsFactory(Document doc, String part) {
			this.doc = doc;
			this.part=part;
		}
	 
	
	 
	 public CommandsFactory(Document doc, ArrayList<Integer> soundParameters) {
			//this.doc = doc;
			this.doc=new Document(doc).getDocument();
			this.sound_par=new ArrayList<Integer>();
			for(Integer a:soundParameters) {
				this.sound_par.add(a);
			}
		}


	public ActionListener createCommand(String command){
		 
		 if (command.equals("openDocument")){
			 
			 openDocument =new OpenDocument(this.doc);
	         return openDocument;
			 
		 }
		 else if(command.equals("editDocument")) {
			 
			 editDocument=new EditDocument(doc,part);
			 return editDocument;
		 }else if(command.equals("saveDocument")) {
			 
			 saveDocument = new SaveDocument(doc);
			 return saveDocument;
		 }
		 else if(command.equals("DocumentToSpeech")) { //ALL CONTENTS
			 
			 docToSpeech = new DocumentToSpeech(doc);
			 return docToSpeech;
			 
	    }else if(command.equals("playAPartOfLines")) { //play a part of contents
			 
	    	 playAPartOfLines = new PlayAPartOfLines(doc,part);
			 return playAPartOfLines;
			 
	    }else if(command.equals("soundSettings")) {
			 
	    	 soundSettings = new SoundSettings(doc,sound_par);
			 return soundSettings;
			 
	    }else if(command.equals("replay")) {
			 
	    	 replaymanager = new ReplayManager(doc,this.actionEvent);
			 return replaymanager;
			 
	    }
		 
		return null;
}

}
