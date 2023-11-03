package clients;

import tools.BanksException;

/**
 * Класс Director, который с помощью builder'а создает клиента.
 */
public class Director {
    /**
     * Минимально допустимое значение id.
     */
    private static final int MINIMUM_ID = 1;
    private ClientName name;
    private ClientAddress address;
    private ClientPassport passport;
    private int id;

    /**
     * @param name     имя клиента
     * @param id       id клиента
     * @param address  адрес клиента
     * @param passport паспорт клиента
     * @throws BanksException проверка на корректность передаваемых данных
     */
    public Director(ClientName name, int id, ClientAddress address, ClientPassport passport) {
        if (name == null) {
            throw new BanksException("Incorrect value of full name!");
        }
        if (id < MINIMUM_ID) {
            throw new BanksException("Incorrect value of Id");
        }
        this.name = name;
        this.id = id;
        this.address = address;
        this.passport = passport;
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

    public ClientPassport getPassport() {
        return passport;
    }

    /**
     * Метод, создающий клиента
     *
     * @param builder создатель клиента
     * @return созданный клиент
     */
    public Client create(Builder builder) {
        builder.setFullName(name);
        builder.setId(id);
        builder.setFullAddress(address);
        builder.setPassport(passport);
        return builder.getClient();
    }
}
