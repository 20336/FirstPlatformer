import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Dragon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Dragon extends Enemy
{
    private int yMovement = 1;
    private int acceleration = 1;
    
    private int damage = 0;
    
    private int fireballDelay;
    private int fireballDelayCount;
    
    private boolean fireballShot;
    
    private GreenfootImage image1;
    private GreenfootImage image2;
    
    private int startY;
    
    public Dragon(int startY){
        this.startY = startY;
        
        fireballDelay = 80;
        fireballDelayCount = 0;
        
        fireballShot = false;
        
        image1 = new GreenfootImage("Dragon.png");
        image2 = new GreenfootImage("DragonAngry.png");
    }
    
    /**
     * Act - do whatever the Dragon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act(){
        shootFireball();
        fireballShotFalse();
        arrowDamage();
        move();
        moveUpAndDown();
        fireballDelayCount++;
    }
    
    /**
     * Moves the dragon a certain amount in the y direction.
     */
    public void move(){
        setLocation(getX(), getY()+yMovement);
    }
    
    
    public void moveUpAndDown(){
        if(getY() >= startY + 40){
            yMovement = -1;
        }else if(getY() <= startY - 40){
            yMovement = 1;
        }
    }
    
    /**
     * Tallies up a point of damage after getting hit with an arrow as well as deleting the arrow if 
     * it's touching the dragon.
     */
    public void arrowDamage(){
        if(isTouching(Arrow.class)){
            damage++;
            removeTouching(Arrow.class);
        }
    }
    
    /**
     * Sets the angry dragon image.
     */
    public void setAngryDragon(){
        if(getImage() == image1){
            setImage(image2);
        }
    }
    
    /**
     * Sets the angry dragon image.
     */
    public void setNormalDragon(){
        if(getImage() == image2){
            setImage(image1);
        }
    }
    
    /**
     * The dragon summons a fireball.
     */
    public void shootFireball(){
        if(!fireballShot){
            getWorld().addObject(new Fireball(), getX()-10, getY()-55);
            fireballDelayCount = 0;
            setAngryDragon();
            fireballShot = true;
        }
    }
    
    /**
     * Sets the boolean fireballShot to false after a certain amount of time.
     * Makes the dragon put the keep the angry face.
     */
    public void fireballShotFalse(){
        if(fireballDelayCount >= fireballDelay){
            fireballShot = false;
            setAngryDragon();
        }
    }
    
    /**
     * Gets the dragon's damage taken.
     */
    public int getDamage(){
        return damage;
    }
}
