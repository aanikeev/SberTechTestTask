/**
 * Created by aanikeev on 02.03.2016.
 */

import org.junit.Test;
import org.junit.Assert;
import SberTechTestTask.ArithmeticOperations;
import ru.yandex.qatools.allure.annotations.Parameter;

import java.util.Objects;

public class Tests
{
    public static final String inputFile = Tests.class.getResource("source.csv").getPath();

    @Parameter("Operand 1")
    private String firstOperand;

    @Parameter("Operand 2")
    private String secondOperand;

    @Parameter("Operation")
    private String operation;

    @Parameter("Result")
    private String fileResult;

    @Test
    public void verifyAddition() throws Exception
    {
        this._validateArithmeticOperation("+");
    }

    @Test
    public void verifySubtraction() throws Exception
    {
        this._validateArithmeticOperation("-");
    }

    @Test
    public void verifyDivision() throws Exception
    {
        this._validateArithmeticOperation("/");
    }

    @Test
    public void verifyMultiplication() throws Exception
    {
        this._validateArithmeticOperation("*");
    }

    /*
    * Call getOperationResult to get a tuple of strings with operands, operation and result,
    * set test parameters accordingly, validate operands, result and operator, and finally validate that result is correct
    * */
    protected void _validateArithmeticOperation(String operator) throws Exception
    {
        String[] operandsAndResult = ArithmeticOperations.getOperationResult(inputFile, operator);
        this._setTestParameters(operandsAndResult);
        this._validateOperation();
        Long realResult = null;
        if (operator.equals("+")) {
            realResult = _validateOperandOrResult(firstOperand) + _validateOperandOrResult(secondOperand);
        } else if (operator.equals("-")) {
            realResult = _validateOperandOrResult(firstOperand) - _validateOperandOrResult(secondOperand);
        } else if (operator.equals("*")) {
            realResult = _validateOperandOrResult(firstOperand) * _validateOperandOrResult(secondOperand);
        } else if (operator.equals("/")) {
            realResult = _validateOperandOrResult(firstOperand) / _validateOperandOrResult(secondOperand);
        }
        if (realResult != null) {
            Assert.assertEquals(realResult, _validateOperandOrResult(fileResult));
        } else {
            throw new Exception("Test case contains incorrect arithmetic operation");
        }

    }

    protected void _setTestParameters(String[] operandsAndResult)
    {
        firstOperand = operandsAndResult[0];
        secondOperand = operandsAndResult[1];
        operation = operandsAndResult[2];
        fileResult = operandsAndResult[3];
    }

    protected void _validateOperation()
    {
        if (!Objects.equals(operation, "+") && !Objects.equals(operation, "-") && !Objects.equals(operation, "*") && !Objects.equals(operation, "/")) {
            throw new AssertionError("Operator is not an arithmetic operation: " + operation);
        }
    }

    /*
     * Convert string to long.
     * */
    protected static Long _validateOperandOrResult(String number)
    {
        Long numberLong;
        try {
            numberLong = Long.parseLong(number);
        } catch (NumberFormatException e) {
            throw new AssertionError("Value is not an integer: " + number);
        }
        return numberLong;
    }
}
