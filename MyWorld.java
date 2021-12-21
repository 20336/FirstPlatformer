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
    private boolean lvlOne;
    private boolean lvlTwo;
    
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
        
        setBeginning(true);
        
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
    
    public void enemiesKilled(){
        showText("To move, press 'a' or 'd'.", getWidth()/2, 50);
    }
    
    /**
     * Starts the game.
     */
    public void start(){
        if(Greenfoot.isKeyDown("enter") && getBeginning()){
            removeObject(start);
            showText("To move, press 'a' or 'd'.", getWidth()/2, 50);
            showText("To jump, press 'space'.", getWidth()/2, 75);
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
            Sounds.winSound();
            Greenfoot.setWorld(Win);
        }
    }
    
    /**
     * Gets the beginning boolean value
     */
    public boolean getBeginning(){
        return beginning;
    }
    
    /**
     * Sets the beginning boolean to true or false.
     */
    public void setBeginning(boolean beginning){
        this.beginning = beginning;
    }
    
    /**
     * Gets the lvlOne boolean value
     */
    public boolean getLvlOne(){
        return lvlOne;
    }
    
    /**
     * Sets the lvlOne boolean to true or false.
     */
    public void setLvlOne(boolean lvlOne){
        this.lvlOne = lvlOne;
    }
    
    /**
     * Gets the lvlTwo boolean value
     */
    public boolean getLvlTwo(){
        return lvlTwo;
    }
    
    /**
     * Sets the lvlTwo boolean to true or false.
     */
    public void setLvlTwo(boolean lvlTwo){
        this.lvlTwo = lvlTwo;
    }
    
    /**
     * Checks all the levels at once.
     */
    public void levels(){
        lvlOne();
        lvlTwo();
        lvlThree();
    }
    
    /**
     * Sets up everything for level one if it is level one.
     */
    public void lvlOne(){
        if(getBeginning() && player.getX() >= getWidth()-1 && player.getSpawnDragon() == 0){
            setBeginning(false);
            setLvlOne(true);
            player.setLocation(1, getHeight()-97);
            lvlOneObjects();
        }
    }
    
    /**
     * Sets up everything for level two if it is level two.
     */
    public void lvlTwo(){
        if(getLvlOne() && player.getX() >= getWidth()-1 && player.getSpawnDragon() == 1){
            setLvlOne(false);
            setLvlTwo(true);
            player.setLocation(1, getHeight()-97);
            lvlTwoObjects();
        }
    }
    
    /**
     * Sets up everything for level three if it is level three.
     */
    public void lvlThree(){
        if(getLvlTwo() && player.getX() >= getWidth()-1 && player.getSpawnDragon() == 5){
            player.setLocation(1, getHeight()-97);
            lvlThreeObjects();
        }
    }
    
    /**
     * Sets the objects for the first level.
     */
    public void lvlOneObjects(){
        showText("To use your attack, press 'enter'.", getWidth()/2, 50);
        showText("", getWidth()/2, 75);
        addObject(new Sword(), getWidth()/4, getHeight()-100);
        addObject(creature1, getWidth()-100, getHeight()-77);
    }
    
    /**
     * Sets the objects for the second level.
     */
    public void lvlTwoObjects(){
        showText("You are a knight. You were sent out to kill the dragon.", getWidth()/2, 50);
        showText("To get to the dragon, you must kill all the creatures.", getWidth()/2, 75);
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
    
    /**
     * Sets the objects for the third level.
     */
    public void lvlThreeObjects(){
        showText("", getWidth()/2, 50);
        showText("", getWidth()/2, 75);
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
