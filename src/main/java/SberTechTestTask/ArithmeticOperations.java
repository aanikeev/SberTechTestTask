package SberTechTestTask;

import com.opencsv.CSVReader;
import org.apache.commons.lang3.tuple.Triple;
import org.junit.Assert;

import java.io.FileReader;
import java.util.Objects;

/**
 * Parse input file, and extract operations results
 */
public class ArithmeticOperations
{
    /*
    * Read the input file, find the required operation and return its operands and result from file.
    * */
    public static Triple<Long, Long, Long> getOperationResult(String inputFile, String operation) throws Exception
    {
        Triple operandsAndResult;
        String operator;
        String brokenOperator = null;
        CSVReader reader = new CSVReader(new FileReader(inputFile), ';');
        String[] line;
        while ((line = reader.readNext()) != null) {
            operator = line[2];
            if (!Objects.equals(operator, "+") && !Objects.equals(operator, "-") && !Objects.equals(operator, "*") && !Objects.equals(operator, "/")) {
                brokenOperator = operator;
            }
            if (Objects.equals(operator, operation)) {
                Long lineNumber = reader.getLinesRead();
                operandsAndResult = Triple.of(_parseLong(line[0], lineNumber), _parseLong(line[1], lineNumber), _parseLong(line[3], lineNumber));
                return operandsAndResult;
            }
        }
        throw new AssertionError("Operator is not an arithmetic operation: " + brokenOperator);
    }

    /*
    * Convert string to long.
    * */
    protected static Long _parseLong(String number, Long stringId) throws AssertionError
    {
        Long numberLong;
        try {
            numberLong = Long.parseLong(number);

        } catch (NumberFormatException e) {
            throw new AssertionError("Operand is not a Long type. Position: " + stringId + " Value: " + number);
        }
        return numberLong;
    }
}
