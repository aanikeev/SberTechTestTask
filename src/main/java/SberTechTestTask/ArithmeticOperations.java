package SberTechTestTask;

import com.opencsv.CSVReader;
import org.javatuples.Quartet;
import java.io.FileReader;
import java.util.Objects;

/**
 * Parse input file, and extract operations results
 */
public class ArithmeticOperations
{
    /*
    * Read the input file, find the required operation and return corresponding string as a tuple
    * If operator was not found, return a string with broken operator
    * */
    public static String[] getOperationResult(String inputFile, String operation) throws Exception
    {
        String[] operandsAndResult = null;
        String[] brokenOperandsAndResult = null;
        String operator;
        CSVReader reader = new CSVReader(new FileReader(inputFile), ';');
        String[] line;
        while ((line = reader.readNext()) != null) {
            operator = line[2];
            if (!Objects.equals(operator, "+") && !Objects.equals(operator, "-") && !Objects.equals(operator, "*") && !Objects.equals(operator, "/")) {
                brokenOperandsAndResult = line;
            }
            if (Objects.equals(operator, operation)) {
                operandsAndResult = line;
            }
        }
        if (operandsAndResult == null && brokenOperandsAndResult != null) {
            return brokenOperandsAndResult;
        } else {
            return operandsAndResult;
        }
    }
}
