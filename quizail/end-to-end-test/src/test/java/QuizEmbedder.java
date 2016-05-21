import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromRelativeFile;
import org.jbehave.core.io.StoryLoader;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.SilentStepMonitor;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static org.jbehave.core.reporters.StoryReporterBuilder.*;

public class QuizEmbedder extends Embedder {

    private Configuration configuration;

    public QuizEmbedder() {
        configuration = loadConfiguration();
    }

    @Override
    public Configuration configuration() {
        return configuration;
    }

    @Override
    public List<CandidateSteps> candidateSteps() {
        return new InstanceStepsFactory(configuration(), new QuizSteps()).createCandidateSteps();
    }


    private Configuration loadConfiguration() {
        Configuration configuration = new MostUsefulConfiguration();
        configuration.useStoryLoader(loadStoryLoader());
        configuration.useStoryReporterBuilder(loadStoryReporterBuilder());
        configuration.useStepMonitor(new SilentStepMonitor());

        return configuration;
    }

    private StoryLoader loadStoryLoader() {
        return new LoadFromRelativeFile(
                CodeLocations.codeLocationFromClass(this.getClass()));
    }

    private StoryReporterBuilder loadStoryReporterBuilder() {
        Properties viewResources = new Properties();
        viewResources.put("decorateNonHtml", "true");
        StoryReporterBuilder storyReporterBuilder = new StoryReporterBuilder();
        storyReporterBuilder.withDefaultFormats();
        storyReporterBuilder.withViewResources(viewResources);
        storyReporterBuilder.withFormats(Format.CONSOLE, Format.TXT, Format.HTML);
        return storyReporterBuilder;
    }

    public void runStory(String story) {
        if (story != null && story.endsWith(".story")) {
            this.runStoriesAsPaths(Arrays.asList(story));
        } else {
            throw new RuntimeException("Problem locating .story file:" + story);
        }
    }

    public static void main(String[] args) {
        String storyRelativePath = "quiz.story";
        QuizEmbedder eclipseEmbedder = new QuizEmbedder();
        eclipseEmbedder.runStory(storyRelativePath);
    }
}
