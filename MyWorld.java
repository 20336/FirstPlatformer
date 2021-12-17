import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    private int spawnDelayTime;
    private int spawnDelayCounter;
    
    private int animationDelay;
    private int animationCounter;
    
    private int treeCounter = 0;
    
    private boolean playerDead;
    
    Win Win = new Win();
    Dead Dead = new Dead();
    
    GreenfootImage[] treesMove = new GreenfootImage[4];
    Player player = new Player();
    Dragon dragon = new Dragon(getHeight()/2);
    
    Creature creature1 = new Creature(0, getWidth()/2, 5);
    Creature creature2 = new Creature(10, getWidth()/5, 5);
    Creature creature3 = new Creature(40, getWidth()-270, 5);
    Creature creature4 = new Creature(20, getWidth()/3, 5);
    Creature creature5 = new Creature(30, getWidth()-190, 5);
    
    Platform platform1 = new Platform();
    Platform platform2 = new Platform();
    Platform platform3 = new Platform();
    Platform platform4 = new Platform();
    

    /**
     * Constructor for objects of class MyWorld.
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        
        spawnDelayTime = 10;
        spawnDelayCounter = 0;
        animationDelay = 80;
        animationCounter = 0;
        
        for(int i = 0 ; i <= 3 ; i++){
            addObject(new Ground(), i*100, getHeight()-15);
        }
        addObject(platform1, 125, getHeight()/3);
        addObject(platform2, getWidth()-250, getHeight()/3);
        addObject(platform3, 200, getHeight() -160);
        addObject(platform4, getWidth()-200, getHeight() -160);
                
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
        checkForEnoughDamage();
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
            removeObject(platform1);
            removeObject(platform2);
            addObject(dragon, getWidth()-50, getHeight()/2);
            addObject(new Bow(), getWidth()/4, getHeight()-100);
            player.setSpawnDragon(0);
        }
    }
    
    /**
     * Allows the trees in the background to sway.
     */
    public void treeAnimation(){
        if(animationCounter >= animationDelay){
            animationCounter = 0;
            setBackground(treesMove[treeCounter++ % 4]);
            animationCounter = 0;
        }
    }
    
    /**
     * Sets the tree swaying images.
     */
    public void treeAnimationImages(){
        for(int i = 0; i < 4; i++){
            String filename = "Sky" +i+ ".png";
            treesMove[i] = new GreenfootImage(filename);
        }
    }
    
    /**
     * Checks if the dragon has tallied up a certain amount of damage, if so then the dragon gets 
     * removed.
     */
    public void checkForEnoughDamage(){
        if(dragon.getDamage() == 10){
            removeObject(dragon);
            Greenfoot.setWorld(Win);
        }
    }
    
    public boolean getPlayerDead(){
        return playerDead;
    }
    
    public void setPlayerDead(boolean playerDead){
        this.playerDead = playerDead;
    }
}
