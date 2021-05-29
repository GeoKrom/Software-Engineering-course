package input;

import java.io.IOException;
import java.util.ArrayList;

public class ReaderRot13Decorator extends ReaderDecorator{

	private ArrayList<String> decoded_lines =new ArrayList<String>();
	private ArrayList<String> readed_lines=new ArrayList<String>();
	
	public ReaderRot13Decorator(DocumentReader componentReader) {
		super(componentReader);
		
	}
	
	public ArrayList<String> read() throws IOException {
		this.readed_lines=super.read();
		for(String e:readed_lines) {
			this.decoded_lines.add(decode(e));
		}
		
		return this.decoded_lines;
	}

	
	public String decode(String string_to_decode) {
		String str_reverse="";
		for (int i = 0; i < string_to_decode.length(); i++) {
            char c = string_to_decode.charAt(i);
            if       (c >= 'a' && c <= 'm') c += 13;
            else if  (c >= 'A' && c <= 'M') c += 13;
            else if  (c >= 'n' && c <= 'z') c -= 13;
            else if  (c >= 'N' && c <= 'Z') c -= 13;
            
            str_reverse+=c;
        }
       return (str_reverse);
    }



	


	
	
	
	
	
	
	
	
	
	
	
	
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

