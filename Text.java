import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Text here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Text extends Actor
{
    private int lvlText;
    
    
    private GreenfootImage txtBeginning;
    private GreenfootImage txt1;
    private GreenfootImage txt2;
    
    /**
     * The text.
     */
    public Text(int lvlText){
        this.lvlText = lvlText;
        
        txtBeginning = new GreenfootImage("beginningtext.png");
        txt1 = new GreenfootImage("lvl2text.png");
        txt2 = new GreenfootImage("lvl1text.png");
    }
    
    /**
     * Act - do whatever the Text wants to do.
     */
    public void act()
    {
        setLevelText();
    }
    
    /**
     * Sets the lvlText int.
     */
    public void setLvlText(int lvlText){
        this.lvlText = lvlText;
    }
    
    /**
     * Sets the level text.
     */
    public void setLevelText(){
        beginning();
        lvlOne();
        lvlTwo();
    }
    
    /**
     * If the lvlText int is 0 then the text displayed is the beginning text.
     */
    public void beginning(){
        if(lvlText == 0){
            setImage(txtBeginning);
        }
    }
    
    /**
     * If the lvlText int is 1 then the text displayed is the level one text.
     */
    public void lvlOne(){
        if(lvlText == 1){
            setImage(txt1);
        }
    }
    
    /**
     * If the lvlText int is 2 then the text displayed is the level two text.
     */
    public void lvlTwo(){
        if(lvlText == 2){
            setImage(txt2);
        }
    }
}
