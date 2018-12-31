package com.wmi.tutorials.bdd.stack.specs;

import java.util.List;

import org.jbehave.core.Embeddable;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.io.LoadFromRelativeFile;
import org.jbehave.core.io.StoryPathResolver;
import java.io.File;

public class StackBehaviourStory extends JUnitStory {
    @Override 
    public Configuration configuration() { System.out.println("Inside the log");
        try{
	     return new MostUsefulConfiguration().useStoryPathResolver(new StoryPathResolver() {
			
			@Override
			public String resolve(Class<? extends Embeddable> arg0) {
				return "";
			}
		}).useStoryLoader(new LoadFromRelativeFile(new File("./stories/stack_behaviour_story.story").toURI().toURL())).useStoryReporterBuilder(
            new StoryReporterBuilder()
            .withDefaultFormats()
            .withFormats(Format.HTML, Format.CONSOLE)
            .withRelativeDirectory("jbehave-report")
); 
        }catch(Exception e){System.out.println(e.getMessage());}
        return null;
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration()
                                      , new StackBehaviourSteps());   
    }


	
}
