package ma.forix.puissance4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Fenetre extends JFrame implements KeyListener {

    Container container = new Container();
    Graphics g;

    public Fenetre(){
        this.setSize(1000, 700);
        this.setTitle("Puissance 4");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        this.setContentPane(container);
        this.addKeyListener(this);
        this.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        if (e.getKeyCode() == 97){
            container.addCircle(0);
        } else if (e.getKeyCode() == 98){
            container.addCircle(1);
        } else if (e.getKeyCode() == 99){
            container.addCircle(2);
        } else if (e.getKeyCode() == 100){
            container.addCircle(3);
        } else if (e.getKeyCode() == 101){
            container.addCircle(4);
        } else if (e.getKeyCode() == 102){
            container.addCircle(5);
        } else if (e.getKeyCode() == 103){
            container.addCircle(6);
        } else if (e.getKeyCode() == 10){
            container.clear();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
