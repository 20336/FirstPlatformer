import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Creature here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Creature extends Enemy
{
    private int hitPoints;
    private int animationDelay;
    private int animationCounter;
    private int squishCounter = 0;
    
    GreenfootImage[] enemySquishImg = new GreenfootImage[2];
    
    Player player = new Player();
    
    /**
     * The creature has time that it starts moving and it's first X position.
     */
    public Creature(int startTime, int startX, int hitPoints){
        this.setStartTime(startTime);
        this.setStartX(startX);
        this.hitPoints = player.getEnemyHit();
        
        animationDelay = 30;
        animationCounter = 0;
    }
    
    /**
     * Act - do whatever the Creature wants to do.
     */
    public void act(){
        checkForFall();
        move();
        moveSidetoSide();
        SquishAnimation();
        squishAnimationImages();
        setStartCounter(getStartCounter()+1);
        animationCounter++;
    }
    
    /**
     * Gets the hitPoints int.
     */
    public int getHitPoints(){
        return hitPoints;
    }
    
    /**
     * Sets the hitPoints int.
     */
    public void setHitPoints(int hitPoints){
        this.hitPoints = hitPoints;
    }
    
    /**
     * Squishy enemy animation.
     */
    public void SquishAnimation(){
        if(animationCounter >= animationDelay){
            animationCounter = 0;
            setImage(enemySquishImg[squishCounter++ % 2]);
            animationCounter = 0;
        }
    }
    
    /**
     * Squishy enemy images.
     */
    public void squishAnimationImages(){
        for(int i = 0; i < 2; i++){
            String filename = "Creature" +i+ ".png";
            enemySquishImg[i] = new GreenfootImage(filename);
        }
    }
}
