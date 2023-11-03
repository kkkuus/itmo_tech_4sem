package accounts;

import banks.Bank;
import clients.Client;
import org.jetbrains.annotations.NotNull;
import tools.BanksException;

/**
 * Класс DebitAccount, наследуюмый от Account, для работы с дебетовым счетом.
 * {@link accounts.Account}
 */
public class DebitAccount implements Account {
    /**
     * Количество дней в году.
     */
    private static final int COUNT_OF_DAYS_IN_YEAR = 365;
    /**
     * Максимально допустимое значение процентной ставки.
     */
    private static final int MAXIMUM_VALUE_OF_PERCENT = 100;
    /**
     * Количество дней в месяце.
     */
    private static final int COUNT_OF_DAYS_IN_MONTH = 30;
    /**
     * Максимально недопустимое значение суммы лежащей на дебетовом счете и процентная ставка для дебетового счета.
     */
    private static final int MINIMUM_MONEY_AND_PERCENT_COUNT = 0;
    /**
     * Минимально допустимое значение id.
     */
    private static final int MINIMUM_VALUE_OF_ID = 0;
    private double money = 0;
    private double percent;
    private double accruedBalance = 0;
    private double lastTransaction;
    private double limitForDoubtfulAccount;
    private int passedDays = 0;
    private int id;
    private Client accountClient;
    private Bank accountBank;

    /**
     * Конструктор, в котором инициализируются поля accountBank, accountClient, id.
     * Инициализируются поля, связанные с дебетовыми условиями, через методы bank'а.
     * Клиенту client добавляется новый дебетовый счет.
     * @param bank              банк, в котором открывается дебетовый счет
     * @param client            клиент, открывший новый счет
     * @param id                id счета
     * @throws BanksException   проверка на корректность передаваемых данных
     */

    public DebitAccount(Bank bank, Client client, int id) {
        if (bank == null) {
            throw new BanksException("Incorrect value of bank!");
        }
        if (client == null) {
            throw new BanksException("Incorrect value of client!");
        }
        if (id < MINIMUM_VALUE_OF_ID) {
            throw new BanksException("Incorrect value of id!");
        }
        this.accountClient = client;
        this.accountBank = bank;
        this.id = id;
        this.percent = bank.getConditions().getDebitPercent();
        this.limitForDoubtfulAccount = bank.getConditions().getLimitForDoubtfulAccount();
        client.addAccount(this);
    }

    public Client getAccountClient() {
        return accountClient;
    }
    public Bank getAccountBank() {
        return accountBank;
    }
    @Override
    public double balance() {
        return money;
    }
    @Override
    public int id() {
        return id;
    }
    @Override
    public void addMoney(double newMoney) {
        if (newMoney < MINIMUM_MONEY_AND_PERCENT_COUNT) {
            throw new BanksException("You haven't added any money!");
        }
        money += newMoney;
    }
    @Override
    public void takeOffMoney(double removeMoney) {
        if (removeMoney <= MINIMUM_MONEY_AND_PERCENT_COUNT) {
            throw new BanksException("Incorrect withdrawal amount!");
        }
        if (money < removeMoney) {
            throw new BanksException("Not enough money in the account!");
        }
        if (!accountClient.isVarified() && removeMoney > limitForDoubtfulAccount) {
            throw new BanksException("Your account isn't varified!");
        }
        money -= removeMoney;
    }
    @Override
    public double getLastTransaction() {
        return lastTransaction;
    }
    @Override
    public void lastTransaction(double money) {
        lastTransaction = money;
    }

    /**
     * Метод, меняющий процентную ставку.
     * @param newPercentRate    значение новой процентной ставки для дебетового счета
     * @throws BanksException   проверка на корректность передаваемых данных
     */
    public void changeThePercentRate(double newPercentRate) {
        if (newPercentRate <= MINIMUM_MONEY_AND_PERCENT_COUNT) {
            throw new BanksException("Incorrect value of percent rate");
        }
        percent = newPercentRate;
    }

    /**
     * Метод, рассчитывающий размер кэшбека за пройденное кол-во дней.
     * @param countDays     пройденное кол-во дней
     */
    public void calculateTheDailyPercentage(int countDays) {
        accruedBalance += (percent / (MAXIMUM_VALUE_OF_PERCENT * COUNT_OF_DAYS_IN_YEAR)) * money * countDays;
    }

    /**
     * Метод, начисляющий на счет накопленный за месяц кэшбек.
     */
    public void chargeMonthlyPercent() {
        money += accruedBalance;
        accruedBalance = MINIMUM_MONEY_AND_PERCENT_COUNT;
    }
    @Override
    public void daysPassed(int countDays) {
        calculateTheDailyPercentage(countDays);
        passedDays += countDays;
        if (passedDays >= COUNT_OF_DAYS_IN_MONTH) {
            chargeMonthlyPercent();
            passedDays -= COUNT_OF_DAYS_IN_MONTH;
        }
    }
    @Override
    public double getPercent() {
        return percent;
    }
    @Override
    public Bank getBank() {
        return accountBank;
    }
}

