import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
    private int dX = 1;
    private int dY = 2;
    private int startX;
    private int startTime;
    private int startCounter;
     /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        move();
        moveSidetoSide();
    }
    
    /**
     * Moves the entity a certain amount in the x direction.
     */
    public void move(){
        if(this.startTime < startCounter){
           setLocation(getX()+dX, getY()); 
        }
    }
    
    /**
     * Moves the entity side to side.
     */
    public void moveSidetoSide(){
        if(getX() >= startX + 20){
            dX = -1;
        }else if(getX() <= startX - 20){
            dX = 1;
        }
    }
    
    /**
     * 
     */
    public int getStartX() {
        return startX;
    }
    
    /**
     * 
     */
    public void setStartX(int startX) {
        this.startX = startX;
    }
}
