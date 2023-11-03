package accounts;

/**
 * Интерфейс AccountCreator, определяющий общую структру для создателей разный аккаунтов.
 */
public interface AccountCreator {
    /**
     * Метод, позволяющий создать счет.
     * @return созданный счет
     */
    Account create();
}

