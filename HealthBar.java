import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HealthBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HealthBar extends Enemy
{
    GreenfootImage[] HP = new GreenfootImage[8];
    /**
     * The healthbar has time that it starts moving and it's first X position.
     */
    public HealthBar(int startTime, int startX){
        this.setStartTime(startTime);
        this.setStartX(startX);
    }
    
    /**
     * Act - do whatever the HealthBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act(){
        move();
        moveSidetoSide();
        setStartCounter(getStartCounter()+1);
    }
    
    public void checkForCreatureHP(){
        
        
    }
}
