import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;


public class Game extends Canvas implements Runnable {

    public static final long serialVersionUID = 1L;
    public static int WIDTH = 640, HEIGHT = WIDTH / 12*9;
    public String title = "Slime Hunter";

    public Thread thread;
    private boolean isRunning = false;

    private Handler handler;
    private KeyInput input;
    private MouseInput minput;
    private Camera camera;
    private Level level;
    private SpriteSheet sheet;
    private HUD hud;
    private Menu menu;
    private Fonts customFonts;
    public static enum GAMESTATE {
        MENU,
        GAME, 
        PAUSE,
    };

    public GAMESTATE gameState;

    public Game() {
        new Window(WIDTH, HEIGHT, title, this);
        start();

    }

    private void init() {

        handler = new Handler();
        input = new KeyInput();
        camera = new Camera(0, 0, WIDTH, HEIGHT, handler);
        
        sheet = new SpriteSheet("res/level1tileset.png");
        level = new Level("res/level1.txt", sheet, camera, handler);
        handler.newPlayer(new Player(32, 32, input, camera, level, handler, true, "res/player2.png"));
        
        gameState = GAMESTATE.MENU;
        
        customFonts = new Fonts();
        hud = new HUD(handler, customFonts.font2_12);
        menu = new Menu(handler, WIDTH, HEIGHT, customFonts);
        minput = new MouseInput(handler, camera, level, this, menu);

        // handler.addObject(new Enemy(250, 128, camera, level, handler, true, "res/slime.png"));
        this.addKeyListener(input);
        this.addMouseListener(minput);

    }

    private synchronized void start() {
        if (isRunning)
            return;
        thread = new Thread(this);
        thread.start();
        isRunning = true;
    }

    private synchronized void stop() {
        if (!isRunning)
            return;
        try {
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int ticks = 0, frames = 0;
        long timer = System.currentTimeMillis();
        init();

        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                ticks++;
                tick();
                delta--;
            }
            render();
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("Frames: " + frames + ", Ticks: " + ticks);
                frames = 0;
                ticks = 0;
            }
        }
        stop();
    }

    private void tick() {
        // if(input.isPauseKey()) {
        //     gameState = GAMESTATE.PAUSE;
        // } else {
        //     gameState = GAMESTATE.GAME;
        // }
        if(handler.endGame())
        {
            System.out.println(handler.endGame());
            gameState = GAMESTATE.MENU;
            menu.menuType = 3;
        }
        if (gameState == GAMESTATE.GAME) {
            handler.tick();
            camera.tick();
            level.tick();
            hud.tick();
        } else if(gameState == GAMESTATE.MENU) {
            menu.tick();
        }

        
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        if(gameState == GAMESTATE.GAME) {
            level.render(g);
            handler.render(g);
            hud.render(g);
        } else if (gameState == GAMESTATE.MENU) {
            menu.render(g);
        } else {
            g.setFont(customFonts.font2_48);
            g.setColor(Color.white);
            g.drawString("Game Paused", 100, 100);
        }
        
        bs.show();
        g.dispose();
    }

    public static void main(String args[]) {
        new Game();
    }

	public int currentGameState() {
        if(gameState == GAMESTATE.MENU) {
            return 1;
        } else if(gameState == GAMESTATE.GAME) {
            return 2;
        } else if(gameState == GAMESTATE.PAUSE) {
            return 3;
        } else {
            return 0;
        }
    }
    public void setGameState(GAMESTATE state) {
        gameState = state;
    }

	public void newGame() {
        handler.newGame();
        level.newGame();
	}
}
