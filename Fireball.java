import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fireball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fireball extends Projectile
{
    private int dX = Greenfoot.getRandomNumber(3)+1;
    private int dY = Greenfoot.getRandomNumber(3)+1;
    Dead Dead = new Dead();
    
    /**
     * Act - do whatever the Fireball wants to do.
     */
    public void act()
    {
        move();
        killPlayer();
        goOffScreen();
    }
    
    /**
     * How the fireball moves.
     */
    public void move()
    {
        setLocation(getX()- dX, getY()+ dY);
    }
    
    /**
     * If the fire is touching the player, the player dies.
     */
    public void killPlayer()
    {
        if(isTouching(Player.class))
        {
            removeTouching(Player.class);
            Sounds.deadSound();
            Greenfoot.setWorld(Dead);
        }
    }
}
