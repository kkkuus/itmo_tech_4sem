package clients;

import tools.BanksException;

/**
 * Класс, описывающий паспортные данные клиента.
 */
public class ClientPassport {
    /**
     * Минимально допустимое значение серии паспорта.
     */
    private static final int MINIMUM_VALUE_OF_SERIES = 1000;
    /**
     * Максимально допустимое значение серии паспорта.
     */
    private static final int MAXIMUM_VALUE_OF_SERIES = 9999;
    /**
     * Минимально допустимое значение номера паспорта.
     */
    private static final int MINIMUM_VALUE_OF_NUMBER = 100000;
    /**
     * Максимально допусимое значение номера паспорта.
     */
    private static final int MAXIMUM_VALUE_OF_NUMBER = 999999;
    private int seriesOfPassport;
    private int numberOfPassport;

    /**
     * @param series серия паспорта клиента
     * @param number номер паспорта клиента
     * @throws BanksException проверка на корректность передаваемых данных
     */
    public ClientPassport(int series, int number) {
        if (series < MINIMUM_VALUE_OF_SERIES || series > MAXIMUM_VALUE_OF_SERIES) {
            throw new BanksException("Incorrect series of passport!");
        }
        if (number < MINIMUM_VALUE_OF_NUMBER || number > MAXIMUM_VALUE_OF_NUMBER) {
            throw new BanksException("Incorrect number of passport!");
        }
        this.seriesOfPassport = series;
        this.numberOfPassport = number;
    }

    public int getSeriesOfPassport() {
        return seriesOfPassport;
    }

    public int getNumberOfPassport() {
        return numberOfPassport;
    }
}
