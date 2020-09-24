import java.util.*;
public class BaseMove {
    
    private String name, displayname;
    private int power,pp,moveid,movetype,priority,targetid,damageid,effectid;
    private double accuracy,effectchance;
    
    public BaseMove(int moveid,String movename,int movetype,int power,int pp,Double accuracy,int priority,
                    int targetid,int damageid,int effectid,Double effectchance) {
        this.name = movename;
        this.moveid = moveid;
        this.movetype = movetype;
        this.power = power;
        this.pp = pp;
        this.accuracy = accuracy;
        this.priority = priority;
        this.targetid = targetid;
        this.damageid = damageid;
        this.effectid = effectid;
        this.effectchance = effectchance;
        
        String str = name;
        String s = str.substring(0,1).toUpperCase();
        str = s + str.substring(1);
        int index = 0;
        if (name.contains("-")) {
            index = name.indexOf("-");
            String st = str.substring(0,index+1);
            s = str.substring(index+1,index+2).toUpperCase();
            str = st + s + str.substring(index+2,str.length());
        }
        if (name.contains("-")) {
            index = name.indexOf("-",index+1);
            String st = str.substring(0,index+1);
            s = str.substring(index+1,index+2).toUpperCase();
            str = st + s + str.substring(index+2,str.length());
        }
        
        str = str.replace("-"," ");
        
        this.displayname = str;
    }
    
    public String getName() {
        return this.displayname;
    }
    
    public String getDataName() {
        return this.name;
    }
    
    public int getPower() {
        return this.power;
    }
    
    public String getCategory() {
        return findCategory(this.damageid);
    }
    
    public int getTargetID() {
        return this.targetid;
    }
    
    public int getPriority() {
        return this.priority;
    }
    
    public int getDamageID() {
        return this.damageid;
    }
    
    public int getEffectID() {
        return this.effectid;
    }
    
    public double getEffectChance() {
        return this.effectchance;
    }
    
    public double getAccuracy() {
        return this.accuracy;
    }
    
    public int getPP() {
        return this.pp;
    }
    
    public String getType() {
        return findType(this.movetype);
    }
    
    public int getTypeID() {
        return this.movetype;
    }
    
    public ArrayList<Integer> getTypeColor(int type) {
        ArrayList<Integer> colors = new ArrayList<Integer>();
        if (findType(type).equals("Water")) {
            colors.add(104);
            colors.add(152);
            colors.add(207);
        }
        else if (findType(type).equals("Fire")) {
            colors.add(248);
            colors.add(144);
            colors.add(48);
        }
        else if (findType(type).equals("Grass")) {
            colors.add(144);
            colors.add(232);
            colors.add(128);
        }
        else if (findType(type).equals("Ground")) {
            colors.add(191);
            colors.add(172);
            colors.add(33);
        }
        else if (findType(type).equals("Rock")) {
            colors.add(200);
            colors.add(160);
            colors.add(72);
        }
        else if (findType(type).equals("Poison")) {
            colors.add(224);
            colors.add(144);
            colors.add(248);
        }
        else if (findType(type).equals("Ghost")) {
            colors.add(168);
            colors.add(112);
            colors.add(248);
        }
        else if (findType(type).equals("Fighting")) {
            colors.add(248);
            colors.add(112);
            colors.add(112);
        }
        else if (findType(type).equals("Bug")) {
            colors.add(160);
            colors.add(200);
            colors.add(136);
        }
        else if (findType(type).equals("Flying")) {
            colors.add(88);
            colors.add(200);
            colors.add(240);
        }
        else if (findType(type).equals("Ice")) {
            colors.add(48);
            colors.add(216);
            colors.add(207);
        }
        else if (findType(type).equals("Dark")) {
            colors.add(144);
            colors.add(136);
            colors.add(136);
        }
        else if (findType(type).equals("Normal")) {
            colors.add(184);
            colors.add(184);
            colors.add(168);
        }
        else if (findType(type).equals("Steel")) {
            colors.add(184);
            colors.add(184);
            colors.add(208);
        }
        else if (findType(type).equals("Electric")) {
            colors.add(224);
            colors.add(224);
            colors.add(0);
        }
        else if (findType(type).equals("Dragon")) {
            colors.add(70);
            colors.add(130);
            colors.add(180);
        }
        else if (findType(type).equals("Fairy")) {
            colors.add(255);
            colors.add(101);
            colors.add(213);
        }
        else if (findType(type).equals("Psychic")) {
            colors.add(248);
            colors.add(56);
            colors.add(168);
        }
        return colors;
    }
    
    public String findType(int type) {
        if (type == 1) {return "Normal";}
        if (type == 2) {return "Fighting";}
        if (type == 3) {return "Flying";}
        if (type == 4) {return "Poison";}
        if (type == 5) {return "Ground";}
        if (type == 6) {return "Rock";}
        if (type == 7) {return "Bug";}
        if (type == 8) {return "Ghost";}
        if (type == 9) {return "Steel";}
        if (type == 10) {return "Fire";}
        if (type == 11) {return "Water";}
        if (type == 12) {return "Grass";}
        if (type == 13) {return "Electric";}
        if (type == 14) {return "Psychic";}
        if (type == 15) {return "Ice";}
        if (type == 16) {return "Dragon";}
        if (type == 17) {return "Dark";}
        if (type == 18) {return "Fairy";}
        return "";
    }
    
    public int findTypeID(String type) {
        if (type.equals("Normal")) {return 1;}
        if (type.equals("Fighting")) {return 2;}
        if (type.equals("Flying")) {return 3;}
        if (type.equals("Poison")) {return 4;}
        if (type.equals("Ground")) {return 5;}
        if (type.equals("Rock")) {return 6;}
        if (type.equals("Bug")) {return 7;}
        if (type.equals("Ghost")) {return 8;}
        if (type.equals("Steel")) {return 9;}
        if (type.equals("Fire")) {return 10;}
        if (type.equals("Water")) {return 11;}
        if (type.equals("Grass")) {return 12;}
        if (type.equals("Electric")) {return 13;}
        if (type.equals("Psychic")) {return 14;}
        if (type.equals("Ice")) {return 15;}
        if (type.equals("Dragon")) {return 16;}
        if (type.equals("Dark")) {return 17;}
        if (type.equals("Fairy")) {return 18;}
        return -1;
    }
    
    public Double typeEffectivenessMult(int type2) {
        int typemult = -1;
        if (this.movetype == 1) {
            if (type2 == 1) {typemult = 100;}
            if (type2 == 2) {typemult = 100;}
            if (type2 == 3) {typemult = 100;}
            if (type2 == 4) {typemult = 100;}
            if (type2 == 5) {typemult = 100;}
            if (type2 == 6) {typemult = 50;}
            if (type2 == 7) {typemult = 100;}
            if (type2 == 8) {typemult = 0;}
            if (type2 == 9) {typemult = 50;}
            if (type2 == 10) {typemult = 100;}
            if (type2 == 11) {typemult = 100;}
            if (type2 == 12) {typemult = 100;}
            if (type2 == 13) {typemult = 100;}
            if (type2 == 14) {typemult = 100;}
            if (type2 == 15) {typemult = 100;}
            if (type2 == 16) {typemult = 100;}
            if (type2 == 17) {typemult = 100;}
            if (type2 == 18) {typemult = 100;}
        }
        if (this.movetype == 2) {
            if (type2 == 1) {typemult = 200;}
            if (type2 == 2) {typemult = 100;}
            if (type2 == 3) {typemult = 50;}
            if (type2 == 4) {typemult = 50;}
            if (type2 == 5) {typemult = 100;}
            if (type2 == 6) {typemult = 200;}
            if (type2 == 7) {typemult = 50;}
            if (type2 == 8) {typemult = 0;}
            if (type2 == 9) {typemult = 200;}
            if (type2 == 10) {typemult = 100;}
            if (type2 == 11) {typemult = 100;}
            if (type2 == 12) {typemult = 100;}
            if (type2 == 13) {typemult = 100;}
            if (type2 == 14) {typemult = 50;}
            if (type2 == 15) {typemult = 200;}
            if (type2 == 16) {typemult = 100;}
            if (type2 == 17) {typemult = 200;}
            if (type2 == 18) {typemult = 50;}
        }
        if (this.movetype == 3) {
            if (type2 == 1) {typemult = 100;}
            if (type2 == 2) {typemult = 200;}
            if (type2 == 3) {typemult = 100;}
            if (type2 == 4) {typemult = 100;}
            if (type2 == 5) {typemult = 100;}
            if (type2 == 6) {typemult = 50;}
            if (type2 == 7) {typemult = 200;}
            if (type2 == 8) {typemult = 100;}
            if (type2 == 9) {typemult = 50;}
            if (type2 == 10) {typemult = 100;}
            if (type2 == 11) {typemult = 100;}
            if (type2 == 12) {typemult = 200;}
            if (type2 == 13) {typemult = 50;}
            if (type2 == 14) {typemult = 100;}
            if (type2 == 15) {typemult = 100;}
            if (type2 == 16) {typemult = 100;}
            if (type2 == 17) {typemult = 100;}
            if (type2 == 18) {typemult = 100;}
        }
        if (this.movetype == 4) {
            if (type2 == 1) {typemult = 100;}
            if (type2 == 2) {typemult = 100;}
            if (type2 == 3) {typemult = 100;}
            if (type2 == 4) {typemult = 50;}
            if (type2 == 5) {typemult = 50;}
            if (type2 == 6) {typemult = 50;}
            if (type2 == 7) {typemult = 100;}
            if (type2 == 8) {typemult = 50;}
            if (type2 == 9) {typemult = 0;}
            if (type2 == 10) {typemult = 100;}
            if (type2 == 11) {typemult = 100;}
            if (type2 == 12) {typemult = 200;}
            if (type2 == 13) {typemult = 100;}
            if (type2 == 14) {typemult = 100;}
            if (type2 == 15) {typemult = 100;}
            if (type2 == 16) {typemult = 100;}
            if (type2 == 17) {typemult = 100;}
            if (type2 == 18) {typemult = 200;}
        }
        if (this.movetype == 5) {
            if (type2 == 1) {typemult = 100;}
            if (type2 == 2) {typemult = 100;}
            if (type2 == 3) {typemult = 0;}
            if (type2 == 4) {typemult = 200;}
            if (type2 == 5) {typemult = 100;}
            if (type2 == 6) {typemult = 200;}
            if (type2 == 7) {typemult = 50;}
            if (type2 == 8) {typemult = 100;}
            if (type2 == 9) {typemult = 200;}
            if (type2 == 10) {typemult = 200;}
            if (type2 == 11) {typemult = 100;}
            if (type2 == 12) {typemult = 50;}
            if (type2 == 13) {typemult = 200;}
            if (type2 == 14) {typemult = 100;}
            if (type2 == 15) {typemult = 100;}
            if (type2 == 16) {typemult = 100;}
            if (type2 == 17) {typemult = 100;}
            if (type2 == 18) {typemult = 100;}
        }
        if (this.movetype == 6) {
            if (type2 == 1) {typemult = 100;}
            if (type2 == 2) {typemult = 50;}
            if (type2 == 3) {typemult = 200;}
            if (type2 == 4) {typemult = 100;}
            if (type2 == 5) {typemult = 50;}
            if (type2 == 6) {typemult = 100;}
            if (type2 == 7) {typemult = 200;}
            if (type2 == 8) {typemult = 100;}
            if (type2 == 9) {typemult = 50;}
            if (type2 == 10) {typemult = 200;}
            if (type2 == 11) {typemult = 100;}
            if (type2 == 12) {typemult = 100;}
            if (type2 == 13) {typemult = 100;}
            if (type2 == 14) {typemult = 100;}
            if (type2 == 15) {typemult = 200;}
            if (type2 == 16) {typemult = 100;}
            if (type2 == 17) {typemult = 100;}
            if (type2 == 18) {typemult = 100;}
        }
        if (this.movetype == 7) {
            if (type2 == 1) {typemult = 100;}
            if (type2 == 2) {typemult = 50;}
            if (type2 == 3) {typemult = 50;}
            if (type2 == 4) {typemult = 50;}
            if (type2 == 5) {typemult = 100;}
            if (type2 == 6) {typemult = 100;}
            if (type2 == 7) {typemult = 100;}
            if (type2 == 8) {typemult = 50;}
            if (type2 == 9) {typemult = 50;}
            if (type2 == 10) {typemult = 50;}
            if (type2 == 11) {typemult = 100;}
            if (type2 == 12) {typemult = 200;}
            if (type2 == 13) {typemult = 100;}
            if (type2 == 14) {typemult = 200;}
            if (type2 == 15) {typemult = 100;}
            if (type2 == 16) {typemult = 100;}
            if (type2 == 17) {typemult = 200;}
            if (type2 == 18) {typemult = 50;}
        }
        if (this.movetype == 8) {
            if (type2 == 1) {typemult = 0;}
            if (type2 == 2) {typemult = 100;}
            if (type2 == 3) {typemult = 100;}
            if (type2 == 4) {typemult = 100;}
            if (type2 == 5) {typemult = 100;}
            if (type2 == 6) {typemult = 100;}
            if (type2 == 7) {typemult = 100;}
            if (type2 == 8) {typemult = 200;}
            if (type2 == 9) {typemult = 100;}
            if (type2 == 10) {typemult = 100;}
            if (type2 == 11) {typemult = 100;}
            if (type2 == 12) {typemult = 100;}
            if (type2 == 13) {typemult = 100;}
            if (type2 == 14) {typemult = 200;}
            if (type2 == 15) {typemult = 100;}
            if (type2 == 16) {typemult = 100;}
            if (type2 == 17) {typemult = 50;}
            if (type2 == 18) {typemult = 100;}
        }
        if (this.movetype == 9) {
            if (type2 == 1) {typemult = 100;}
            if (type2 == 2) {typemult = 100;}
            if (type2 == 3) {typemult = 100;}
            if (type2 == 4) {typemult = 100;}
            if (type2 == 5) {typemult = 100;}
            if (type2 == 6) {typemult = 200;}
            if (type2 == 7) {typemult = 100;}
            if (type2 == 8) {typemult = 100;}
            if (type2 == 9) {typemult = 50;}
            if (type2 == 10) {typemult = 50;}
            if (type2 == 11) {typemult = 50;}
            if (type2 == 12) {typemult = 100;}
            if (type2 == 13) {typemult = 50;}
            if (type2 == 14) {typemult = 100;}
            if (type2 == 15) {typemult = 200;}
            if (type2 == 16) {typemult = 100;}
            if (type2 == 17) {typemult = 100;}
            if (type2 == 18) {typemult = 200;}
        }
        if (this.movetype == 10) {
            if (type2 == 1) {typemult = 100;}
            if (type2 == 2) {typemult = 100;}
            if (type2 == 3) {typemult = 100;}
            if (type2 == 4) {typemult = 100;}
            if (type2 == 5) {typemult = 100;}
            if (type2 == 6) {typemult = 50;}
            if (type2 == 7) {typemult = 200;}
            if (type2 == 8) {typemult = 100;}
            if (type2 == 9) {typemult = 200;}
            if (type2 == 10) {typemult = 50;}
            if (type2 == 11) {typemult = 50;}
            if (type2 == 12) {typemult = 200;}
            if (type2 == 13) {typemult = 100;}
            if (type2 == 14) {typemult = 100;}
            if (type2 == 15) {typemult = 200;}
            if (type2 == 16) {typemult = 50;}
            if (type2 == 17) {typemult = 100;}
            if (type2 == 18) {typemult = 100;}
        }
        if (this.movetype == 11) {
            if (type2 == 1) {typemult = 100;}
            if (type2 == 2) {typemult = 100;}
            if (type2 == 3) {typemult = 100;}
            if (type2 == 4) {typemult = 100;}
            if (type2 == 5) {typemult = 200;}
            if (type2 == 6) {typemult = 200;}
            if (type2 == 7) {typemult = 100;}
            if (type2 == 8) {typemult = 100;}
            if (type2 == 9) {typemult = 100;}
            if (type2 == 10) {typemult = 200;}
            if (type2 == 11) {typemult = 50;}
            if (type2 == 12) {typemult = 50;}
            if (type2 == 13) {typemult = 100;}
            if (type2 == 14) {typemult = 100;}
            if (type2 == 15) {typemult = 100;}
            if (type2 == 16) {typemult = 50;}
            if (type2 == 17) {typemult = 100;}
            if (type2 == 18) {typemult = 100;}
        }
        if (this.movetype == 12) {
            if (type2 == 1) {typemult = 100;}
            if (type2 == 2) {typemult = 100;}
            if (type2 == 3) {typemult = 50;}
            if (type2 == 4) {typemult = 50;}
            if (type2 == 5) {typemult = 200;}
            if (type2 == 6) {typemult = 200;}
            if (type2 == 7) {typemult = 50;}
            if (type2 == 8) {typemult = 100;}
            if (type2 == 9) {typemult = 50;}
            if (type2 == 10) {typemult = 50;}
            if (type2 == 11) {typemult = 200;}
            if (type2 == 12) {typemult = 50;}
            if (type2 == 13) {typemult = 100;}
            if (type2 == 14) {typemult = 100;}
            if (type2 == 15) {typemult = 100;}
            if (type2 == 16) {typemult = 50;}
            if (type2 == 17) {typemult = 100;}
            if (type2 == 18) {typemult = 100;}
        }
        if (this.movetype == 13) {
            if (type2 == 1) {typemult = 100;}
            if (type2 == 2) {typemult = 100;}
            if (type2 == 3) {typemult = 200;}
            if (type2 == 4) {typemult = 100;}
            if (type2 == 5) {typemult = 0;}
            if (type2 == 6) {typemult = 100;}
            if (type2 == 7) {typemult = 100;}
            if (type2 == 8) {typemult = 100;}
            if (type2 == 9) {typemult = 100;}
            if (type2 == 10) {typemult = 100;}
            if (type2 == 11) {typemult = 200;}
            if (type2 == 12) {typemult = 50;}
            if (type2 == 13) {typemult = 50;}
            if (type2 == 14) {typemult = 100;}
            if (type2 == 15) {typemult = 100;}
            if (type2 == 16) {typemult = 50;}
            if (type2 == 17) {typemult = 100;}
            if (type2 == 18) {typemult = 100;}
        }
        if (this.movetype == 14) {
            if (type2 == 1) {typemult = 100;}
            if (type2 == 2) {typemult = 200;}
            if (type2 == 3) {typemult = 100;}
            if (type2 == 4) {typemult = 200;}
            if (type2 == 5) {typemult = 100;}
            if (type2 == 6) {typemult = 100;}
            if (type2 == 7) {typemult = 100;}
            if (type2 == 8) {typemult = 100;}
            if (type2 == 9) {typemult = 50;}
            if (type2 == 10) {typemult = 100;}
            if (type2 == 11) {typemult = 100;}
            if (type2 == 12) {typemult = 100;}
            if (type2 == 13) {typemult = 100;}
            if (type2 == 14) {typemult = 50;}
            if (type2 == 15) {typemult = 100;}
            if (type2 == 16) {typemult = 100;}
            if (type2 == 17) {typemult = 0;}
            if (type2 == 18) {typemult = 100;}
        }
        if (this.movetype == 15) {
            if (type2 == 1) {typemult = 100;}
            if (type2 == 2) {typemult = 100;}
            if (type2 == 3) {typemult = 200;}
            if (type2 == 4) {typemult = 100;}
            if (type2 == 5) {typemult = 200;}
            if (type2 == 6) {typemult = 100;}
            if (type2 == 7) {typemult = 100;}
            if (type2 == 8) {typemult = 100;}
            if (type2 == 9) {typemult = 50;}
            if (type2 == 10) {typemult = 50;}
            if (type2 == 11) {typemult = 50;}
            if (type2 == 12) {typemult = 200;}
            if (type2 == 13) {typemult = 100;}
            if (type2 == 14) {typemult = 100;}
            if (type2 == 15) {typemult = 50;}
            if (type2 == 16) {typemult = 200;}
            if (type2 == 17) {typemult = 100;}
            if (type2 == 18) {typemult = 100;}
        }
        if (this.movetype == 16) {
            if (type2 == 1) {typemult = 100;}
            if (type2 == 2) {typemult = 100;}
            if (type2 == 3) {typemult = 100;}
            if (type2 == 4) {typemult = 100;}
            if (type2 == 5) {typemult = 100;}
            if (type2 == 6) {typemult = 100;}
            if (type2 == 7) {typemult = 100;}
            if (type2 == 8) {typemult = 100;}
            if (type2 == 9) {typemult = 50;}
            if (type2 == 10) {typemult = 100;}
            if (type2 == 11) {typemult = 100;}
            if (type2 == 12) {typemult = 100;}
            if (type2 == 13) {typemult = 100;}
            if (type2 == 14) {typemult = 100;}
            if (type2 == 15) {typemult = 100;}
            if (type2 == 16) {typemult = 200;}
            if (type2 == 17) {typemult = 100;}
            if (type2 == 18) {typemult = 0;}
        }
        if (this.movetype == 17) {
            if (type2 == 1) {typemult = 100;}
            if (type2 == 2) {typemult = 50;}
            if (type2 == 3) {typemult = 100;}
            if (type2 == 4) {typemult = 100;}
            if (type2 == 5) {typemult = 100;}
            if (type2 == 6) {typemult = 100;}
            if (type2 == 7) {typemult = 100;}
            if (type2 == 8) {typemult = 200;}
            if (type2 == 9) {typemult = 100;}
            if (type2 == 10) {typemult = 100;}
            if (type2 == 11) {typemult = 100;}
            if (type2 == 12) {typemult = 100;}
            if (type2 == 13) {typemult = 100;}
            if (type2 == 14) {typemult = 200;}
            if (type2 == 15) {typemult = 100;}
            if (type2 == 16) {typemult = 100;}
            if (type2 == 17) {typemult = 50;}
            if (type2 == 18) {typemult = 50;}
        }
        if (this.movetype == 18) {
            if (type2 == 1) {typemult = 100;}
            if (type2 == 2) {typemult = 200;}
            if (type2 == 3) {typemult = 100;}
            if (type2 == 4) {typemult = 50;}
            if (type2 == 5) {typemult = 100;}
            if (type2 == 6) {typemult = 100;}
            if (type2 == 7) {typemult = 100;}
            if (type2 == 8) {typemult = 100;}
            if (type2 == 9) {typemult = 50;}
            if (type2 == 10) {typemult = 50;}
            if (type2 == 11) {typemult = 100;}
            if (type2 == 12) {typemult = 100;}
            if (type2 == 13) {typemult = 100;}
            if (type2 == 14) {typemult = 100;}
            if (type2 == 15) {typemult = 100;}
            if (type2 == 16) {typemult = 200;}
            if (type2 == 17) {typemult = 200;}
            if (type2 == 18) {typemult = 100;}
        }
        
        Double doubletypemult = typemult * 1.0;
        return doubletypemult/100;
    }
    
    public String findCategory(int type) {
        if (type == 1) {return "Status";}
        if (type == 2) {return "Physical";}
        if (type == 3) {return "Special";}
        return "";
    }
    
    
}