package view;

import java.io.IOException;
import java.util.ArrayList;

import commands.CommandsFactory;
import commands.DocumentToSpeech;
import commands.EditDocument;
import commands.OpenDocument;
import commands.PlayAPartOfLines;
import commands.SoundSettings;
import model.Document;
import model.TextToSpeechFactory;

public class Test1 {

	public static void main(String[] args) throws IOException {
		
	String path="C:\\Users\\Hacknet\\eclipse-workspace\\myy803-main.zip_expanded\\myy803-main\\Project2021\\test_files\\bla.docx";	
	Document doc1 =new Document(null,path,"None");

	OpenDocument docopen =new OpenDocument(doc1);
	docopen.actionPerformed(null);
	
	//docopen.getDocument().playContents();
	
	ArrayList<Integer> list =new ArrayList<Integer>();
	list.add(100);
	list.add(150);
	list.add(100);
	SoundSettings sound =new SoundSettings(doc1,list);
	sound.actionPerformed(null);
	//docopen.getDocument().playContents();
	
	
	SoundSettings copy =new SoundSettings(sound);
	ArrayList<Integer> list2 =new ArrayList<Integer>();
	doc1.edit("KWSTAS");
	list2.add(90);
	list2.add(140);
	list2.add(130);
	sound.setSettings_sound(list2);
	System.out.println(sound.getDocument());
	System.out.println(copy.getSoundSettingsDocument().getDocument());
	copy.getSoundSettingsDocument().getDocument().playContents();

	
	}

}
