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
    Player player = new Player();
    /**
     * The creature has time that it starts moving and it's first X position.
     */
    public Creature(int startTime, int startX, int hitPoints){
        this.setStartTime(startTime);
        this.setStartX(startX);
        this.hitPoints = player.getEnemyHit();
    }
    
    /**
     * Act - do whatever the Creature wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act(){
        checkForFall();
        move();
        moveSidetoSide();
        setStartCounter(getStartCounter()+1);
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
}
