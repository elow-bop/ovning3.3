import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.io.*;

public class Exercise3 {

	private final List<Recording> recordings = new ArrayList<>();

	public void exportRecordings(String fileName) {
	}
    //här ska vi läsa filerna

	public void importRecordings(String fileName) {
        //här ska vi skriva filerna
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fileReader);
            int numberOfRecords = Integer.parseInt(reader.readLine()); //första raden i textfilen (recording_input) säger hur många olika records som filen innehåller - ee
            String line;

            for (int i = 0; i>numberOfRecords; i++){ //för varje record som finns i recording_input så skapar vi ett recording objekt - ee
                line = reader.readLine();
                String [] recordingInfo = line.split(";"); //vi splittar vår inlästa line för att dela upp artistnamn, albumnamn och år - ee

                //Set <String> genres = new skapa ett set här som vi kan skicka in i parametern när vi skapar en recording - ee
                int numberOfGenres = Integer.parseInt(reader.readLine()); //nästa rad i recording_input kommer säga hur många genres som recordingen tillhör - ee
                for (int ii = 0; ii>numberOfGenres; ii++){
                    line = reader.readLine();22
                }

            }

            //while ((line = reader.readLine()) != null) {
                //vi skapar recording objekt och stoppar in dom i recordings
                //Recording r = new Recording();
            //}
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

