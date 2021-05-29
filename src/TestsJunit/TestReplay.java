package TestsJunit;
import static org.junit.Assert.assertEquals;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import commands.DocumentToSpeech;
import commands.EditDocument;
import commands.OpenDocument;
import model.Document;
import model.FakeTTSFacade;
public class TestReplay {

	private Document doc;
	
	
	
	@Test
	public final void TestPlaySelectedContents() throws IOException {
		String path="C:\\Users\\Hacknet\\eclipse-workspace\\myy803-main.zip_expanded\\myy803-main\\Project2021\\test_files\\bla.docx";
		doc=new Document(null,path,"None");
		
		
		
		ArrayList<ActionListener> list =new ArrayList<ActionListener>();
		Document copy =new Document(doc);
		OpenDocument openDoc =new OpenDocument(copy.getDocument());
		
		

		FakeTTSFacade faketts =new FakeTTSFacade();
		copy.getDocument().setAudioManager(faketts);
		DocumentToSpeech docTo =new DocumentToSpeech(copy.getDocument());
		
		list.add(openDoc);
		list.add(docTo);
		
		ArrayList<String> listContents =new ArrayList<String>();
		listContents.add("Lampros vlahopoulos panwraia tsami giwrgos krommudas");
		listContents.add(" Texnologia logismikou");
		listContents.add("2021" );
		listContents.add("Final report");
		EditDocument edit =new EditDocument(doc,"lampros hahahah");
		list.add(edit);
		doc.replay(list, null);
		
		assertEquals(openDoc.getDocument().getContents(),listContents);
		assertEquals(faketts.getContentToPlay(),listContents);
		assertEquals(edit.getDocument().getContents().get(0),"lampros hahahah");
	}
	
	
	
}
