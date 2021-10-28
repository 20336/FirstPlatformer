import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Creature here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Creature extends Enemy
{
    private int dY = 2;
    private int acceleration = 1;
    /**
     * Act - do whatever the Creature wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        checkForFall();
    }
    
    public void health()
    {
        
    }
    
    public void fall()
    {
       setLocation(getX(), getY()+dY);
       dY += acceleration;
    }
    
    public void checkForFall()
    {
        if(isOnGround() || isOnPlatform())
        {
            dY = 0;
        }
        else
        {  
            fall();
        }
    }
    /**
     * Detects if the creature is on the platform.
     */
    public boolean isOnPlatform()
    {
        Actor under = getOneObjectAtOffset(0, getImage().getHeight()/2, Platform.class);
        return under != null;
    }

    /**
     * Detects if the creature is on the ground.
     */
    public boolean isOnGround()
    {
        Actor under = getOneObjectAtOffset(0, getImage().getHeight()/2, Ground.class);
        return under != null;
    }
}
