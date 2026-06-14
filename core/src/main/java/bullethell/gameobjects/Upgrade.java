package bullethell.gameobjects;

import bullethell.gameobjects.supermove.SuperMove;

import java.util.function.Supplier;

public class  Upgrade {
    private final String name;
    private final String description;
    private final int price;
    private final Supplier<Weapon> weaponFactory;
    private final Supplier<Shield> shieldFactory;
    private final Supplier<SuperMove> superMoveFactory;

    private Upgrade(String name, String description, int price,
                    Supplier<Weapon> weaponFactory,
                    Supplier<Shield> shieldFactory,
                    Supplier<SuperMove> superMoveFactory) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.weaponFactory = weaponFactory;
        this.shieldFactory = shieldFactory;
        this.superMoveFactory = superMoveFactory;
    }

    // Factory for a Weapon upgrade
    public static Upgrade weapon(String name, String description, int price, Supplier<Weapon> weaponFactory) {
        return new Upgrade(name, description, price, weaponFactory, null, null);
    }

    // Factory for a Shield upgrade
    public static Upgrade shield(String name, String description, int price, Supplier<Shield> shieldFactory) {
        return new Upgrade(name, description, price, null, shieldFactory, null);
    }

    // Factory for a SuperMove upgrade
    public static Upgrade superMove(String name, String description, int price, Supplier<SuperMove> superMoveFactory) {
        return new Upgrade(name, description, price, null, null, superMoveFactory);
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
        return weaponFactory != null ? weaponFactory.get() : null;
    }

    public Shield getShield() {
        return shieldFactory != null ? shieldFactory.get() : null;
    }

    public SuperMove getSuper() { return superMoveFactory != null ? superMoveFactory.get() : null; }

    public boolean isWeaponUpgrade() {
        return weaponFactory != null;
    }

    public boolean isShieldUpgrade() { return shieldFactory != null; }

    public boolean isSuperUpgrade() { return superMoveFactory != null; }

    public String toString() {
        if (isWeaponUpgrade()) return "[Weapon]";
        else if (isShieldUpgrade()) return "[Shield]";
        else return "[Super Move]";
    }
}
