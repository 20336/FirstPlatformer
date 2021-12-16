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
    
    int animationDelay;
    int animationCounter;
    int treeCounter = 0;
    GreenfootImage[] treesMove = new GreenfootImage[3];
    Player player = new Player();
    Dragon dragon = new Dragon(getHeight()/2);
    
    Creature creature1 = new Creature(0, getWidth()/2, 5);
    Creature creature2 = new Creature(10, getWidth()/5, 5);
    Creature creature3 = new Creature(40, getWidth()-270, 5);
    Creature creature4 = new Creature(20, getWidth()/3, 5);
    Creature creature5 = new Creature(30, getWidth()-190, 5);
    
    HealthBar healthBar1 = new HealthBar(0, getWidth()/2);
    HealthBar healthBar2 = new HealthBar(10, getWidth()/5);
    HealthBar healthBar3 = new HealthBar(40, getWidth()-270);
    HealthBar healthBar4 = new HealthBar(20, getWidth()/3);
    HealthBar healthBar5 = new HealthBar(30, getWidth()-190);
    /**
     * Constructor for objects of class MyWorld.
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        
        spawnDelayTime = 10;
        spawnDelayCounter = 0;
        int animationDelay = 10;
        int animationCounter = 0;
        
        addObject(new Ground(), getWidth()/2, getHeight()-15);
        addObject(new Platform(), 125, getHeight()/3);
        addObject(new Platform(), getWidth()-250, getHeight()/3);
        addObject(new Platform(), 200, getHeight() -160);
        addObject(new Platform(), getWidth()-200, getHeight() -160);
        
        
        
        
        addObject(new Sword(), getWidth()/4, getHeight()-100);
        
        
        addObject(creature1, getWidth()/2, getHeight()-77);
                    //addObject(healthBar1, getWidth()/2, getHeight()-97);
                    
        addObject(creature2, getWidth()/5, 106);
                    //addObject(healthBar2, getWidth()/5, 86);
                    
        addObject(creature3, getWidth()-270, 106);
                    //addObject(healthBar3, getWidth()-270, 86);
                    
        addObject(creature4, getWidth()/3, getHeight()-187);
                    //addObject(healthBar4, getWidth()/3, getHeight()-207);
                    
        addObject(creature5, getWidth()-190, getHeight()-187);
                    //addObject(healthBar5, getWidth()-190, getHeight()-207);
        
        
            
        
        addObject(player, 100, getHeight()-97);        
    }
    
    /**
     * Act.
     */
    public void act()
    {
        checkIfAllCreaturesDead();
        treeAnimation();
        animationCounter++;
        treeAnimationImages();
    }
    
    /**
     * Checks if all the creatures are dead.
     */
    public void checkIfAllCreaturesDead()
    {
        if(player.getSpawnDragon() == 5)
        {
            addObject(dragon, getWidth()-50, getHeight()/2);
            addObject(new Bow(), getWidth()/4, getHeight()-100);
            player.setSpawnDragon(0);
        }
    }
    public void treeAnimation(){
        if(animationCounter >= animationDelay){
            animationCounter = 0;
            setBackground(treesMove[treeCounter++ % 3]);
            animationCounter = 0;
        }
    }
    
    public void treeAnimationImages(){
        if(animationCounter >= animationDelay){
        for(int i = 0; i < 3; i++){
            String filename = "Sky" +i+ ".png";
            treesMove[i] = new GreenfootImage(filename);
        }
    }}
    
    /**
     * Checks if the dragon has tallied up a certain amount of damage, if so then the dragon gets 
     * removed.
     */
    public void checkForEnoughDamage(){
        if(dragon.getDamage() == 20){
            removeObject(dragon);
        }
    }
}
