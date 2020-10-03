package swingy.model.Enemy;

import swingy.model.Weapons.Artifacts;

public class Ghost extends Villian{

    public Ghost(int level, int attack, int defence, int hitPoints, int experience, Artifacts artifacts){
        super(level, attack, defence, hitPoints, experience, artifacts);
        int typeId = 1;
        super.setTypeId(typeId);
    }
}
