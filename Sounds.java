import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Sounds here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Sounds extends Actor
{
    /**
     * Sword attack sound effect.
     */
    public static void swordSound(){
        GreenfootSound sound = new GreenfootSound("sword.mp3");
        sound.setVolume(50);
        sound.play();
    }
    
    /**
     * Sword equip sound effect.
     */
    public static void swordEquippedSound(){
        GreenfootSound sound = new GreenfootSound("equip_sword.mp3");
        sound.setVolume(50);
        sound.play();
    }
    
    /**
     * Arrow shot sound effect.
     */
    public static void arrowShotSound(){
        GreenfootSound sound = new GreenfootSound("arrow_shot.mp3");
        sound.setVolume(50);
        sound.play();
    }
    
    /**
     * Arrow hit sound effect.
     */
    public static void arrowHitSound(){
        GreenfootSound sound = new GreenfootSound("arrow_hit.mp3");
        sound.setVolume(50);
        sound.play();
    }
    
    
    /**
     * Player death sound effect.
     */
    public static void deadSound(){
        GreenfootSound sound = new GreenfootSound("dead.mp3");
        sound.setVolume(50);
        sound.play();
    }
    
    /**
     * Creature death sound effect.
     */
    public static void creatureDeathSound(){
        GreenfootSound sound = new GreenfootSound("creature_death.mp3");
        sound.setVolume(50);
        sound.play();
    }
    
    /**
     * Fireball sound effect.
     */
    public static void fireballSound(){
        GreenfootSound sound = new GreenfootSound("fireball.mp3");
        sound.setVolume(50);
        sound.play();
    }
    
    /**
     * Win sound effect.
     */
    public static void winSound(){
        GreenfootSound sound = new GreenfootSound("win.mp3");
        sound.setVolume(50);
        sound.play();
    }
    
    /**
     * Start sound effect.
     */
    public static void startSound(){
        GreenfootSound sound = new GreenfootSound("start.mp3");
        sound.setVolume(50);
        sound.play();
    }
    
    /**
     * Jump sound effect.
     */
    public static void jumpSound(){
        GreenfootSound sound = new GreenfootSound("jump.mp3");
        sound.play();
    }
}
