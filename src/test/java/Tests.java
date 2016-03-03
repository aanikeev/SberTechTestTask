/**
 * Created by aanikeev on 02.03.2016.
 */
import org.apache.commons.lang3.tuple.Triple;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import SberTechTestTask.ArithmeticOperations;
import ru.yandex.qatools.allure.annotations.Parameter;

public class Tests
{
    public static final String inputFile = Tests.class.getResource("source.csv").getPath();
    Triple additionParams;
    Triple subtractionParams;
    Triple divisionParams;
    Triple multiplicationParams;

    @Before
    public void precondition() throws Exception
    {
        additionParams = ArithmeticOperations.getOperationResult(inputFile, "+");
        subtractionParams = ArithmeticOperations.getOperationResult(inputFile, "-");
        divisionParams = ArithmeticOperations.getOperationResult(inputFile, "/");
        multiplicationParams = ArithmeticOperations.getOperationResult(inputFile, "*");
    }


    @Parameter("Operand 1")
    private Long firstOperand;

    @Parameter("Operand 2")
    private Long secondOperand;

    @Parameter("Result")
    private Long fileResult;

    @Test
    public void verifyAddition() throws Exception
    {
        Triple operandsAndResult = additionParams;
        this._setParameters(operandsAndResult);
        Long realResult = firstOperand + secondOperand;
        Assert.assertEquals(realResult, fileResult);
    }

    @Test
    public void verifySubtraction() throws Exception
    {
        Triple operandsAndResult = subtractionParams;
        this._setParameters(operandsAndResult);
        Long realResult = firstOperand - secondOperand;
        Assert.assertEquals(realResult, fileResult);
    }

    @Test
    public void verifyDivision() throws Exception
    {
        Triple operandsAndResult = divisionParams;
        this._setParameters(operandsAndResult);
        Long realResult = firstOperand / secondOperand;
        Assert.assertEquals(realResult, fileResult);
    }

    @Test
    public void verifyMultiplication() throws Exception
    {
        Triple operandsAndResult = multiplicationParams;
        this._setParameters(operandsAndResult);
        Long realResult = firstOperand * secondOperand;
        Assert.assertEquals(realResult, fileResult);
    }

    protected void _setParameters(Triple operandsAndResult)
    {
        firstOperand = (Long)operandsAndResult.getLeft();
        secondOperand = (Long)operandsAndResult.getMiddle();
        fileResult = (Long)operandsAndResult.getRight();
    }
}
