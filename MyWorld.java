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
    
    private int skyCounter = 0;
    
    
    
    private boolean beginning;
    private boolean lvlOne = false;
    private boolean lvlTwo = false;
    
    Win Win = new Win();
    
    
    GreenfootImage[] cloudsMove = new GreenfootImage[4];
    Player player = new Player();
    Dragon dragon = new Dragon(getHeight()/2);
    
    Creature creature1 = new Creature(0, getWidth()-100, 5);
    Creature creature2 = new Creature(10, getWidth()/5, 5);
    Creature creature3 = new Creature(40, getWidth()-140, 5);
    Creature creature4 = new Creature(20, getWidth()/3, 5);
    Creature creature5 = new Creature(30, getWidth()-190, 5);
    
    Platform platform1 = new Platform();
    Platform platform2 = new Platform();
    Platform platform3 = new Platform();
    Platform platform4 = new Platform();
    
    Text txt = new Text(0);
    Text txt1 = new Text(1);
    Text txt2 = new Text(2);
    
    Start start = new Start();
    /**
     * Constructor for objects of class MyWorld.
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        
        spawnDelayTime = 10;
        spawnDelayCounter = 0;
        animationDelay = 40;
        animationCounter = 0;
        
        beginning = true;
        
        
        addObject(txt, getWidth()/2, getHeight()/2);
        
        for(int i = 0 ; i <= 2 ; i++){
            addObject(new Ground(), i*100, getHeight()-15);
        }
        
        for(int i = 4 ; i <= 6 ; i++){
            addObject(new Ground(), i*100, getHeight()-15);
        }
        addObject(player, 100, getHeight()-97);
        addObject(start, getWidth()/2, getHeight()/2);
    }
    
    /**
     * Act.
     */
    public void act()
    {
        start();
        checkForEnoughDamage();
        skyAnimation();
        animationCounter++;
        skyAnimationImages();
        levels();
    }
    
    public void start(){
        if(Greenfoot.isKeyDown("enter")){
            removeObject(start);
        }
    }
    
    /**
     * Allows the trees in the background to sway.
     */
    public void skyAnimation(){
        if(animationCounter >= animationDelay){
            animationCounter = 0;
            setBackground(cloudsMove[skyCounter++ % 4]);
            animationCounter = 0;
        }
    }
    
    /**
     * Sets the tree swaying images.
     */
    public void skyAnimationImages(){
        for(int i = 0; i < 4; i++){
            String filename = "Sky" +i+ ".png";
            cloudsMove[i] = new GreenfootImage(filename);
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
    
    public boolean getBeginning(){
        return beginning;
    }
    
    
    
    public void levels(){
        lvlOne();
        lvlTwo();
        lvlThree();
    }
    
    public void lvlOne(){
        if(beginning = true && player.getX() >= getWidth()-1 && player.getSpawnDragon() == 0){
            beginning = false;
            lvlOne = true;
            player.setLocation(1, getHeight()-97);
            lvlOneObjects();
        }
    }
    
    public void lvlTwo(){
        if(lvlOne = true && player.getX() >= getWidth()-1 && player.getSpawnDragon() == 1){
            lvlOne = false;
            lvlTwo = true;
            player.setLocation(1, getHeight()-97);
            lvlTwoObjects();
        }
    }
    
    public void lvlThree(){
        if(lvlTwo = true && player.getX() >= getWidth()-1 && player.getSpawnDragon() == 5){
            lvlThreeObjects();
        }
    }
    
    public void lvlOneObjects(){
        
        removeObject(txt);
        addObject(txt1, getWidth()/2, getHeight()/2);
        addObject(new Sword(), getWidth()/4, getHeight()-100);
        addObject(creature1, getWidth()-100, getHeight()-77);
    }
    
    public void lvlTwoObjects(){
        removeObject(txt1);
        addObject(txt2, getWidth()/2, getHeight()/2);
        addObject(new Ground(), getWidth()/2, getHeight()-15);
        
        addObject(platform1, 125, getHeight()/3);
        addObject(platform2, getWidth()-125, getHeight()/3);
        addObject(platform3, 200, getHeight() -160);
        addObject(platform4, getWidth()-200, getHeight() -160);
        
        addObject(creature2, getWidth()/5, 106);
        addObject(creature3, getWidth()-140, 106);
        addObject(creature4, getWidth()/3, getHeight()-187);
        addObject(creature5, getWidth()-190, getHeight()-187);
    }
    
    public void lvlThreeObjects(){
        removeObject(txt2);
        removeObject(platform1);
        removeObject(platform2);
        removeObject(platform4);
        for(int i = 0 ; i <= 3 ; i++){
            addObject(new Ground(), i*100, getHeight()-15);
        }
        addObject(dragon, getWidth()-50, getHeight()/2);
        addObject(new Bow(), getWidth()/4, getHeight()-100);
        player.setSpawnDragon(0);
    }
}
