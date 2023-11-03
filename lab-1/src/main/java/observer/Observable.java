package observer;

import clients.Client;

/**
 * Интерфейс, описывающий наблюдаемый объект в паттерне Наблюдатель.
 */
public interface Observable {
    /**
     * Метод, регистрирующий для наблюдаемого объекта нового наблюдателя.
     *
     * @param client новый наблюдатель
     */
    void registerObserver(Client client);

    /**
     * Метод, удалающий наблюдателя у наблюдаемого объекта.
     *
     * @param client наблюдатель, которого нужно удалить из подписки
     */
    void removeObserver(Client client);

    /**
     * Метод, оповещающий всех наблюдателей наблюдаемого объекта о каких-либо изменениях.
     */
    void notifyObservers();
}
