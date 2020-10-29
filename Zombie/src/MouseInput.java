import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class MouseInput extends MouseAdapter{

    private Game game;
    private Menu menu;
    public MouseInput(Handler handler, Camera camera, Level level, Game game, Menu menu) {
        this.game = game;
        this.menu = menu;
    }
    
    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        switch (game.currentGameState()) {
            case 1:
                clickWhileInMenu(mouseX, mouseY);
                System.out.println("click in menu");
                break;
            case 2:
                clickWileInGame(mouseX, mouseY);
                System.out.println("click in game");
                break;
            case 3:
                clickWhilePaused(mouseX, mouseY);
                System.out.println("click in pause");
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
        if(mouseOver(mouseX, mouseY, 245, 105, 140, 50)) {
            game.setGameState(Game.GAMESTATE.GAME);
            game.newGame();
        } else if(mouseOver(mouseX, mouseY, 245, 170, 140, 50)) {
            menu.menuType = 2;
        } else if(mouseOver(mouseX, mouseY, 245, 235, 140, 50)) {
            if (menu.menuType == 1) {
                System.exit(1);
            } else if(menu.menuType == 2) {
                menu.menuType = 1;
            } else if(menu.menuType == 3) {
                menu.menuType = 1;
            }   
        }
    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if(mx > x && mx < x + width) {
            if(my > y && my < y + height) {
                return true;
            } else {
                return false; 
            }
        } else {
            return false;
        }
    }
}