import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Dragon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Dragon extends Enemy
{
    private int yMovement = Greenfoot.getRandomNumber(30)-10;
    private int acceleration = 1;
    /**
     * Act - do whatever the Dragon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        moveUpAndDown();
    }
    
    public void moveUpAndDown()
    {
        if(Greenfoot.getRandomNumber(100)<20)
        {
           setLocation(getX(), getY()+yMovement);
           yMovement += acceleration;
        }
    }
}
