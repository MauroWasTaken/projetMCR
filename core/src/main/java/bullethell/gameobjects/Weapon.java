package bullethell.gameobjects;

import bullethell.gameobjects.ships.Ship;

public class Weapon{
    Ship ship;
    Projectile[] projectiles;

    public Weapon (Projectile[] projectiles){
        this.projectiles = projectiles;
    }

    public void fire(Ship ship){
        for (Projectile p : projectiles) {
            Projectile copy = new Projectile(
                ship.context,
                (float)ship.getShootingX() + p.x,
                (float)ship.getShootingY() + p.y,
                p.velocityX,
                p.velocityY,
                p.isPlayerProjectile,
                p.sprite
            );
            ship.context.spawn(copy);
        }
    }
}

