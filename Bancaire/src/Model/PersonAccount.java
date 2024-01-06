package Model;

public class PersonAccount {
    private Person person;
    private Account account;

    public PersonAccount(Person person, Account account) {
        this.person = person;
        this.account = account;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }


    
}