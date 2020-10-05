package swingy.model.Enemy;

import swingy.model.Weapons.Artifacts;

public class Villian {
    private int xCoordinates;
    private int yCoordinates;
    private int level, attack, defence, hitPoints, experience, power;
    private long id;
    private long idCounter = 1;
    private int typeId;
    private Artifacts artifacts;
    private static final String enemies[] = {"GHOST", "MONSTER"};

    public Villian(){

    }

    public Villian(int level, int attack, int defence, int hitPoints, int experience, Artifacts artifacts) {

        this.level = level;
        this.attack = attack;
        this.defence = defence;
        this.hitPoints = hitPoints;
        this.experience = experience;
        this.id = nextID();
        this.power = attack + defence;
        this.artifacts = artifacts;
    }

    private long nextID(){
        idCounter ++;
        return idCounter;
    }

//    GETTERS
    public int getVertX(){
        return this.xCoordinates;
    }

    public int getVertY(){
        return this.yCoordinates;
    }

    public int getTypeId(){
        return this.typeId;
    }

    public int getLevel(){
        return this.level;
    }

    public int getHitPoints(){
        return this.hitPoints;
    }

    public int getPower(){
        return this.power;
    }

    public Artifacts getArtifacts(){
        return artifacts;
    }

//    SETTERS

    public void setLevel(int level){
        this.level += level;
    }

    public void setTypeId(int id){
        this.typeId =id;
    }

    public void setHitPoints(int hitPoints){
        this.hitPoints += hitPoints;
        if(this.hitPoints <= 0){
            this.hitPoints = 0;
        }
    }

    public void setVPosition(int x, int y){
        this.xCoordinates = x;
        this.yCoordinates = y;
    }

}
