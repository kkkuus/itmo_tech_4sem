package banks;

import tools.BanksException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Метод, описывающий все условия для счетов в банке.
 */
public class BankConditions {
    /**
     * Минимально допустимое значение для условий банка.
     */
    private static final double MINIMUM_VALUE_FOR_BANK_CONDITIONS = 0;
    private double limitForDoubtfulAccount;
    private double debitPercent;
    private double creditCommission;
    private double creditLimit;
    private ArrayList<Double> depositPercents = new ArrayList<Double>();
    private ArrayList<Double> depositLimits = new ArrayList<Double>();

    /**
     * @param limitForDoubtfulAccount   лимит на снятие денег для подозрительного счета
     * @param debitPercent              процентная ставка для дебетового счета
     * @param creditCommission          комиссия для кредитного счета
     * @param creditLimit               лимит для кредитного счета
     * @param depositPercents           процентная ставка для депозитного счета
     * @param depositLimits             лимит для депозитного счета
     * @throws BanksException           проверка передаваемых данных на корректность
     */

    public BankConditions(double limitForDoubtfulAccount, double debitPercent, double creditCommission, double creditLimit, ArrayList<Double> depositPercents, ArrayList<Double> depositLimits) {
        if (limitForDoubtfulAccount < MINIMUM_VALUE_FOR_BANK_CONDITIONS) {
            throw new BanksException("Incorrect value of limit for doubtful account!");
        }
        if (debitPercent <= MINIMUM_VALUE_FOR_BANK_CONDITIONS) {
            throw new BanksException("Incorrect value of debit percent!");
        }
        if (creditCommission <= MINIMUM_VALUE_FOR_BANK_CONDITIONS) {
            throw new BanksException("Incorrect value of credit commission!");
        }
        if (creditLimit <= MINIMUM_VALUE_FOR_BANK_CONDITIONS) {
            throw new BanksException("Incorrect value of credit limit!");
        }
        if (depositPercents.size() <= MINIMUM_VALUE_FOR_BANK_CONDITIONS) {
            throw new BanksException("Incorrect value of count of deposit percents!");
        }
        if (depositLimits.size() != depositPercents.size() - 1) {
            throw new BanksException("Incorrect value of number of deposit limits!");
        }
        this.limitForDoubtfulAccount = limitForDoubtfulAccount;
        this.debitPercent = debitPercent;
        this.creditCommission = creditCommission;
        this.creditLimit = creditLimit;
        this.depositPercents = depositPercents;
        this.depositLimits = depositLimits;
    }

    public double getLimitForDoubtfulAccount() {
        return limitForDoubtfulAccount;
    }

    public double getDebitPercent() {
        return debitPercent;
    }

    public double getCreditCommission() {
        return creditCommission;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public List<Double> getDepositPercents() {
        return Collections.unmodifiableList(depositPercents);
    }

    public List<Double> getDepositLimits() {
        return Collections.unmodifiableList(depositLimits);
    }

    /**
     * Метод, изменяющий условия для дебетового счета.
     * @param newDebitPercent   новая процентная ставака для дебетового счета
     * @throws BanksException   проверка передаваемых данных на корректность
     */
    public void changeDebitConditions(double newDebitPercent) {
        if (newDebitPercent < MINIMUM_VALUE_FOR_BANK_CONDITIONS) {
            throw new BanksException("Incorrect new percent rate!");
        }
        this.debitPercent = newDebitPercent;
    }

    /**
     * Метод, изменяющий условия для кредитного счета.
     * @param newCreditCommission   новый размер комиссии для кредитного счета
     * @param newCreditLimit        новый размер лимита для кредитного счета
     * @throws BanksException       проверка передаваемых данных на корректность
     */
    public void changeCreditConditions(double newCreditCommission, double newCreditLimit) {
        if (newCreditCommission < MINIMUM_VALUE_FOR_BANK_CONDITIONS) {
            throw new BanksException("Incorrect new credit commission");
        }
        if (newCreditLimit < MINIMUM_VALUE_FOR_BANK_CONDITIONS) {
            throw new BanksException("Incorrect new credit limit");
        }
        this.creditLimit = newCreditLimit;
        this.creditCommission = newCreditCommission;
    }

    /**
     * Метод, изменяющий условия депозитного счета
     * @param newDepositPercents    новая процентная ставка для депозитного счета
     * @param newDepositLimits      новый размер лимита для депозитного счета
     * @throws BanksException       проверка передаваемых данных на корректность
     */
    public void changeDepositConditions(ArrayList<Double> newDepositPercents, ArrayList<Double> newDepositLimits) {
        if (newDepositPercents.size() < MINIMUM_VALUE_FOR_BANK_CONDITIONS) {
            throw new BanksException("Incorrect count of percents");
        }
        if (newDepositLimits.size() != newDepositPercents.size() - 1) {
            throw new BanksException("Incorrect count of limits");
        }
        this.depositLimits = newDepositLimits;
        this.depositPercents = newDepositPercents;
    }
}
