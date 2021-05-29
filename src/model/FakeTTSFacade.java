package model;

import java.util.ArrayList;

public class FakeTTSFacade implements TextToSpeechApi {

	
	
	private ArrayList<String> contentToPlay=new ArrayList<String>();
	private int volumeValue;
	private int pitchValue;
	private int rateValue;
	
	
	
	@Override
	public void play(String contentToPlay) {
		this.contentToPlay.add(contentToPlay);

	}

	@Override
	public void setVolume(int volumeValue) {
		// TODO Auto-generated method stub
		this.volumeValue=volumeValue;

	}

	@Override
	public void setPinch(int pinchValue) {
		// TODO Auto-generated method stub
		this.pitchValue=pinchValue;

	}

	@Override
	public void setRate(int rateValue) {
		// TODO Auto-generated method stub
		this.rateValue=rateValue;
	}

	
	
	
	
	
	public ArrayList<String> getContentToPlay() {
		return this.contentToPlay;
	}

	public int getVolumeValue() {
		return volumeValue;
	}

	public int getPitchValue() {
		return pitchValue;
	}

	public int getRateValue() {
		return rateValue;
	}

}
