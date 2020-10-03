package swingy.model.Enemy;

import swingy.model.Weapons.Artifacts;

public class Monster extends Villian {

    public Monster(int level, int attack, int defence, int hitPoints, int experience, Artifacts artifacts){
        super(level, attack, defence, hitPoints, experience, artifacts);
        int typeId = 2;
        super.setTypeId(typeId);
    }
}
