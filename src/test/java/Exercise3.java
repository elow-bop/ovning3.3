import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.io.*;

public class Exercise3 {

	private final List<Recording> recordings = new ArrayList<>();

	public void exportRecordings(String fileName) {
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                //vi skapar recording objekt och stoppar in dom i recordings
            }
            fileReader.close();
            reader.close();
        }
        catch (FileNotFoundException e){
            System.out.printf( "%s file not found", fileName);
        }
        catch (IOException e){
            System.out.printf( "%s file not found", fileName);
        }
	}

	public void importRecordings(String fileName) {

	}

	public Map<Integer, Double> importSales(String fileName) {
		return null;
	}

	public List<Recording> getRecordings() {
		return Collections.unmodifiableList(recordings);
	}

	public void setRecordings(List<Recording> recordings) {
		this.recordings.clear();
		this.recordings.addAll(recordings);
	}
}

