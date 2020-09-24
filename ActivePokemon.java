import java.util.*;
import java.lang.Math;

public class ActivePokemon {
    
    private String name,dataname,nickname,nature,type1;
    private String type2;
    private int level;
    private int basehp,baseatk,basedef,basespatk,basespdef,basespeed;
    private int maxhp,currenthp,currentatk,currentdef,currentspatk,currentspdef,currentspeed;
    private int hpiv,atkiv,defiv,spatkiv,spdefiv,speediv;
    private int hpev,atkev,defev,spatkev,spdefev,speedev;
    private int atkboost, defboost, spatkboost, spdefboost, speedboost, evasionboost, accuracyboost;
    private NonVolatileStatus nonvolatilestatus;
    private ArrayList<BaseMove> moveset;
    private BasePokemon basepokemon;
    
    public ActivePokemon(String nickname,String nature, String ability, int level, ArrayList<BaseMove> moveset, BasePokemon basepokemon, int hpiv, int atkiv, int defiv, int spatkiv, int spdefiv, int speediv,
                         int hpev, int atkev, int defev, int spatkev, int spdefev, int speedev)
    {
        
        this.nickname = nickname;
        this.nature = nature;
        this.level = level;
        this.moveset = moveset;
        this.name = basepokemon.getName();
        this.dataname = basepokemon.getDataName();
        this.basehp = basepokemon.getBaseHP();
        this.baseatk = basepokemon.getBaseAtk();
        this.basedef = basepokemon.getBaseDef();
        this.basespatk = basepokemon.getBaseSpAtk();
        this.basespdef = basepokemon.getBaseSpDef();
        this.basespeed = basepokemon.getBaseSpeed();
        this.hpiv = hpiv;
        this.atkiv = atkiv;
        this.defiv = defiv;
        this.spatkiv = spatkiv;
        this.spdefiv = spdefiv;
        this.speediv = speediv;
        this.hpev = hpev;
        this.atkev = atkev;
        this.defev = defev;
        this.spatkev = spatkev;
        this.spdefev = spdefev;
        this.speedev = speedev;
        this.name = name;
        this.autoSetCurrentHP(this.basehp);
        this.autoSetCurrentAtk(this.baseatk,1);
        this.autoSetCurrentDef(this.basedef,1);
        this.autoSetCurrentSpAtk(this.basespatk,1);
        this.autoSetCurrentSpDef(this.basespdef,1);
        this.autoSetCurrentSpeed(this.basespeed,1);
        this.basepokemon = basepokemon;
        
        
    }
    
    public String toString() {
        String str = name + ": \nHP - " + currenthp + " \n Atk - " + currentatk + " \n Def - " + currentdef + " \n SpAtk - " + currentspatk + " \n SpDef - " + currentspdef
            + " \n Speed - " + currentspeed;
        return str;
    }
    
    public boolean hasType2() {
        if (type2.equals(""))
            return false;
        else
            return true;
    }
    
    public boolean hasFainted() {
        if (this.currenthp <= 0)
            return true;
        else
            return false;
    }
    
    
    public String getName() {
        return this.name;
    }
    
    public int getLevel() {
        return this.level;
    }
    
    public String getDataName() {
        return this.dataname;
    }
    
    public NonVolatileStatus getNonVolatileStatus() {
            return nonvolatilestatus;
    }
    
    public String getType1() {
        return this.type1;
    }
    
    public String getType2() {
        return this.type2;
    }
    
    public ArrayList<BaseMove> getMoveset() {
        return this.moveset;
    }
    
    public int getAtkBoost() {
        return this.atkboost;
    }
    
    public void setAtkBoost(int atkboost) {
        this.atkboost = atkboost;
    }
    
    public int getDefBoost() {
        return this.defboost;
    }
    
    public void setDefBoost(int defboost) {
        this.defboost = defboost;
    }
    
    public int getSpAtkBoost() {
        return this.spatkboost;
    }
    
    public void setSpAtkBoost(int spatkboost) {
        this.spatkboost = spatkboost;
    }
    
    public int getSpDefBoost() {
        return this.spdefboost;
    }
    
    public void setSpDefBoost(int spdefboost) {
        this.spdefboost = spdefboost;
    }
    
    public int getSpeedBoost() {
        return this.speedboost;
    }
    
    public void setSpeedBoost(int speedboost) {
        this.speedboost = speedboost;
    }
    
    public int getEvasionBoost() {
        return this.evasionboost;
    }
    
    public void setEvasionBoost(int evasionboost) {
        this.evasionboost = evasionboost;
    }
    
    public int getAccuracyBoost() {
        return this.accuracyboost;
    }
    
    public void setAccuracyBoost(int accuracyboost) {
        this.atkboost = accuracyboost;
    }
    
    public Double getStageMult(String stat, boolean inverse) {
        int inverseMult = 1;
        if (inverse)
            inverseMult = -1;
        if (stat.equals("Atk"))
            return stageMultConverter(this.atkboost*inverseMult,0);
        if (stat.equals("Def"))
            return stageMultConverter(this.defboost*inverseMult,0);
        if (stat.equals("SpAtk"))
            return stageMultConverter(this.spatkboost*inverseMult,0);
        if (stat.equals("SpDef"))
            return stageMultConverter(this.spdefboost*inverseMult,0);
        if (stat.equals("Speed"))
            return stageMultConverter(this.speedboost*inverseMult,0);
        if (stat.equals("Accuracy"))
            return stageMultConverter(this.accuracyboost*inverseMult,1);
        if (stat.equals("Evasion"))
            return stageMultConverter(this.evasionboost*inverseMult,1);
        return 1.0;
    }
    
    
    private Double stageMultConverter(int boost, int id) {
        if (id==0) {
            if (boost >= 0)
                return 1.0*((2+boost)/2);
            else
                return 1.0*(2/(2-boost));
        }
        else {
            if (boost >= 0)
                return 1.0*((3+boost)/3);
            else
                return 1.0*(3/(3-boost));
        }
    }
    
    public int getCurrentHP() {
        return this.currenthp;
    }
    
    public void setCurrentHP(int hp) {
        this.currenthp = hp;
    }
    
    public void autoSetCurrentHP(int basehp) {
        this.currenthp = (int)Math.floor(((2*basehp+hpiv+hpev/4)*level/100)+level+10);
        this.maxhp = this.currenthp;
    }
    
    public int getMaxHP() {
        return this.maxhp;
    }
    
    public void takeDamage(int damage) {
        this.currenthp -= damage;
    }
    
    public int getCurrentAtk() {
        return this.currentatk;
    }
    
    public void setCurrentAtk(int currentatk) {
        this.currentatk = currentatk;
    }
    
    public void autoSetCurrentAtk(int baseatk, double multiplier) {
        
        this.currentatk = (int)Math.floor(((2*baseatk+atkiv+(atkev/4))*level)/100)+5;
        if (nature.equals("Lonely") || nature.equals("Brave") || nature.equals("Adamant") || nature.equals("Naughty"))
            multiplier = multiplier * 1.1;
        if (nature.equals("Bold") || nature.equals("Timid") || nature.equals("Modest") || nature.equals("Calm"))
            multiplier = multiplier * .9;
        multiplier = multiplier*getStageMult("Atk", false);
        currentatk = (int)(Math.floor(currentatk * multiplier));
    }
    
    public int getCurrentDef() {
        return this.currentdef;
    }
    
    public void setCurrentDef(int currentdef) {
        this.currentdef = currentdef;
    }
    
    public void autoSetCurrentDef(int basedef,double multiplier) {
        this.currentdef = (int)Math.floor(((2*basedef+defiv+(defev/4))*level)/100)+5;
        if (nature.equals("Bold") || nature.equals("Relaxed") || nature.equals("Impish") || nature.equals("Lax"))
            multiplier = multiplier * 1.1;
        if (nature.equals("Lonely") || nature.equals("Hasty") || nature.equals("Mild") || nature.equals("Gentle"))
            multiplier = multiplier * .9;  
        multiplier = multiplier*getStageMult("Def", false);
        currentdef = (int)Math.floor(currentdef * multiplier);
    }
    
    public int getCurrentSpAtk() {
        return this.currentspatk;
    }
    
    public void setCurrentSpAtk(int currentspatk) {
        this.currentspatk = currentspatk;
    }
    
    public void autoSetCurrentSpAtk(int basespatk,double multiplier) {
        this.currentspatk = (int)Math.floor(((2*basespatk+spatkiv+(spatkev/4))*level)/100)+5;
        if (nature.equals("Modest") || nature.equals("Mild") || nature.equals("Quiet") || nature.equals("Rash"))
            multiplier = multiplier * 1.1;
        if (nature.equals("Adamant") || nature.equals("Impish") || nature.equals("Jolly") || nature.equals("Careful"))
            multiplier = multiplier * .9;  
        multiplier = multiplier*getStageMult("SpAtk", false);
        currentspatk = (int)Math.floor(currentspatk * multiplier);
    }
    
    public int getCurrentSpDef() {
        return this.currentspdef;
    }
    
    public void setCurrentSpDef(int currentspdef) {
        this.currentspdef = currentspdef;
    }
    
    public void autoSetCurrentSpDef(int basespdef, double multiplier) {
        this.currentspdef = (int)Math.floor(((2*basespdef+spdefiv+(spdefev/4))*level)/100)+5;
        if (nature.equals("Calm") || nature.equals("Gentle") || nature.equals("Sassy") || nature.equals("Careful"))
            multiplier = multiplier * 1.1;
        if (nature.equals("Naughty") || nature.equals("Lax") || nature.equals("Naive") || nature.equals("Rash"))
            multiplier = multiplier * .9; 
        multiplier = multiplier*getStageMult("SpDef", false);
        currentspdef = (int)(Math.floor(currentspdef * multiplier));
    }
    
    public int getCurrentSpeed() {
        return this.currentspeed;
    }
    
    public void setCurrentSpeed(int currentspeed) {
        this.currentspeed = currentspeed;
    }
    
    public void autoSetCurrentSpeed(int basespeed,double multiplier) {
        this.currentspeed = (int)Math.floor(((2*basespeed+speediv+(speedev/4))*level)/100)+5;
        if (nature.equals("Timid") || nature.equals("Jolly") || nature.equals("Hasty") || nature.equals("Naive"))
            multiplier = multiplier * 1.1;
        if (nature.equals("Brave") || nature.equals("Relaxed") || nature.equals("Quiet") || nature.equals("Sassy"))
            multiplier = multiplier * .9;
        multiplier = multiplier*getStageMult("Speed", false);
        currentspeed = (int)(Math.floor(currentspeed * multiplier));
    } 
    
    public BasePokemon getBasePokemon() {
        return this.basepokemon;
    }
    
    public void addNonVolatileStatus(NonVolatileStatus status) {
        if (this.nonvolatilestatus == null) {
            this.nonvolatilestatus = status;
        }
    }
    
    public void setNonVolatileStatus(NonVolatileStatus nvs) {
        this.nonvolatilestatus = nvs;
    }
    
    public void progressNonVolatileStatus() {
        
    }
    
    public void progressVolatileStatus() {
        
    }
}


