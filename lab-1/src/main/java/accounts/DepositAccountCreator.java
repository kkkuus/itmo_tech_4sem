package accounts;

import banks.Bank;
import clients.Client;
import org.jetbrains.annotations.NotNull;
import tools.BanksException;
public class DepositAccountCreator implements AccountCreator {
    /**
     * Минимально допустимое значение id.
     */
    private static final int MINIMUM_VALUE_OF_ID = 0;
    /**
     * Максимально недопустимый период, на который открывается депозитный счет.
     */
    private static final int MINIMUM_PERIOD = 0;
    /**
     * Максимально недопустимая сумма, на которую открывается депозитный счет.
     */
    private static final double MINIMUM_AMOUNT = 0;
    private Bank bank;
    private Client client;
    private int period;
    private double amount;
    private int id;

    /**
     * @param bank              банк, в котором открывается депозитный счет
     * @param client            клиент, который хочет открыть депозитный счет
     * @param id                id счета
     * @param period            период, на который хотят открыть депозитный счет
     * @param amount            сумма, на которую хотят открыть депозитный счет
     * @throws BanksException   проверка на корректность передаваемых данных
     */

    public DepositAccountCreator(Bank bank, Client client, int id, int period, double amount) {
        if (bank == null) {
            throw new BanksException("Incorrect value of bank!");
        }
        if (client == null) {
            throw new BanksException("Incorrect value of client!");
        }
        if (id <= MINIMUM_VALUE_OF_ID) {
            throw new BanksException("Incorrect value of id!");
        }
        if (period <= MINIMUM_PERIOD) {
            throw new BanksException("Incorrect value of period!");
        }
        if (amount <= MINIMUM_AMOUNT) {
            throw new BanksException("Incorrect value of amount!");
        }
        this.bank = bank;
        this.client = client;
        this.period = period;
        this.amount = amount;
        this.id = id;
    }

    @Override
    public Account create() {
        return new DepositAccount(bank, client, id, amount, period);
    }
}