package banks;

import accounts.Account;
import accounts.AccountFactory;
import clients.Builder;
import clients.Client;
import clients.ClientAddress;
import clients.ClientBuilder;
import clients.ClientName;
import clients.ClientPassport;
import clients.Director;
import observer.Observable;
import tools.BanksException;
import transactions.TransactionMoney;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Класс, описывающий работу банка.
 * Реализует интерфейс Observable, так как является наблюдаемым объектом для клиентов.
 */

public class Bank implements Observable {

    /**
     * Минимально допустимое значение для id.
     */
    private static final int MINIMUM_ID = 1;

    /**
     * Максимально недопустимое значение для суммы и периода.
     */
    private static final int MINIMUM_VALUE_OF_PERIOD_OR_AMOUNT = 0;
    private int clientId = 1;
    private int accountId = 0;
    private int id;
    private ArrayList<Client> clients = new ArrayList<Client>();
    private ArrayList<Account> accounts = new ArrayList<Account>();
    private ArrayList<TransactionMoney> transactions = new ArrayList<TransactionMoney>();
    private AccountFactory accountFactory = new AccountFactory();
    private String name;
    private BankConditions conditions;
    private CentralBank centralBank;


    /**
     * @param bankName          наименование банка
     * @param bankId            id банка
     * @param bankConditions    все условия банка
     * @param centralBank       центральный банк
     * @throws BanksException   проверка на коррректность передаваемых данных
     */
    public Bank(String bankName, int bankId, BankConditions bankConditions, CentralBank centralBank) {
        if (bankName.isBlank()) {
            throw new BanksException("Invalid bank name!");
        }
        if (bankConditions == null) {
            throw new BanksException("Incorrect value of banks conditions!");
        }
        if (bankId < MINIMUM_ID) {
            throw new BanksException("Incorrect value of ID!");
        }
        this.id = bankId;
        this.name = bankName;
        this.conditions = bankConditions;
        this.centralBank = centralBank;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public BankConditions getConditions() {
        return conditions;
    }

    public CentralBank getCentralBank() {
        return centralBank;
    }

    public List<Client> getClients() {
        return Collections.unmodifiableList(clients);
    }

    public List<Account> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }

    public List<TransactionMoney> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    /**
     * Метод, создающий нового клиента в банке.
     * @param clientName        имя нового клиента
     * @param clientAddress     адрес нового клиента
     * @param clientPassport    паспортные данные нового клиента
     * @return                  новый клиент
     */
    public Client createClient(ClientName clientName, ClientAddress clientAddress, ClientPassport clientPassport) {
        Director director = new Director(clientName, clientId, clientAddress, clientPassport);
        Builder builder = new ClientBuilder();
        Client client = director.create(builder);
        clients.add(client);
        ++clientId;
        return client;
    }

    /**
     * Метод, добавлящий клиента в банк.
     * @param newClient         новый клиент банка
     * @throws BanksException   проверка на корректность передаваемых данных
     */
    public void addClient(Client newClient) {
        if (newClient == null) {
            throw new BanksException("There isn't a client!");
        }
        clients.add(newClient);
    }

    /**
     * Метод, добавляющий новый счет в банк.
     * @param newAccount        новый счет
     * @throws BanksException   проверка на корректность передаваемых данных
     */
    public void addAccount(Account newAccount) {
        if (newAccount == null) {
            throw new BanksException("Incorrect value of account!");
        }
        accounts.add(newAccount);
    }

    /**
     * Метод, создающий новый счет в банке.
     * @param client            клиент, который хочет открыть новый счет
     * @param period            период, на который он хочет открыть новый счет
     * @param amount            сумма, на которую он хочет открыть новый счет
     * @param type              тип счета, который хочет открыть клиент
     * @return                  новый счет
     * @throws BanksException   проверка на корректность передаваемых данных
     */
    public Account createAccount(Client client, int period, double amount, String type) {
        if (client == null) {
            throw new BanksException("Incorrect value of client!");
        }
        if (type.isBlank()) {
            throw new BanksException("You didn't enter the account type!");
        }
        if (type != "debit" && type != "deposit" && type != "credit") {
            throw new BanksException("Incorrect value of account!");
        }
        ++accountId;
        Account account = accountFactory.createAccount(this, client, accountId, period, amount, type);
        accounts.add(account);
        return account;
    }

    /**
     * {@link observer.Observable#registerObserver(Client)}
     */
    @Override
    public void registerObserver(Client newClient) {
        if (newClient == null) {
            throw new BanksException("Incorrect value of new client!");
        }
        newClient.changeSubscribe(true);
    }

    /**
     * {@link observer.Observable#removeObserver(Client)}
     */
    @Override
    public void removeObserver(Client oldClient) {
        if (oldClient == null) {
            throw new BanksException("Incorrect value of client!");
        }
        oldClient.changeSubscribe(false);
    }

    /**
     * {@link observer.Observable#notifyObservers()}
     */
    @Override
    public void notifyObservers() {
        for (Client client : clients) {
            if (client.isSubscribe()) {
                client.update("Banks' conditions changed! You can watch difference in official website");
            }
        }
    }

    /**
     * Метод, изменяющий условия для дебетового счета.
     * @param newPercent        новая процентная ставка для дебетового счета
     * @throws BanksException   проверка на корректность передаваемых данных
     */
    public void changeDebitConditions(double newPercent) {
        if (newPercent <= MINIMUM_VALUE_OF_PERIOD_OR_AMOUNT) {
            throw new BanksException("Incorrect value of new percent!");
        }
        conditions.changeDebitConditions(newPercent);
        notifyObservers();
    }

    /**
     * Метод, изменяющий условия для кредитного счета.
     * @param newCommission     новый размер комиссии кредитного счета
     * @param newLimit          новый лимит для кредитного счета
     * @throws BanksException   проверка на корректность передаваемых данных
     */
    public void changeCreditConditions(double newCommission, double newLimit) {
        if (newCommission <= MINIMUM_VALUE_OF_PERIOD_OR_AMOUNT || newLimit <= MINIMUM_VALUE_OF_PERIOD_OR_AMOUNT) {
            throw new BanksException("Incorrect value of new credit conditions!");
        }
        conditions.changeCreditConditions(newCommission, newLimit);
        notifyObservers();
    }

    /**
     * Метод, изменяющий условия для депозитного счета.
     * @param newPercents       новая процентная ставка депозитного счета
     * @param newLimits         новый лимит для депозитного счета
     * @throws BanksException   проверка на корректность передаваемых данных
     */
    public void changeDepositConditions(ArrayList<Double> newPercents, ArrayList<Double> newLimits) {
        if (newPercents.size() < MINIMUM_VALUE_OF_PERIOD_OR_AMOUNT || newLimits.size() < MINIMUM_VALUE_OF_PERIOD_OR_AMOUNT) {
            throw new BanksException("Incorrect value of new deposit conditions!");
        }
        conditions.changeDepositConditions(newPercents, newLimits);
        notifyObservers();
    }

    /**
     * Метод, позволяющий пополнить счет на нужную сумму.
     * @param account           счет, на который положили сумму
     * @param money             сумма, которую положили на счет
     * @throws BanksException   проверка на корректность передаваемых данных
     */
    public void depositCash(Account account, double money) {
        if (account == null || !accounts.contains(account)) {
            throw new BanksException("Incorrect value of client!");
        }
        if (money < MINIMUM_VALUE_OF_PERIOD_OR_AMOUNT) {
            throw new BanksException("Incorrect value of money!");
        }
        TransactionMoney transaction = new TransactionMoney(money, account);
        transaction.depositCash();
        transactions.add(transaction);
    }

    /**
     * Метод, позволяющий снять сумму со счета.
     * @param account           аккаунт, с которого сняли сумму
     * @param money             сумма, которую сняли со счета
     * @throws BanksException   проверка на корректность передаваемых данных
     */
    public void withdrawalCash(Account account, double money) {
        if (account == null || !accounts.contains(account)) {
            throw new BanksException("Incorrect value of client!");
        }
        if (money < MINIMUM_VALUE_OF_PERIOD_OR_AMOUNT) {
            throw new BanksException("Incorrect value of money!");
        }
        TransactionMoney transaction = new TransactionMoney(money, account);
        transaction.withdrawalCash();
        transactions.add(transaction);
    }
}