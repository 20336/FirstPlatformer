import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{   private int enemyHit = 0;
    public static int enemiesKilled;
    
    private boolean bowEquipped;
    
    private int dX = 4;
    private int dY = 2;
    private int acceleration = 1;
    private int jumpStrength = 15;
    
    private boolean isAttacking;
    private boolean arrowShot;
    
    private int jumpDelayTime;
    private int jumpDelayCounter;
    private int shootDelayTime;
    private int shootDelayCounter;
    
    private GreenfootImage image1;
    private GreenfootImage image2;
    private GreenfootImage image3;
    private GreenfootImage image4;
    private GreenfootImage image5;
    private GreenfootImage image6;
    private GreenfootImage image7;
    private GreenfootImage image8;
    private GreenfootImage image9;
    private GreenfootImage image10;
    
    public Player()
    {
        jumpDelayTime = 20;
        jumpDelayCounter = 0;   
        shootDelayTime = 10;
        shootDelayCounter = 0;  
        
        image1 = new GreenfootImage("KnightRight.png");
        image2 = new GreenfootImage("KnightLeft.png");
        image3 = new GreenfootImage("KnightWalkingRight.png");
        image4 = new GreenfootImage("KnightWalkingLeft.png");
        image5 = new GreenfootImage("KnightJumpingRight.png");
        image6 = new GreenfootImage("KnightJumpingLeft.png");
        image7 = new GreenfootImage("KnightWithSwordUpRight.png");
        image8 = new GreenfootImage("KnightWithSwordUpLeft.png");
        image9 = new GreenfootImage("KnightWithBowUpRight.png");
        image10 = new GreenfootImage("KnightWithBowUpLeft.png");
    }
    
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        checkKeys();
        checkForFall();
        pickUpSword();
        pickUpBow();
        checkForEnoughHits();
        jumpDelayCounter++;
        shootDelayCounter++;
    }
    
    public void jumpDelayTime(int jumpDelay)
    {
        jumpDelayTime = jumpDelay;
    }
    public void shootDelayTime(int shootDelay)
    {
        shootDelayTime = shootDelay;
    }
    
    
    
    
    
    /*
     * Player movement and falling speed.
     */
    /**
     * Allows the character to move Right.
     */
    public void moveRight()
    {
        setLocation(getX()+dX, getY());
    }
    /**
     * Allows the character to move left.
     */
    public void moveLeft()
    {
        setLocation(getX()-dX, getY());
    }
    /**
     * Sets how fast the character will fall.
     */
    public void fall()
    {
       setLocation(getX(), getY()+dY);
       dY += acceleration;
    }
    
    
    
    
    
    /**
     * Detects if the player is on the platform.
     */
    public boolean isOnPlatform()
    {
        Actor under = getOneObjectAtOffset(0, getImage().getHeight()/2, Platform.class);
        return under != null;
    }
    /**
     * Detects if the player is on the ground.
     */
    public boolean isOnGround()
    {
        Actor under = getOneObjectAtOffset(0, getImage().getHeight()/2, Ground.class);
        return under != null;
    }
    
    public boolean checkIfNearEnemy()
    {
        Actor under = getOneObjectAtOffset(0, getImage().getHeight()/2, Ground.class);
        return under != null;
    }
    
    
    
    
    
    
    
    /**
     * Checks if a key is being pressed.
     */
    public void checkKeys()
    {   
        if(Greenfoot.isKeyDown("d"))
        {
            moveRight();
            animateRightWalk();
        }
        else if(Greenfoot.isKeyDown("a"))
        {
            moveLeft();
            animateLeftWalk();
        }
        else
        {
            setToStand();
        }
        if(Greenfoot.isKeyDown("w") && isOnGround() || Greenfoot.isKeyDown("w") && isOnPlatform())
        {
            jump();
            animateJump();
        }
        
        if(Greenfoot.isKeyDown("space") && !isAttacking)
        {
            attack();
            shootArrow();
            isAttacking = true;
            attackRightAnimate();
            attackLeftAnimate();
        }
        if(!Greenfoot.isKeyDown("space") && isAttacking)
        {
            isAttacking = false;
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    /*
     * Attacking enemy.
     */
    /**
     * Tallies up the amount of times the enemy(creature) is hit.
     */
    public void attack()
    {
         if(isTouching(Creature.class))
         {
            enemyHit++; 
         }
    }
    
    /**
     * Sees if the enemy has taken enough hits, if so then the enemy dies.
     */
    public void checkForEnoughHits()
    {
        if(enemyHit >= 8)
        {
            removeTouching(Enemy.class);
            enemiesKilled++;
            enemyHit = 0;
        }
    }
    
    /**
     * Checks if the player is holding the bow and if so then it shoots an arrow.
     */
    public void shootArrow()
    {
        if(bowEquipped && shootDelayCounter >= shootDelayTime)
        {
            getWorld().addObject(new Arrow(), getX()+2, getY()-5);
            shootDelayCounter = 0;
            arrowShot = true;
        }
    }
    
    public void arrowShotCounter()
    {
        if(arrowShot = true)
        {
            shootDelayCounter++;
        }
    }
    
    
    
    /*
     * Jumping and fall.
     */
    /**
     * Checks if the plager is on the ground or on the platform, if not then the player will fall.
     */
    public void checkForFall()
    {
        if(isOnGround() || isOnPlatform())
        {
            dY = 0;
            setToLand();
        }
        else
        {  
            fall();
        }
    }
    
    /**
     * Sets how the player will jump.
     */
    public void jump()
    {
       if(jumpDelayCounter >= jumpDelayTime)
       {
           dY = -jumpStrength; 
           fall();  
           jumpDelayCounter = 0;
       }
    }
    
    
    
    
    
    
    
    
    
    public void pickUpSword()
    {
        if(isTouching(Sword.class))
        {
            bowEquipped = false;
            removeTouching(Sword.class);
            
            image1 = new GreenfootImage("KnightWithSwordRight.png");
            image2 = new GreenfootImage("KnightWithSwordLeft.png");
            image3 = new GreenfootImage("KnightWithSwordWalkingRight.png");
            image4 = new GreenfootImage("KnightWithSwordWalkingLeft.png");
            image5 = new GreenfootImage("KnightWithSwordJumpingRight.png");
            image6 = new GreenfootImage("KnightWithSwordJumpingLeft.png");
        }
    }
    public void pickUpBow()
    {
        if(isTouching(Bow.class))
        {
            bowEquipped = true;
            removeTouching(Bow.class);
            
            
            image1 = new GreenfootImage("KnightWithBowRight.png");
            image2 = new GreenfootImage("KnightWithBowLeft.png");
            image3 = new GreenfootImage("KnightWithBowWalkingRight.png");
            image4 = new GreenfootImage("KnightWithBowWalkingLeft.png");
            image5 = new GreenfootImage("KnightWithBowJumpingRight.png");
            image6 = new GreenfootImage("KnightWithBowJumpingLeft.png");
        }
    }
    
    
    /*
     * Animations?
     */
    
    /**
     * Animates the hit when facing right.
     */
    public void bowLift()
    {
         if(getImage() == image1)
        {
            setImage(image9);
        }
    }
    
    /**
     * Animates the hit when facing right.
     */
    public void attackRightAnimate()
    {
         if(getImage() == image1)
        {
            setImage(image7);
        }
        else if(getImage() == image7)
        {
            setImage(image1);
        }
    }
    
    /**
     * Animates the hit when facing left.
     */
    public void attackLeftAnimate()
    {
        if(getImage() == image2)
        {
            setImage(image8);
        }
        else if(getImage() == image8)
        {
            setImage(image2);
        }
    }
    
    /**
     * Sets the player to the plain standing image after jumping.
     */
    public void setToLand()
    {
        if(getImage() == image5)
        {
            setImage(image1);
        }
        else if(getImage() == image6)
        { 
            setImage(image2);
        }
    }
    
    /**
     * Sets the player to the plain standing image after running.
     */
    public void setToStand()
    {
        if(getImage() == image3)
        {
            setImage(image1);
        }
        else if(getImage() == image4)
        { 
            setImage(image2);
        }
    }
    
    /**
     * Animates the jump.
     */
    public void animateJump()
    {
        if(getImage() == image1 || getImage() == image3)
        {
            setImage(image5);
        }
        if(getImage() == image2 ||getImage() == image4)
        {
            setImage(image6);
        }
    }
    
    /**
     * Animates the walk towards the right.
     */
    public void animateRightWalk()
    {
        if(getImage() == image1)
        {
            setImage(image3);
        }
        else
        {
            setImage(image1);
        }
    }
    
    /**
     * Animates the walk towards the left.
     */
    public void animateLeftWalk()
    {
        if(getImage() == image2)
        {
            setImage(image4);
        }
        else
        {
            setImage(image2);
        }
        
    }
}
