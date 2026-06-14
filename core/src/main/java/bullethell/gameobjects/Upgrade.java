package bullethell.gameobjects;

import bullethell.gameobjects.supermove.SuperMove;

public class  Upgrade {
    private final String name;
    private final String description;
    private final int price;
    private final Weapon weapon;
    private final Shield shield;
    private final SuperMove superMove;

    // Constructor for a Weapon upgrade
    public Upgrade(String name, String description, int price, Weapon weapon) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.weapon = weapon;
        this.shield = null;
        this.superMove = null;
    }

    // Constructor for a Shield upgrade
    public Upgrade(String name, String description, int price, Shield shield) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.shield = shield;
        this.weapon = null;
        this.superMove = null;
    }

    // Constructor for a SuperMove upgrade
    public Upgrade(String name, String description, int price, SuperMove superMove) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.shield = null;
        this.weapon = null;
        this.superMove = superMove;
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

    public SuperMove getSuper() { return superMove; }

    public boolean isWeaponUpgrade() {
        return weapon != null;
    }

    public boolean isShieldUpgrade() { return shield != null; }

    public boolean isSuperUpgrade() { return superMove != null; }

    public String toString() {
        if (isWeaponUpgrade()) return "[Weapon]";
        else if (isShieldUpgrade()) return "[Shield]";
        else return "[Super Move]";
    }
}
