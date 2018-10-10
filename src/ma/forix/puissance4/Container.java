package ma.forix.puissance4;

import javax.swing.*;
import java.awt.*;

public class Container extends JPanel {


    public Container(){

    }

     static String player = "Player 1";

    static int[][] coords = {
            {
                0, 0, 0, 0, 0, 0, 0
            },
            {
                0, 0, 0, 0, 0, 0, 0
            },
            {
                0, 0, 0, 0, 0, 0, 0
            },
            {
                0, 0, 0, 0, 0, 0, 0
            },
            {
                0, 0, 0, 0, 0, 0, 0
            },
            {
                0, 0, 0, 0, 0, 0, 0
            }
    };



    public void paintComponent(Graphics g){
        int x, y;
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.BLACK);
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 7; j++){
                g.drawOval(j*80+200, i*80+140, 60, 60);
            }
        }

        for(y = 0; y < 6; y++){
            for (x = 0; x < 7; x++){
                if(coords[y][x] == 1){
                    g.setColor(Color.RED);
                    g.fillOval(x*80+201, y*80+141, 58, 58);
                }
                if (coords[y][x] == 2){
                    g.setColor(Color.BLUE);
                    g.fillOval(x*80+201, y*80+141, 58, 58);
                }
            }
        }

        g.drawString("Tour: "+player, this.getWidth()/2-50, 10);
    }

    public void addCircle(int posX){
        int y = 5;
        boolean reloop = true;

        if (coords[y][posX] == 0) {
            if (player.equals("Player 1")) {
                coords[y][posX] = 1;
                verifWin(posX, y);
                player = "Player 2";
            }else if (player.equals("Player 2")) {
                coords[y][posX] = 2;
                verifWin(posX, y);
                player = "Player 1";
            }
            repaint();
        } else {
            while (reloop) {
                y--;
                if (y >= 0) {
                    if (coords[y][posX] == 0) {
                        if (player.equals("Player 1")) {
                            coords[y][posX] = 1;
                            verifWin(posX, y);
                            player = "Player 2";
                        }else if (player.equals("Player 2")) {
                            coords[y][posX] = 2;
                            verifWin(posX, y);
                            player = "Player 1";
                        }
                        repaint();
                        reloop = false;
                    }
                } else
                    reloop = false;
            }
        }
    }

    public void verifWin(int posX, int posY){
        int loop = 0;
        int serie = 1;
        int x = posX, y = posY;
        int nbrPlayer = 1;
        if (player.equals("Player 1"))
            nbrPlayer = 1;
        else if (player.equals("Player 2"))
            nbrPlayer = 2;
        while(loop < 1000){
            if (y < 5){
                if(coords[y+1][x] == nbrPlayer) {
                    serie++;
                    y++;
                    if (serie == 4) {
                        clear();
                        System.out.println(player + " GAGNE !");
                        return;
                    }
                }
            } else if (y < 5 && x < 6){
                if (coords[y+1][x+1] == nbrPlayer) {
                    serie++;
                    y++;
                    x++;
                    if (serie == 4) {
                        clear();
                        System.out.println(player + " GAGNE !");
                        return;
                    }
                }
            } else if (x < 6){
                if (coords[y][x+1] == nbrPlayer) {
                    serie++;
                    x++;
                    if (serie == 4) {
                        clear();
                        System.out.println(player + " GAGNE !");
                        return;
                    }
                }
            } else if (y > 0 && x < 6){
                if (coords[y-1][x+1] == nbrPlayer) {
                    serie++;
                    y--;
                    x++;
                    if (serie == 4) {
                        clear();
                        System.out.println(player + " GAGNE !");
                        return;
                    }
                }
            } else if (y > 0){
                if (coords[y-1][x] == nbrPlayer) {
                    serie++;
                    y--;
                    if (serie == 4) {
                        clear();
                        System.out.println(player + " GAGNE !");
                        return;
                    }
                }
            } else if (y > 0 && x > 0){
                if (coords[y-1][x-1] == nbrPlayer) {
                    serie++;
                    y--;
                    x--;
                    if (serie == 4) {
                        clear();
                        System.out.println(player + " GAGNE !");
                        return;
                    }
                }
            } else if (x > 0){
                if (coords[y][x-1] == nbrPlayer) {
                    serie++;
                    x--;
                    if (serie == 4) {
                        clear();
                        System.out.println(player + " GAGNE !");
                        return;
                    }
                }
            } else if (y < 5 && x > 0){
                if (coords[y+1][x-1] == nbrPlayer) {
                    serie++;
                    y++;
                    x--;
                    if (serie == 4) {
                        clear();
                        System.out.println(player + " GAGNE !");
                        return;
                    }
                }
            } else {
                System.out.println("Série: "+serie);
                return;
            }
            loop++;
        }
            /*
            D'abord vérifier 0 0 2 1
                             0 0 1 2
                             0 1 2 1
                             1 2 2 2
            [+1][0]
            [+1][+1]
            [0][+1]
            [-1][+1]
            [-1][0]
            [-1][-1]
            [0][-1]
            [+1][-1]


             */

    }

    public void clear(){
        int x, y;
        for(y = 0; y < 6; y++){
            for (x = 0; x < 7; x++){
                coords[y][x] = 0;

            }
        }
        repaint();
    }

}
