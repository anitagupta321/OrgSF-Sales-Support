package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.ExamplePage;
import sobject.Account;
import sobjectmanager.AccountSObjectManager;
import util.SalesforceApiUtil;

/**
 * TODO: This class contains step definitions for a team's functionality.
 * Name: [Functionality]Steps
 */
public class AccountCreationSteps {

  /**
   * Create an Account record and store it in AccountSObjectManager.
   */
  @When("I create an Account")
  public void createAccount() {
    Account account = Account.builder()
        .customName("Custom-name")
        .name("Test")
        .build();

    SalesforceApiUtil.apiCreateObject(account);
    AccountSObjectManager.getInstance().setAccount(account);
  }

  /**
   * Validate that the account Id was populated.
   */
  @Then("the Account Id is not null")
  public void validateAccountIdNotNull() {
    Account account = AccountSObjectManager.getInstance().getAccount();
    Assert.assertNotNull(account.getId(), "The Account Id should not be null.");
    System.out.println("Account Id: " + account.getId());
  }

  /**
   * Click button on Example page.
   */
  @Given("I click Example button on Example page")
  public void clickExampleButton() {
    ExamplePage.getInstance().clickExampleButton();
  }
}
