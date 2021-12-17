import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Projectile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Projectile extends Actor
{   
    /**
     * Act - do whatever the Projectile wants to do.
     */
    public void act()
    {
        
    }
    
    /**
     * When the projectile goes off the screen, it disappears.
     */
    public void goOffScreen()
    {
        if(isAtEdge())
        {
            getWorld().removeObject(this);
        }
    }
}
