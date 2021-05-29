package output;

import java.util.ArrayList;



public abstract class WriterDecorator implements DocumentWriter {
	
	
	private DocumentWriter compont;
	
	public WriterDecorator(DocumentWriter doc) {
		this.compont=doc;
		
	}
	
   protected abstract String encode(String string_to_encode);

   @Override
	public void write(ArrayList<String> list) {
		this.compont.write(list);
	}
}
