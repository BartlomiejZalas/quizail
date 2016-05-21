import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;

import static junit.framework.Assert.assertEquals;

public class QuizSteps {

    private int n1;
    private int n2;
    private int sum;

    @Given("a $n1 and $n2 numbers")
    public void givenNumbers(int n1, int n2) {
        this.n1 = n1;
        this.n2 = n2;
    }

    @When("I add them")
    public void add() {
        sum = n1 + n2;
    }

    @Then("the sum should be $sum")
    public void theGridShouldLookLike(int $sum) {
        assertEquals($sum, sum);
    }

}
