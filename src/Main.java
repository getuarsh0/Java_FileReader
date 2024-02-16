import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "src\\test.csv";
        List<List<String>> parsedCsv = CSVFileParser.parseCsvFile(filePath);

        StringBuilder output = new StringBuilder("[");
        for (int i = 0; i < parsedCsv.size(); i++) {
            List<String> row = parsedCsv.get(i);
            output.append("[");
            for (int j = 0; j < row.size(); j++) {
                if (row.get(j).contains("\n")) {
                    output.append("\"").append(row.get(j)).append("\"");
                } else {
                    output.append("'").append(row.get(j)).append("'");
                }
                if (j < row.size() - 1) {
                    output.append(", ");
                }
            }
            output.append("]");
            if (i < parsedCsv.size() - 1) {
                output.append(", ");
            }
        }
        output.append("]");

        System.out.println(output.toString());
    }
}