package observer;

/**
 * Интерфейс, описывающий "наблюдателя" для паттерна Наблюдатель.
 */
public interface Observer {
    /**
     * Метод, оповещающий всех наблюдателей об изменениях.
     *
     * @param notification сообщение об изменении в наблюдаемом объекте
     */
    void update(String notification);
}
