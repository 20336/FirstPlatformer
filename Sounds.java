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
        GreenfootSound sound = new GreenfootSound("sword.wav");
        sound.setVolume(50);
        sound.play();
    }
}
