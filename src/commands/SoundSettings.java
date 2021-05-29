package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.Document;

public class SoundSettings implements ActionListener {

	private Document document;
	private SoundSettings copy;
	private ArrayList<Integer> settings_sound=new ArrayList<Integer>();
	

	public SoundSettings() {
		
	}
	
	
	public SoundSettings(SoundSettings sound) {
		this.copy=new SoundSettings();
		
		document=new Document(sound.getDocument());
		
		this.copy.setDocument(sound.getDocument());
		document=this.copy.getDocument();
		for(Integer e:sound.getSettings_sound()) {
			this.copy.getSettings_sound().add(e);
			this.settings_sound=this.copy.getSettings_sound();
			
		}
	}
	
	public SoundSettings(Document doc,ArrayList<Integer> sound_fix) {
		
		this.document=doc;
		
		this.settings_sound=new ArrayList<Integer>();
		for(Integer a:sound_fix) {
			this.settings_sound.add(a);
		}
		
	}
	
	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document=new Document(document).getDocument();
		
	}

	public ArrayList<Integer> getSettings_sound() {
		return this.settings_sound;
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.document.setSoundSettings(this.settings_sound);
	}

	public SoundSettings getSoundSettingsDocument() {
	
		return copy;
	}


	public void setSettings_sound(ArrayList<Integer> settings_sound) {
		this.settings_sound = settings_sound;
	}
}
