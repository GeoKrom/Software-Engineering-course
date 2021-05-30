package TestsJunit;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import model.Document;
import model.FakeTTSFacade;	

public class TestPlayAllContents {
	
	private Document doc;
	
	@Test
	public final void TestPlayAllContents() throws IOException {
		String path="C:\\Users\\Hacknet\\eclipse-workspace\\myy803-main.zip_expanded\\myy803-main\\Project2021\\test_files\\bla.docx";
		doc=new Document(null,path,"None");
		
		doc.open(null,path,"None");
		
		FakeTTSFacade faketts =new FakeTTSFacade();
		doc.setAudioManager(faketts);
		doc.playContents();
		assertEquals(((FakeTTSFacade)doc.getAudiomanager()).getContentToPlay(),doc.getContents());
		
	}
	
	@Test
	public final void TestPlayAllContents2() throws IOException {
		
		doc=new Document(null,null,null);
		
		doc.open(null,null,null);
		
		ArrayList<String> list =new ArrayList<String>();
		list.add("lampros vlahopoulos ");
		list.add("Test play contents");
		
		doc.setContents(list);
		FakeTTSFacade faketts =new FakeTTSFacade();
		doc.setAudioManager(faketts);
		doc.playContents();
		assertEquals(((FakeTTSFacade)doc.getAudiomanager()).getContentToPlay(),doc.getContents());
		
	}
	
	@Test
	public final void TestPlayAllContents3() throws IOException {
		
		doc=new Document(null,null,null);
		
		doc.open(null,null,null);
		
	
		FakeTTSFacade faketts =new FakeTTSFacade();
		doc.setAudioManager(faketts);
		doc.playContents();
		assertEquals(((FakeTTSFacade)doc.getAudiomanager()).getContentToPlay(),doc.getContents());
		
	}

}
