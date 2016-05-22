import com.quizail.com.logic.QuizEJBRemote;
import com.quizali.com.domain.Quiz;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;
import java.util.Properties;

import static junit.framework.Assert.assertEquals;

public class QuizSteps extends RemoteServiceTestSteps{

    private QuizEJBRemote quizEJBRemote;
    private List<Quiz> quizzes;

    @Given("quiz service")
    public void init() throws NamingException {
        quizEJBRemote = (QuizEJBRemote) getInitialContext().lookup("com.quizail.com.logic.QuizEJBRemote");
    }

    @When("I get quizzes")
    public void getQuizzes() {
        quizzes = quizEJBRemote.getQuizzes();
    }

    @Then("there should be $sum")
    public void assertSum(int $sum) {
        assertEquals($sum, quizzes.size());
    }

}
