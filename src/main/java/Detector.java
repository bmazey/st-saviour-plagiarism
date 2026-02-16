import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Detector {
    public static void main(String[] args) {
        // Represent the size of the input alphabet.
        int d = 256;

        // A prime number used for hashing.
        int q = 101;

        // Read the input files and iterate over the contents line-by-line.
        try {
            // Start by reading the file we want to check for plagiarism.
            File submitted = new File("./resources/cheat.txt");
            Scanner scanner = new Scanner(submitted);
            String content = scanner.useDelimiter("\\A").next();
            

            File original = new File("./resources/poem.txt");
            scanner = new Scanner(original);
            while (scanner.hasNextLine()) {
                // System.out.println(scanner.nextLine());

            }
   
            scanner.close();
  
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } 
    }

    public static void search(String pattern, String text, int d, int q) {
        int M = pattern.length();
        int N = text.length();
        int p = 0; // hash value for pattern
        int t = 0; // hash value for text
        int h = 1;

        // The value of h would be: pow(d, M-1) % q
        for (int i = 0; i < M - 1; i++)
            h = (h * d) % q;

        // Calculate the hash value of pattern and first window of text.
        for (int i = 0; i < M; i++) {
            p = (d * p + pattern.charAt(i)) % q;
            t = (d * t + text.charAt(i)) % q;
        }

        // Slide the pattern over text one by one
        for (int i = 0; i <= N - M; i++) {

            // Check the hash values of current window of text and pattern. 
            // If the hash values match, then check characters.
            if (p == t) {
                // Check characters.
                int j = 0;
                while (j < M) {
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        break;
                    }
                    j++;  
                }

                // if p == t and pat[0...M-1] = txt[i, i+1, ...i+M-1]
                if (j == M) {
                    System.out.println("Pattern found at index: " + i);
                }
            }

            // Calculate hash value for next window of text. 
            // Remove leading digit, add trailing digit.
            if (i < N - M) {
                t = (d * (t - text.charAt(i) * h) + text.charAt(i + M)) % q;

                // We might get negative value of t, converting it to positive.
                if (t < 0)
                    t = (t + q);
            }
        }
    }
}
