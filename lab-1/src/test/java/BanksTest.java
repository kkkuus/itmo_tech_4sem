import accounts.Account;
import banks.Bank;
import banks.BankConditions;
import banks.CentralBank;
import clients.Client;
import clients.ClientAddress;
import clients.ClientName;
import clients.ClientPassport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tools.BanksException;
import transactions.TransactionMoney;

import java.util.ArrayList;
import java.util.List;

class BanksTest {
    CentralBank centralBank = CentralBank.getInstance();

    @Test
    public void createBankAndClient() {
        BankConditions bankConditions = new BankConditions(10000, 3, 50, 500000, new ArrayList<>(List.of(1.0, 2.0, 3.0)),  new ArrayList<>(List.of(50000.0, 100000.0)));
        centralBank.createBank("Tinkoff", bankConditions);
        Assertions.assertEquals("Tinkoff", centralBank.getBanks().get(0).getName());
        Bank bank = centralBank.getBanks().get(0);
        ClientName clientName = new ClientName("Кусайкина", "Елизавета");
        ClientPassport clientPassport = new ClientPassport(1234, 123456);
        Client client = bank.createClient(clientName, null, clientPassport);
        Assertions.assertEquals("Кусайкина Елизавета", client.getName().getFullName());
        Assertions.assertEquals("Кусайкина Елизавета", centralBank.getBanks().get(0).getClients().get(0).getName().getFullName());
        Assertions.assertNull(centralBank.getBanks().get(0).getClients().get(0).getAddress());
        Assertions.assertFalse(centralBank.getBanks().get(0).getClients().get(0).isVarified());
        ClientAddress address = new ClientAddress("Saint Petersburg", "Kronverkskiy prospect", 1, 2);
        centralBank.getBanks().get(0).getClients().get(0).addAddress(address);
        Assertions.assertTrue(centralBank.getBanks().get(0).getClients().get(0).isVarified());
        centralBank.clearBanks();
        centralBank.clearTransactions();
    }

    @Test
    public void createAccounts() {
        BankConditions bankConditions = new BankConditions(10000, 3, 50, 500000, new ArrayList<>(List.of(1.0, 2.0, 3.0)), new ArrayList<>(List.of(50000.0, 100000.0)));
        centralBank.createBank("Tinkoff", bankConditions);
        Bank bank = centralBank.getBanks().get(0);
        ClientName clientName = new ClientName("Кусайкина", "Елизавета");
        ClientPassport clientPassport = new ClientPassport(1234, 123456);
        bank.createClient(clientName, null, clientPassport);
        Account account = bank.createAccount(centralBank.getBanks().get(0).getClients().get(0), 12, 1000, "credit");

        Assertions.assertTrue(centralBank.getBanks().get(0).getAccounts().contains(account));
        Assertions.assertTrue(centralBank.getBanks().get(0).getClients().get(0).getAccounts().contains(account));
        account = bank.createAccount(centralBank.getBanks().get(0).getClients().get(0), 12, 1000, "debit");

        Assertions.assertTrue(centralBank.getBanks().get(0).getAccounts().contains(account));
        Assertions.assertTrue(centralBank.getBanks().get(0).getClients().get(0).getAccounts().contains(account));
        account = bank.createAccount(centralBank.getBanks().get(0).getClients().get(0), 12, 500000, "deposit");

        Assertions.assertTrue(centralBank.getBanks().get(0).getAccounts().contains(account));
        Assertions.assertTrue(centralBank.getBanks().get(0).getClients().get(0).getAccounts().contains(account));
        centralBank.clearBanks();
        centralBank.clearTransactions();
    }

    @Test
    public void differentOperationsWithCreditAccount() {
        BankConditions bankConditions = new BankConditions(10000, 3, 50, 500000, new ArrayList<>(List.of(1.0, 2.0, 3.0)), new ArrayList<>(List.of(50000.0, 100000.0)));
        centralBank.createBank("Tinkoff", bankConditions);
        Bank bank = centralBank.getBanks().get(0);
        ClientName clientName = new ClientName("Кусайкина", "Елизавета");
        ClientPassport clientPassport = new ClientPassport(1234, 123456);
        bank.createClient(clientName, null, clientPassport);
        Account account = bank.createAccount(centralBank.getBanks().get(0).getClients().get(0), 12, 1000, "credit");

        account.addMoney(5000);
        Assertions.assertEquals(505000, account.balance());
        account.takeOffMoney(10000);
        Assertions.assertEquals(494950, account.balance());
        Assertions.assertThrows(BanksException.class,
                ()->{
                    account.takeOffMoney(80000);
                });
        ClientAddress address = new ClientAddress("Saint Petersburg", "Kronverkskiy prospect", 1, 2);
        centralBank.getBanks().get(0).getClients().get(0).addAddress(address);
        account.takeOffMoney(80000);
        Assertions.assertEquals(414900, account.balance());
        centralBank.clearBanks();
        centralBank.clearTransactions();
    }

    @Test
    public void differentOperationsWithDebitAccount() {
        BankConditions bankConditions = new BankConditions(10000, 3, 50, 500000, new ArrayList<>(List.of(1.0, 2.0, 3.0)), new ArrayList<>(List.of(50000.0, 100000.0)));
        centralBank.createBank("Tinkoff", bankConditions);
        Bank bank = centralBank.getBanks().get(0);
        ClientName clientName = new ClientName("Кусайкина", "Елизавета");
        ClientPassport clientPassport = new ClientPassport(1234, 123456);
        bank.createClient(clientName, null, clientPassport);
        Account account = bank.createAccount(centralBank.getBanks().get(0).getClients().get(0), 12, 1000, "debit");

        account.addMoney(20000);
        Assertions.assertEquals(20000, account.balance());
        Assertions.assertThrows(BanksException.class,
                ()->{
                    account.takeOffMoney(80000);
                });
        ClientAddress address = new ClientAddress("Saint Petersburg", "Kronverkskiy prospect", 1, 2);
        centralBank.getBanks().get(0).getClients().get(0).addAddress(address);
        account.takeOffMoney(11000);
        Assertions.assertEquals(9000, account.balance());
        account.daysPassed(30);
        double tempValue = (90 * 30 * (3.0 / 365.0)) + 9000;
        Assertions.assertEquals(tempValue, account.balance());
        centralBank.clearBanks();
        centralBank.clearTransactions();
    }

    @Test
    public void differentOperationsWithDepositAccount() {
        BankConditions bankConditions = new BankConditions(10000, 3, 50, 500000, new ArrayList<>(List.of(1.0, 2.0, 3.0)), new ArrayList<>(List.of(50000.0, 100000.0)));
        centralBank.createBank("Tinkoff", bankConditions);
        Bank bank = centralBank.getBanks().get(0);
        ClientName clientName = new ClientName("Кусайкина", "Елизавета");
        ClientPassport clientPassport = new ClientPassport(1234, 123456);
        bank.createClient(clientName, null, clientPassport);
        Account account = bank.createAccount(centralBank.getBanks().get(0).getClients().get(0), 12, 70000, "deposit");

        Assertions.assertEquals(2, account.getPercent());
        Assertions.assertThrows(BanksException.class,
                ()->{
                    account.takeOffMoney(1000);
                });
        account.daysPassed(30);
        double tempValue = (700 * 30 * (2.0 / 365.0)) + 70000;
        Assertions.assertEquals(tempValue, account.balance());
        account.daysPassed(332);
        account.takeOffMoney(1000);
        tempValue = (700 * 30 * (2.0 / 365.0) * 12) + 70000 - 1000;
        Assertions.assertEquals(String.format("%.10f", tempValue), String.format("%.10f", account.balance()));
        centralBank.clearBanks();
        centralBank.clearTransactions();
    }

    @Test
    public void transactionsMoney() {
        BankConditions bankConditions = new BankConditions(10000, 3, 50, 500000, new ArrayList<>(List.of(1.0, 2.0, 3.0)), new ArrayList<>(List.of(50000.0, 100000.0)));
        centralBank.createBank("Tinkoff", bankConditions);
        Bank bank1 = centralBank.getBanks().get(0);
        ClientName clientName = new ClientName("Kusaikina", "Elizaveta");
        ClientPassport clientPassport = new ClientPassport(1234, 123456);
        bank1.createClient(clientName, null, clientPassport);
        Account account1 = bank1.createAccount(centralBank.getBanks().get(0).getClients().get(0), 12, 1000, "debit");

        bankConditions = new BankConditions(5000, 3, 100, 100000, new ArrayList<>(List.of(2.0, 3.0, 5.0)), new ArrayList<>(List.of(100000.0, 500000.0)));
        centralBank.createBank("Sberbank", bankConditions);
        Bank bank2 = centralBank.getBanks().get(1);
        clientName = new ClientName("Kusaikina", "Elizaveta");
        bank2.createClient(clientName, null, null);
        Account account2 = bank2.createAccount(centralBank.getBanks().get(0).getClients().get(0), 12, 1000, "debit");

        bank1.depositCash(account1, 5000);
        Assertions.assertEquals(5000, account1.balance());
        bank1.withdrawalCash(account1, 1000);
        Assertions.assertEquals(4000, account1.balance());
        centralBank.transfer(account1, account2, 1000);
        Assertions.assertEquals(3000, account1.balance());
        Assertions.assertEquals(1000, account2.balance());
        Assertions.assertFalse(centralBank.getTransactions().isEmpty());
        Assertions.assertFalse(bank1.getTransactions().isEmpty());
        TransactionMoney tempTransaction = centralBank.getTransactions().get(0);
        centralBank.cancelTransaction(tempTransaction, account2);
        Assertions.assertEquals(4000, account1.balance());
        Assertions.assertEquals(0, account2.balance());
        Assertions.assertTrue(centralBank.getTransactions().isEmpty());
        centralBank.clearBanks();
        centralBank.clearTransactions();
    }

    @Test
    public void changeConditionsAndNotifyingClients() {
        BankConditions bankConditions = new BankConditions(10000, 3, 50, 500000, new ArrayList<>(List.of(1.0, 2.0, 3.0)), new ArrayList<>(List.of(50000.0, 100000.0)));
        centralBank.createBank("Tinkoff", bankConditions);
        Bank bank = centralBank.getBanks().get(0);
        ClientName clientName = new ClientName("Кусайкина", "Елизавета");
        ClientPassport clientPassport = new ClientPassport(1234, 123456);
        bank.createClient(clientName, null, clientPassport);
        Client client = bank.getClients().get(0);
        Assertions.assertFalse(client.isSubscribe());
        client.changeSubscribe(true);
        Assertions.assertTrue(client.isSubscribe());
        bank.changeCreditConditions(100, 100000);
        Assertions.assertEquals(100, bank.getConditions().getCreditCommission());
        Assertions.assertEquals(100000, bank.getConditions().getCreditLimit());
        Assertions.assertFalse(client.getUpdates().isEmpty());
        centralBank.clearBanks();
        centralBank.clearTransactions();
    }
}