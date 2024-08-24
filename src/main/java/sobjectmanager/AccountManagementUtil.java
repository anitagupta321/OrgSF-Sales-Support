package sobjectmanager;

import com.sforce.soap.partner.PartnerConnection;
import com.sforce.soap.partner.QueryResult;
import com.sforce.soap.partner.sobject.SObject;
import com.sforce.ws.ConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sobject.Account;
import util.SalesforceConnectorUtil;

/**
 * TODO: This class contains utilities for a functional area.
 * Name: [FunctionalArea]Util
 * (this example is for the Account Management space)
 * Add queries for any SObjects stored in this repo.
 */
public class AccountManagementUtil {

  private static final Logger LOGGER = LoggerFactory.getLogger(AccountManagementUtil.class);

  /**
   * This method refreshes account.
   *
   * @param account account object
   * @return account object
   */
  public static Account refreshAccount(Account account) {
    String customName = account.getCustomName();
    LOGGER.info("Account name :" + customName);
    String accId = account.getId();

    if ((accId == null) || (accId.equals(""))) {
      LOGGER.warn("The given Account object does not have an object ID. Please create the record on server and save the object ID first.");
      return null;
    }
    if (!accId.startsWith(Account.validIdPrefix)) {
      LOGGER.warn("The given Account object ID {} is not valid. Please give an ID start with {}", accId, Account.validIdPrefix);
      return null;
    }

    account = Account.builder().customName(customName).build();

    try {
      PartnerConnection connection = SalesforceConnectorUtil.getConnection();
      QueryResult result = connection.query("SELECT Name "
          + "FROM Account WHERE Id = '" + accId + "'");
      if (result.getRecords().length > 0) {
        SObject record = result.getRecords()[0];
        account.setId(accId);
        account.setAccName(record.getField("Name") == null ? "" : record.getField("Name").toString());
        AccountSObjectManager.getInstance().setAccount(account);
        return account;
      }
    } catch (ConnectionException e) {
      LOGGER.error(String.format("ConnectionException@refreshAccount %s", e.fillInStackTrace()));
    }
    return null;
  }
}
