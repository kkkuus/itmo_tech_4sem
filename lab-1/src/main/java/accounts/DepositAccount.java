package accounts;

import banks.Bank;
import clients.Client;
import tools.BanksException;

/**
 * Класс DepositAccount, наследуюмый от Account, для работы с депозитным счетом.
 * {@link accounts.Account}
 */
public class DepositAccount implements Account {
    /**
     * Количество дней в году.
     */
    private static final int COUNT_OF_DAYS_IN_A_YEAR = 365;
    /**
     * Количество дней в месяце.
     */
    private static final int COUNT_OF_DAYS_IN_A_MONTH = 30;
    /**
     * Минимально допустимое значение id.
     */
    private static final int MINIMUM_PERIOD_VALUE = 0;
    /**
     * Максимально недопустимое значение суммы, с которой открывает депозитный счет и процентной ставки.
     */
    private static final double MINIMUM_MONEY_AND_PERCENT_COUNT = 0;
    /**
     * Максимально допустимое значение процентной ставки.
     */
    private static final double MAXIMUM_PERCENT = 100;
    /**
     * Минимально допустимое значение id.
     */
    private static final int MINIMUM_VALUE_OF_ID = 0;
    private double money = 0;
    private double accruedPercent;
    private int period;
    private int passedDays = 0;
    private double lastTransaction;
    private int id;
    private double percent;
    private Bank accountBank;
    private Client accountClient;

    /**
     * Конструктор, в котором инициализируются поля accountBank, accountClient, id, money и period.
     * Инициализируются поля, связанные с депозиьтными условиями, через методы bank'а.
     * Клиенту client добавляется новый депозитный счет.
     * Вычисляет процентную ставку и начисляемый процент.
     *
     * @param bank              банк, в котором открывается кредитный счет
     * @param client            клиент, открывший новый счет
     * @param id                id счета
     * @param summ              сумма, на которую открывается депозитный счет
     * @param period            период, на который открывается депозитный счет
     * @throws BanksException   проверка на корректность передаваемых данных
     */
    public DepositAccount(Bank bank, Client client, int id, double summ, int period) {
        if (bank == null) {
            throw new BanksException("Incorrect value of bank!");
        }
        if (client == null) {
            throw new BanksException("Incorrect value of client!");
        }
        if (id < MINIMUM_VALUE_OF_ID) {
            throw new BanksException("Incorrect value of id!");
        }
        if (summ <= MINIMUM_MONEY_AND_PERCENT_COUNT) {
            throw new BanksException("You haven't invested the money!");
        }
        if (period < MINIMUM_PERIOD_VALUE) {
            throw new BanksException("Incorrect period value!");
        }
        this.accountBank = bank;
        this.accountClient = client;
        this.money = summ;
        this.period = period * COUNT_OF_DAYS_IN_A_MONTH;
        this.id = id;
        client.addAccount(this);
        calculatePercent();
        calculateAccruedPercent(percent, summ);
    }

    public Client getAccountClient() {
        return accountClient;
    }

    public double getPercent() {
        return percent;
    }

    public int getPeriod() {
        return period;
    }

    @Override
    public double balance() {
        return money;
    }

    @Override
    public int id() {
        return id;
    }

    /**
     * Метод, вычисляющий процент начисляемого процента, в зависимости от суммы, на которую открыт депозитный счет.
     */
    public void calculatePercent() {
        if (money > accountBank.getConditions().getDepositLimits().get(accountBank.getConditions().getDepositLimits().size() - 1)) {
            percent = accountBank.getConditions().getDepositPercents().get(accountBank.getConditions().getDepositPercents().size() - 1);
        } else {
            for (int i = 0; i < accountBank.getConditions().getDepositLimits().size(); ++i) {
                if (money <= accountBank.getConditions().getDepositLimits().get(i)) {
                    percent = accountBank.getConditions().getDepositPercents().get(i);
                }
            }
        }
    }

    /**
     * Метод, рассчитывающий размер начисляемого процента.
     *
     * @param percent           размер процентной ставки
     * @param summ              сумма, на которую открылся депозитный счет
     * @throws BanksException   проверка на корректность передаваемых данных
     */
    public void calculateAccruedPercent(double percent, double summ) {
        if (percent <= MINIMUM_MONEY_AND_PERCENT_COUNT || summ <= MINIMUM_MONEY_AND_PERCENT_COUNT) {
            throw new BanksException("Incorrect value of percent or summ!");
        }
        accruedPercent = (percent / (MAXIMUM_PERCENT * COUNT_OF_DAYS_IN_A_YEAR)) * summ;
    }

    /**
     * Метод, начисляющий ежеммесячный процент к текущей сумме счета.
     */
    public void accrueMonthlyPercent() {
        if (period < MINIMUM_PERIOD_VALUE) {
            accruedPercent = MINIMUM_MONEY_AND_PERCENT_COUNT;
        }
        money += accruedPercent * COUNT_OF_DAYS_IN_A_MONTH;
    }

    @Override
    public void addMoney(double newMoney) {
        if (newMoney <= MINIMUM_MONEY_AND_PERCENT_COUNT) {
            throw new BanksException("You haven't added any money!");
        }
        money += newMoney;
    }

    @Override
    public void takeOffMoney(double removeMoney) {
        if (period >= MINIMUM_PERIOD_VALUE) {
            throw new BanksException("You cannot take money out of the deposit account!");
        }
        if (removeMoney < MINIMUM_MONEY_AND_PERCENT_COUNT) {
            throw new BanksException("Incorrect withdrawal amount!");
        }
        if (money < removeMoney) {
            throw new BanksException("Not enough money in the account!");
        }
        if (!accountClient.isVarified() && removeMoney > accountBank.getConditions().getLimitForDoubtfulAccount()) {
            throw new BanksException("You can't take off money");
        }
        money -= removeMoney;
    }

    @Override
    public void lastTransaction(double money) {
        lastTransaction = money;
    }

    @Override
    public double getLastTransaction() {
        return lastTransaction;
    }

    @Override
    public void daysPassed(int countDays) {
        passedDays += countDays;
        while (passedDays >= COUNT_OF_DAYS_IN_A_MONTH) {
            accrueMonthlyPercent();
            passedDays -= COUNT_OF_DAYS_IN_A_MONTH;
        }

        period -= countDays;
    }

    @Override
    public Bank getBank() {
        return accountBank;
    }
}
