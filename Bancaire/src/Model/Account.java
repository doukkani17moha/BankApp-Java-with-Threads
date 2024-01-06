package Model;

public class Account {
    
    private static int nextAccountNumber = 1;
    private String accountNumber, internalAccountNumber;
    private int accountHolder,id;
    private String balance;

    public Account(){

    }
    public Account(int id,String accountNumber, String internalAccountNumber, int accountHolder, String balance) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.internalAccountNumber = internalAccountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    public Account(String accountNumber, String internalAccountNumber,  String balance) {
        this.accountNumber = accountNumber;
        this.internalAccountNumber = internalAccountNumber;
        this.balance = balance;
    }
    
    public Account(String balance) {
        this.balance = balance;
    }

    public Account(String accountNumber, String balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public static int getNextAccountNumber() {
        return nextAccountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getInternalAccountNumber() {
        return internalAccountNumber;
    }

    public int getAccountHolder() {
        return accountHolder;
    }

    public String getBalance() {
        return balance;
    }

    public static void setNextAccountNumber(int nextAccountNumber) {
        Account.nextAccountNumber = nextAccountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setInternalAccountNumber(String internalAccountNumber) {
        this.internalAccountNumber = internalAccountNumber;
    }

    public void setAccountHolder(int accountHolder) {
        this.accountHolder = accountHolder;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Account [id=" + id + ", Account [accountNumber=" + accountNumber + ", internalAccountNumber=" + internalAccountNumber
                + ", accountHolder=" + accountHolder + ", balance=" + balance + "]";
    }

    
}
