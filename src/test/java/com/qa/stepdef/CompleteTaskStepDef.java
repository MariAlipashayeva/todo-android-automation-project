package com.qa.stepdef;

import com.qa.pages.CreateTaskPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class CompleteTaskStepDef {

    private CreateTaskPage createTaskPage = new CreateTaskPage();
    @When("the user completes the first {int} tasks")
    public void theUserCompletesTheFirstTasks(int count) throws InterruptedException {
        createTaskPage.completeFirstNTasks(count);
    }

    @Then("{int} tasks should be marked as completed")
    public void allTasksShouldBeMarkedAsCompleted(int count) {
        int actualCount = createTaskPage.getCompletedTaskCount();
        Assert.assertEquals("Completed task count mismatch", count, actualCount);

        createTaskPage.printTaskList();
    }

    @When("the user completes {int} random tasks")
    public void theUserCompletesCountRandomTasks(int count) {
        createTaskPage.completeRandomTasks(count);
    }

    @Then("completed task should be removed from list")
    public void completedTaskShouldBeRemovedFromList() {
    }
}