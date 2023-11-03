package accounts;

import banks.Bank;
import clients.Client;
import org.jetbrains.annotations.NotNull;
import tools.BanksException;
/**
 * Класс CreditAccountCreator, наследуемый от AccountCreator, и предназначенный для создания кредитного счета.
 * {@link accounts.AccountCreator}
 */
public class CreditAccountCreator implements AccountCreator {
    /**
     * Минимально допустимое значение id.
     */
    private static final int MINIMUM_VALUE_OF_ID = 0;
    private Bank bank;
    private Client client;
    private int id;

    /**
     * @param bank              банк, в котором хотят открыть кредитный счет
     * @param client            клиент, который хочет открыть кредитный счет
     * @param id                id счета
     * @throws BanksException   проверка на корректность передаваемых данных
     */

    public CreditAccountCreator(Bank bank, Client client, int id) {
        if (bank == null) {
            throw new BanksException("Incorrect value of bank!");
        }
        if (client == null) {
            throw new BanksException("Incorrect value of client!");
        }
        if (id < MINIMUM_VALUE_OF_ID) {
            throw new BanksException("Incorrect value of id!");
        }
        this.bank = bank;
        this.client = client;
        this.id = id;
    }
    @Override
    public Account create() {
        return new CreditAccount(bank, client, id);
    }
}