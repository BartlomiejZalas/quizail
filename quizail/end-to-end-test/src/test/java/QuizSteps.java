import com.quizail.com.logic.QuizEJBRemote;
import com.quizali.com.domain.Quiz;
import org.jbehave.core.annotations.*;

import javax.naming.NamingException;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class QuizSteps extends RemoteServiceTestSteps{

    private QuizEJBRemote quizEJBRemote;
    private Quiz quiz;
    private Quiz createdQuiz;
    private List<Quiz> allQuizzes;

    @BeforeScenario
    public void init() throws NamingException {
        quizEJBRemote = (QuizEJBRemote) getInitialContext().lookup("com.quizail.com.logic.QuizEJBRemote");
        removeAllQuizzes();
    }

    @Given("quiz with title: $title and description: $desc and date: $date and time: $time")
    public void createQuiz(String title, String description, Date date, int time) {
        quiz = new Quiz(title, description, date, time);
    }

    @Given("persisted quiz with title: $title and description: $desc and date: $date and time: $time")
    public void createAndPersistQuiz(String title, String description, Date date, int time) {
        createQuiz(title, description, date, time);
        addQuiz();
    }

    @When("quiz is added")
    public void addQuiz() {
        createdQuiz = quizEJBRemote.createQuiz(quiz);
    }

    @When("find all quizzes")
    public void findAll() {
        allQuizzes = quizEJBRemote.getQuizzes();
    }

    @When("this quiz is deleted")
    public void deleteQuiz() {
        quizEJBRemote.removeQuiz(createdQuiz);
    }

    @When("find this quiz")
    public void findQuiz() {
        createdQuiz = quizEJBRemote.getQuiz(createdQuiz.getId());
    }

    @Then("this quiz should be removed from system")
    public void assertDeleted() {
        Quiz quiz = quizEJBRemote.getQuiz(createdQuiz.getId());
        assertNull(quiz);
    }

    @Then("there should be quiz with title: $title and description: $description and date: $date and time: $time in the system")
    public void assertQuiz(String title, String description, Date date, int time) {
        Quiz quiz = quizEJBRemote.getQuiz(createdQuiz.getId());
        assertEquals(title, quiz.getTitle());
        assertEquals(description, quiz.getDescription());
        assertEquals(date, quiz.getDate());
        assertEquals(time, quiz.getTime());
    }

    @Then("teardown - remove added quiz")
    public void quizTeardown() {
        deleteQuiz();
    }

    @Then("number of quizzes should be $n")
    public void assertAllQuizzesNumber(int n) {
        assertEquals(n, allQuizzes.size());
    }

    private void removeAllQuizzes() {
        List<Quiz> quizzes = quizEJBRemote.getQuizzes();
        for (Quiz quiz : quizzes) {
            quizEJBRemote.removeQuiz(quiz);
        }
    }

}
