package model;



public class TextToSpeechFactory {

	private  TextToSpeechApi freetts;
	
	
	public TextToSpeechApi createAPI(String api) {
		
		if(api.equals("FreeTTS")) {
			freetts=new TTSFacade();
			return freetts;
		}else if(api.equals("FakeTTS")) {
			
			freetts=new FakeTTSFacade();
			return freetts;
			
		}else {
			
			System.out.println("Error not FreeTTS");
			System.exit(0);
		}
		return null;
	}
	
}
