package model;


import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class TTSFacade implements TextToSpeechApi{

	private VoiceManager vm;
	private Voice voice;
	
	public TTSFacade() {
		
		System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
		
		this.voice = VoiceManager.getInstance().getVoice("kevin16");
		this.voice.allocate();
	}
	
	
	
	@Override
	public void play(String contentToPlay) {
		
		this.voice.speak(contentToPlay);
	}

	

	@Override
	public void setVolume(int volumeValue) {
		this.voice.setVolume((float)volumeValue/100 );
	}

	@Override
	public void setPinch(int pinchValue) {
		this.voice.setPitchRange((float)pinchValue);
	}

	@Override
	public void setRate(int rateValue) {
		this.voice.setRate((float)rateValue);
		
	}




}
