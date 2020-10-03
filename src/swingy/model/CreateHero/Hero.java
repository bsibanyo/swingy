package swingy.model.CreateHero;

import swingy.model.Weapons.Artifacts;
import javax.validation.constraints.NotNull;

public class Hero {
    @NotNull
        private String newHero;
        @NotNull
        private Stats stats = new Stats();
        @NotNull
        private Artifacts artifacts;

        public Hero(){

        }

        protected Hero(String newHero, Stats stats, Artifacts artifacts){
            this.newHero = newHero;
            this.stats = stats;
            this.artifacts = artifacts;
        }

        public Stats getHeroStats(){
            return stats;
        }

        public Artifacts getArtifacts(){
            return artifacts;
        }

        public String getNewHero(){
            return this.newHero;
        }

        public void setArtifacts(Artifacts artifacts){
            this.artifacts = artifacts;
        }
}
