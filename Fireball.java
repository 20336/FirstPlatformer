import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fireball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fireball extends Projectile
{
    /**
     * Act - do whatever the Fireball wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        killPlayer();
    }
    
    public void killPlayer()
    {
        if(isTouching(Player.class))
        {
            removeTouching(Player.class);
            Greenfoot.stop();
        }
    }
}
