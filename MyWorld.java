import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        
        addObject(new Ground(), getWidth()/2, getHeight()-15);
        addObject(new Player(), 100, getHeight()-100);
        
        addObject(new Platform(), getWidth()/4, getHeight()/4);
        addObject(new Platform(), getWidth()/4, getHeight() -150);
    }
}
