import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpecialCharacters {

    public static void main(String[]args){
        String inputFile = "D:/Projects/client/input.txt";
        String outputFile = "D:/Projects/client/output.txt";

        try {
            String inputString = readFromFile(inputFile);
            String result = extractNumbersAndSpecialCharacters(inputString);
            writeToFile(outputFile, result);
            System.out.println(" output written to " + outputFile);
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static String readFromFile(String fileName) throws IOException {
        StringBuilder content = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;

        while ((line = reader.readLine()) != null) {
            content.append(line);
        }
        reader.close();
        return content.toString();
    }

 private static String extractNumbersAndSpecialCharacters(String inputString) {
        String regex = "[0-9!@#$%^&*()\\-=_+\\[\\]{}|;':\",.<>/?]+";

        StringBuilder result = new StringBuilder();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputString);

            while (matcher.find()) {
            result.append(matcher.group()).append(" ");
        }

        return result.toString();
    }

    private static void writeToFile(String fileName, String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(content);
        writer.close();
    }
}

