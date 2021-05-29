package input;

import java.io.IOException;
import java.util.ArrayList;

public interface DocumentReader {

	public ArrayList<String> read() throws IOException;

}
