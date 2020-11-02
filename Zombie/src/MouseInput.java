import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter{

    private Game game;
    private Menu menu;
    private int mouseX, mouseY;
    public MouseInput(Handler handler, Camera camera, Level level, Game game, Menu menu) {
        this.game = game;
        this.menu = menu;

    }
    
    public void mousePressed(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        switch (game.getGameState()) {
            case 1:
                clickWhileInMenu(mouseX, mouseY);
                break;
            case 2:
                clickWileInGame(mouseX, mouseY);
                break;
            case 3:
                clickWhilePaused(mouseX, mouseY);
            default:
                System.out.println("Unknown game state");
                break;
        }
    }

    private void clickWileInGame(int mouseX, int mouseY) {
        //no interactions atm
    }

    private void clickWhilePaused(int mouseX, int mouseY) {
        //no interactions atm
    }

    private void clickWhileInMenu(int mouseX, int mouseY) {
        if(mouseOverCoordinates(245, 105, 140, 50)) {
            game.setGameState(Game.GAMESTATE.GAME);
            game.newGame();
        } else if(mouseOverCoordinates(245, 170, 140, 50)) {
            menu.menuType = 2;
        } else if(mouseOverCoordinates(245, 235, 140, 50)) {
            if (menu.menuType == 1) {
                System.exit(1);
            } else {
                menu.menuType = 1;
            }   
        }
    }

    private boolean mouseOverCoordinates(int x, int y, int width, int height) {
        if(mouseX > x && mouseX < x + width) {
            if(mouseY > y && mouseY < y + height) {
                return true;
            } else {
                return false; 
            }
        } else {
            return false;
        }
    }
}