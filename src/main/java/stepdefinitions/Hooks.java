package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import sobjectmanager.AccountSObjectManager;

public class Hooks {

  /**
   * Runs before each test case.
   */
  @Before
  public void before() {
    System.out.println("This will run before the Scenario");
    /*
    * Before each scenario, create a new instance of AccountSObjectManager.
    * This ensures that test data does not carry between test scenarios.
    * */
    AccountSObjectManager.resetInstance();
  }

  /**
   * Runs after each test case.
   */
  @After
  public void after(Scenario scenario) {
    System.out.println("This will run after the Scenario");
    if (scenario.isFailed()) {
      System.out.println("Test failed");
      // Take a screenshot if the test fails - this functionality will be available in nui-utility
    } else {
      System.out.println("Test successful");
    }
  }
}
