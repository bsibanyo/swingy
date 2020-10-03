package swingy.model.Weapons;

public class Helm extends Artifacts{

    private int hitPoints = 75;

    public Helm(String type){
        super(type);
    }

    public int getHitPoints(){
        return this.hitPoints;
    }

}
