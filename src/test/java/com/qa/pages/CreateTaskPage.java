package com.qa.pages;

import com.qa.utils.DriverManager;
import com.qa.utils.TestUtils;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CreateTaskPage extends BasePage {
    TestUtils utils = new TestUtils();

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").instance(3)")
    private WebElement createTaskButton;

    @AndroidFindBy(xpath = "//android.widget.ScrollView/android.widget.EditText[1]")
    private WebElement titleField;

    @AndroidFindBy(xpath = "//android.widget.ScrollView/android.widget.EditText[2]")
    private WebElement textField;

    @AndroidFindBy(xpath = "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[2]/android.widget.Button")
    private WebElement submitTaskButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Task added\"]")
    private WebElement message;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").instance(2)")
private WebElement moreButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").instance(4)")
    private WebElement refreshButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").instance(3)")
    private WebElement clearCompleted;

    @AndroidFindBy(xpath = "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.Button")
    private WebElement backButton;

    public CreateTaskPage() {
    }
    public CreateTaskPage clickButtonByName(String buttonName) {

        switch (buttonName.toLowerCase().trim()) {
            case "+":
            case "create task":
            case "add":
                click(createTaskButton);
                break;

            case "more":
            case "more options":
                click(moreButton);
                try { Thread.sleep(1000); } catch (InterruptedException e) { }
                break;

            case "refresh":

                click(refreshButton);

                try { Thread.sleep(1000); } catch (InterruptedException e) { }
                break;

            case "clear completed":
                click(clearCompleted);

                try { Thread.sleep(1000); } catch (InterruptedException e) { }
                break;

            case "back":
            case "return":
            case "navigate up":
                click(backButton);
                try { Thread.sleep(500); } catch (InterruptedException e) { }
                break;

            case "submit":
            case "save":
            case "add task":

                click(submitTaskButton);
                break;

            default:
                throw new IllegalArgumentException(
                        "Unknown button: '" + buttonName + "'\n" +
                                "Available: '+', 'create task', 'more', 'refresh', " +
                                "'clear completed', 'back', 'submit'"
                );
        }
        return this;
    }

    public CreateTaskPage startCreateTask() {
click(createTaskButton);
return this;
    }

    public CreateTaskPage enterTitle(String titleText){
        sendKeys(titleField, titleText);
        return this;
    }

    public CreateTaskPage enterText(String taskTest){
        sendKeys(textField, taskTest);
        return this;
    }
    public CreateTaskPage submitTask(){
        click(submitTaskButton);
        return this;
    }

    public CreateTaskPage refreshPage(){
        click(refreshButton);
        return this;
    }

    public CreateTaskPage clearCompletedTasks(){
        click(clearCompleted);
        return this;
    }

    public CreateTaskPage selectMore(){
        click(moreButton);
        return this;
    }
    private void clickDynamicButton(String buttonName) {
        try {
            // Try by text
            WebElement button = DriverManager.getDriver().findElement(
                    AppiumBy.androidUIAutomator(
                            "new UiSelector().text(\"" + buttonName + "\")"
                    )
            );
            click(button);
            return;
        } catch (Exception e1) { }

        try {

            WebElement button = DriverManager.getDriver().findElement(
                    AppiumBy.androidUIAutomator(
                            "new UiSelector().description(\"" + buttonName + "\")"
                    )
            );
            click(button);
            return;
        } catch (Exception e2) { }

        try {

            WebElement button = DriverManager.getDriver().findElement(
                    AppiumBy.androidUIAutomator(
                            "new UiSelector().textContains(\"" + buttonName + "\")"
                    )
            );
            click(button);
            return;
        } catch (Exception e3) {
            throw new RuntimeException("Button not found: " + buttonName);
        }
    }
    public String getMessage(){

        return getText(message, "Task added");

    }

    public CreateTaskPage pressBackButton(){
        click(backButton);
        return this;
    }
    public CreateTaskPage waitForMainPage() {
        try {

            WebDriverWait wait = new WebDriverWait(
                    DriverManager.getDriver(),
                    Duration.ofSeconds(5)
            );
            wait.until(ExpectedConditions.visibilityOf(createTaskButton));
            System.out.println("Back on main page");
        } catch (TimeoutException e) {
            System.out.println("Warning: Main page not detected, but continuing...");
        }
        return this;
    }

    public List<WebElement> getAllCheckboxes() {
        return DriverManager.getDriver().findElements(
                By.className("android.widget.CheckBox")
        );
    }
 public int getTaskCount() {
        return getAllCheckboxes().size();
    }


    public CreateTaskPage printTaskList() {
        List<WebElement> tasks = getAllCheckboxes();


        for (int i = 0; i < tasks.size(); i++) {
            boolean completed = isCheckboxCheckedAtIndex(i);
            String status = completed ? "☑" : "☐";

        }

        return this;
    }



    public CreateTaskPage clickCheckboxAtIndex(int index) {
        try {


            WebElement checkbox = DriverManager.getDriver().findElement(
                    AppiumBy.androidUIAutomator(
                            "new UiSelector().className(\"android.widget.CheckBox\").instance(" + index + ")"
                    )
            );
            click(checkbox);


            Thread.sleep(800);

        } catch (Exception e) {

        }
        return this;
    }

    public boolean isCheckboxCheckedAtIndex(int index) {
        try {
            WebElement checkbox = DriverManager.getDriver().findElement(
                    AppiumBy.androidUIAutomator(
                            "new UiSelector().className(\"android.widget.CheckBox\").instance(" + index + ")"
                    )
            );

            String checked = checkbox.getAttribute("checked");
            String enabled = checkbox.getAttribute("enabled");


            return "true".equals(checked);
        } catch (Exception e) {

            return false;
        }
    }

    public CreateTaskPage completeFirstNTasks(int n) {


        List<WebElement> allCheckboxes = getAllCheckboxes();
        int totalCheckboxes = allCheckboxes.size();




        int checkboxesToClick = Math.min(n, totalCheckboxes);

        for (int i = 0; i < checkboxesToClick; i++) {


            try {

                WebElement checkbox = allCheckboxes.get(i);

                String checkedBefore = checkbox.getAttribute("checked");

                checkbox.click();

                Thread.sleep(700);

                allCheckboxes = getAllCheckboxes();
                checkbox = allCheckboxes.get(i);


                String checkedAfter = checkbox.getAttribute("checked");


            } catch (Exception e) {

                e.printStackTrace();
            }
        }


        return this;
    }
    public int getCompletedTaskCount() {
        List<WebElement> checkboxes = getAllCheckboxes();
        int completedCount = 0;

        for (WebElement checkbox : checkboxes) {
            try {

                if ("true".equals(checkbox.getAttribute("checked"))) {
                    completedCount++;
                }
            } catch (Exception e) {

            }
        }


        return completedCount;
    }
    public CreateTaskPage completeRandomTasks(int count) {
        int totalTasks = getTaskCount();
        count = Math.min(count, totalTasks);

        List<Integer> randomIndices = new ArrayList<>();
        while (randomIndices.size() < count) {
            int randomIndex = (int) (Math.random() * totalTasks);
            if (!randomIndices.contains(randomIndex)) {
                randomIndices.add(randomIndex);
            }
        }



        for (int index : randomIndices) {


            try {

                WebElement checkbox = scrollToCheckboxAtIndex(index);

                // Check current state
                String checked = checkbox.getAttribute("checked");

                // Click if not already checked
                if (!"true".equals(checked)) {

                    checkbox.click();
                    Thread.sleep(600);

                } else {

                }

            } catch (Exception e) {

            }
        }



        return this;
    }


}