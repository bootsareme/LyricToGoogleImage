import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

/**
 * <h1>Widget Class</h1>
 * <p>
 * Widget Class used to display GUI input
 * to end user for ease of access.
 * </p>
 *
 * @author  Vincent Zhang
 * @version 1.0
 * @since   2021-9-12
 */
public class Widget extends JFrame {

    // edit this field to change editions
    private static final boolean LIGHTWEIGHT = false;
    private File lyricFile, imgOutputDestination;

    /**
     * <h2>Widget Constructor</h2>
     * <p>
     * Constructs a JFrame with size 450x170,
     * 3 buttons, 1 text field, and 1 label
     * </p>
     *
     * @see JFrame
     * @see JLabel
     * @see JButton
     */
    public Widget() {
        // use 'this' keyword for transparency
        super("LyricToGoogleImage");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setBounds(0, 0, 450, 170);

        // "API KEY:" label
        JLabel apikeyLabel = new JLabel("<html><span style='font-size:16px'>API KEY:</span></html>");
        apikeyLabel.setBounds(20, 10, 150, 30);

        // API key TextField
        JTextField apiKey = new JTextField(10);
        apiKey.setBounds(160, 10, 250, 30);
        apiKey.setFont(apiKey.getFont().deriveFont(14f));

        // select lyric file button
        JButton lyricFileButton = new JButton("Select Lyrics File");
        lyricFileButton.setBounds(20, 50, 150, 30);

        // select output directory
        JButton imageFolderStorageButton = new JButton("Select Image Storage Folder");
        imageFolderStorageButton.setBounds(200, 50, 200, 30);

        // start button
        JButton start = new JButton("START!");
        start.setBounds(20, 90, 400, 30);

        // event binders for JButtons
        lyricFileButton.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
            chooser.addChoosableFileFilter(filter);
            chooser.setFileFilter(filter);
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                lyricFile = chooser.getSelectedFile();
                lyricFileButton.setEnabled(false);
            }
        });

        imageFolderStorageButton.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                imgOutputDestination = chooser.getSelectedFile();
                imageFolderStorageButton.setEnabled(false);
            }
        });

        // when start button is pressed
        start.addActionListener(e -> {
            ArrayList<String> lyrics = IFileHandler.parseFile(lyricFile);
            if (LIGHTWEIGHT) {
                // set removes all duplicates
                HashSet<String> unique = new HashSet<>(lyrics);
                try {
                    for (String lyric : unique) {
                        // wait for 100 milliseconds because of Google's rate limit
                        TimeUnit.MILLISECONDS.sleep(100);
                        GoogleSearchAPI api = new GoogleSearchAPI(apiKey.getText(), lyric);
                        api.searchImage();
                        api.downloadImageFromLink(this.imgOutputDestination, true, lyric);
                    }
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            } else {
                try {
                    for (String lyric : lyrics) {
                        // wait for 100 milliseconds because of Google's rate limit
                        TimeUnit.MILLISECONDS.sleep(100);
                        GoogleSearchAPI api = new GoogleSearchAPI(apiKey.getText(), lyric);
                        api.searchImage();
                        api.downloadImageFromLink(this.imgOutputDestination, false, null);
                    }
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
            imageFolderStorageButton.setEnabled(true);
            lyricFileButton.setEnabled(true);
        });

        // add all labels to JFrame Widget
        this.add(apikeyLabel);
        // add all text fields to JFrame Widget
        this.add(apiKey);
        // add all buttons to JFrame Widget
        this.add(lyricFileButton); this.add(imageFolderStorageButton); this.add(start);
    }

    public static void main(String[] args) {
        new Widget().setVisible(true);
    }
}
