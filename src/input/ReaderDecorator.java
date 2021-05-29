package input;

import java.io.IOException;
import java.util.ArrayList;

public abstract class ReaderDecorator implements DocumentReader{
	
	private DocumentReader componentReader;

	public ReaderDecorator(DocumentReader componentReader) {
		this.componentReader=componentReader;
	}

	@Override
	public ArrayList<String> read() throws IOException{
		
		return this.componentReader.read();
	};
		
	protected abstract String decode(String string_to_decode);

	

	
	
}
