import Controller.BankController;
import DAO.AccountDAO;
import DAO.BankTransactionDAO;
import DAO.PersonAccountDAO;
import DAO.PersonDAO;
import View.BankAppGUI;

public class Main{
    public static void main(String[] args) {
        BankAppGUI view = new BankAppGUI();
        AccountDAO pdo = new AccountDAO();
        PersonDAO ppo = new PersonDAO();
        BankTransactionDAO ban = new BankTransactionDAO();
        PersonAccountDAO pao = new PersonAccountDAO();
        new BankController(pdo, pao, ppo, ban, view);
    }

}
