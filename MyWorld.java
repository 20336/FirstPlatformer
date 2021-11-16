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
        addObject(new Platform(), 125, getHeight()/3);
        addObject(new Platform(), getWidth()-250, 120);
        addObject(new Platform(), 200, getHeight() -160);
        addObject(new Platform(), getWidth()-200, getHeight() -160);
        
        
        addObject(new Player(), 100, getHeight()-90);
        
        addObject(new Sword(), getWidth()/4, getHeight()-100);
        for(int i = 0; i < 8; i ++)
        {
            addObject(new Creature(), Greenfoot.getRandomNumber(600), getHeight()-70);
        }
        
    }
    
    public void act()
    {
        checkIfAllCreaturesDead();
    }
    
    public void checkIfAllCreaturesDead()
    {
        if(Player.enemiesKilled == 8)
        {
            addObject(new Dragon(), getWidth()-50, getHeight()/2);
            addObject(new Bow(), getWidth()/4, getHeight()-100);
            Player.enemiesKilled = 0;
        }
    }
}
