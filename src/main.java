import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class main {

    public static void main(String[] args) {

        try {
            String content = readFileInString();
            System.out.println(content + "\n");
            System.out.println(countWords(content));
        } catch (IOException e) {
            System.out.println("There was an error reading the file");
        }


    }


    public static String readFileInString() throws IOException {

        String fileLocation = "D:\\Documentos\\prueba_lumu\\proyect\\src\\data.txt";
        BufferedReader reader = new BufferedReader(new FileReader(fileLocation));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
        // delete the last new line separator
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        reader.close();

        String content = stringBuilder.toString();

        return content;
    }


    public static String countWords(String text) {

        //delete \r that contain from the .txt file
        String textPreprocessed = text.replaceAll("\r", "");
        //convert the text in array of strings
        String[] words = textPreprocessed.split(" |\n");
        List<String> flags = new ArrayList<String>();
        List<Integer> counts = new ArrayList<Integer>();
        //text without character \n
        String notBreakRow = textPreprocessed.replaceAll("\n", "");
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int counter = 0;
            if (!flags.contains(word)) {
                for (int j = i; j < words.length; j++) {
                    if (word.equals(words[j])) {
                        counter++;
                    }
                }
                flags.add(word);
                counts.add(counter);
            }
        }

        String response = words.length + " words\n" + notBreakRow.length() + " characters\n\n";
        for (int i = 0; i < flags.size(); i++) {
            response += flags.get(i) + ": " + counts.get(i) + "\n";
        }
        return response;
    }

}

