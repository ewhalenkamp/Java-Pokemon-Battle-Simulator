import java.awt.Font;
import java.util.*;

public class Draw {
    
    private int height;
    private int width;
    
      /***********************************************************************
      * Method:   Draw Constructor                                           *
      * Purpose:  Constructor for the Draw object                            *
      * Parameters:                                                          *
      *     height - height of the canvas                                    *
      *     width - width of the canvas                                      *
      * Return Value:  none                                                  *
      ***********************************************************************/
    
    public Draw(int width, int height) {
        this.height = height;
        this.width = width;
        StdDraw.enableDoubleBuffering();
    }
    
    public void initCanvas() {
        StdDraw.setCanvasSize(this.width, this.height);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);
    }
    
    public int waitForInput(ArrayList<Button> buttonList, ArrayList<ActivePokemon> inactiveList) {
        while (true) {
            for (Button button : buttonList) {
                if (!inactiveList.contains(button.getPokemon())) {
                    if (button.isPressed()) {
                        StdDraw.pause(250);
                        return buttonList.indexOf(button);
                    }
                }
            }
        }
    }
    
    public int waitForInput(ArrayList<Button> buttonList) {
        while (true) {
            for (Button button : buttonList) {
                if (button.isPressed()) {
                    StdDraw.pause(250);
                    return buttonList.indexOf(button);
                }
            }
        }
    }
    
    public void drawField(ArrayList<ActivePokemon> team1List, ArrayList<ActivePokemon> team2List) {
        ActivePokemon currentpokemon1 = team1List.get(0);
        ActivePokemon currentpokemon2 = team2List.get(0);
        
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.line(-10,310,width+10,310);
        currentpokemon1.getBasePokemon().displayImage(150,310+150);
        currentpokemon2.getBasePokemon().displayImage(width-150,height-150);
        StdDraw.text(150,height-80,"Team One");
        StdDraw.text(width-150,310+80,"Team Two");
        StdDraw.text(150,310+150+100+40,currentpokemon1.getName() + ": " + currentpokemon1.getCurrentHP() + "/" + currentpokemon1.getMaxHP() + " HP");
        StdDraw.text(width-150,height-150-100-20,currentpokemon2.getName() + ": " + currentpokemon2.getCurrentHP() + "/" + currentpokemon2.getMaxHP() + " HP");
        if (currentpokemon1.getCurrentHP() > 0) {
            StdDraw.setPenColor(StdDraw.GREEN);
            if (currentpokemon1.getCurrentHP() < currentpokemon1.getMaxHP()/2)
                StdDraw.setPenColor(StdDraw.YELLOW);
            if (currentpokemon1.getCurrentHP() < currentpokemon1.getMaxHP()/4)
                StdDraw.setPenColor(StdDraw.RED);
            StdDraw.filledRectangle(150,310+150+100+20,currentpokemon1.getCurrentHP()*100/currentpokemon1.getMaxHP(),5);
        }
        if (currentpokemon2.getCurrentHP() > 0) {
            StdDraw.setPenColor(StdDraw.GREEN);
            if (currentpokemon2.getCurrentHP() < currentpokemon2.getMaxHP()/2)
                StdDraw.setPenColor(StdDraw.YELLOW);
            if (currentpokemon2.getCurrentHP() < currentpokemon2.getMaxHP()/4)
                StdDraw.setPenColor(StdDraw.RED);
            StdDraw.filledRectangle(width-150,height-150-100-40,currentpokemon2.getCurrentHP()*100/currentpokemon2.getMaxHP(),5);
        }
    }
    
    
    
}