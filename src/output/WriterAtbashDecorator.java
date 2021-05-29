package output;

import java.util.ArrayList;

public class WriterAtbashDecorator extends WriterDecorator {

	private ArrayList<String> write_list=new ArrayList<String>();
	

	public WriterAtbashDecorator(DocumentWriter componentWriter) {
		super(componentWriter);

	}

	@Override
	public void write(ArrayList<String> contents_write) {
		for(String e:contents_write) {
			write_list.add(encode(e));
		}
		super.write(write_list);
	}

	@Override
	public String encode(String string_to_encode) {
		String str_reverse ="";
		for(int i=0; i<string_to_encode.length(); i++) {
			char c=string_to_encode.charAt(i);
			if (c >= 'A' && c <= 'Z') c = (char) ('Z'-(c - 'A'));
	        else if (c >= 'a' && c <= 'z') c = (char) ('z'-(c - 'a'));
	        
			str_reverse+=c;
			
		}
		
		return str_reverse;
		
	}



}
