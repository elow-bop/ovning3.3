import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;

public class Exercise3 {

	private final List<Recording> recordings = new ArrayList<>();

	public void exportRecordings(String fileName) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            PrintWriter writer = new PrintWriter(fileWriter);

            for(Recording r : recordings){
                writer.println("<recording>");
                writer.println("  <artist>" + r.getArtist() + "</artist>");
                writer.println("  <title>" + r.getTitle() + "</title>");
                writer.println("  <year>" + r.getYear() + "</year>");
                writer.println("  <genres>");

                for(int i=0; i<r.getGenre().size(); i++){
                    writer.println("    <genre>" + r.getGenre()+ "</genre>");
                }
                writer.println("  </genres>");
                writer.println("  </recording>");
            }


        }
        catch (FileNotFoundException e){
            System.out.printf( "%s file not found%n", fileName);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

	public void importRecordings(String fileName) {
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fileReader);
            int numberOfRecords = Integer.parseInt(reader.readLine()); //första raden i textfilen (recording_input) säger hur många olika records som filen innehåller - ee
            String line;

            for (int i = 0; i<numberOfRecords; i++){ //för varje record som finns i recording_input så skapar vi ett recording objekt - ee
                line = reader.readLine();
                String [] recordingInfo = line.split(";"); //vi splittar vår inlästa line för att dela upp artistnamn, albumnamn och år - ee
                int year = Integer.parseInt(recordingInfo[2]);

                Set <String> genres = new HashSet<>();//skapa ett set här som vi kan skicka in i parametern när vi skapar en recording - ee
                int numberOfGenres = Integer.parseInt(reader.readLine()); //nästa rad i recording_input kommer säga hur många genres som recordingen tillhör - ee
                for (int j = 0; j<numberOfGenres; j++){
                    line = reader.readLine();
                    genres.add(line);
                }
                Recording r = new Recording(recordingInfo[0], recordingInfo[1], year, genres);
                recordings.add(r);
            }

            fileReader.close();
            reader.close();
        }
        catch (FileNotFoundException e){
            System.out.printf( "%s file not found%n", fileName);
        }
        catch (IOException e){
            e.printStackTrace();
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

