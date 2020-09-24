import java.util.*;
import java.awt.Font;
import java.io.File;
import java.io.IOException;

/*******************************************************************************
  *                     Final Project -- Pokemon BattleSim                       *
  *                                                                              *
  * PROGRAMMER:        Erik Halenkamp (hale0084)                                 *
  * CLASS:             CS200 – Object Oriented Programming                       *
  * INSTRUCTOR:        Dean Zeller                                               *
  * TERM:              Spring 2020                                               *
  * SUBMISSION DATE:   5/3/2020                                                 *
  *                                                                              *
  * DESCRIPTION:                                                                 *
  * Uses my personal database in order to create a functioning Pokemon Battle    *
  * simulator, complete with graphics and UE.                                    *
  *                                                                              *
  * COPYRIGHT:                                                                   *
  * This program is copyright (c)2020 Erik Halenkamp and Dean Zeller.            *
  * It is original work without use of outside sources. Credit to StdDraw for    *
  * the drawing library and veekun for the database help.                        *
  *                                                                              *
  *******************************************************************************/

public class aaaMain {
    
    public static void main(String avg[]) 
        throws IOException
    { 
        //loading in pokemon stats to pokeList arraylist
        
        DataClass data = new DataClass();
        data.initBasePokemon();
        data.initBaseAbility();
        data.initBaseMove();
        data.initNonVolatileStatus();
        data.merge();
        ArrayList<ActivePokemon> team1List = data.teambuilder(1);
        ArrayList<ActivePokemon> team2List = data.teambuilder(2);
        
        Game game = new Game(team1List,team2List,data);
        int winner = game.runGame();
        
        System.out.println("Team " + winner + " wins!");
    }
}