package View;

import javax.swing.*;
import javax.swing.table.*;

import DAO.AccountDAO;

import java.util.List;

import Model.BankTransaction;
import Model.PersonAccount;

import java.awt.*;

public class BankAppGUI extends JFrame {

    private JTextField nomClientField, prenomClientField, emailClientField, phoneClientField, balanceClientField, balanceTransField;
    private JTable Tab, Tab2;
    private JPanel P1, P2, P3, Paneau, P4, P5, P6, PaneauTr;
    private JLabel labelnom, labelprenom, labelEmail, labelPhone, labelBalance, labelClientA, labelClientB, labelBalanceTr;
    private JButton Ajouter, Supprimer, Afficher, Transfert, deposit, withdraw, Alltransferts, logs;
    private DefaultTableModel te, te2;
    private JComboBox clientListComboBox, clientListComboBox2;
    private JTabbedPane tabbedPane;

    AccountDAO accountDAO = new AccountDAO();

    public BankAppGUI() {
        setTitle("Banque");
        setSize(1150, 400);
        tabbedPane = new JTabbedPane();
        createTabCompte(tabbedPane, "Compte");
        createTabTransfert(tabbedPane, "Transfert");
        getContentPane().add(tabbedPane);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void initializeButtonsCm() {
        Afficher = new JButton("Afficher");
        Ajouter = new JButton("Ajouter");
        Supprimer = new JButton("Supprimer");
    }

    private void initializeButtonsTr() {
        Transfert = new JButton("Transfert");
        deposit = new JButton("Depot");
        withdraw = new JButton("Retrait");
        Alltransferts = new JButton("All Transactions");
        logs = new JButton("Export All Transactions");
        clientListComboBox = new JComboBox<>();
        clientListComboBox2 = new JComboBox<>();
        updateClientListComboBox();
        updateClient2ListComboBox();
    }

    private void createTabCompte(JTabbedPane tabbedPane, String tabName) {
        // Paneau
        P1 = new JPanel();
        P2 = new JPanel();
        P3 = new JPanel();
        Paneau = new JPanel();
        initializeButtonsCm();
        // Tabs
        te = new DefaultTableModel();
        // Labels
        labelnom = new JLabel("Nom du Client: ");
        labelprenom = new JLabel("Prenom du Client: ");
        labelEmail = new JLabel("Email du Client: ");
        labelPhone = new JLabel("Phone du Client: ");
        labelBalance = new JLabel("Balance: ");
        // TextFiels
        nomClientField = new JTextField(6);
        prenomClientField = new JTextField(6);
        emailClientField = new JTextField(6);
        phoneClientField = new JTextField(6);
        balanceClientField = new JTextField(6);
        // Columns
        Tab = new JTable(te);
        te.addColumn("Id");
        te.addColumn("First Name");
        te.addColumn("Last Name");
        te.addColumn("Email");
        te.addColumn("Phone");
        te.addColumn("Account Number");
        te.addColumn("Int Account Number");
        te.addColumn("Balance");
        // Ordonancement (GridLayout & add)
        P1.setLayout(new GridLayout(2, 1));
        P3.setLayout(new GridLayout(5, 2));
        Paneau.setLayout(new BorderLayout());
        P3.add(labelnom);
        P3.add(nomClientField);
        P3.add(labelprenom);
        P3.add(prenomClientField);
        P3.add(labelEmail);
        P3.add(emailClientField);
        P3.add(labelPhone);
        P3.add(phoneClientField);
        P3.add(labelBalance);
        P3.add(balanceClientField);
        P1.add(P3);
        P2.add(Ajouter);
        P2.add(Supprimer);
        P2.add(Afficher);
        P1.add(P2);
        Paneau.add(new JScrollPane(Tab), BorderLayout.CENTER);
        Paneau.add(P1, BorderLayout.NORTH);
        add(Paneau);
        tabbedPane.addTab(tabName, Paneau);
    }


    private void createTabTransfert(JTabbedPane tabbedPane, String tabName) {
        // Paneau
        P4 = new JPanel();
        P5 = new JPanel();
        P6 = new JPanel();
        PaneauTr = new JPanel();
        initializeButtonsTr();
        // Tabs
        te2 = new DefaultTableModel();
        te2.addColumn("Id");
        te2.addColumn("Amount");
        te2.addColumn("Date");
        te2.addColumn("Transaction Type");
        te2.addColumn("Src Acc Number");
        te2.addColumn("Trg Acc Number");
        // Labels
        labelClientA = new JLabel("Client A: ");
        labelClientB = new JLabel("Client B: ");
        labelBalanceTr = new JLabel("Balance: ");
        // TextFiels
        balanceTransField = new JTextField(6);
        // Columns
        Tab2 = new JTable(te2);

        // Ordonancement (GridLayout & add)
        P4.setLayout(new GridLayout(2, 1));
        P6.setLayout(new GridLayout(3, 2));
        PaneauTr.setLayout(new BorderLayout());
        P6.add(labelClientA);
        P6.add(clientListComboBox);
        P6.add(labelBalanceTr);
        P6.add(balanceTransField);
        P6.add(labelClientB);
        P6.add(clientListComboBox2);
        P4.add(P6);
        P5.add(Transfert);
        P5.add(deposit);
        P5.add(withdraw);
        P5.add(Alltransferts);
        P5.add(logs);
        P4.add(P5);
        PaneauTr.add(new JScrollPane(Tab2), BorderLayout.CENTER);
        PaneauTr.add(P4, BorderLayout.NORTH);
        add(PaneauTr);
        tabbedPane.addTab(tabName, PaneauTr);
    }

    // Getters Of Buttons
    public JButton getButtonAjouter() {
        return Ajouter;
    }

    public JButton getButtonTransfert() {
        return Transfert;
    }

    public JButton getButtonalltrans() {
        return Alltransferts;
    }

    public JButton getButtonAfficher() {
        return Afficher;
    }

    public JButton getButtonSupprimer() {
        return Supprimer;
    }


    public JButton getButtonlogs() {
        return logs;
    }

    public JButton getButtondepo() {
        return deposit;
    }

    public JButton getButtonwithdr() {
        return withdraw;
    }

    public JTable getTable() {
        return Tab;
    }

    // Getters Of TextFields
    public JTextField getNomTextField() {
        return nomClientField;
    }

    public JTextField getPrenomTextField() {
        return prenomClientField;
    }

    public JTextField getEmailTextField() {
        return emailClientField;
    }

    public JTextField getPhoneTextField() {
        return phoneClientField;
    }

    public JTextField getBalanceTextField() {
        return balanceClientField;
    }
    public JTextField getBalanceTrTextField() {
        return balanceTransField;
    }

    // function to cleartext
    public void clearTextFields() {
        nomClientField.setText("");
        prenomClientField.setText("");
        emailClientField.setText("");
        phoneClientField.setText("");
        balanceClientField.setText("");
    }

    public void updateClientListComboBox() {
        List<String> persontNames = accountDAO.getAllAccounts();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(persontNames.toArray(new String[0]));
        clientListComboBox.setModel(model);
    }
    public void updateClient2ListComboBox() {
        List<String> persontNames = accountDAO.getAllAccounts();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(persontNames.toArray(new String[0]));
        clientListComboBox2.setModel(model);
    }

    public void displayPersonAccounts(List<PersonAccount> personAccounts) {
        te.setRowCount(0); // Clear existing rows
        for (PersonAccount personAccount : personAccounts) {
            // Assuming you have getters for the PersonAccount class
            int personId = personAccount.getPerson().getId();
            String firstName = personAccount.getPerson().getFirstName();
            String lastName = personAccount.getPerson().getLastName();
            String email = personAccount.getPerson().getEmail();
            String phone = personAccount.getPerson().getPhoneNumber();
            String accountNumber = personAccount.getAccount().getAccountNumber();
            String internalAccountNumber = personAccount.getAccount().getInternalAccountNumber();
            String balance = personAccount.getAccount().getBalance();

            Object[] rowData = { personId, firstName, lastName, email, phone, accountNumber, internalAccountNumber,
                    balance };
            te.addRow(rowData);
        }
    }

    public void displayAllTransac(List<BankTransaction> transactions) {
        te2.setRowCount(0); // Clear existing rows
        for (BankTransaction i : transactions) {
            // Assuming you have getters for the i class
            int id = i.getId();
            String ammount = i.getAmount();
            String date = i.getDate();
            String type = i.getType();
            String src = i.getSourceAccountNumber();
            String trg = i.getTargetAccountNumber();

            Object[] rowData = { id, ammount, date, type, src, trg };
            te2.addRow(rowData);
        }
    }

    public String getSelectedClientAFromComboBox() {
        Object selectedObject = clientListComboBox.getSelectedItem();
        return (selectedObject != null) ? selectedObject.toString() : null;
    }

    public String getSelectedClientBFromComboBox() {
        Object selectedObject = clientListComboBox2.getSelectedItem();
        return (selectedObject != null) ? selectedObject.toString() : null;
    }
}
