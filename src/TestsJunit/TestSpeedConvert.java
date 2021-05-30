package TestsJunit;

import static org.junit.Assert.assertEquals;

import java.awt.event.ActionListener;
import java.io.IOException;

import org.junit.Test;

import commands.CommandsFactory;
import model.Document;
import model.FakeTTSFacade;

public class TestSpeedConvert {

	
	private Document doc;
	
	
	
	@Test
	public final void testSpeedConvertTextToSpeech() throws IOException {
		
		
		doc=new Document(null,null,null);
		FakeTTSFacade fake=new FakeTTSFacade();
		doc.setAudioManager(fake);
		CommandsFactory comf =new CommandsFactory(doc,"the final test speed lampros vlahopoulos nine lexical numbers.");
		ActionListener playline= comf.createCommand("playAPartOfLines");
		playline.actionPerformed(null);
		assertEquals(((FakeTTSFacade)doc.getAudiomanager()).getContentToPlay().get(0),"the final test speed lampros vlahopoulos nine lexical numbers.");
		
	}
	
	
	
}
