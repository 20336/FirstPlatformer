import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HealthBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HealthBar extends Actor
{
    private int xLocation;
    private int yLocation;
    
    
    /**
     * Act - do whatever the Heart wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        setLocation(xLocation, yLocation);
    }
    
    public HealthBar(int xLocation, int yLocation){
        this.xLocation = xLocation;
        this.yLocation = yLocation;
    }
}
