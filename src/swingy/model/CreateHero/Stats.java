package swingy.model.CreateHero;

public class Stats {

    private String heroType;
    private int level;
    private int attack;
    private int defence;
    private int hitPoints;
    private int experience;
    private int power;

    public Stats(){

    }

    public Stats(String type, int level, int attack, int defence, int hitPoints, int experience){
        this.heroType = type;
        this.level = level;
        this.attack = attack;
        this.defence = defence;
        this.hitPoints = hitPoints;
        this.experience = experience;
        this.power = attack + defence;
    }

//    GETTERS

    public String getHeroType(){
        return this.heroType;
    }

    public int getAttack(){
        return this.attack;
    }

    public int getLevel(){
        return this.level;
    }

    public int getDefence(){
        return this.defence;
    }

    public int getHitPoints(){
        return this.hitPoints;
    }

    public int getExperience(){
        return this.experience;
    }

    public int getPower(){
        return this.power;
    }

//    SETTERS
    public void setLevel(int level){
        this.level = level;
    }

    public void setAttack(int attack){
        this.attack = attack;
    }

    public void setDefence(int defence){
        this.defence += defence;
        if(this.defence <= 0){
            this.defence = 0;
        }
    }

    public void setHitPoints(int hitPoints){
        this.hitPoints += hitPoints;
        if(this.hitPoints <= 0){
            this.hitPoints = 0;
        }
    }

    public void setExperience(int experience){
        this.experience = experience;
    }

}
