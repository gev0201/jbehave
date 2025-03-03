package com.insite.jbehave;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;

import java.util.Arrays;
import java.util.List;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;

import com.insite.jbehave.steps.CheckQIsExistSteps;

public class CheckQIsExistStories extends JUnitStories {

	@SuppressWarnings("deprecation")
	public CheckQIsExistStories() {
		configuredEmbedder().useMetaFilters(Arrays.asList("-skip"));
		configuredEmbedder().embedderControls().doGenerateViewAfterStories(true).doIgnoreFailureInStories(false)
				.doIgnoreFailureInView(false).useStoryTimeoutInSecs(600);
	}

	@Override
	public Configuration configuration() {
		return new MostUsefulConfiguration()
				// where to find the stories
				.useStoryLoader(new LoadFromClasspath(this.getClass()))
				// CONSOLE and TXT reporting
				.useStoryReporterBuilder(
						new StoryReporterBuilder().withDefaultFormats().withFormats(Format.CONSOLE, Format.HTML));
	}

	@Override
	protected List<String> storyPaths() {
		return new StoryFinder().findPaths(codeLocationFromClass(this.getClass()), "**/CheckQIsExist.story",
				"**/excluded*.story");
	}

	@Override
	public InjectableStepsFactory stepsFactory() {
		return new InstanceStepsFactory(configuration(), new CheckQIsExistSteps());
	}
}