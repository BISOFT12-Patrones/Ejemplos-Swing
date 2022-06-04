package main;

import javax.swing.*;

public class main {

    public static void main (String [] args){

        JFrame window = new JFrame();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Para que la ventana se cierre al presionar la x, muy raro la verdad pero bueno
        window.setResizable(false); //Para que no se pueda hacer mas grande o pequena la ventana
        window.setTitle("2D RPG");

        //llamamos la pantalla del juego
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel); //ya que extiende la clase JPanel se puede anadir

        window.pack();// hace que la ventana se cree del tamano preferidos y sus componentes (lo que esta en GamePanel)

        window.setLocationRelativeTo(null); //al no especificar donde, al ponerlo null se va a poner en el centro de la pantalla
        window.setVisible(true);

        gamePanel.startGameThread();
    }

}
