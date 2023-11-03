package clients;

import tools.BanksException;

/**
 * Класс, описывающий имя клиента.
 */
public class ClientName {
    private String surname;
    private String name;

    /**
     * @param surname фамилия клиента
     * @param name    имя клиента
     * @throws BanksException проверка на корректность передаваемых данных
     */
    public ClientName(String surname, String name) {
        if (surname.isBlank()) {
            throw new BanksException("Enter your surname!");
        }
        if (name.isBlank()) {
            throw new BanksException("Enter your name!");
        }
        this.surname = surname;
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return surname + " " + name;
    }
}