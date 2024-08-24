package sobject;

import com.sforce.soap.partner.sobject.SObject;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * TODO: use this class as a reference for creating SObjects
 */
public class Account extends SObject {

  // TODO: update with the valid prefix of your object type
  public static final String validIdPrefix = "001";

  @Getter
  @Setter
  private String customName;

  public Account() {
    // TODO: set correct SObject type here
    setType("Account");
  }

  /**
   * Builds an Example SObject.
   */
  @Builder
  public Account(String type, String customName, String name) {
    if (type == null) {
      type = "Account";
    }
    this.setType(type);
    this.setCustomName(customName);
    this.setAccName(name);
  }

  // TODO: update getters and setters for all relevant fields for your object
  public String getAccName() {
    Object accName = getSObjectField("Name");
    return (accName == null) ? null : accName.toString();
  }

  public final void setAccName(String accName) {
    setSObjectField("Name", accName);
  }
}
