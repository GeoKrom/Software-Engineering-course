package input;

import java.io.IOException;
import java.util.ArrayList;

public class ReaderAtbashDecorator extends ReaderDecorator {

	
	private ArrayList<String> decoded_lines =new ArrayList<String>();
	private	ArrayList<String> readed_lines=new ArrayList<String>();
	
	
	public ReaderAtbashDecorator(DocumentReader componentReader) {
		super(componentReader);
		
	}


	public String decode(String string_to_decode) {
		
		String str_reverse ="";
		for(int i=0; i<string_to_decode.length(); i++) {
			char c=string_to_decode.charAt(i);
			if (c >= 'A' && c <= 'Z') c = (char) ('Z'-(c - 'A'));
	        else if (c >= 'a' && c <= 'z') c = (char) ('z'-(c - 'a'));
	        
			str_reverse+=c;
			
		}
		
		return str_reverse;
	}


	
	public ArrayList<String> read() throws IOException {
		this.readed_lines=super.read();
		for(String e:this.readed_lines) {
		this.decoded_lines.add(decode(e));
		}
		return this.decoded_lines;
	}


	
	


}
