import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{   
    private int dX = 4;
    private int dY = 2;
    private int acceleration = 1;
    private int jumpStrength = 15;
    
    private int enemyHit = 0;
    private int enemiesKilled;
    private int spawnDragon;
    
    private boolean emptyHand;
    private boolean bowEquipped;
    private boolean swordEquipped;
    private boolean isAttacking;
    private boolean arrowShot;
    
    private int jumpDelayTime;
    private int jumpDelayCounter;
    private int arrowDelayTime; 
    private int arrowDelayCounter;
    private int swordAttackTime;
    private int swordAttackCounter;
    private int animationCounter = 0;
    
    private GreenfootImage image1;
    private GreenfootImage image2;
    private GreenfootImage image3;
    private GreenfootImage image4;
    private GreenfootImage image5;
    private GreenfootImage image6;
    private GreenfootImage image7;
    private GreenfootImage image8;
    GreenfootImage[] walkRight = new GreenfootImage[8];
    GreenfootImage[] walkLeft = new GreenfootImage[8];
    
    Dead Dead = new Dead();
    /**
     * The player.
     */
    public Player(){
        jumpDelayTime = 10;
        jumpDelayCounter = 0;   
        arrowDelayTime = 50;
        arrowDelayCounter = 0;
        swordAttackTime = 20;
        swordAttackCounter = 0;
        
        emptyHand = true;
        arrowShot = false;
        swordEquipped = false;
        bowEquipped = false;
        
        emptyHandAnimationSprites();
        
        image1 = new GreenfootImage("KnightRight.png");
        image2 = new GreenfootImage("KnightLeft.png");
        
        image3 = new GreenfootImage("KnightJumpingRight.png");
        image4 = new GreenfootImage("KnightJumpingLeft.png");
        image5 = new GreenfootImage("KnightWithSwordUpRight.png");
        image6 = new GreenfootImage("KnightWithSwordUpLeft.png");
        image7 = new GreenfootImage("KnightWithBowUpRight.png");
        image8 = new GreenfootImage("KnightWithBowUpLeft.png");
    }
    
    /**
     * Act - do whatever the Player wants to do.
     */
    public void act(){
        checkKeys();
        checkForFall();
        belowPlatform();
        checkForMoveRight();
        checkForMoveLeft();
        
        pickUpSword();
        pickUpBow();
        checkForEnoughHits();
        arrowShotFalse();
        
        bottomDeath();
        
        jumpDelayCounter++;
        arrowDelayCounter++;
        swordAttackCounter++;
    }
    
    /**
     * Makes the player die when they touch then bottom of the world.
     */
    public void bottomDeath(){
        if(getY()>= getWorld().getHeight()-1){
            Sounds.deadSound();
            Greenfoot.setWorld(Dead);
        }
    }
    
    /*
     * Player moving, jumping, and falling.
     */
    /**
     * Allows the character to move Right.
     */
    public void moveRight(){
        setLocation(getX()+dX, getY());
        animateWalkRight();
    }
    
    /**
     * Allows the character to move left.
     */
    public void moveLeft(){
        setLocation(getX()-dX, getY());
        animateWalkLeft();
    }
    
    /**
     * Sets how fast the character will fall.
     */
    public void fall(){
        detectPlatform();
        setLocation(getX(), getY()+dY);
        dY += acceleration;
    }
    
    /**
     * Checks if the plager is on the ground or on the platform, if not then the player will fall.
     */
    public void checkForFall(){
        if(isOnGround() || isOnPlatform()){
            dY = 0;
            setToLand();
        }else{  
            fall();
        }
    }
    
    /**
     * Checks if the plager is below the platform, if not then the player will not jump any higher.
     */
    public void checkForHeadHit(){
        if(belowPlatform()){
            jump();
        }else{
            fall();
        }
    }
    
    /**
     * Makes the player move right.
     */
    public void checkForMoveRight(){
        if(!rightSidePlatform() && !rightSideGround()){
            dX = 4;
        }else{  
            dX = 0;
        }
    }
    
    /**
     * Makes the player move left.
     */
    public void checkForMoveLeft(){
        if(!leftSidePlatform() && !leftSideGround()){
            dX = 4;
        }else{  
            dX = 0;
        }
    }
    
    /**
     * Sets how the player will jump.
     */
    public void jump(){
       if(jumpDelayCounter >= jumpDelayTime){
           dY = -jumpStrength; 
           fall();  
           Sounds.jumpSound();
           jumpDelayCounter = 0;
       }
    }
    
    /*
     * Player platform and ground detection
     */
    /**
     * Detects if the player is on the platform.
     */
    public boolean isOnPlatform(){
        Actor under = getOneObjectAtOffset(0, getImage().getHeight()/2, Platform.class);
        return under != null;
    }
    
    /**
     * Detects if the player is to the right of the platform.
     */
    public boolean rightSidePlatform(){
        Actor bumped = getOneObjectAtOffset(getImage().getWidth()/2, getImage().getHeight()/2, Platform.class);
        return bumped != null;
    }
    
    /**
     * Detects if the player is to the left of the platform.
     */
    public boolean leftSidePlatform(){
        Actor bumped = getOneObjectAtOffset(0, 0, Platform.class);
        return bumped != null;
    }
    
    /**
     * Detects if the player is to the right of the ground.
     */
    public boolean rightSideGround(){
        Actor bumped = getOneObjectAtOffset(getImage().getWidth()/2, getImage().getHeight()/2, Ground.class);
        return bumped != null;
    }
    
    /**
     * Detects if the player is to the left of the ground.
     */
    public boolean leftSideGround(){
        Actor bumped = getOneObjectAtOffset(0, 0, Ground.class);
        return bumped != null;
    }
    
    /**
     * Detects if the player is below the platform.
     */
    public boolean belowPlatform(){
        Actor above = getOneObjectAtOffset(0, 0, Platform.class);
        if(above != null){
            dY = 1;
            hitHead(above);
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * The player hits their head on the platfrom above
     */
    public void hitHead(Actor above){
        int aboveHeight = above.getImage().getHeight();
        int newY = above.getY() + (aboveHeight + getImage().getHeight())/2;
        
        setLocation(getX(), newY);
    }
    
    /**
     * Detects if the player is on the ground.
     */
    public boolean isOnGround(){
        Actor under = getOneObjectAtOffset(0, getImage().getHeight()/2, Ground.class);
        return under != null;
    }
    
    /**
     * Detects whether or not there is a platform beneath the player.
     */
    public void detectPlatform(){
        for(int i = 0; i < dY; i++){
            Actor under = getOneObjectAtOffset(0, getImage().getHeight()/2+i, Platform.class);
            if(under != null){
                dY = i;
            }
        }
        for(int i = 0; i < dY; i++){
            Actor under = getOneObjectAtOffset(0, getImage().getHeight()/2+i, Ground.class);
            if(under != null){
                dY = i;
            }
        }
        for(int i = 0; i < dX; i++){
            Actor under = getOneObjectAtOffset(0, getImage().getHeight()/2+i, Platform.class);
            if(under != null){
                dX = i;
            }
        }
        for(int i = 0; i < dX; i++){
            Actor under = getOneObjectAtOffset(0, getImage().getHeight()/2+i, Ground.class);
            if(under != null){
                dX = i;
            }
        }
    }
    
    
    
    
    /*
     * Keybindings.
     */
    /**
     * Checks if a key is being pressed.
     */
    public void checkKeys(){   
        if(Greenfoot.isKeyDown("d")){
            moveRight();
        }else if(Greenfoot.isKeyDown("a")){
            moveLeft();
        }else{
            setToStand();
        }
        
        if(Greenfoot.isKeyDown("space") && isOnGround() || Greenfoot.isKeyDown("space") && isOnPlatform()){
            jump();
            animateJump();
        }
        
        if(Greenfoot.isKeyDown("enter") && !isAttacking){
            attack();
            shootArrow();
            isAttacking = true;
            attackingAnimate();
        }
        if(!Greenfoot.isKeyDown("enter") && isAttacking){
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
    public void attack(){
         if(swordAttackCounter >= swordAttackTime && isTouching(Creature.class) && swordEquipped){
            enemyHit++;
            Sounds.swordSound();
            swordAttackCounter = 0;
         }
    }
    
    /**
     * Sees if the enemy has taken enough hits, if so then the enemy dies.
     */
    public void checkForEnoughHits(){
        if(enemyHit == 1){
            removeTouching(Creature.class);
            enemiesKilled++;
            spawnDragon++;
            Sounds.creatureDeathSound();
            enemyHit = 0;
        }
    }
    
    /**
     * Checks if the player is holding the bow and if so then it shoots an arrow.
     */
    public void shootArrow(){
        if(bowEquipped && !arrowShot){
            getWorld().addObject(new Arrow(), getX()+2, getY()-5);
            arrowDelayCounter = 0;
            bowLift();
            Sounds.arrowShotSound();
            arrowShot = true;
        }
    }
    
    /**
     * Sets the boolean arrowShot to false after a certain amount of time.
     * Makes the player put the bow dow to show that the play is ready to shoot the bow again.
     */
    public void arrowShotFalse(){
        if(arrowDelayCounter >= arrowDelayTime){
            arrowShot = false;
            bowDown();
        }
    }
    
    /**
     * Gets the spawnDragon int.
     */
    public int getSpawnDragon(){
        return spawnDragon;
    }
    
    /**
     * Sets the spawnDragon int.
     */
    public void setSpawnDragon(int spawnDragon){
        this.spawnDragon = spawnDragon;
    }
    
    /**
     * Gets the enemyHit int.
     */
    public int getEnemyHit(){
        return enemyHit;
    }
    
    /**
     * Sets the enemyHit int.
     */
    public void setEnemyHit(int enemyHit){
        this.enemyHit = enemyHit;
    }
    
    /*
     * Equipping weapons.
     */
    /**
     * Changes the information when the sword is "picked up".
     */    
    public void pickUpSword(){
        if(isTouching(Sword.class)){
            emptyHand = false;
            bowEquipped = false;
            swordEquipped = true;
            swordImages();
            Sounds.swordEquippedSound();
            removeTouching(Sword.class);
        }
    }
    
    /**
     * Sets the sword images.
     */
    public void swordImages(){
        if(swordEquipped = true){
            image1 = new GreenfootImage("KnightWithSwordRight.png");
            image2 = new GreenfootImage("KnightWithSwordLeft.png");
            image3 = new GreenfootImage("KnightWithSwordJumpingRight.png");
            image4 = new GreenfootImage("KnightWithSwordJumpingLeft.png");
            swordAnimationSprites();
        }
    }
    
    /**
     * Changes the information when the bow is "picked up".
     */
    public void pickUpBow(){
        if(isTouching(Bow.class)){
            emptyHand = false;
            swordEquipped = false;
            bowEquipped = true;
            bowImages();
            removeTouching(Bow.class);
        }
    }
    
    /**
     * Sets the bow images.
     */
    public void bowImages(){
        if(bowEquipped = true){
            image1 = new GreenfootImage("KnightWithBowRight.png");
            image2 = new GreenfootImage("KnightWithBowLeft.png");
            image3 = new GreenfootImage("KnightWithBowJumpingRight.png");
            image4 = new GreenfootImage("KnightWithBowJumpingLeft.png");
            bowAnimationSprites();
        }
    }
    
    /*
     * Arrays.
     */
    /**
     * Walking array with no weapon equipped.
     */
    public void emptyHandAnimationSprites(){
        if(emptyHand = true){
            for(int i = 0; i < 8; i++){
                String filename = "KnightWalkRight" +i+ ".png";
                walkRight[i] = new GreenfootImage(filename);
            }
            for(int i = 0; i < 8; i++){
                String filename = "KnightWalkRight" +i+ ".png";
                walkLeft[i] = new GreenfootImage(filename);
                walkLeft[i].mirrorHorizontally();
            } 
        }
    }
    
    /**
     * Walking array with sword equipped.
     */
    public void swordAnimationSprites(){
        if(swordEquipped = true){
            for(int i = 0; i < 8; i++){
                String filename = "KnightWalkSwordRight" +i+ ".png";
                walkRight[i] = new GreenfootImage(filename);
            }
            for(int i = 0; i < 8; i++){
                String filename = "KnightWalkSwordRight" +i+ ".png";
                walkLeft[i] = new GreenfootImage(filename);
                walkLeft[i].mirrorHorizontally();
            } 
        }
    }
    
    /**
     * Walking array with bow equipped.
     */
    public void bowAnimationSprites(){
        if(bowEquipped = true){
            for(int i = 0; i < 8; i++){
                String filename = "KnightWalkBowRight" +i+ ".png";
                walkRight[i] = new GreenfootImage(filename);
            }
            for(int i = 0; i < 8; i++){
                String filename = "KnightWalkBowRight" +i+ ".png";
                walkLeft[i] = new GreenfootImage(filename);
                walkLeft[i].mirrorHorizontally();
            } 
        }
    }
    
    /*
     * Animations?
     */
    /**
     * If the sword is equipped then the sword attacking animation can play.
     */
    public void attackingAnimate(){
        if(swordEquipped){
            attackRightAnimate();
            attackLeftAnimate();
        }
    }
    
    /**
     * Sets the image for when the bow needs to be held up.
     */
    public void bowLift(){
        if(getImage() == image1){
            setImage(image7);
        }else if(getImage() == image2){
            setImage(image8);
        }
    }
    
    /**
     * Sets the image for when the bow needs to be held down.
     */
    public void bowDown(){
        if(getImage() == image7){
            setImage(image1);
        }else if(getImage() == image8){
            setImage(image2);
        }
    }
    
    /**
     * Animates the hit when facing right.
     */
    public void attackRightAnimate(){
        if(getImage() == image1){
            setImage(image5);
        }else if(getImage() == image5){
            setImage(image1);
        }
    }
    
    /**
     * Animates the hit when facing left.
     */
    public void attackLeftAnimate(){
        if(getImage() == image2){
            setImage(image6);
        }else if(getImage() == image6){
            setImage(image2);
        }
    }
    
    /**
     * Sets the player to the plain standing image after jumping.
     */
    public void setToLand(){
        setToLandRight();
    }
    
    /**
     * Sets the player to the plain right standing image after running.
     */
    public void setToLandRight(){
        if(getImage() == walkRight[animationCounter++ % 8] || getImage() == image3){
            setImage(image1);
        }
    }
    
    /**
     * Sets the player to the plain left standing image after running.
     */
    public void setToLandLeft(){
        if(getImage() == walkLeft[animationCounter++ % 8] || getImage() == image4){
            setImage(image2);
        }
    }
    
    /**
     * Sets the player to the plain standing image after running.
     */
    public void setToStand(){
        setToStandRight();
        setToStandLeft();
    }
    
    /**
     * Sets the player to the plain right standing image after running.
     */
    public void setToStandRight(){
        if(getImage() == walkRight[animationCounter++ % 8] || getImage() == image3 && isOnGround()){
            setImage(image1);
        }
    }
    
    /**
     * Sets the player to the plain left standing image after running.
     */
    public void setToStandLeft(){
        if(getImage() == walkLeft[animationCounter++ % 8] || getImage() == image4){
            setImage(image2);
        }
    }
    
    /**
     * Animates the jump.
     */
    public void animateJump(){
        animateJumpLeft();
        animateJumpRight();
    }
    
    /**
     * Right jump animate images.
     */
    public void animateJumpRight(){
        if(getImage() == walkRight[animationCounter++ % 8] || getImage() == image1){
            setImage(image3);
        }
    }
    
    /**
     * Left jump animate images.
     */
    public void animateJumpLeft(){
        if(getImage() == walkLeft[animationCounter++ % 8] || getImage() == image2){
            setImage(image4);
        }
    }
    
    /**
     * Animates the walk towards the right.
     */
    public void animateWalkRight(){
        setImage(walkRight[animationCounter++ % 8]);
    }
    
    /**
     * Animates the walk towards the left.
     */
    public void animateWalkLeft(){
        setImage(walkLeft[animationCounter++ % 8]);
    }
    
    /**
     * Sword attack sound effect.
     */
    public void swordSound(){
        GreenfootSound sound = new GreenfootSound("sword.wav");
        sound.setVolume(50);
        sound.play();
    }
}
