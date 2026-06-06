package bullethell.currencysystem;

public class CurrencySystem {
    // Every 1000 points = 1 currency
    private static final int SCORE_TO_CURR_DIV = 1000;

    private final CurrencyBank bank;

    public CurrencySystem(CurrencyBank bank) {
        this.bank = bank.getInstance();
    }

    public void pointsToCurrency(int points) {
        if (points <= 0) {
            return;
        }

        // Apparently this rounds to 2 decimal places lol
        this.bank.addFunds((float) Math.round((float) points / SCORE_TO_CURR_DIV * 100) / 100);
    }

    public void expendFunds(float cost) {
        this.bank.purchase(cost);
    }
}
