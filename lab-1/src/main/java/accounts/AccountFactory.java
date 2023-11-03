package accounts;

import banks.Bank;
import clients.Client;
import tools.BanksException;

/**
 * Класс AccountFactory, который в зависимости от вводимого типа, создает нужный тип аккаунта.
 */
public class AccountFactory {
    /**
     * Минимальное допустимое значение id, требуемое для проверки получаемого id.
     */
    private static final int MINIMUM_ID = 0;
    private Account account;
    private AccountCreator accountCreator;

    /**
     * Метод createAccount, который создает нужный тип аккаунта.
     *
     * @param bank              банк, в котором создается аккаунт
     * @param client            клиент, который создает аккаунт
     * @param id                id аккаунта
     * @param period            на какой период открывается аккаунт
     * @param amount            первоначальная сумма, которую хотят положить на карту
     * @param type              тип желаемого аккаунта
     * @throws BanksException   проверка на корректно передаваемых данных
     * @return                  созданный аккаунт
     */
    public Account createAccount(Bank bank, Client client, int id, int period, double amount, String type) {
        if (bank == null) {
            throw new BanksException("Incorrect value of bank!");
        }
        if (client == null) {
            throw new BanksException("Incorrect value of client!");
        }
        if (id <= MINIMUM_ID) {
            throw new BanksException("Incorrect value of id");
        }
        if (type == null) {
            throw new BanksException("You didn't enter account type!");
        }
        switch (type) {
            case "debit":
                accountCreator = new DebitAccountCreator(bank, client, id);
                account = accountCreator.create();
                break;
            case "deposit":
                accountCreator = new DepositAccountCreator(bank, client, id, period, amount);
                account = accountCreator.create();
                break;
            case "credit":
                accountCreator = new CreditAccountCreator(bank, client, id);
                account = accountCreator.create();
                break;
            default:
                throw new BanksException("Incorrect value of account type!");
        }
        return account;
    }
}

