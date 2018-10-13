package ma.forix.puissance4;

import javax.swing.*;
import java.awt.*;

public class Container extends JPanel {

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

        if (player.equals("Player 1"))
            g.setColor(Color.RED);
        else if (player.equals("Player 2"))
            g.setColor(Color.BLUE);
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
        int serie = 0;
        int x = posX, y = posY;
        int nbrPlayer = 1;
        if (player.equals("Player 1"))
            nbrPlayer = 1;
        else if (player.equals("Player 2"))
            nbrPlayer = 2;

        disp();

        System.out.println();
        System.out.println("X = "+x+" Y = "+y);

        if (y > 0){
            y--;
            if(coords[y][x] == nbrPlayer) {
                y++;
                System.out.println("Case trouvé en haut");
                y--;
                serie++;
                for (int i = 0; i < 2; i++){
                    if (y > 0) {
                        if (coords[y--][x] == nbrPlayer) {
                            y++;
                            System.out.println("HAUT");
                            serie++;
                            y--;
                        }
                    }
                }
                if (serie == 3){
                    System.out.println(player+" A GAGNE !!!");
                    clear();
                } else
                    serie = 0;
                x = posX;
                y = posY;
            } else {
                y++;
            }
        }
        System.out.println();
        System.out.println("X = "+x+" Y = "+y);

        if (y > 0 && x < 6){
            y--;
            x++;
            if (coords[y][x] == nbrPlayer) {
                y++;
                x--;
                System.out.println("Case trouvé en haut à droite");
                y--;
                x++;
                serie++;
                for (int i = 0; i < 2; i++){
                    if (y > 0 && x < 6) {
                        if (coords[y--][x++] == nbrPlayer) {
                            y++;
                            x--;
                            System.out.println("HAUT DROITE");
                            serie++;
                            y--;
                            x++;
                        }
                    }
                }
                if (serie == 3){
                    System.out.println(player+" A GAGNE !!!");
                    clear();
                } else
                    serie = 0;
                x = posX;
                y = posY;
            } else {
                y++;
                x--;
            }
        }
        System.out.println();
        System.out.println("X = "+x+" Y = "+y);

        if (x < 6){
            x++;
            if (coords[y][x] == nbrPlayer) {
                x--;
                System.out.println("Case trouvé à droite");
                x++;
                serie++;
                for (int i = 0; i < 2; i++){
                    if (x < 6) {
                        if (coords[y][x++] == nbrPlayer) {
                            x--;
                            System.out.println("DROITE");
                            serie++;
                            x++;
                        }
                    }
                }
                if (serie == 3){
                    System.out.println(player+" A GAGNE !!!");
                    clear();
                } else
                    serie = 0;
                x = posX;
                y = posY;
            } else
                x--;
        }

        if (y < 5 && x < 6){
            y++;
            x++;
            if (coords[y][x] == nbrPlayer) {
                y--;
                x--;
                System.out.println("Case trouvé en bas à droite");
                y++;
                x++;
                serie++;
                for (int i = 0; i < 2; i++){
                    if (y < 5 && x < 6) {
                        if (coords[y++][x++] == nbrPlayer) {
                            y--;
                            x--;
                            System.out.println("BAS DROITE");
                            serie++;
                            y++;
                            x++;
                        }
                    }
                }
                if (serie == 3){
                    System.out.println(player+" A GAGNE !!!");
                    clear();
                } else
                    serie = 0;
                x = posX;
                y = posY;
            } else {
                y--;
                x--;
            }
        }

        if (y < 5){
            y++;
            if (coords[y][x] == nbrPlayer) {
                y--;
                System.out.println("Case trouvé en bas");
                y++;
                serie++;
                for (int i = 0; i < 2; i++){
                    if (y < 5) {
                        if (coords[y++][x] == nbrPlayer) {
                            y--;
                            System.out.println("BAS");
                            serie++;
                            y++;
                        }
                    }
                }
                if (serie == 3){
                    System.out.println(player+" A GAGNE !!!");
                    clear();
                } else
                    serie = 0;
                x = posX;
                y = posY;
            } else
                y--;
        }

        if (y < 5 && x > 0){
            y++;
            x--;
            if (coords[y][x] == nbrPlayer) {
                y--;
                x++;
                System.out.println("Case trouvé en bas à gauche");
                y++;
                x--;
                serie++;
                for (int i = 0; i < 2; i++){
                    if (y < 5 && x > 0) {
                        if (coords[y++][x--] == nbrPlayer) {
                            y--;
                            x++;
                            System.out.println("BAS GAUCHE");
                            serie++;
                            y++;
                            x--;
                        }
                    }
                }
                if (serie == 3){
                    System.out.println(player+" A GAGNE !!!");
                    clear();
                } else
                    serie = 0;
                x = posX;
                y = posY;
            } else {
                y--;
                x++;
            }
        }

        if (x > 0){
            x--;
            if (coords[y][x] == nbrPlayer) {
                x++;
                System.out.println("Case trouvé à gauche");
                x--;
                serie++;
                for (int i = 0; i < 2; i++){
                    if (x > 0) {
                        if (coords[y][x--] == nbrPlayer) {
                            x++;
                            System.out.println("GAUCHE");
                            serie++;
                            x--;
                        }
                    }
                }
                if (serie == 3){
                    System.out.println(player+" A GAGNE !!!");
                    clear();
                } else
                    serie = 0;
                x = posX;
                y = posY;
            } else
                x++;
        }

        if (y > 0 && x > 0){
            y--;
            x--;
            if (coords[y][x] == nbrPlayer) {
                y++;
                x++;
                System.out.println("Case trouvé en haut à gauche");
                y--;
                x--;
                serie++;
                for (int i = 0; i < 2; i++){
                    if (y > 0 && x > 0) {
                        if (coords[y--][x--] == nbrPlayer) {
                            y++;
                            x++;
                            System.out.println("BAS GAUCHE");
                            serie++;
                            y--;
                            x--;
                        }
                    }
                }
                if (serie == 3){
                    System.out.println(player+" A GAGNE !!!");
                    clear();
                } else
                    serie = 0;
                x = posX;
                y = posY;
            } else {
                y++;
                x++;
            }
        }
        System.out.println();
        System.out.println("-----------------------------");
        System.out.println();
    }

    public void disp(){
        int x, y;
        for(y = 0; y < 6; y++){
            for (x = 0; x < 7; x++){
                System.out.print(coords[y][x]+" ");
            }
            System.out.println();
        }
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
