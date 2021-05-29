package output;


import java.util.ArrayList;



public class WriterRot13Decorator extends WriterDecorator{

	private ArrayList<String> write_list=new ArrayList<String>();
	
	public WriterRot13Decorator(DocumentWriter componentWriter) {
		super(componentWriter);
		
	}

	@Override
	public void write(ArrayList<String> list) {
		System.out.println(list);
		for(String e:list) {
			write_list.add(encode(e));
			System.out.println(encode(e));
		}
		super.write(write_list);
	}

	@Override
	public String encode(String string_to_encode) {
		String str_reverse="";
		for (int i = 0; i < string_to_encode.length(); i++) {
            char c = string_to_encode.charAt(i);
            if       (c >= 'a' && c <= 'm') c += 13;
            else if  (c >= 'A' && c <= 'M') c += 13;
            else if  (c >= 'n' && c <= 'z') c -= 13;
            else if  (c >= 'N' && c <= 'Z') c -= 13;
            
            str_reverse+=c;
        }
       return (str_reverse);
		
	}

	
	

	
	

}
