import java.util.*;
import java.io.*;

public class Exercise3 {

	private final List<Recording> recordings = new ArrayList<>();

	public void exportRecordings(String fileName) {
        try{
            FileWriter fileWriter = new FileWriter(fileName);
            PrintWriter writer = new PrintWriter(fileWriter);

            for (Recording r : recordings){
                writer.println("<recording>");
                writer.println("  <artist>" + r.getArtist() + "</artist>");
                writer.println("  <title>" + r.getTitle() + "</title>");
                writer.println("  <year>" + r.getYear() + "</year>");
                writer.println("  <genres>");

                for(String genre : r.getGenre()){
                    writer.println("    <genre>" + genre + "</genre>");
                }

                writer.println("  </genres>");
                writer.println("</recording>");
            }

            writer.close();
            fileWriter.close();
        } catch (FileNotFoundException e) {
            System.out.printf("%s not found%n", fileName);
        } catch (IOException e) {
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
		try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            DataInputStream disWriter = new DataInputStream(fileInputStream);

            int numberOfSales = disWriter.readInt(); // vi läser in hur många olika försäljningsposter filen innehåller, för att kunna bygga en loop.
            // readInt metoden använder vi då vi redan vet att den första binära byten kommer representera en siffra som säger hur många poster filen innehåller - ee

            Map<Integer, Double> dateToValue = new TreeMap<>();
            while (numberOfSales > 0 ){ //en while sats där vi för för varje säljpost läser år, månad, dag och värde - ee
                int year = disWriter.readInt();
                int month = disWriter.readInt();
                int day = disWriter.readInt(); //vi kommer inte använda dag i  vår formatering men måste läsa in den ändå (tror jag...) - ee
                double value = disWriter.readDouble(); //vi vet att dom kommer i denna ordning i filen! år-månad-dag och sist värde som kommer vara en double - ee

                int key = year * 100 + month; //här skapar vi vårt nyckel - datumformatering yyyymm - ee

                if (!dateToValue.containsKey(key)){ //om det inte redan finns poster från denna månad så skapar vi ett nytt nyckel-värde par... - ee
                    dateToValue.put(key, value);
                }else{ //...men om det redan finns poster från denna månad så uppdaterar vi värdet knutet till denna nyckel (dvs denna specifika månad) - ee
                    double updatedValue = dateToValue.get(key);
                    dateToValue.replace(key, updatedValue+=value);
                }
                numberOfSales --;
            }
            disWriter.close();
            fileInputStream.close();
            return dateToValue;
        }
        catch(FileNotFoundException e){
            System.out.printf( "%s file not found%n", fileName);
        }
        catch (IOException e){
            e.printStackTrace();
        }
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

