package StepDefinitions;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.WaitForSelectorState;
import io.cucumber.java.en.*;
import utilities.Base;

public class ProductsSteps extends Base {

    @Given("^I am on the Takealot homepage$")
    public void i_am_on_the_takealot_homepage() {
        try {
            initializeBrowser();
            navigateToPage("https://www.takealot.com/");
        } catch (Exception e) {
            throw new RuntimeException("Failed to navigate to Takealot homepage", e);
        }
    }

    @When("^I search for (.*)$")
    public void i_search_for(String item) {
        try {
            inputTextBySelector("//input[@name='search']", item);
            clickBySelector("//button[@data-ref='search-submit-button']");
        } catch (Exception e) {
            throw new RuntimeException("Failed to search for item: " + item, e);
        }
    }

    @And("^I select the (.*) from the search results$")
    public void i_select_the_item(String itemName) {
        try {
            String itemKey = itemName.split(" ")[0].toLowerCase();

            clickBySelector("//a[contains(@href, '" + itemKey + "') and contains(@class, 'button')]");
        } catch (Exception e) {
            throw new RuntimeException("Failed to select item from results: " + itemName, e);
        }
    }

    @And("^I choose the (.*) keyboard option$")
    public void i_choose_keyboard_option(String switchType) {
        try {
            clickBySelector("(//img[@alt='" + switchType + "'])[2]");
        } catch (Exception e) {
            throw new RuntimeException("Failed to choose keyboard switch: " + switchType, e);
        }
    }

    @And("^I click the (.*) button$")
    public void i_click_button(String btnText) {
        try {
            clickByText(btnText);
        } catch (Exception e) {
            throw new RuntimeException("Failed to click button: " + btnText, e);
        }
    }

    @Then("^I should see a confirmation message with text (.*)$")
    public void i_should_see_confirmation_message(String expectedText) {
        try {
            Locator message = getPage().locator("//*[contains(text(), '" + expectedText + "')]");
            message.waitFor(new Locator.WaitForOptions()
                    .setTimeout(10000) // 30 seconds timeout
                    .setState(WaitForSelectorState.VISIBLE));
        } catch (Exception e) {
            throw new RuntimeException("Failed to validate confirmation message: " + expectedText, e);
        }
    }

    @And("^I should see the (.*) button$")
    public void i_should_see_button(String btnLabel) {
        try {
            Locator button = getPage().locator(
                    "//a[contains(.,'" + btnLabel + "') or contains(.,'" + btnLabel.toUpperCase() + "')]");
            if (!button.isVisible()) {
                throw new AssertionError("Expected to see button with label: " + btnLabel);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to validate presence of button: " + btnLabel, e);
        }
    }
}
