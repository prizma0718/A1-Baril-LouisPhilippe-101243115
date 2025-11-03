package steps;
import com.example.Calculator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.jupiter.api.Assertions.assertEquals;

//code is based on cucumber.io examples

public class CalculatorSteps {
    private int result;
    private Calculator calculator;

    @Given("I have a calculator")
    public void i_have_a_calculator() {
        calculator = new Calculator();
        System.out.println("Calculator initialized");
    }

    @When("I add {int} and {int}")
    public void i_add_and(int num1, int num2) {
        result = calculator.add(num1, num2);
        System.out.println("Adding " + num1 + " + " + num2 + " = " + result);
    }

    @When("I subtract {int} from {int}")
    public void i_subtract_from(int num2, int num1) {
        result = calculator.subtract(num1, num2);
        System.out.println("Subtracting " + num1 + " - " + num2 + " = " + result);
    }

    @Then("the result should be {int}")
    public void the_result_should_be(int expected) {
        assertEquals(expected, result, "Expected result to be " + expected + " but got " + result);
        System.out.println("Result verified: " + result);
    }

}
