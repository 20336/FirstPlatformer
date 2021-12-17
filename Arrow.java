import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Arrow here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Arrow extends Projectile
{   private int dX = 5;
    
    /**
     * Act - do whatever the Arrow wants to do.
     */
    public void act()
    {
        move();
        goOffScreen();
    }
    
    /**
     * How the arrow moves.
     */
    public void move()
    {
            setLocation(getX()+ dX, getY());
    }
}
