package accounts;

import banks.Bank;
import clients.Client;
import org.jetbrains.annotations.NotNull;
import tools.BanksException;

/**
 * Класс CreditAccount, наследуюмый от Account, для работы с кредитным счетом.
 * {@link accounts.Account}
 */
public class CreditAccount implements Account {
    /**
     * Максимально недопустимое значение процента для кредитной карты.
     */
    private static final int CREDIT_PERCENT = 0;
    /**
     * Максильно недопустимое значение на сумму кредита и размер комиссии.
     */
    private static final double MINIMUM_VALUE_OF_COMMISSION_OR_MONEY = 0;
    /**
     * Минимально допустимое значение id.
     */
    private static final int MINIMUM_VALUE_OF_ID = 0;
    private double money;
    private double lastTransaction;
    private double creditLimit;
    private double bankCommission;
    private double limitForDoubtfulAccount;
    private Bank accountBank;
    private Client accountClient;
    private int id;

    /**
     * Конструктор, в котором инициализируются поля accountBank, accountClient, id.
     * Инициализируются поля, связанные с кредитными условиями, через методы bank'а.
     * Клиенту client добавляется новый кредитный счет.
     * @param bank              банк, в котором открывается кредитный счет
     * @param client            клиент, открывший новый счет
     * @param id                id счета
     * @throws BanksException   проверка на корректность передаваемых данных
     */

    public CreditAccount(Bank bank, Client client, int id) {
        if (bank == null) {
            throw new BanksException("Incorrect value of bank!");
        }
        if (client == null) {
            throw new BanksException("Incorrect value of client");
        }
        if (id < MINIMUM_VALUE_OF_ID) {
            throw new BanksException("Incorrect value of id!");
        }
        this.accountBank = bank;
        this.accountClient = client;
        this.id = id;
        this.creditLimit = bank.getConditions().getCreditLimit();
        this.money += creditLimit;
        this.bankCommission = bank.getConditions().getCreditCommission();
        this.limitForDoubtfulAccount = bank.getConditions().getLimitForDoubtfulAccount();
        client.addAccount(this);
    }
    public Client getAccountClient() {
        return accountClient;
    }
    public double getCommission() {
        return bankCommission;
    }
    public double getLimit() {
        return creditLimit;
    }
    @Override
    public double balance() { return money; }
    @Override
    public Bank getBank() {
        return accountBank;
    }
    @Override
    public int id() {
        return id;
    }
    @Override
    public void addMoney(double newMoney) {
        if (newMoney <-MINIMUM_VALUE_OF_COMMISSION_OR_MONEY) {
            throw new BanksException("You haven't added money!");
        }
        money += newMoney;
    }
    @Override
    public void takeOffMoney(double removeMoney) {
        if (removeMoney <= MINIMUM_VALUE_OF_COMMISSION_OR_MONEY) {
            throw new BanksException("Incorrect withdrawal amount!");
        }
        if (!accountClient.isVarified() && removeMoney > limitForDoubtfulAccount) {
            throw new BanksException("Your account isn't varified!");
        }
        if (removeMoney + bankCommission > money) {
            throw new BanksException("You have exceeded the limit!");
        }
        money -= removeMoney;
        if (money < creditLimit) {
            money -= bankCommission;
        }
    }
    @Override
    public void daysPassed(int countDays) {}
    @Override
    public double getPercent() {
        return CREDIT_PERCENT;
    }
    @Override
    public void lastTransaction(double money) {
        lastTransaction = money;
    }
    @Override
    public double getLastTransaction() {
        return lastTransaction;
    }
}