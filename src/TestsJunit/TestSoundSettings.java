package TestsJunit;


import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import model.Document;
import model.FakeTTSFacade;


public class TestSoundSettings {
	
	private Document doc;
	
	
	@Test
	public final void setSoundSettingsTest() throws IOException {
		doc=new Document(null,null,null);
		ArrayList<Integer> sound_settings=new ArrayList<Integer>();
		sound_settings.add(160); //pintch
		sound_settings.add(150); //rate
		sound_settings.add(100); //volume
		FakeTTSFacade faketts=new FakeTTSFacade();
		doc.setAudioManager(faketts);
		
		doc.setSoundSettings(sound_settings);
		assertEquals(doc.getSettings_sound(),sound_settings);
		assertEquals(((FakeTTSFacade) doc.getAudiomanager()).getVolumeValue(),100 );
		assertEquals(((FakeTTSFacade) doc.getAudiomanager()).getPitchValue(), 160);
		assertEquals(((FakeTTSFacade) doc.getAudiomanager()).getRateValue(),150 );
	}
	
	
	@Test
	public final void setSoundSettingsTestNull() throws IOException {
		doc=new Document(null,null,null);
		ArrayList<Integer> sound_settings=new ArrayList<Integer>();
		FakeTTSFacade faketts=new FakeTTSFacade();
		doc.setAudioManager(faketts);
		
		doc.setSoundSettings(sound_settings);
		
		assertEquals(doc.getSettings_sound(),sound_settings);
		assertEquals(((FakeTTSFacade) doc.getAudiomanager()).getVolumeValue(),0 );
		assertEquals(((FakeTTSFacade) doc.getAudiomanager()).getPitchValue(), 0);
		assertEquals(((FakeTTSFacade) doc.getAudiomanager()).getRateValue(),0 );
	}
	
	
	
}
	
	
	
	
	

