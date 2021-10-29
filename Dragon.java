import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Dragon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Dragon extends Enemy
{
    private int yMovement = Greenfoot.getRandomNumber(30)-10;
    private int acceleration = 1;
    
    private int damage = 0;
    
    private int fireballDelay;
    private int fireballDelayCount;
    
    public Dragon()
    {
        fireballDelay = 80;
        fireballDelayCount = 0;
    }
    
    public void fireballDelay(int fireballShoot)
    {
        fireballDelay = fireballShoot;
    }
    
    /**
     * Act - do whatever the Dragon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        shootFireball();
        fireballDelayCount++;
        arrowDamage();
        checkForEnoughDamage();
    }
    
    public void shootFireball()
    {
        if(fireballDelayCount >= fireballDelay)
        {
            getWorld().addObject(new Fireball(), getX()-10, getY()-55);
            fireballDelayCount = 0;
        }
    }
    
    public void moveUpAndDown()
    {
        if(Greenfoot.getRandomNumber(100)<20)
        {
           setLocation(getX(), getY()+yMovement);
           yMovement += acceleration;
        }
    }
    
    public void arrowDamage()
    {
        if(isTouching(Arrow.class))
        {
            damage++;
            removeTouching(Arrow.class);
        }
    }
    
    public void checkForEnoughDamage()
    {
        if(damage == 20)
        {
            getWorld().removeObject(this);
        }
    }
    
}
