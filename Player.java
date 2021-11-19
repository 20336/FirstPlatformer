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
    public static int spawnDragon;
    
    private boolean bowEquipped;
    private boolean swordEquipped;
    
    private int dX = 4;
    private int dY = 2;
    private int acceleration = 1;
    private int jumpStrength = 15;
    
    private boolean isAttacking;
    private boolean arrowShot;
    
    public static boolean facingRight;
    public static boolean facingLeft;

    
    private int jumpDelayTime;
    private int jumpDelayCounter;
    private int arrowDelayTime;
    private int arrowDelayCounter;
    private int swordAttackTime;
    private int swordAttackCounter;
    
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
        jumpDelayTime = 40;
        jumpDelayCounter = 0;   
        arrowDelayTime = 50;
        arrowDelayCounter = 0;
        swordAttackTime = 20;
        swordAttackCounter = 0;
    
        arrowShot = false;
        
        
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
        arrowShotFalse();
        
        jumpDelayCounter++;
        arrowDelayCounter++;
        swordAttackCounter++;
    }
    
    
    
    
    public void rightFacing()
    {
        if(getImage() == image1 || getImage() == image3 || getImage() == image5 || getImage() == image7 || getImage() == image9)
        {
            facingRight = true;
        }
    }
    public void leftFacing()
    {
        if(getImage() == image2 || getImage() == image4 || getImage() == image6 || getImage() == image8 || getImage() == image10)
        {
            facingLeft = true;
        }
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
        detectPlatform();
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
    
    public void detectPlatform()
    {
        for(int i = 0; i < dY; i++)
        {
            Actor under = getOneObjectAtOffset(0, getImage().getHeight()/2+i, Platform.class);
            if(under != null)
            {
                dY = i;
            }
        }
        for(int i = 0; i < dY; i++)
        {
            Actor under = getOneObjectAtOffset(0, getImage().getHeight()/2+i, Ground.class);
            if(under != null)
            {
                dY = i;
            }
        }
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
        if(Greenfoot.isKeyDown("space") && isOnGround() || Greenfoot.isKeyDown("space") && isOnPlatform())
        {
            jump();
            animateJump();
        }
        
        if(Greenfoot.isKeyDown("enter") && !isAttacking)
        {
            attack();
            shootArrow();
            isAttacking = true;
            attackingAnimate();
        }
        if(!Greenfoot.isKeyDown("enter") && isAttacking)
        {
            isAttacking = false;
            attackingAnimate();
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
         if(swordAttackCounter >= swordAttackTime && isTouching(Creature.class) && swordEquipped)
         {
            enemyHit++; 
            swordAttackCounter = 0;
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
            spawnDragon++;
            enemyHit = 0;
        }
    }
    
    /**
     * Checks if the player is holding the bow and if so then it shoots an arrow.
     */
    public void shootArrow()
    {
        if(bowEquipped && !arrowShot)
        {
            
            getWorld().addObject(new Arrow(), getX()+2, getY()-5);
            arrowDelayCounter = 0;
            bowLift();
            arrowShot = true;
        }
    }
    
    public void arrowShotFalse()
    {
        if(arrowDelayCounter >= arrowDelayTime)
        {
            arrowShot = false;
            bowDown();
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
    
    
    
    
    
    
    
    
    /*
     * Equipping weapons.
     */
    /**
     * Changes the information when the sword is "picked up".
     */    
    public void pickUpSword()
    {
        if(isTouching(Sword.class))
        {
            bowEquipped = false;
            swordEquipped = true;
            removeTouching(Sword.class);
            
            image1 = new GreenfootImage("KnightWithSwordRight.png");
            image2 = new GreenfootImage("KnightWithSwordLeft.png");
            image3 = new GreenfootImage("KnightWithSwordWalkingRight.png");
            image4 = new GreenfootImage("KnightWithSwordWalkingLeft.png");
            image5 = new GreenfootImage("KnightWithSwordJumpingRight.png");
            image6 = new GreenfootImage("KnightWithSwordJumpingLeft.png");
        }
    }
    
    /**
     * Changes the information when the bow is "picked up".
     */
    public void pickUpBow()
    {
        if(isTouching(Bow.class))
        {
            swordEquipped = false;
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
     * If the sword is equipped then the sword attacking animation can play.
     */
    public void attackingAnimate()
    {
        if(swordEquipped)
        {
            attackRightAnimate();
            attackLeftAnimate();
        }
    }
    
    /**
     * Sets the image for when the bow needs to be held up.
     */
    public void bowLift()
    {
        if(getImage() == image1)
        {
            setImage(image9);
        }
        else if(getImage() == image2)
        {
            setImage(image10);
        }
    }
    
    /**
     * Sets the image for when the bow needs to be held down.
     */
    public void bowDown()
    {
        if(getImage() == image9)
        {
            setImage(image1);
        }
        else if(getImage() == image10)
        {
            setImage(image2);
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
