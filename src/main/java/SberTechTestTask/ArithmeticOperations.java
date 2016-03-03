package SberTechTestTask;

import com.opencsv.CSVReader;
import org.apache.commons.lang3.tuple.Triple;

import java.io.FileReader;
import java.util.Objects;

/**
 * Parse input file, read operands,
 */
public class ArithmeticOperations
{

    public static Triple<Long, Long, Long> getOperationResult(String inputFile, String operation) throws Exception
    {
        Triple operandsAndResult;
        if (!Objects.equals(operation, "+") && !Objects.equals(operation, "-") && !Objects.equals(operation, "*") && !Objects.equals(operation, "/") &&
                !Objects.equals(operation, "\\")) {
            throw new Exception("Operator is not an arithmetic operation");
        }
        CSVReader reader = new CSVReader(new FileReader(inputFile), ';');
        String[] line;
        while ((line = reader.readNext()) != null) {
            if (Objects.equals(line[2], operation)) {
                Long lineNumber = reader.getLinesRead();
                operandsAndResult = Triple.of(_parseLong(line[0], lineNumber), _parseLong(line[1], lineNumber), _parseLong(line[3], lineNumber));
                return operandsAndResult;
            }
        }
        throw new Exception("Could not perform '" + operation + "' operation based on the input data file");
    }

    protected static Long _parseLong(String number, Long stringId) throws Exception
    {
        Long numberLong;
        try {
            numberLong = Long.parseLong(number);

        } catch (NumberFormatException e) {
            throw new Exception("Operand is not a Long type. Position: " + stringId + " Value: " + number);
        }
        return numberLong;
    }
}
