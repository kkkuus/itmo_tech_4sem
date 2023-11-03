package clients;

import tools.BanksException;

/**
 * Класс ClientBuilder реализующий интерфейс Builder, который создает нового клиента.
 * {@link clients.Builder}
 */
public class ClientBuilder implements Builder {
    /**
     * Минимально допустимое значение id.
     */
    private static final int MINIMUM_ID = 1;
    private ClientName clientName = new ClientName("Surname", "Name");
    private int clientId;
    private ClientAddress clientAddress;
    private ClientPassport clientPassport;

    @Override
    public void setFullName(ClientName clientName) {
        if (clientName == null) {
            throw new BanksException("Invalid full name!");
        }
        this.clientName = clientName;
    }

    @Override
    public void setId(int clientId) {
        if (clientId < MINIMUM_ID) {
            throw new BanksException("Incorrect ID!");
        }
        this.clientId = clientId;
    }

    @Override
    public void setFullAddress(ClientAddress clientAddress) {
        this.clientAddress = clientAddress;
    }

    @Override
    public void setPassport(ClientPassport clientPassport) {
        this.clientPassport = clientPassport;
    }

    @Override
    public Client getClient() {
        return new Client(clientName, clientId, clientAddress, clientPassport);
    }
}
