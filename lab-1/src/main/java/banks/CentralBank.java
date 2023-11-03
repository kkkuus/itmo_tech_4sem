package banks;

import accounts.Account;
import tools.BanksException;
import transactions.TransactionMoney;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Класс, описывающий работу центрального банка, который управляет всеми банками.
 */
public class CentralBank {
    /**
     * Минимально допустимое значение для суммы денег.
     */
    private static final double MINIMUM_VALUE_OF_MONEY = 0;
    /**
     * Минимально допустимое значения для кол-ва прошедших дней.
     */
    private static final int MINIMUM_VALUE_OF_DAYS_COUNT = 0;
    private static CentralBank instance;
    private int bankId = 1;
    private ArrayList<Bank> banks = new ArrayList<Bank>();
    private ArrayList<TransactionMoney> transactions = new ArrayList<TransactionMoney>();
    private ArrayList<Account> accountsTo = new ArrayList<Account>();

    private CentralBank() {}

    public List<Bank> getBanks() {
        return Collections.unmodifiableList(banks);
    }

    public List<TransactionMoney> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    /**
     * Метод, который создает центральный банк, если он еще не создан.
     * Если ЦБ к этому времени уже был создан, то возвращается ранее созданный ЦБ.
     * @return      центральный банк
     */
    public static CentralBank getInstance() {
        if (instance == null) {
            instance = new CentralBank();
        }
        return instance;
    }

    /**
     * Метод, очищающи список банков, которыми управляет ЦБ.
     */
    public void clearBanks() {
        banks.clear();
    }

    /**
     * Метод, очищающий список всех проводимых транзакций.
     */
    public void clearTransactions() {
        transactions.clear();
        accountsTo.clear();
    }

    /**
     * Метод, создающий новый банк.
     * @param newBankName       имя нового банка
     * @param bankConditions    условия счетов нового банка
     * @return                  новый банк
     * @throws BanksException   проверка на корректность передаваемых данных
     */
    public Bank createBank(String newBankName, BankConditions bankConditions) {
        if (bankConditions == null) {
            throw new BanksException("Bank conditions is null");
        }
        if (newBankName.isBlank()) {
            throw new BanksException("Invalid value of bank name!");
        }
        Bank tmpBank = new Bank(newBankName, bankId, bankConditions, this);
        ++bankId;
        banks.add(tmpBank);
        return tmpBank;
    }

    /**
     * Метод, добавляющий банк под управление ЦБ.
     * @param bank              новый банк
     * @throws BanksException   проверка на корректность передаваемых данных
     */
    public void addBank(Bank bank) {
        if (bank == null) {
            throw new BanksException("There isn't a bank!");
        }
        banks.add(bank);
    }

    /**
     * Метод, с помощью которого реализуется транзакция перевода денег между счетами.
     * @param accountFrom       счет, с которого отправили деньги
     * @param accountTo         счет, на который отправили деньги
     * @param money             сумма, которую переводят
     * @throws BanksException   проверка на корректнсоть передаваемых данных
     */
    public void transfer(Account accountFrom, Account accountTo, double money) {
        if (accountFrom == null || accountTo == null) {
            throw new BanksException("Incorrect value of accounts!");
        }
        if (money <= MINIMUM_VALUE_OF_MONEY) {
            throw new BanksException("Incorrect value of money!");
        }
        if (!banks.contains(accountFrom.getBank()) || !banks.contains(accountTo.getBank())) {
            throw new BanksException("There aren't this banks!");
        }
        TransactionMoney transaction = new TransactionMoney(money, accountFrom);
        transaction.transferCash(accountTo);
        transactions.add(transaction);
        accountsTo.add(accountTo);
    }

    /**
     * Метод, отменяющий транзакцию.
     * @param transactionMoney      транзакция, которую хотят отменить
     * @param accountTo             аккаунт с которого будет возвращен перевод
     * @throws BanksException       проверка на корректность передаваемых данных
     */
    public void cancelTransaction(TransactionMoney transactionMoney, Account accountTo) {
        if (!transactions.contains(transactionMoney)) {
            throw new BanksException("Incorrect value of transaction!");
        }
        int tempIndex = transactions.indexOf(transactionMoney);
        transfer(accountTo, transactionMoney.getAccount(), transactionMoney.getMoney());
        transactions.remove(transactionMoney);
        accountsTo.remove(tempIndex);
        transactions.remove(transactions.size() - 1);
        accountsTo.remove(accountsTo.size() - 1);
    }

    /**
     * Метод, позволяющий перемотать некоторое количество дней.
     * @param countOfDays       количество дней, на которое нужно перемотать время
     * @throws BanksException   проверка на корректность передаваемых данных
     */
    public void rewindDays(int countOfDays) {
        if (countOfDays <= MINIMUM_VALUE_OF_DAYS_COUNT) {
            throw new BanksException("Incorrect value of count of days!");
        }
        for (Bank bank : banks) {
            for (Account account : bank.getAccounts()) {
                account.daysPassed(countOfDays);
            }
        }
    }
}
