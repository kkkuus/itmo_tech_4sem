package transactions;

import accounts.Account;
import org.jetbrains.annotations.NotNull;
import tools.BanksException;

/**
 * Класс, описывающий транзакции, проводимые с деньгами на счетах.
 */
public class TransactionMoney {
    /**
     * Максимально недопустимое значение суммы денег для проведения транзакции.
     */
    private static final double MINIMUM_VALUE_OF_MONEY = 0;
    private double money;
    private Account account;

    /**
     * @param money   сумма, с которой проводилась транзакция
     * @param account счет, с которого проводилась транзакция
     * @throws BanksException проверка на корректность передаваемых данных
     */
    @NotNull
    public TransactionMoney(double money, Account account) {
        if (money <= MINIMUM_VALUE_OF_MONEY) {
            throw new BanksException("Incorrect value of money!");
        }
        if (account == null) {
            throw new BanksException("Incorrect value of account!");
        }
        this.money = money;
        this.account = account;
    }

    public double getMoney() {
        return money;
    }

    public Account getAccount() {
        return account;
    }

    /**
     * Метод, проводящий транзакцию снятия денег со счета.
     */
    public void withdrawalCash() {
        account.takeOffMoney(money);
    }

    /**
     * Метод, проводящий транзакцию начисления денег на счет.
     */
    public void depositCash() {
        account.addMoney(money);
    }

    /**
     * Метод, проводящий транзакцию перевода денег с одного счета на другой.
     *
     * @param accountTo счет, на который нужно отправить деньги
     * @throws BanksException проверка на корректность передаваемых данных
     */
    public void transferCash(Account accountTo) {
        if (accountTo == null) {
            throw new BanksException("Incorrect value of account!");
        }
        account.takeOffMoney(money);
        accountTo.addMoney(money);
    }
}