package swingy.model.Weapons;

public class Armor extends Artifacts{
    private int defence = 100;

    public Armor(String type){
        super(type);
    }

    public int getDefence(){
        return this.defence;
    }
}
