package clients;

/**
 * Интерфейс, описывающий строителя, позволяющего создать нового клиента.
 */
public interface Builder {
    /**
     * Метод, устанавливающий имя клиента.
     *
     * @param clientName
     */
    void setFullName(ClientName clientName);

    /**
     * Метод, устанавливающий id клиента.
     *
     * @param clientId
     */
    void setId(int clientId);

    /**
     * Метод, устанавливающий адрес клиента.
     *
     * @param clientAddress
     */
    void setFullAddress(ClientAddress clientAddress);

    /**
     * Метод, устанавливающий паспортные данные клиента.
     *
     * @param clientPassport
     */
    void setPassport(ClientPassport clientPassport);

    /**
     * Метод, позволяющий получить созданного клиента.
     *
     * @return созданный клиент
     */
    Client getClient();
}
