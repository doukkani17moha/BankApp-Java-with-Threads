package Controller;

import java.util.List;
import java.awt.event.*;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import DAO.AccountDAO;
import DAO.BankTransactionDAO;
import DAO.PersonAccountDAO;
import DAO.PersonDAO;
import Model.Account;
import Model.BankTransaction;
import Model.Person;
import Model.PersonAccount;
import View.BankAppGUI;

public class BankController {
    private AccountDAO accountDAO;
    private PersonDAO personDAO;
    private PersonAccountDAO personAccountDAO;
    private BankAppGUI bankAppGUI;
    private BankTransactionDAO bankTransactionDAO;

    public BankController (AccountDAO accountDAO, PersonAccountDAO personAccountDAO, PersonDAO personDAO, BankTransactionDAO bankTransactionDAO, BankAppGUI bankAppGUI){
        this.personDAO = personDAO;
        this.accountDAO = accountDAO;
        this.personAccountDAO = personAccountDAO;
        this.bankAppGUI = bankAppGUI;
        this.bankTransactionDAO = bankTransactionDAO;

        bankAppGUI.getButtonAfficher().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent args0) {
                List<PersonAccount> personAccountList = personAccountDAO.getAllPersonAccountInfo();
                bankAppGUI.displayPersonAccounts(personAccountList);
                bankAppGUI.clearTextFields();
                
            }
        });

        bankAppGUI.getButtonAjouter().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent args0) {
                    int id = 0;
                    String nom = bankAppGUI.getNomTextField().getText();
                    String prenom = bankAppGUI.getPrenomTextField().getText();
                    String email = bankAppGUI.getEmailTextField().getText();
                    String phone = bankAppGUI.getPhoneTextField().getText();
                    String balance = bankAppGUI.getBalanceTextField().getText();
                    Person person = new Person(id, nom, prenom, email, phone);
                    personDAO.addPerson(person);
                    Account account = new Account(balance);
                    accountDAO.createAccount(account);
                    bankAppGUI.getButtonAfficher().doClick();
                    bankAppGUI.clearTextFields();
            }
        });

        bankAppGUI.getTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && bankAppGUI.getTable().getSelectedRow() != -1) {
                    int selectedRow = bankAppGUI.getTable().getSelectedRow();
                    String accntnumb = (String) bankAppGUI.getTable().getValueAt(selectedRow, 5);

                    bankAppGUI.getButtonSupprimer().setEnabled(true);
                    ActionListener[] listeners = bankAppGUI.getButtonSupprimer().getActionListeners();
                    for (ActionListener listener : listeners) {
                        bankAppGUI.getButtonSupprimer().removeActionListener(listener);
                    }

                    bankAppGUI.getButtonSupprimer().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent args0) {
                            accountDAO.deleteAccount(accntnumb);
                            bankAppGUI.getButtonAfficher().doClick();
                            bankAppGUI.clearTextFields();
                            bankAppGUI.getButtonSupprimer().setEnabled(false);
                        }
                    });
                    bankAppGUI.getTable().addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyReleased(KeyEvent e) {
                            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                                int id = (Integer) bankAppGUI.getTable().getValueAt(selectedRow, 0);
                                String nom = (String) bankAppGUI.getTable().getValueAt(selectedRow, 1);
                                String prenom = (String) bankAppGUI.getTable().getValueAt(selectedRow, 2);
                                String email = (String) bankAppGUI.getTable().getValueAt(selectedRow, 3);
                                String phone = (String) bankAppGUI.getTable().getValueAt(selectedRow, 4);
                                Person person = new Person(id, nom, prenom, email, phone);
                                personDAO.updatePerson(person);
                                String balance = (String) bankAppGUI.getTable().getValueAt(selectedRow, 7);
                                Account account = new Account(accntnumb, balance);
                                accountDAO.updateAccount(account);
                                bankAppGUI.getButtonAfficher().doClick();
                                bankAppGUI.clearTextFields();
                            }
                        }
                    });

                }
            }
        });

        bankAppGUI.getButtonalltrans().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent args0) {
                List<BankTransaction> bankTransactionsList = bankTransactionDAO.getAllPersonAccountInfo();
                bankAppGUI.displayAllTransac(bankTransactionsList);
                bankAppGUI.clearTextFields();
            }
        });

        bankAppGUI.getButtonTransfert().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent args0) {
                String sourceAccountNumber = bankAppGUI.getSelectedClientAFromComboBox();
                String targetAccountNumber = bankAppGUI.getSelectedClientBFromComboBox();
                double amount = Double.parseDouble(bankAppGUI.getBalanceTrTextField().getText());
        
                if (sourceAccountNumber != null && targetAccountNumber != null && amount > 0) {
                    // Perform the transfer
                    accountDAO.transfer(sourceAccountNumber, targetAccountNumber, amount);
        
                    bankAppGUI.getButtonalltrans().doClick();
                    bankAppGUI.clearTextFields();
                    JOptionPane.showMessageDialog(bankAppGUI, "The Transfert was succesfully added", "Error",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(bankAppGUI, "The Transfert was not succesfully added", "Error",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        bankAppGUI.getButtondepo().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent args0) {
                String sourceAccountNumber = bankAppGUI.getSelectedClientAFromComboBox();
                double amount = Double.parseDouble(bankAppGUI.getBalanceTrTextField().getText());
        
                if (sourceAccountNumber != null && amount > 0) {
                    // Perform the transfer
                    accountDAO.deposit(sourceAccountNumber, amount);
                    bankAppGUI.getButtonalltrans().doClick();
                    bankAppGUI.clearTextFields();
                    JOptionPane.showMessageDialog(bankAppGUI, "The Deposit was succesfully added", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(bankAppGUI, "The Deposit was not succesfully added", "Error",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        bankAppGUI.getButtonwithdr().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent args0) {
                String sourceAccountNumber = bankAppGUI.getSelectedClientAFromComboBox();
                double amount = Double.parseDouble(bankAppGUI.getBalanceTrTextField().getText());
        
                if (sourceAccountNumber != null && amount > 0) {
                    // Perform the transfer
                    accountDAO.withdraw(sourceAccountNumber, amount);
                    bankAppGUI.getButtonalltrans().doClick();
                    bankAppGUI.clearTextFields();
                    JOptionPane.showMessageDialog(bankAppGUI, "The Withdraw was succesfully added", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(bankAppGUI, "The Withdraw was not succesfully added", "Error",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        bankAppGUI.getButtonlogs().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent args0) {
               int status =  bankTransactionDAO.logTransaction("Transaction_Logs.txt");
                if (status == 1) {
                    JOptionPane.showMessageDialog(bankAppGUI, "The Transactions was succesfully Extracted", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(bankAppGUI, "The Transactions was not succesfully Extracted", "Error",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

    }
    
}
