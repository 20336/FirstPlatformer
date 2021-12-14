import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    int spawnDelayTime;
    int spawnDelayCounter;
    Player player = new Player();
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        
        spawnDelayTime = 10;
        spawnDelayCounter = 0;
        
        addObject(new Ground(), getWidth()/2, getHeight()-15);
        addObject(new Platform(), 125, getHeight()/3);
        addObject(new Platform(), getWidth()-250, getHeight()/3);
        addObject(new Platform(), 200, getHeight() -160);
        addObject(new Platform(), getWidth()-200, getHeight() -160);
        
        
        
        
        addObject(new Sword(), getWidth()/4, getHeight()-100);
        
        addObject(new Creature(10, getWidth()/5, 5), getWidth()/5, 99);
        addObject(new Creature(20, getWidth()/3, 5), getWidth()/3, getHeight()-194);
        addObject(new Creature(30, getWidth()-190, 5), getWidth()-190, getHeight()-194);
        addObject(new Creature(40, getWidth()-270, 5), getWidth()-270, 99);
        
        addObject(new Creature(0, getWidth()/2, 5), getWidth()/2, getHeight()-84);
            
        
        addObject(player, 100, getHeight()-97);        
    }
    
    public void act()
    {
        checkIfAllCreaturesDead();
    }
    
    public void checkIfAllCreaturesDead()
    {
        if(player.getSpawnDragon() == 5)
        {
            addObject(new Dragon(getHeight()/2), getWidth()-50, getHeight()/2);
            addObject(new Bow(), getWidth()/4, getHeight()-100);
            player.setSpawnDragon(0);
        }
    }
}
