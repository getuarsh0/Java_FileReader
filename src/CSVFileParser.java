import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVFileParser {

    public static List<List<String>> parseCsvFile(String filePath) {
        List<List<String>> rows = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            List<String> currentRow = new ArrayList<>();
            StringBuilder currentValue = new StringBuilder();
            boolean inQuotes = false;

            String line;
            while ((line = br.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    char character = line.charAt(i);
                    if (character == '"') {
                        if (inQuotes && i + 1 < line.length() && line.charAt(i + 1) == '"') {
                            // Handle escaped quotes inside quoted value
                            currentValue.append(character);
                            i++; // Skip the next quote
                        } else {
                            inQuotes = !inQuotes; // Toggle the inQuotes flag
                        }
                    } else if (character == ',' && !inQuotes) {
                        // End of the current value
                        currentRow.add(currentValue.toString());
                        currentValue.setLength(0);
                    } else {
                        currentValue.append(character);
                    }
                }
                if (!inQuotes) {
                    // Add the last value of the line to the row
                    currentRow.add(currentValue.toString());
                    currentValue.setLength(0);

                    // Add the completed row to the list of rows
                    rows.add(new ArrayList<>(currentRow));
                    currentRow.clear();
                } else {
                    // Append an actual newline character for multiline values
                    currentValue.append("\\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rows;
    }
}
