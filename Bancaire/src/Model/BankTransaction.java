package Model;

public class BankTransaction {
    private int id;
    private String amount;
    private String date;
    private String type;
    private String sourceAccountNumber, targetAccountNumber;
    public BankTransaction(int id, String amount, String date, String type, String sourceAccountNumber,
            String targetAccountNumber) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.type = type;
        this.sourceAccountNumber = sourceAccountNumber;
        this.targetAccountNumber = targetAccountNumber;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getSourceAccountNumber() {
        return sourceAccountNumber;
    }
    public void setSourceAccountNumber(String sourceAccountNumber) {
        this.sourceAccountNumber = sourceAccountNumber;
    }
    public String getTargetAccountNumber() {
        return targetAccountNumber;
    }
    public void setTargetAccountNumber(String targetAccountNumber) {
        this.targetAccountNumber = targetAccountNumber;
    }
    
    
    
}
