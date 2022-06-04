package main;

import entity.Player;
import tiles.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable { //Para poder usar los hilos

    // configuraciones de la pantalla
    final int originalTitleSize = 16; //el tamano de los personajes o objetos 16 x 16 pixeles
    final int scale = 3; //dado que el tamano de 16 px se va a ver muy pequeno se tiene que escalar para poder ver de manera correcta

    final public int titleSize = originalTitleSize * scale; //el tamano que se vera 48 x 48 px
    //Para definir el tamano de la pantalla 16 * 12
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;

    //Definir el tamano del la ventana
    public final int screenWidth = titleSize * maxScreenCol; //768 px
    public final int screenHeight = titleSize * maxScreenRow; //576 px
    //Parametros del mundo
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = titleSize * maxWorldCol;
    public final int worldHeight = titleSize * maxWorldRow;

    //FPS
    int FPS = 60;

    //Para crear el tiempo en el juego 'es decir que no se detenga' se utiliza hilos
    Thread gameThread;

    //Para las teclas del movimiento del personaje
    KeyHandler KeyH = new KeyHandler();

    //Para el jugador
    public Player player = new Player(this, KeyH);

    //Para el mundo
    TileManager tileM = new TileManager(this);
    /*------------------------------------------------------------------------------------------------------------------*/

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // Con esto en true, todos los dibujos de este componente se van hacer en una pantalla aparte para cargar,
        // pocas palabras, para mejorar el rendimiento
        this.addKeyListener(KeyH);//Lo agregamos al GamePanel
        this.setFocusable(true); //En true el GamePanel se centra en recibir la entrada de teclas


    }

    public void startGameThread() {
        gameThread = new Thread(this);//instanciamos el gameThread a la clase
        gameThread.start();//va a llamar el metodo run
    }

    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override //Esto es donde el hilo se va a correr
    public void run() {
        //vamos a crear el cliclo del juego en este hilo, lo cual sera el nucleo del juego

        double drawInterval = 1000000000 / FPS; //1 segundo dividido entre fps
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) { //Mientras el hilo exista

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                //Actualizar informacion, como posicion del jugador
                update();
                //Dibujar lo que se ve en la pantalla
                repaint(); //para llamar el paintComponent se utiliza el repaint
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }

    }

    public void update() {
        player.update();

    }

    public void paintComponent(Graphics g) { //el nombre de paintComponent ya existe en la clase JPanel, si se utiliza se tiene que hacer el super.paintComponent, lo hace java de esa manera
        super.paintComponent(g);
        //la clase Graphics2D da mas control sobre los objetos, como geometria, coordenadas, color ,etc...
        Graphics2D g2 = (Graphics2D) g;
        //Para pintar el mundo, tiene que ser antes que el jugador ya que es por capas
        tileM.draw(g2);
        //Para pintar el mundo
        player.draw(g2);

        g2.dispose();// para desacerse de cualquier otro objetos y dejar el nuevo, para liberar memoria
    }


}
