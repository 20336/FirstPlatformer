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
    private int acceleration = 1;
    private int startX;
    private int startTime;
    private int startCounter = 0;
     /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        
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
     * Makes the entity fall if not on platform.
     */
    public void fall(){
        detectPlatform();
        setLocation(getX(), getY()+dY);
        dY += acceleration;
    }
    
    /**
     * Checks if the entity is on the ground or playform, if not then it will fall.
     */
    public void checkForFall(){
        if(isOnGround() || isOnPlatform()){
            dY = 0;
        }else{  
            fall();
        }
    }
    
    /**
     * Detects if the entity is on the platform.
     */
    public boolean isOnPlatform(){
        Actor under = getOneObjectAtOffset(0, getImage().getHeight()/2, Platform.class);
        return under != null;
    }

    /**
     * Detects if the entity is on the ground.
     */
    public boolean isOnGround(){
        Actor under = getOneObjectAtOffset(0, getImage().getHeight()/2, Ground.class);
        return under != null;
    }
    
    /**
     * Detects how far the platform is from the bottom of the entity.
     */
    public void detectPlatform(){
        for(int i = 0; i < dY; i++){
            Actor under = getOneObjectAtOffset(0, getImage().getHeight()/2+i, Platform.class);
            if(under != null){
                dY = i;
            }
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
    
    /**
     * 
     */
    public int getStartTime() {
        return startX;
    }
    
    /**
     * 
     */
    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }
    
    /**
     * 
     */
    public int getStartCounter() {
        return startCounter;
    }
    
    /**
     * 
     */
    public void setStartCounter(int startCounter) {
        this.startCounter = startCounter;
    }
}
