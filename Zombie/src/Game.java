import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;


public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;
    public static int WIDTH = 640, HEIGHT = WIDTH / 12*9;
    public String title = "Game";

    private Thread thread;
    private boolean isRunning = false;

    private Handler handler;
    private KeyInput input;
    private MouseInput minput;
    private Camera camera;
    private Level level;


    public Game() {
        new Window(WIDTH, HEIGHT, title, this);
        start();
        
    }

    private void init() {
        
        handler = new Handler();
        input = new KeyInput();
        camera = new Camera(0, 0, WIDTH, HEIGHT, handler);
        level = new Level("../res/map.txt", camera);
        handler.addObject(new Player(WIDTH/2,HEIGHT/2,ID.Player,input,camera,level));
        minput = new MouseInput(handler, camera, level);
        

        this.addKeyListener(input);
        this.addMouseListener(minput);
        minput.findPlayer();

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
        handler.tick();
        camera.tick();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        //Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.GRAY);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        level.render(g);

        //g2d.translate(-camera.getX(), -camera.getY());

        handler.render(g);

        //g2d.translate(camera.getX(), camera.getY());

        bs.show();
        g.dispose();
    }

    public static void main(String args[]) {
        new Game();
    }
}
