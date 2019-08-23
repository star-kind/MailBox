package mapper;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import dao.AccountDAO;
import entity.Account;

/**
 * DAO层测试类
 * 
 * @author gzh
 *
 */
public class ORMAccountTest {

    @Test
    public void insertAccountTest() {
	Account account = new Account();
	AccountDAO dao = new AccountDAO();

	account.setAcname("wdff");
	account.setEmail("wdf@qq.com");
	account.setGender(0);
	account.setPassword("uee++1");
	account.setSalt("dopfcbfss");
	account.setRegTime(new Date());
	account.setBirth(new Date());

	Integer integer = dao.insertNewAccount(account);

	System.out.println(integer);
    }

    @Test
    public void selectAllAccountTest() {
	AccountDAO dao = new AccountDAO();
	List<Account> list = dao.selectAllAccount();

	for (Account account : list) {
	    System.out.println(":" + account.toString());
	}

    }

    @Test
    public void selectAllByAcnameTest() {
	AccountDAO dao = new AccountDAO();
	Account ac = dao.selectAccountByAcname("户碘钨灯");
	System.err.println(ac);
    }

    @Test
    public void updatePwdByAcidTest() {
	AccountDAO dao = new AccountDAO();
	Integer affact = dao.updatePasswordWhereAcid(2, "9995555");
	System.out.println(affact);
    }

    @Test
    public void selectAllTest() {
	AccountDAO dao = new AccountDAO();

	List<Account> list = dao.selectAllAccount();

	for (Account account : list) {
	    System.err.println(account.toString());
	}

    }

    @Test
    public void deleteAccountTest() {
	AccountDAO dao = new AccountDAO();

	int affects = dao.deleteAccountByAcid(2);

	System.out.println(affects);
    }

}
