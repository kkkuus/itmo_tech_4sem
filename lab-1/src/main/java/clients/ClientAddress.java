package clients;

import tools.BanksException;

/**
 * Класс, описывающий адрес клиента.
 */
public class ClientAddress {
    /**
     * Минимально допустимое значение для номера дома или квартиры клиента.
     */
    private static final int MINIMUM_VALUE_OF_HOUSE_OR_FLAT_NUMBER = 1;
    private String city;
    private String street;
    private int house;
    private int flat;

    /**
     * @param city   город, в котором проживает клиент
     * @param street улица, на которой проживает клиент
     * @param house  номер дома, в котором проживает клиент
     * @param flat   номер квартиры, в которой проживает клиент
     * @throws BanksException проверка на корректность передаваемых данных
     */
    public ClientAddress(String city, String street, int house, int flat) {
        if (city.isBlank()) {
            throw new BanksException("Enter city!");
        }
        if (street.isBlank()) {
            throw new BanksException("Enter street!");
        }
        if (house < MINIMUM_VALUE_OF_HOUSE_OR_FLAT_NUMBER) {
            throw new BanksException("Invalid value of house number!");
        }
        if (flat < MINIMUM_VALUE_OF_HOUSE_OR_FLAT_NUMBER) {
            throw new BanksException("Invalid value of flat number!");
        }
        this.city = city;
        this.street = street;
        this.house = house;
        this.flat = flat;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public int getHouse() {
        return house;
    }

    public int getFlat() {
        return flat;
    }

    public String getAdress() {
        return city + ", " + street + ", " + String.valueOf(house) + ", " + String.valueOf(flat);
    }
}
