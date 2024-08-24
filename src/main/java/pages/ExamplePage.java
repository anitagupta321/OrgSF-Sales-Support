package pages;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.PageElementUtil;

/**
 * TODO: This class represents a web page.
 * It should contain web elements and actions to interact with this page.
 */
public class ExamplePage extends PageElementUtil {

  private static Map<String, ExamplePage> instanceMap = new HashMap<>();
  private transient WebDriver driver;

  /**
   * Get instance of ExamplePage.
   *
   * @return ExamplePage instance
   */
  public static ExamplePage getInstance() {
    if (!instanceMap.containsKey("instance")) {
      instanceMap.put("instance", new ExamplePage());
    }
    return instanceMap.get("instance");
  }

  /**
   * Set the web driver for this page.
   *
   * @param driver WebDriver to use
   */
  @Override
  public void setDriver(WebDriver driver) {
    this.driver = driver;
    super.setDriver(driver);
    PageFactory.initElements(driver, new ExamplePage());
  }

  // TODO: Add Web Elements for this page using @FindBy annotation
  @FindBy(xpath = "")
  private static WebElement EXAMPLE_BUTTON;

  /**
   * TODO: Add methods to perform actions on this page
   */
  public void clickExampleButton() {
    click(EXAMPLE_BUTTON);
  }

  public void goToUrl(String url) {
    driver.get(url);
  }
}
