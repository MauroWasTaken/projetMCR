package bullethell.currencysystem;

public class CurrencyBank {
    // Singleton object to manage player's funds
    private CurrencyBank bank;
    // Current value, rounded to two decimals to avoid hell
    private float value;

    private CurrencyBank() {
        // TODO: find a way to store the latest value, in a config file perhaps? (optional)
        this.value = readFromFile();
    }

    CurrencyBank getInstance() {
        if (this.bank == null) {
            this.bank = new CurrencyBank();
        }
        return this.bank;
    }

    void purchase(float cost) throws InsufficientFundsException {
        if (this.hasEnoughFunds(cost)) {
            this.value -= cost;
            this.saveFunds();
        } else {
            throw new InsufficientFundsException("Not enough money");
        }
    }

    void addFunds(float amount) {
        this.value += amount;
        this.saveFunds();
    }

    private boolean hasEnoughFunds(float cost) {
        return this.value >= cost;
    }

    private void saveFunds() {
        // TODO: write to some offline storage for the player to resume their game (optional)
    }

    private float readFromFile() {
        return 0; // TODO: optional
    }
}
