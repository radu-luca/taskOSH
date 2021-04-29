import Model.Test_table;
import ModelDAO.Test_tableDAO;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImportCSV {
    private static final String urlCsv = "Interview-task-data-osh.csv";
    private static final String badDataUrl = "bad-data-" + new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss").format(new Date()) + ".csv";

    public static void importData(Connection connection) throws IOException, CsvValidationException, SQLException {
        int nrSuccessful = 0, nrUnsuccessful = 0;
        File file = new File("./logs/"+badDataUrl);
        file.createNewFile();
        FileWriter outputfile = new FileWriter(file);
        CSVWriter writer = new CSVWriter(outputfile);

        String[] header = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        writer.writeNext(header);

        FileReader filereader = new FileReader(urlCsv);
        CSVReader csvReader = new CSVReader(filereader);
        Test_tableDAO test_tableDAO = new Test_tableDAO(connection);
        String[] nextRecord = csvReader.readNext();

        while ((nextRecord = csvReader.readNext()) != null) {
            if (isCorrectData(nextRecord)) {
                test_tableDAO.insert(new Test_table(nextRecord[0], nextRecord[1], nextRecord[2], nextRecord[3], nextRecord[4],
                        nextRecord[5], parseToDouble(nextRecord[6]), nextRecord[7], nextRecord[8], nextRecord[9]));
                nrSuccessful++;
            } else {
                writer.writeNext(doubleQuoted(nextRecord));
                nrUnsuccessful++;
            }
        }
        printLog(nrSuccessful,nrUnsuccessful);
        filereader.close();
        csvReader.close();
        writer.close();
    }

    private static double parseToDouble(String string) {
        if (string.length() > 1) {
            return Double.valueOf(string.substring(1));
        }
        return 0;
    }

    private static boolean isCorrectData(String[] strings) {
        if (strings.length != 10)
            return false;
        return true;
    }

    private static String checkCommas(String string)
    {
        if(string.contains(","))
        return "\"" + string + "\"";
        return string;
    }
    private static String[] doubleQuoted(String[] strings)
    {
        for(int i=0; i<strings.length; ++i)
        {
            strings[i] = checkCommas(strings[i]);
        }
        return strings;
    }

    private static void printLog(int nrSuccessful, int nrUnsuccessful) throws IOException {
        int total = nrSuccessful + nrUnsuccessful;
        FileWriter fw = new FileWriter("logs.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(new Date()+"\n"+total+" of records received\n" + nrSuccessful +" of records successful\n"+nrSuccessful+" of records failed\n");
        bw.newLine();
        bw.close();
    }
}