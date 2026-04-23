public class Elias {
    public static void main (String [] arfs){
        Exercise3 exercise = new Exercise3();
        exercise.importRecordings("src/recording_input.txt");
        System.out.print(exercise.getRecordings());
    }
}
