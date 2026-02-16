import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Detector {
    public static void main(String[] args) {
        // Read the input file and iterate over the contents line-by-line.
        try {
            File file = new File("./resources/poem.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
   
            scanner.close();
  
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } 
    }
}
