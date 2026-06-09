package bullethell.currencysystem;

public class CurrencyBank {
    // Singleton object to manage player's funds
    private static CurrencyBank bank;
    // Current value, rounded to two decimals to avoid hell
    private float value;

    private CurrencyBank() {
        // TODO: find a way to store the latest value, in a config file perhaps? (optional)
        this.value = readFromFile();
    }

    public static CurrencyBank getInstance() {
        if (CurrencyBank.bank == null) {
            CurrencyBank.bank = new CurrencyBank();
        }
        return CurrencyBank.bank;
    }

    public void purchase(float cost) throws InsufficientFundsException {
        if (this.hasEnoughFunds(cost)) {
            this.value -= cost;
            this.saveFunds();
        } else {
            throw new InsufficientFundsException("Not enough money");
        }
    }

    public void addFunds(float amount) {
        this.value += amount;
        this.saveFunds();
    }

    private boolean hasEnoughFunds(float cost) {
        return this.value >= cost;
    }

    private void saveFunds() {
        // TODO: write to some offline storage for the player to resume their game (optional)
    }

    public float getValue() {
        return this.value;
    }

    private float readFromFile() {
        return 100; //stole this function to setup the starting score
    }
}
