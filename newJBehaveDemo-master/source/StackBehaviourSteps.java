package com.wmi.tutorials.bdd.stack.specs;

import java.util.Stack;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
//import org.jbehave.core.annotations.And;
//import org.jbehave.core.junit.Assert;

public class StackBehaviourSteps {
	public Stack stack;
    @Given("I have an empty stack")
    public void givenIHaveAnEmptyStack() {  stack= new Stack();System.out.println("Stack created"); }

    @When("I push an item $item")
    public void whenIPushAnItem(@Named("item") String item) { stack.push(item); System.out.println("Pushed"+item);}

    @Then("I should count $expected")
    public void thenIShouldCount(@Named("expected") int expected) {
        int actual = stack.size();
        System.out.println("count"+actual);
        if (actual != expected) 
            throw new RuntimeException("expected:"+expected+";actual:"+actual);
    }
}
