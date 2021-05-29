package TestsJunit;


import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import model.Document;
import model.FakeTTSFacade;	

public class TestPlayLine {

private Document doc;
	
	@Test
	public final void TestPlaySelectedContents() throws IOException {
		String path="C:\\Users\\Hacknet\\eclipse-workspace\\myy803-main.zip_expanded\\myy803-main\\Project2021\\test_files\\bla.docx";
		doc=new Document(null,path,"None");
		
		doc.open(null,path,"None");
		
		FakeTTSFacade faketts =new FakeTTSFacade();
		doc.setAudioManager(faketts);
		doc.playLine("hello wordl my name is..");
		assertEquals(((FakeTTSFacade)doc.getAudiomanager()).getContentToPlay().get(0),"hello wordl my name is..");
		
	}
	
	@Test
	public final void TestPlaySelectedContentsV_2() throws IOException {
		doc=new Document(null,null,null);
		FakeTTSFacade faketts =new FakeTTSFacade();
		doc.setAudioManager(faketts);
		doc.playLine(null);
		assertEquals(((FakeTTSFacade)doc.getAudiomanager()).getContentToPlay().get(0),"");
		
	}
}
