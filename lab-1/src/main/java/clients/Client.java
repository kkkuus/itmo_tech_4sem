package clients;

import observer.Observer;
import accounts.Account;
import tools.BanksException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Класс Client, описывающий поведение клиента.
 * Реализует интерфейс Observer, так как является наблюдателем по отношению к банку.
 */

public class Client implements Observer {
    /**
     * Минимально допустимое значение id.
     */
    private static final int MINIMUM_VALUE_OF_ID = 0;
    private ArrayList<Account> accounts = new ArrayList<>();
    private ArrayList<String> updates = new ArrayList<>();
    private Boolean isSubscribe = false;
    private ClientName name;
    private ClientPassport passport;
    private ClientAddress address;
    private int id;

    /**
     * @param name     имя клиента
     * @param id       id клиента
     * @param address  адрес клиента
     * @param passport паспортные данные клиента
     * @throws BanksException проверка на корректность передаваемых данных
     */
    public Client(ClientName name, int id, ClientAddress address, ClientPassport passport) {
        if (name == null) {
            throw new BanksException("Enter your full name!");
        }
        if (id < MINIMUM_VALUE_OF_ID) {
            throw new BanksException("Incorrect value of ID!");
        }
        this.name = name;
        this.address = address;
        this.passport = passport;
        this.id = id;
    }

    public ClientName getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public ClientAddress getAddress() {
        return address;
    }

    private void setAddress(ClientAddress address) {
        this.address = address;
    }

    public ClientPassport getPassport() {
        return passport;
    }

    private void setPassport(ClientPassport passport) {
        this.passport = passport;
    }

    public List<Account> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }

    public List<String> getUpdates() {
        return Collections.unmodifiableList(updates);
    }

    /**
     * Метод, позволяющий узнать, является ли счет проверенным.
     *
     * @return true - если счет проверенный, false - иначе
     */
    public Boolean isVarified() {
        return !(passport == null) && !(address == null);
    }

    /**
     * Метод, позволяющий узнать, подписан ли клиент на оповещения об изменениях в банках.
     *
     * @return true - если клиент является подписчиком, false - иначе.
     */
    public Boolean isSubscribe() {
        return isSubscribe;
    }

    /**
     * Метод, позволяющий поменять статус подписки клиента на оповещения об изменениях в банках.
     *
     * @param subscribeFlag изменяемый статус
     */
    public void changeSubscribe(Boolean subscribeFlag) {
        isSubscribe = subscribeFlag;
    }

    /**
     * Метод, добавляющий адрес клиента.
     *
     * @param newAddress адрес клиента
     * @throws BanksException проверка на корректсность передаваемых данных
     */
    public void addAddress(ClientAddress newAddress) {
        if (newAddress == null) {
            throw new BanksException("Incorrect value address!");
        }
        address = newAddress;
    }

    /**
     * Метод, добавляющий паспортные данные клиента.
     *
     * @param newPassport паспортные данные клиента
     * @throws BanksException проверка на корректность передаваемых данных
     */
    public void addPassport(ClientPassport newPassport) {
        if (newPassport == null) {
            throw new BanksException("Incorrect value of passport!");
        }
        passport = newPassport;
    }

    /**
     * Метод, добавляющий клиенту новый счет.
     *
     * @param newAccount новый счет клиента
     * @throws BanksException проверка на корректность передаваемых данных
     */
    public void addAccount(Account newAccount) {
        if (newAccount == null) {
            throw new BanksException("There isn't account");
        }
        accounts.add(newAccount);
    }

    /**
     * {@link observer.Observer#update(String)}
     */
    @Override
    public void update(String notification) {
        if (notification.isBlank()) {
            throw new BanksException("Incorrect value of new update!");
        }
        updates.add(notification);
    }
}
