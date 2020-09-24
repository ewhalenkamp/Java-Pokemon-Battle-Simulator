import java.util.*;

public class Button {
    
    private double posX, posY, width, height;
    private String text;
    private ArrayList<Integer> rgb;
    private BaseMove move;
    private ActivePokemon pokemon;
    
    public Button(double posX, double posY, double width, double height, BaseMove move) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.rgb = move.getTypeColor(move.getTypeID());
        this.move = move;
    }
    
    public Button(double posX, double posY, double width, double height, String text) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.text = text;
        this.move = move;
    }
    
    public Button(double posX, double posY, double width, double height, ActivePokemon pokemon) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.pokemon = pokemon;
    }
    
    public void drawMoveButton() {
        StdDraw.setPenColor(this.rgb.get(0),this.rgb.get(1),this.rgb.get(2));
        StdDraw.filledRectangle(posX,posY,width/2,height/2);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(posX,posY+20,move.getName());
        StdDraw.text(posX,posY,move.getPower() + " Power");
        StdDraw.text(posX,posY-20,move.getType());
    }
    
    public void drawButton() {
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        StdDraw.filledRectangle(posX,posY,width/2,height/2);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(posX,posY,text);
    }
    
    public void drawPokemonButton() {
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        StdDraw.filledRectangle(posX,posY,width/2,height/2);
        StdDraw.setPenColor(StdDraw.BLACK);
        if (this.pokemon.hasFainted())
            StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.text(posX,posY+10,this.pokemon.getName());
        StdDraw.text(posX,posY-10,this.pokemon.getCurrentHP() + "/" + this.pokemon.getMaxHP() + " HP");
    }
    
    public ActivePokemon getPokemon() {
        return this.pokemon;
    }
    
    public Boolean isPressed() {
        if (StdDraw.isMousePressed()) {
            if (StdDraw.mouseX() > this.posX-(this.width/2) && StdDraw.mouseY() > this.posY-(this.height/2) && StdDraw.mouseX() < this.posX+(this.width/2) && StdDraw.mouseY() < this.posY+(this.height/2))
                return true;
        }
        return false;
    }
        
}