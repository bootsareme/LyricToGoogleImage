import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * <h1>FileHandler Interface</h1>
 * <p>
 * Interface for handling
 * file operations.
 * </p>
 *
 * @author  Vincent Zhang
 * @version 1.0
 * @since   2021-9-12
 */
public interface IFileHandler {
    /**
     * <h2>parseFile Function</h2>
     * <p>
     * Parses file by line delimited by spaces.
     * Used to parse multiline lyric files.
     * </p>
     * @param file Input file for parsing.
     * @return results
     */
    static ArrayList<String> parseFile(File file) {
        ArrayList<String> results = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new FileInputStream(file));
            ArrayList<String[]> splitLine = new ArrayList<>();

            // read file line by line
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                splitLine.add(line.split("\\s+"));
            }

            // splitLine is in incorrect format; must convert to 1D ArrayList
            for (String[] i : splitLine)
                results.addAll(Arrays.asList(i));

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return results;
    }
}