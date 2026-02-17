package com.qa.stepdef;

import com.qa.pages.CreateTaskPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class CreateTaskStepDef {
    private int tasksCreated = 0;



    private CreateTaskPage createTaskPage = new CreateTaskPage();

    @Given("the user is on the task creation screen")
    public void theUserIsOnTheTaskCreationScreen() {
        // Already on the screen after app launch in Hooks
    }

    @When("the user clicks on the {string} button")
    public void theUserClicksOnTheButton(String buttonName) {
        createTaskPage.clickButtonByName(buttonName);
    }

    @When("the user enters a title as {string}")
    public void theUserEntersATitleAs(String titleText) {
        createTaskPage.enterTitle(titleText);
    }

    @When("the user enters a description as {string}")
    public void theUserEntersADescriptionAs(String descriptionText) {
        createTaskPage.enterText(descriptionText);
    }

    @When("the user submits the task")
    public void theUserSubmitsTheTask() {
        createTaskPage.submitTask();
    }

    @And("the user clicks {string} button")
    public void theUserClicksButton(String buttonName) {
        createTaskPage.pressBackButton();
    }

    @Then("the task should be successfully created with {string}")
    public void theTaskShouldBeSuccessfullyCreatedWith(String expectedMessage) {
        String actualMessage = createTaskPage.getMessage();
        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @And("the user is back on the task creation screen")
    public void theUserIsBackOnTheTaskCreationScreen() {
        
    }


    @When("the user creates the following tasks:")
    public void theUserCreatesTheFollowingTasks(List<List<String>> tasks) {
        int totalTasks = tasks.size() - 1;


        for (int i = 1; i < tasks.size(); i++) {
            List<String> row = tasks.get(i);
            String title = row.get(0);
            String description = row.get(1);

            createTaskPage.startCreateTask()
                    .enterTitle(title)
                    .enterText(description)
                    .submitTask();


            String message = createTaskPage.getMessage();
            Assert.assertEquals("Task added", message);


            createTaskPage.waitForMainPage();

        }


    }
    @Then("all tasks should be created successfully")
    public void allTasksShouldBeCreatedSuccessfully() {
        System.out.println("Task creation scenario completed");
    }


    @Given("the user has tasks in the list")
    public void theUserHasTasksInTheList() {
        int taskCount = createTaskPage.getTaskCount();

        Assert.assertTrue("No tasks found in the list", taskCount > 0);
        createTaskPage.printTaskList();
    }
}