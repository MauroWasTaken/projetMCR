package bullethell.gameobjects;

public class Upgrade {
    private final String name;
    private final String description;
    private final int price;
    private final Weapon weapon;
    private final Shield shield;

    // Constructor for a Weapon upgrade
    public Upgrade(String name, String description, int price, Weapon weapon) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.weapon = weapon;
        this.shield = null;
    }

    // Constructor for a Shield upgrade
    public Upgrade(String name, String description, int price, Shield shield) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.shield = shield;
        this.weapon = null;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Shield getShield() {
        return shield;
    }

    public boolean isWeaponUpgrade() {
        return weapon != null;
    }
}
