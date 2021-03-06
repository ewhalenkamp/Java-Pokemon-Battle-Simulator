import java.util.*;
import java.io.*;
import java.lang.Math;

public class Game {
    
    private ArrayList<ActivePokemon> team1List, team2List, team1LegacyList, team1InactiveList, team2LegacyList, team2InactiveList;
    private boolean wrongselect;
    private String selection1,selection2;
    private int test;
    private BaseMove move1, move2;
    private ActivePokemon pokemon1, pokemon2;
    private ActivePokemon currentpokemon1, currentpokemon2;
    private DataClass data;
    private double buttonwidth = 200.0;
    private double buttonheight = 100.0;
    private Draw draw;
    private int width = 1000;
    private int height = 750;
    
    public Game(ArrayList<ActivePokemon> team1List, ArrayList<ActivePokemon> team2List, DataClass data) {
        this.team1List = team1List;
        this.team2List = team2List;
        this.data = data;
        this.team1LegacyList = team1List;
        this.team2LegacyList = team2List;
        this.team1InactiveList = new ArrayList<ActivePokemon>();
        this.team2InactiveList = new ArrayList<ActivePokemon>();
        
        draw = new Draw(width,height);
        draw.initCanvas();
    }
    
    public int runGame() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Team 1:");
        System.out.println();
        for (ActivePokemon pokemon : team1List) {
            System.out.println(pokemon.getName());
        }
        System.out.println();
        System.out.println("Team 2:");
        System.out.println();
        for (ActivePokemon pokemon : team2List) {
            System.out.println(pokemon.getName());
        }
        
        System.out.println();
        
        System.out.println("Player One: Please select your first Pokemon");
        
        System.out.println();
        
        selectPokemon(1,false);
        
        System.out.println("Player Two: Please select your first Pokemon");
        
        selectPokemon(2,false);
        
        
        int turncounter = 0;
        Double badlyPoisonedMult1 = 0.0;
        Double badlyPoisonedMult2 = 0.0;
        
        //-------------------------------------MAIN WHILE LOOP-------------------------------------
        
        while (true) {
            
            //-----------------PRE-TURN-----------------
            
            turncounter++;
            System.out.println("----------TURN " + turncounter + "----------");
            
            
            
            //apply paralysis speed block
            if (currentpokemon1.getNonVolatileStatus() != null) {
                if (currentpokemon1.getNonVolatileStatus().getName().equals("Paralysis"))
                    currentpokemon1.autoSetCurrentSpeed(currentpokemon1.getBasePokemon().getBaseSpeed(),.5);
            }
            
            if (currentpokemon2.getNonVolatileStatus() != null) {
                if (currentpokemon2.getNonVolatileStatus().getName().equals("Paralysis"))
                    currentpokemon2.autoSetCurrentSpeed(currentpokemon2.getBasePokemon().getBaseSpeed(),.5);
            }
            
            System.out.println();
            
            System.out.println(currentpokemon1.getName() + ": " + currentpokemon1.getCurrentHP() + "/" + currentpokemon1.getMaxHP());
            System.out.println(currentpokemon2.getName() + ": " + currentpokemon2.getCurrentHP() + "/" + currentpokemon2.getMaxHP());
            
            Boolean team1attack = false;
            Boolean team1swap = false;
            Boolean team2attack = false;
            Boolean team2swap = false;
            
            draw.drawField(team1List, team2List);
            
            ArrayList<Button> buttonList = new ArrayList<Button>();
            
            //---------TEAM 1 DECISION---------
            
            while (true) {
                System.out.println("What will " + currentpokemon1.getName() +  " do?");
                System.out.println();
                for (BaseMove move : currentpokemon1.getMoveset()) {
                    Double posX = 50.0+(buttonwidth/2);
                    Double posY = 50.0+(buttonheight/2);
                    ArrayList<Integer> rgb = new ArrayList<Integer>();
                    rgb = move.getTypeColor(move.getTypeID());
                    if (currentpokemon1.getMoveset().indexOf(move) <= 1)
                        posY += 10.0+buttonheight;
                    if (currentpokemon1.getMoveset().indexOf(move) % 2 != 0)
                        posX += 10.0+buttonwidth;
                    
                    Button button = new Button(posX,posY,buttonwidth,buttonheight,move);
                    button.drawMoveButton();
                    
                    buttonList.add(button);
                }
                
                Button button = new Button(width-buttonwidth/2-50,50+(buttonheight/2),buttonwidth,buttonheight,"Swap");
                button.drawButton();
                buttonList.add(button);
                draw.drawField(team1List, team2List);
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.text(width/2,50+(buttonheight)+10+buttonheight+20,currentpokemon1.toString());
                
                StdDraw.show();
                StdDraw.clear(StdDraw.WHITE);
                
                int result = draw.waitForInput(buttonList);
                
                if (result == 4) {
                    if (selectPokemon(1)) {
                        team1swap = true;
                        break;
                    }
                    else {
                        buttonList.clear();
                        continue;
                    }
                }
                else {
                    move1 = currentpokemon1.getMoveset().get(result);
                    team1attack = true;
                    break;
                }
            }
            
            buttonList.clear();
            StdDraw.clear(StdDraw.WHITE);
            
            //---------TEAM 2 DECISION---------
            while (true) {
                System.out.println("What will " + currentpokemon2.getName() +  " do?");
                System.out.println();
                for (BaseMove move : currentpokemon2.getMoveset()) {
                    Double posX = 50.0+(buttonwidth/2);
                    Double posY = 50.0+(buttonheight/2);
                    ArrayList<Integer> rgb = new ArrayList<Integer>();
                    rgb = move.getTypeColor(move.getTypeID());
                    if (currentpokemon2.getMoveset().indexOf(move) <= 1)
                        posY += 10.0+buttonheight;
                    if (currentpokemon2.getMoveset().indexOf(move) % 2 != 0)
                        posX += 10.0+buttonwidth;
                    
                    Button button = new Button(posX,posY,buttonwidth,buttonheight,move);
                    button.drawMoveButton();
                    
                    buttonList.add(button);
                }
                Button button = new Button(width-buttonwidth/2-50,50+(buttonheight/2),buttonwidth,buttonheight,"Swap");
                button.drawButton();
                buttonList.add(button);
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.text(width/2,50+(buttonheight)+10+buttonheight+20,currentpokemon2.toString());
                
                draw.drawField(team1List, team2List);
                StdDraw.show();
                StdDraw.clear(StdDraw.WHITE);
                
                int result = draw.waitForInput(buttonList);
                
                if (result == 4) {
                    if (selectPokemon(2)) {
                        team2swap = true;
                        break;
                    }
                    else {
                        buttonList.clear();
                        continue;
                    }
                }
                else {
                    move2 = currentpokemon2.getMoveset().get(result);
                    team2attack = true;
                    break;
                }
            }
            
            System.out.println();
            
            
            //-----------------TURN-----------------
            
            boolean onehit = false;
            boolean twohit = false;
            
            //---------DETERMINE TURN ORDER---------
            //swapping has highest prio, meaning it will always go before an attack unless that attack is Pursuit
            
            //----BOTH SWAP----
            
            if (team1swap && team2swap) {
                if (swap(1,pokemon1))
                    badlyPoisonedMult1 = 0.0;
                draw.drawField(team1List, team2List);
                StdDraw.pause(500);
                if (swap(2,pokemon2))
                    badlyPoisonedMult2 = 0.0;
                draw.drawField(team1List, team2List);
                StdDraw.pause(500);
            }
            updateCurrents();
            
            //----T1 ATTACKS T2 SWAPS----
            if (team1attack && !team2attack) {
                if (swap(2,pokemon2))
                    badlyPoisonedMult2 = 0.0;
                draw.drawField(team1List, team2List);
                StdDraw.pause(500);
                onehit = attack(1,move1);
                draw.drawField(team1List, team2List);
                StdDraw.pause(500);
            }
            updateCurrents();
            
            //----T1 SWAPS T2 ATTACKS----
            if (!team1attack && team2attack) {
                if (swap(1,pokemon1))
                    badlyPoisonedMult1 = 0.0;
                draw.drawField(team1List, team2List);
                StdDraw.pause(500);
                twohit = attack(2,move2);
                draw.drawField(team1List, team2List);
                StdDraw.pause(500);
            }
            updateCurrents();
            
            //----BOTH ATTACK----
            
            if (team1attack && team2attack) {
                
                //determine who moves first by priority>speed>random
                
                if (move1.getPriority() == move2.getPriority()) {
                    if (currentpokemon1.getCurrentSpeed() == currentpokemon2.getCurrentSpeed()) {
                        Double rand = Math.random();
                        if (rand < .5) {
                            onehit = attack(1,move1);
                            draw.drawField(team1List, team2List);
                            StdDraw.pause(500);
                            if (!onehit)
                                System.out.println(currentpokemon1.getName() + "'s Move Failed!");
                            twohit = attack(2,move2);
                            draw.drawField(team1List, team2List);
                            StdDraw.pause(500);
                            if (!twohit)
                                System.out.println(currentpokemon2.getName() + "'s Move Failed!");
                        }
                        else if (rand >= .5) {
                            twohit = attack(2,move2);
                            draw.drawField(team1List, team2List);
                            StdDraw.pause(500);
                            if (!twohit)
                                System.out.println(currentpokemon2.getName() + "'s Move Failed!");
                            onehit = attack(1,move1);
                            draw.drawField(team1List, team2List);
                            StdDraw.pause(500);
                            if (!onehit)
                                System.out.println(currentpokemon1.getName() + "'s Move Failed!");
                        }
                    }
                    else if (currentpokemon1.getCurrentSpeed() > currentpokemon2.getCurrentSpeed()) {
                        onehit = attack(1,move1);
                        draw.drawField(team1List, team2List);
                        StdDraw.pause(500);
                        if (!onehit)
                            System.out.println(currentpokemon1.getName() + "'s Move Failed!");
                        twohit = attack(2,move2);
                        draw.drawField(team1List, team2List);
                        StdDraw.pause(500);
                        if (!twohit)
                            System.out.println(currentpokemon2.getName() + "'s Move Failed!");
                        
                        
                    }
                    else if (currentpokemon1.getCurrentSpeed() < currentpokemon2.getCurrentSpeed()) {
                        twohit = attack(2,move2);
                        draw.drawField(team1List, team2List);
                        StdDraw.pause(500);
                        if (!twohit)
                            System.out.println(currentpokemon2.getName() + "'s Move Failed!");
                        onehit = attack(1,move1);
                        draw.drawField(team1List, team2List);
                        StdDraw.pause(500);
                        if (!onehit)
                            System.out.println(currentpokemon1.getName() + "'s Move Failed!");
                    }
                }
                else if (move1.getPriority() > move2.getPriority()) {
                    onehit = attack(1,move1);
                    draw.drawField(team1List, team2List);
                    StdDraw.pause(500);
                    if (!onehit)
                        System.out.println(currentpokemon1.getName() + "'s Move Failed!");
                    twohit = attack(2,move2);
                    draw.drawField(team1List, team2List);
                    StdDraw.pause(500);
                    if (!twohit)
                        System.out.println(currentpokemon2.getName() + "'s Move Failed!");
                }
                else if (move1.getPriority() < move2.getPriority()) {
                    twohit = attack(2,move2);
                    draw.drawField(team1List, team2List);
                    StdDraw.pause(500);
                    if (!twohit)
                        System.out.println(currentpokemon2.getName() + "'s Move Failed!");
                    onehit = attack(1,move1);
                    draw.drawField(team1List, team2List);
                    StdDraw.pause(500);
                    if (!onehit)
                        System.out.println(currentpokemon1.getName() + "'s Move Failed!");
                }
            }
            
            System.out.println();
            
            System.out.println(currentpokemon1.getName() + ": " + currentpokemon1.getCurrentHP() + "/" + currentpokemon1.getMaxHP());
            System.out.println(currentpokemon2.getName() + ": " + currentpokemon2.getCurrentHP() + "/" + currentpokemon2.getMaxHP());
            
            if (currentpokemon1.getCurrentHP() > currentpokemon1.getMaxHP())
                currentpokemon1.setCurrentHP(currentpokemon1.getMaxHP());
            if (currentpokemon2.getCurrentHP() > currentpokemon2.getMaxHP())
                currentpokemon2.setCurrentHP(currentpokemon2.getMaxHP());
            if (currentpokemon1.getCurrentHP() < 0)
                currentpokemon1.setCurrentHP(0);
            if (currentpokemon2.getCurrentHP() < 0)
                currentpokemon2.setCurrentHP(0);
            
            //check if one team is down
            
            if (!(team1List.size() <= 1) && team2List.size() <= 1)
                return 1;
            if (team1List.size() <= 1 && !(team2List.size() <= 1))
                return 2;
            
            //check for faints
            
            checkFaint();
            
            
            
            //-----------------POST-TURN-----------------
            if ( currentpokemon1.getNonVolatileStatus() != null) {
                if (currentpokemon1.getNonVolatileStatus().getName().equals("Poison") || currentpokemon1.getNonVolatileStatus().getName().equals("Burn")) {
                    currentpokemon1.takeDamage((int)(.125*currentpokemon1.getMaxHP()));
                }
                if (currentpokemon1.getNonVolatileStatus().getName().equals("Badly Poisoned")) {
                    badlyPoisonedMult1 += .0625;
                    currentpokemon1.takeDamage((int)(badlyPoisonedMult1*currentpokemon1.getMaxHP()));
                }
            }
            
            if (currentpokemon2.getNonVolatileStatus() != null) {
                
                if (currentpokemon2.getNonVolatileStatus().getName().equals("Poison") || currentpokemon2.getNonVolatileStatus().getName().equals("Burn")) {
                    currentpokemon2.takeDamage((int)(.125*currentpokemon2.getMaxHP()));
                }
                
                if (currentpokemon2.getNonVolatileStatus().getName().equals("Badly Poisoned")) {
                    badlyPoisonedMult2 += .0625;
                    currentpokemon2.takeDamage((int)(badlyPoisonedMult2*currentpokemon2.getMaxHP()));
                }
            }
            
            System.out.println();
            
            System.out.println(currentpokemon1.getName() + ": " + currentpokemon1.getCurrentHP() + "/" + currentpokemon1.getMaxHP());
            System.out.println(currentpokemon2.getName() + ": " + currentpokemon2.getCurrentHP() + "/" + currentpokemon2.getMaxHP());
            
            //check if one team is down
            
            if (!(team1List.size() <= 1) && team2List.size() <= 0)
                return 1;
            if (team1List.size() <= 1 && !(team2List.size() <= 0))
                return 2;
            
            checkFaint();
            
        }
        
    }
    
    /*public Boolean attackMenu(int team) {
     Scanner sc = new Scanner(System.in);
     updateCurrents();
     if (team == 1) {
     while (true) {
     System.out.println(currentpokemon1.getName() + " will attack!");
     System.out.println();
     System.out.println("Moves: ");
     System.out.print("- ");
     for (BaseMove move : currentpokemon1.getMoveset()) {
     System.out.print(move.getName() + " - ");
     }
     System.out.println();
     System.out.println();
     System.out.println("B - Back; Move Name - Attack");
     selection1 = sc.nextLine();
     if (selection1.equals("B"))
     return false;
     wrongselect = true;
     for (BaseMove move : currentpokemon1.getMoveset()) {
     if (selection1.equals(move.getName()) || selection1.equals(move.getDataName())) {
     move1 = move;
     wrongselect = false;
     return true;
     }
     }
     if (wrongselect)
     System.out.println("Invalid Selection");
     }
     }
     
     
     if (team == 2) {
     while (true) {
     System.out.println(currentpokemon2.getName() + " will attack!");
     System.out.println();
     System.out.println("Moves: ");
     System.out.print("- ");
     for (BaseMove move : currentpokemon2.getMoveset()) {
     System.out.print(move.getName() + " - ");
     }
     System.out.println();
     System.out.println();
     System.out.println("B - Back; Move Name - Attack");
     selection2 = sc.nextLine();
     if (selection2.equals("B"))
     return false;
     wrongselect = true;
     for (BaseMove move : currentpokemon2.getMoveset()) {
     if (selection2.equals(move.getName()) || selection2.equals(move.getDataName())) {
     move2 = move;
     wrongselect = false;
     return true;
     }
     }
     if (wrongselect)
     System.out.println("Invalid Selection");
     }
     }
     return false;
     }
     
     public Boolean swapMenu(int team) {
     Scanner sc = new Scanner(System.in);
     updateCurrents();
     if (team == 1) {
     while (true) {
     
     System.out.println(currentpokemon1.getName() + " will swap!");
     System.out.println("Available Pokemon: ");
     for (ActivePokemon pokemon : team1List) {
     if (!(pokemon == currentpokemon1))
     System.out.print(pokemon.getName() + ", ");
     }
     System.out.println();
     System.out.println("B - Back; Pokemon Name - Swap");
     selection1 = sc.nextLine();
     if (selection1.equals("B"))
     return false;
     wrongselect = true;
     for (ActivePokemon pokemon : team1List) {
     if (selection1.equals(pokemon.getName()) || selection1.equals(pokemon.getDataName())) {
     pokemon1 = pokemon;
     wrongselect = false;
     return true;
     }
     }
     if (wrongselect)
     System.out.println("Invalid Selection");
     }
     }
     if (team == 2) {
     while (true) {
     
     System.out.println(currentpokemon2.getName() + " will swap!");
     System.out.println("Available Pokemon: ");
     for (ActivePokemon pokemon : team2List) {
     if (!(pokemon == currentpokemon2))
     System.out.print(pokemon.getName() + ", ");
     }
     System.out.println();
     System.out.println("B - Back; Pokemon Name - Swap");
     selection2 = sc.nextLine();
     if (selection2.equals("B"))
     return false;
     wrongselect = true;
     for (ActivePokemon pokemon : team2List) {
     if (selection2.equals(pokemon.getName()) || selection2.equals(pokemon.getDataName())) {
     pokemon2 = pokemon;
     wrongselect = false;
     return true;
     }
     }
     if (wrongselect)
     System.out.println("Invalid Selection");
     }
     }
     return false;
     }*/
    
    public void selectPokemon(int team, Boolean original) {
        ArrayList<Button> buttonList = new ArrayList<Button>();
        
        if (team == 1) {
            for (ActivePokemon pokemon : team1List) {
                if (!(pokemon == currentpokemon1)) {
                    Double posX = (width/2)-buttonwidth-10;
                    Double posY = 10.0+50+(buttonheight/2)+buttonheight;
                    if (team1List.indexOf(pokemon) >= 3)
                        posY -= (10+buttonheight);
                    if (team1List.indexOf(pokemon) != 0 && team1List.indexOf(pokemon) != 3)
                        posX += 10+buttonwidth;
                    if (team1List.indexOf(pokemon) == 2 || team1List.indexOf(pokemon) == 5)
                        posX += 10+buttonwidth;
                    Button button = new Button(posX,posY,buttonwidth,buttonheight,pokemon);
                    button.drawPokemonButton();
                    buttonList.add(button);
                }
            }
            StdDraw.show();
            StdDraw.clear(StdDraw.WHITE);
            int result = draw.waitForInput(buttonList);
            currentpokemon1 = team1List.get(result);
            Collections.swap(team1List,0,result);
        }
        
        if (team == 2) {
            for (ActivePokemon pokemon : team2List) {
                if (!(pokemon == currentpokemon2)) {
                    Double posX = (width/2)-buttonwidth-10;
                    Double posY = 10.0+50+(buttonheight/2)+buttonheight;
                    if (team2List.indexOf(pokemon) >= 3)
                        posY -= (10+buttonheight);
                    if (team2List.indexOf(pokemon) != 0 && team2List.indexOf(pokemon) != 3)
                        posX += 10+buttonwidth;
                    if (team2List.indexOf(pokemon) == 2 || team2List.indexOf(pokemon) == 5)
                        posX += 10+buttonwidth;
                    Button button = new Button(posX,posY,buttonwidth,buttonheight,pokemon);
                    button.drawPokemonButton();
                    buttonList.add(button);
                }
            }
            StdDraw.show();
            StdDraw.clear(StdDraw.WHITE);
            int result = draw.waitForInput(buttonList);
            currentpokemon2 = team2List.get(result);
            Collections.swap(team2List,0,result);
            
        }
    }
    
    public Boolean selectPokemon(int team) {
        ArrayList<Button> buttonList = new ArrayList<Button>();
        
        if (team == 1) {
            System.out.println("Player One: Choose which Pokemon you'd like to send out next.");
            System.out.println();
            for (ActivePokemon pokemon : team1List) {
                if (!(pokemon == currentpokemon1)) {
                    Double posX = (width/2)-buttonwidth-10;
                    Double posY = 10.0+50+(buttonheight/2)+buttonheight;
                    if (team1List.indexOf(pokemon)-1 >= 3)
                        posY -= (10+buttonheight);
                    if (team1List.indexOf(pokemon)-1 != 0 && team1List.indexOf(pokemon)-1 != 3)
                        posX += 10+buttonwidth;
                    if (team1List.indexOf(pokemon)-1 == 2 || team1List.indexOf(pokemon)-1 == 5)
                        posX += 10+buttonwidth;
                    Button button = new Button(posX,posY,buttonwidth,buttonheight,pokemon);
                    button.drawPokemonButton();
                    buttonList.add(button);
                    System.out.println(posX + " " + posY + " " + (team1List.indexOf(pokemon)-1));
                }
            }
            Double posX = (width/2)-buttonwidth-10+10+buttonwidth+10+buttonwidth;
            Double posY = 10.0+50+(buttonheight/2)+buttonheight - (10+buttonheight);
            Button button = new Button(posX,posY,buttonwidth,buttonheight,"Back");
            button.drawButton();
            buttonList.add(button);
            StdDraw.show();
            StdDraw.clear(StdDraw.WHITE);
            int result = draw.waitForInput(buttonList, team1InactiveList);
            if (result == 5)
                return false;
            else {
                pokemon1 = team1List.get(result+1);
                return true;
            }
        }
        
        if (team == 2) {
            System.out.println("Player One: Choose which Pokemon you'd like to send out next.");
            System.out.println();
            for (ActivePokemon pokemon : team2List) {
                if (!(pokemon == currentpokemon2)) {
                    Double posX = (width/2)-buttonwidth-10;
                    Double posY = 10.0+50+(buttonheight/2)+buttonheight;
                    if (team2List.indexOf(pokemon)-1 >= 3)
                        posY -= (10+buttonheight);
                    if (team2List.indexOf(pokemon)-1 != 0 && team2List.indexOf(pokemon)-1 != 3)
                        posX += 10+buttonwidth;
                    if (team2List.indexOf(pokemon)-1 == 2 || team2List.indexOf(pokemon)-1 == 5)
                        posX += 10+buttonwidth;
                    Button button = new Button(posX,posY,buttonwidth,buttonheight,pokemon);
                    button.drawPokemonButton();
                    buttonList.add(button);
                    System.out.println(posX + " " + posY + " " + (team1List.indexOf(pokemon)-1));
                }
            }
            Double posX = (width/2)-buttonwidth-10+10+buttonwidth+10+buttonwidth;
            Double posY = 10.0+50+(buttonheight/2)+buttonheight - (10+buttonheight);
            Button button = new Button(posX,posY,buttonwidth,buttonheight,"Cancel");
            button.drawButton();
            buttonList.add(button);
            StdDraw.show();
            StdDraw.clear(StdDraw.WHITE);
            int result = draw.waitForInput(buttonList, team1InactiveList);
            if (result == 5)
                return false;
            else {
                pokemon2 = team2List.get(result+1);
                return true;
            }
        }
        return false;
    }
    
    
    /*Scanner sc = new Scanner(System.in);
     if (team==1) {
     while (true) {
     wrongselect = true;
     System.out.println("Player One - What Pokemon would you like to send out?");
     System.out.println();
     System.out.print("- ");
     for (ActivePokemon pokemon : team1List) {
     if (!(pokemon == currentpokemon1))
     System.out.print(pokemon.getName() + " - ");
     }
     System.out.println();
     selection1 = sc.nextLine();
     for (ActivePokemon pokemon : team1List) {
     if (selection1.equals(pokemon.getName()) || selection1.equals(pokemon.getDataName())) {
     currentpokemon1 = pokemon;
     Collections.swap(team1List,0,team1List.indexOf(pokemon));
     wrongselect = false;
     return true;
     }
     }
     if (wrongselect)
     System.out.println("Invalid Selection");
     }
     }
     if (team==2) {
     while (true) {
     wrongselect = true;
     System.out.println("Player Two - What Pokemon would you like to send out?");
     System.out.println();
     System.out.print("- ");
     for (ActivePokemon pokemon : team2List) {
     if (!(pokemon == currentpokemon2))
     System.out.print(pokemon.getName() + " - ");
     }
     System.out.println();
     selection2 = sc.nextLine();
     for (ActivePokemon pokemon : team2List) {
     if (selection2.equals(pokemon.getName()) || selection2.equals(pokemon.getDataName())) {
     currentpokemon2 = pokemon;
     Collections.swap(team2List,0,team2List.indexOf(pokemon));
     wrongselect = false;
     return true;
     }
     }
     if (wrongselect)
     System.out.println("Invalid Selection");
     }
     }
     return false;
     }*/
    
    public Boolean attack(int team, BaseMove move) {
        if (team==1) {
            if (!(currentpokemon1.hasFainted())) {
                
                //Non-Volatile Status Checks
                if (currentpokemon1.getNonVolatileStatus() != null) {
                    if (currentpokemon1.getNonVolatileStatus().getName().equals("Paralysis")) {
                        Double rand = Math.random();
                        if (rand < .33) { {
                            System.out.println(currentpokemon1.getName() + " is paralyzed! It can't move!");
                            return false;
                        }
                        }
                        
                        if (currentpokemon1.getNonVolatileStatus().getName().equals("Freeze")) {
                            rand = Math.random();
                            if (rand < .8) {
                                System.out.println(currentpokemon1.getName() + " is frozen! It can't move!");
                                return false;
                            }
                            else {
                                System.out.println(currentpokemon1.getName() + " has thawed out!");
                                currentpokemon1.setNonVolatileStatus(null);
                            }
                        }
                        
                        if (currentpokemon1.getNonVolatileStatus().getName().equals("Sleep")) {
                            rand = Math.random();
                            if (rand < .66) {
                                System.out.println(currentpokemon1.getName() + " is asleep! It can't move!");
                                return false;
                            }
                            else {
                                System.out.println(currentpokemon1.getName() + " woke up!");
                                currentpokemon1.setNonVolatileStatus(null);
                            }
                        }
                    }
                }
                
                //DAMAGE TARGETS
                
                System.out.println(currentpokemon2.getName() + " used " + move.getName() + "!");
                System.out.println();
                
                if (move.getTargetID() == 10 || move.getTargetID() == 9 || move.getTargetID() == 8 || move.getTargetID() == 9) {
                    if ((Math.random() < (move.getAccuracy() * currentpokemon1.getStageMult("Accuracy",false)) * currentpokemon2.getStageMult("Evasion",true)) || move.getAccuracy() == 0) {
                        if (move.getDamageID() == 1) {
                            performAfterEffect(1, move, 0);
                        }
                        if (move.getDamageID() == 2) {
                            Double modifier = 1.0;
                            if (currentpokemon1.getNonVolatileStatus() != null) {
                                if (currentpokemon1.getNonVolatileStatus().getDataName().equals("burn"))
                                    modifier = modifier * .5;
                            }
                            modifier = modifier*((Math.random() * (1.0 - .85)) + .85);
                            if (Math.random() < .05)
                                modifier = modifier * 1.5;
                            if (currentpokemon1.getBasePokemon().getType1().equals(move.getType()) || currentpokemon1.getBasePokemon().getType2().equals(move.getType()))
                                modifier = modifier * 1.5;
                            modifier = modifier * move.typeEffectivenessMult(move.findTypeID(currentpokemon2.getBasePokemon().getType1()));
                            if (!currentpokemon2.getBasePokemon().getType2().equals(""))
                                modifier = modifier * move.typeEffectivenessMult(move.findTypeID(currentpokemon2.getBasePokemon().getType2()));
                            int damage = (int)(((((42)*move.getPower()*currentpokemon1.getCurrentAtk())/(50*currentpokemon2.getCurrentDef()))+2)*modifier);
                            currentpokemon2.takeDamage(damage);
                            performAfterEffect(1, move, damage);
                        }
                        if (move.getDamageID() == 3) {
                            Double modifier = 1.0;
                            modifier = modifier*((Math.random() * (1.0 - .85)) + .85);
                            if (Math.random() < .05)
                                modifier = modifier * 1.5;
                            if (currentpokemon1.getBasePokemon().getType1().equals(move.getType()) || currentpokemon1.getBasePokemon().getType2().equals(move.getType()))
                                modifier = modifier * 1.5;
                            modifier = modifier * move.typeEffectivenessMult(move.findTypeID(currentpokemon2.getBasePokemon().getType1()));
                            if (!currentpokemon2.getBasePokemon().getType2().equals(""))
                                modifier = modifier * move.typeEffectivenessMult(move.findTypeID(currentpokemon2.getBasePokemon().getType2()));
                            int damage = (int)(((((42)*move.getPower()*currentpokemon1.getCurrentSpAtk())/(50*currentpokemon2.getCurrentSpDef()))+2)*modifier);
                            
                            
                            currentpokemon2.takeDamage(damage);
                            performAfterEffect(1, move, damage);
                        }
                        return true;
                    }
                    return false;
                }
                //SELF-CAST MOVES (AFTEREFFECT ONLY)
                if (move.getTargetID() == 7) {
                    performAfterEffect(1, move, 0);
                    return true;
                }
            }
            return false;
        }
        
        if (team==2) {
            if (!(currentpokemon2.hasFainted())) {
                
                //Non-Volatile Status Checks
                if (currentpokemon2.getNonVolatileStatus() != null) {
                    if (currentpokemon2.getNonVolatileStatus().getName().equals("Paralysis")) {
                        Double rand = Math.random();
                        if (rand < .33) {
                            System.out.println(currentpokemon2.getName() + " is paralyzed! It can't move!");
                            return false;
                        }
                    }
                    
                    if (currentpokemon2.getNonVolatileStatus().getName().equals("Freeze")) {
                        Double rand = Math.random();
                        if (rand < .8) {
                            System.out.println(currentpokemon2.getName() + " is frozen! It can't move!");
                            return false;
                        }
                        else {
                            System.out.println(currentpokemon2.getName() + " has thawed out!");
                            currentpokemon1.setNonVolatileStatus(null);
                        }
                    }
                    
                    if (currentpokemon2.getNonVolatileStatus().getName().equals("Sleep")) {
                        Double rand = Math.random();
                        if (rand < .66) {
                            System.out.println(currentpokemon2.getName() + " is asleep! It can't move!");
                            return false;
                        }
                        else {
                            System.out.println(currentpokemon2.getName() + " woke up!");
                            currentpokemon1.setNonVolatileStatus(null);
                        }
                    }
                }
                
                //DAMAGE TARGETS
                
                System.out.println(currentpokemon2.getName() + " used " + move.getName() + "!");
                System.out.println();
                
                if (move.getTargetID() == 10 || move.getTargetID() == 9 || move.getTargetID() == 8 || move.getTargetID() == 9) {
                    if ((Math.random() < (move.getAccuracy() * currentpokemon2.getStageMult("Accuracy",false)) * currentpokemon1.getStageMult("Evasion",true)) || move.getAccuracy() == 0) {
                        if (move.getDamageID() == 1) {
                            performAfterEffect(2, move, 0);
                        }
                        if (move.getDamageID() == 2) {
                            Double modifier = 1.0;
                            if (currentpokemon2.getNonVolatileStatus() != null) {
                                if (currentpokemon2.getNonVolatileStatus().getDataName().equals("burn"))
                                    modifier = modifier * .5;
                            }
                            modifier = modifier*((Math.random() * (1.0 - .85)) + .85);
                            if (Math.random() < .05)
                                modifier = modifier * 1.5;
                            if (currentpokemon2.getBasePokemon().getType1().equals(move.getType()) || currentpokemon2.getBasePokemon().getType2().equals(move.getType()))
                                modifier = modifier * 1.5;
                            modifier = modifier * move.typeEffectivenessMult(move.findTypeID(currentpokemon1.getBasePokemon().getType1()));
                            if (!currentpokemon1.getBasePokemon().getType2().equals(""))
                                modifier = modifier * move.typeEffectivenessMult(move.findTypeID(currentpokemon1.getBasePokemon().getType2()));
                            int damage = (int)(((((42)*move.getPower()*currentpokemon2.getCurrentAtk())/(50*currentpokemon1.getCurrentDef()))+2)*modifier);
                            
                            currentpokemon1.takeDamage(damage);
                            performAfterEffect(2, move, damage);
                        }
                        if (move.getDamageID() == 3) {
                            Double modifier = 1.0;
                            modifier = modifier*((Math.random() * (1.0 - .85)) + .85);
                            if (Math.random() < .05)
                                modifier = modifier * 1.5;
                            
                            if (currentpokemon2.getBasePokemon().getType1().equals(move.getType()) || currentpokemon2.getBasePokemon().getType2().equals(move.getType()))
                                modifier = modifier * 1.5;
                            modifier = modifier * move.typeEffectivenessMult(move.findTypeID(currentpokemon1.getBasePokemon().getType1()));
                            if (!currentpokemon1.getBasePokemon().getType2().equals(""))
                                modifier = modifier * move.typeEffectivenessMult(move.findTypeID(currentpokemon1.getBasePokemon().getType2()));
                            int damage = (int)(((((42)*move.getPower()*currentpokemon2.getCurrentSpAtk())/(50*currentpokemon1.getCurrentSpDef()))+2)*modifier);
                            
                            
                            currentpokemon1.takeDamage(damage);
                            performAfterEffect(2, move, damage);
                        }
                        return true;
                    }
                    return false;
                }
                //SELF-CAST MOVES (AFTEREFFECT ONLY)
                if (move.getTargetID() == 7) {
                    
                    performAfterEffect(2, move, 0);
                    return true;
                }
                
            }
            return false;
        }
        return false;
    }
    
    public Boolean swap(int team, ActivePokemon pokemon) {
        if (team==1) {
            Collections.swap(team1List,0,team1List.indexOf(pokemon));
            
            return true;
        }
        if (team==2) {
            Collections.swap(team2List,0,team2List.indexOf(pokemon));
            
            return true;
        }
        
        return false;
    }
    
    public void updateCurrents() {
        this.currentpokemon1 = team1List.get(0);
        this.currentpokemon2 = team2List.get(0);
    }
    
    public void checkFaint() {
        
        if (currentpokemon1.hasFainted()) {
            System.out.println(currentpokemon1.getName() + " has fainted.");
            selectPokemon(1);
            swap(1,pokemon1);
            team1List.remove(currentpokemon1);
            team1InactiveList.add(currentpokemon1);
            updateCurrents();
        }
        
        if (currentpokemon2.hasFainted()) {
            System.out.println(currentpokemon2.getName() + " has fainted.");
            selectPokemon(2);
            swap(2,pokemon2);
            team2List.remove(currentpokemon2);
            team2InactiveList.add(currentpokemon2);
            updateCurrents();
            
        }
    }
    
    private Boolean done1 = false;
    private Boolean done2 = false;
    
    public void performAfterEffect(int team, BaseMove move, int damage) {
        ActivePokemon target;
        ActivePokemon user;
        
        int effectID = move.getEffectID();
        Double effectchance = move.getEffectChance();
        int targetID = move.getTargetID();
        
        if (team == 1) {
            user = currentpokemon1;
            if (targetID == 7)
                target = currentpokemon1;
            else if (targetID == 14) {
                target = currentpokemon1;
                done1 = true;
                if (!done2)
                    performAfterEffect(2, move, damage);
            }
            else
                target = currentpokemon2;
        }
        else {
            user = currentpokemon2;
            if (targetID == 7)
                target = currentpokemon2;
            else if (targetID == 14 || targetID == 12) {
                target = currentpokemon2;
                done2 = true;
                if (!done1)
                    performAfterEffect(1, move, damage);
            }
            else
                target = currentpokemon1;
        }
        
        if (effectID == 2) {
            target.addNonVolatileStatus(data.getNonVolatileStatusList().get(5));
            System.out.println(target.getName() + " was put to sleep!");
        }
        if (effectID == 3) {
            if (Math.random() < effectchance) {
                if (!(target.getBasePokemon().getType1().equals("Poison") || target.getBasePokemon().getType2().equals("Poison"))) {
                    target.addNonVolatileStatus(data.getNonVolatileStatusList().get(3));
                    System.out.println(target.getName() + " was poisoned!");
                }
            }
        }
        if (effectID == 4) {
            System.out.println(user);
            user.setCurrentHP(user.getCurrentHP() + damage/2);
        }
        if (effectID == 5) {
            if (Math.random() < effectchance) {
                if (!(target.getBasePokemon().getType1().equals("Fire") || target.getBasePokemon().getType2().equals("Fire"))) {
                    target.addNonVolatileStatus(data.getNonVolatileStatusList().get(0));
                    System.out.println(target.getName() + " was burned!");
                }
            }
        }
        if (effectID == 6) {
            if (Math.random() < effectchance) {
                if (!(target.getBasePokemon().getType1().equals("Ice") || target.getBasePokemon().getType2().equals("Ice"))) {
                    target.addNonVolatileStatus(data.getNonVolatileStatusList().get(1));
                    System.out.println(target.getName() + " was frozen!");
                }
            }
        }
        if (effectID == 7) {
            if (Math.random() < effectchance) {
                if (!(target.getBasePokemon().getType1().equals("Electric") || target.getBasePokemon().getType2().equals("Electric"))) {
                    target.addNonVolatileStatus(data.getNonVolatileStatusList().get(2));
                    System.out.println(target.getName() + " was paralyzed!");
                }
            }
        }
        if (effectID == 8) {
            target.setCurrentHP(0);
        }
        if (effectID == 9) {
            if (target.getNonVolatileStatus().getDataName().equals("sleep")){
                user.setCurrentHP(user.getCurrentHP() + damage/2);
            }
        }
        if (effectID == 10) {
            //disabled
        }
        if (effectID == 11) {
            user.setAtkBoost(user.getAtkBoost() + 1);
            user.autoSetCurrentAtk(user.getBasePokemon().getBaseAtk(),1);
            System.out.println(user.getName() + " raised their Attack!");
        }
        if (effectID == 12) {
            user.setDefBoost(user.getDefBoost() + 1);
            user.autoSetCurrentDef(user.getBasePokemon().getBaseDef(),1);
            System.out.println(user.getName() + " raised their Defense!");
        }
        if (effectID == 14) {
            user.setSpAtkBoost(user.getSpAtkBoost() + 1);
            user.autoSetCurrentSpAtk(user.getBasePokemon().getBaseSpAtk(),1);
            System.out.println(user.getName() + " raised their Special Attack!");
        }
        if (effectID == 17) {
            user.setEvasionBoost(user.getEvasionBoost() + 1);
            System.out.println(user.getName() + " raised their Evasiveness!");
        }
        if (effectID == 19) {
            target.setAtkBoost(target.getAtkBoost() - 1);
            user.autoSetCurrentAtk(user.getBasePokemon().getBaseAtk(),1);
            System.out.println(user.getName() + " lowered " + target.getName() + "'s Attack!");
        }
        if (effectID == 20) {
            target.setDefBoost(target.getDefBoost() - 1);
            user.autoSetCurrentDef(user.getBasePokemon().getBaseDef(),1);
            System.out.println(user.getName() + " lowered " + target.getName() + "'s Defense!");
        }
        if (effectID == 21) {
            target.setSpeedBoost(target.getSpeedBoost() - 1);
            user.autoSetCurrentSpeed(user.getBasePokemon().getBaseSpeed(),1);
            System.out.println(user.getName() + " lowered " + target.getName() + "'s Speed!");
        }
        if (effectID == 24) {
            target.setAccuracyBoost(target.getAccuracyBoost() - 1);
            System.out.println(user.getName() + " lowered " + target.getName() + "'s Accuracy!");
        }
        if (effectID == 25) {
            target.setEvasionBoost(target.getEvasionBoost() - 1);
            System.out.println(user.getName() + " lowered " + target.getName() + "'s Evasion!");
        }
        if (effectID == 26) {
            target.setAtkBoost(0);
            target.setDefBoost(0);
            target.setSpAtkBoost(0);
            target.setSpDefBoost(0);
            target.setSpeedBoost(0);
            target.setAccuracyBoost(0);
            target.setEvasionBoost(0);
            System.out.println("All stat boosts reset!");
        }
        if (effectID == 33 || effectID == 215) {
            user.setCurrentHP(user.getCurrentHP() + user.getMaxHP()/2);
        }
        if (effectID == 34) {
            if (!(target.getBasePokemon().getType1().equals("Poison") || target.getBasePokemon().getType2().equals("Poison"))) {
                target.addNonVolatileStatus(data.getNonVolatileStatusList().get(4));
            }
        }
        if (effectID == 51) {
            user.setAtkBoost(user.getAtkBoost() + 2);
            user.autoSetCurrentAtk(user.getBasePokemon().getBaseAtk(),1);
            System.out.println(user.getName() + " sharply raised their Attack!");
        }
        if (effectID == 52) {
            user.setDefBoost(user.getDefBoost() + 2);
            user.autoSetCurrentDef(user.getBasePokemon().getBaseDef(),1);
            System.out.println(user.getName() + " sharply raised their Defense!");
        }
        if (effectID == 53) {
            user.setSpeedBoost(user.getSpeedBoost() + 2);
            user.autoSetCurrentSpeed(user.getBasePokemon().getBaseSpeed(),1);
            System.out.println(user.getName() + " sharply raised their Speed!");
        }
        if (effectID == 54) {
            user.setSpAtkBoost(user.getSpAtkBoost() + 2);
            user.autoSetCurrentSpAtk(user.getBasePokemon().getBaseSpAtk(),1);
            System.out.println(user.getName() + " sharply raised their Special Attack!");
        }
        if (effectID == 55) {
            user.setSpDefBoost(user.getSpDefBoost() + 2);
            user.autoSetCurrentSpDef(user.getBasePokemon().getBaseSpDef(),1);
            System.out.println(user.getName() + " sharply raised their Special Defense!");
        }
        if (effectID == 59) {
            user.setAtkBoost(user.getAtkBoost() - 2);
            user.autoSetCurrentAtk(user.getBasePokemon().getBaseAtk(),1);
            System.out.println(user.getName() + " sharply lowered " + target.getName() + "'s Attack!");
        }
        if (effectID == 60) {
            user.setDefBoost(user.getDefBoost() - 2);
            user.autoSetCurrentDef(user.getBasePokemon().getBaseDef(),1);
            System.out.println(user.getName() + " sharply lowered " + target.getName() + "'s Defense!");
        }
        if (effectID == 61) {
            user.setSpeedBoost(user.getSpeedBoost() - 2);
            user.autoSetCurrentSpeed(user.getBasePokemon().getBaseSpeed(),1);
            System.out.println(user.getName() + " sharply lowered " + target.getName() + "'s Speed!");
        }
        if (effectID == 62) {
            user.setSpAtkBoost(user.getSpAtkBoost() - 2);
            user.autoSetCurrentSpAtk(user.getBasePokemon().getBaseSpAtk(),1);
            System.out.println(user.getName() + " sharply lowered " + target.getName() + "'s Special Attack!");
        }
        if (effectID == 63) {
            user.setSpDefBoost(user.getSpDefBoost() - 2);
            user.autoSetCurrentSpDef(user.getBasePokemon().getBaseSpDef(),1);
            System.out.println(user.getName() + " sharply lowered " + target.getName() + "'s Special Defense!");
        }
        
        
    }
}