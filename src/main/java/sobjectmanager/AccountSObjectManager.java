package sobjectmanager;

import com.sforce.soap.partner.sobject.SObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sobject.Account;

/**
 * TODO: This class stores SObject records that are used during test execution.
 * Name: [FunctionalArea]SObjectManager
 * (this example is for the Account Management space)
 */
public class AccountSObjectManager {

  private static Map<String, AccountSObjectManager> instanceMap = new HashMap<>();
  private static final Logger LOGGER = LoggerFactory.getLogger(AccountSObjectManager.class);
  private static Map<String, SObject> activeObjectMap = new HashMap<>();
  private transient List<Account> accountList = new ArrayList<>();
  private static final String INSTANCE = "instance";
  private static final String ACCOUNT = "Account";

  /**
   * Get instance of AccountSObjectManager.
   *
   * @return AccountSObjectManager instance
   */
  public static AccountSObjectManager getInstance() {
    instanceMap.computeIfAbsent(INSTANCE, k -> new AccountSObjectManager());
    return instanceMap.get(INSTANCE);
  }

  /**
   * Create new instance of AccountSObjectManager to reset the stored objects from current execution.
   *
   * @return AccountSObjectManager instance
   */
  public static AccountSObjectManager resetInstance() {
    AccountSObjectManager newInstance = new AccountSObjectManager();
    instanceMap.put(INSTANCE, newInstance);
    return newInstance;
  }

  /**
   * If there is an Account record in use, query and return it.
   *
   * @return Account
   */
  public Account getAccount() {
    return (Account) activeObjectMap.computeIfPresent(ACCOUNT, (key, value) -> {
      Account acc = AccountManagementUtil.refreshAccount((Account) value);
      activeObjectMap.put(key, acc);
      return acc;
    });
  }

  /**
   * If there is an Account record in use with the given Id, query and return it.
   *
   * @param id Id of the Account to retrieve
   * @return Account
   */
  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  public Account getAccountById(String id) {

    for (int i = 0; i < accountList.size(); i++) {
      if (accountList.get(i).getId() != null && accountList.get(i).getId().equals(id)) {
        Account account = AccountManagementUtil.refreshAccount(accountList.get(i));

        if (account == null) {
          accountList.remove(i);
          i--;
        } else {
          activeObjectMap.put(ACCOUNT, account);
          return account;
        }
      }
    }
    return null;
  }

  /**
   * If there is an Account record in use with the given name, query and return it.
   *
   * @param name Record name or custom name of the Account to retrieve
   * @return Account
   */
  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  public Account getAccountByName(String name) {

    for (int i = 0; i < accountList.size(); i++) {
      if ((accountList.get(i).getCustomName() != null && accountList.get(i).getCustomName().equals(name))
          || (accountList.get(i).getAccName() != null && accountList.get(i).getAccName().equals(name))) {
        Account account = AccountManagementUtil.refreshAccount(accountList.get(i));

        if (account == null) {
          accountList.remove(i);
          i--;
        } else {
          activeObjectMap.put(ACCOUNT, account);
          return account;
        }
      }
    }
    return null;
  }

  /**
   * Set the current working Account record.
   *
   * @param account Account record
   */
  @SuppressWarnings("PMD.DataflowAnomalyAnalysis")
  public void setAccount(Account account) {
    if (account == null) {
      return;
    }

    String accountId = account.getId();

    if ((accountId == null) || (accountId.equals(""))) {
      LOGGER.info("The given Account object does not have an object ID. Please create the Account on server and save the object ID first.");
      return;
    }
    if (!accountId.startsWith(Account.validIdPrefix)) {
      LOGGER.info("The given Account object ID {} is not valid. Please give an ID start with {}", accountId, Account.validIdPrefix);
      return;
    }

    boolean acctExists = false;
    int length = accountList.size();

    for (int i = 0; i < length; i++) {
      Account existingAccount = accountList.get(i);
      if (existingAccount.getId().equals(accountId)) {
        acctExists = true;
        if (account.getCustomName() == null && existingAccount.getCustomName() != null) {
          account.setCustomName(existingAccount.getCustomName());
        }
        accountList.set(i, account);
        break;
      }
    }

    if (!acctExists) {
      accountList.add(account);
    }

    activeObjectMap.put(ACCOUNT, account);
  }
}
