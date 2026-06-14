package bullethell.currencysystem;

/**
 * Currency logic holder. Singleton object to manage player's funds.
 */
public class CurrencyBank {

    private static CurrencyBank bank;
    // Current value, rounded to two decimals to avoid hell
    private float value;

    private CurrencyBank() {
        // TODO: find a way to store the latest value, in a config file perhaps? (optional)
        this.value = 0;
    }

    public static CurrencyBank getInstance() {
        if (CurrencyBank.bank == null) {
            CurrencyBank.bank = new CurrencyBank();
        }
        return CurrencyBank.bank;
    }

    /**
     * Purchase an upgrade
     * @param cost of the upgrade
     * @throws InsufficientFundsException if player has not enough funds
     */
    public void purchase(float cost) throws InsufficientFundsException {
        if (this.hasEnoughFunds(cost)) {
            this.value -= cost;
        } else {
            throw new InsufficientFundsException("Not enough money");
        }
    }

    public void addFunds(float amount) {
        value += amount;
    }

    private boolean hasEnoughFunds(float cost) {
        return this.value >= cost;
    }

    public void reset() {
        this.value = 0;
    }

    public float getValue() {
        return this.value;
    }

}
