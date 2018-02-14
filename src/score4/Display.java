
package score4;

import java.awt.Color;
import javax.swing.JFrame;
import java.awt.geom.Line2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;


//KLASH POU YLOPOIEI TA GRAFIKA
public class Display extends JFrame{
    private int table[][];
    private int mode;
    
    Display(String name, int[][] table, int mode){
        super(name);
        this.setVisible(true);
        this.setSize(580, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        this.table = table;
        this.mode = mode;
        
        this.getContentPane().setBackground(new Color(70,130,180));
    }//Display
    
    //Override THS METHODOU PAINT TOY JFRAME
    @Override
    public void paint(Graphics g){
        repaint();
        
        Graphics2D g2 = (Graphics2D) g;
        
        //ME AYTO TO XROMA SXEDIASE
        g2.setColor(Color.WHITE);
        
        //KATAKORYFES GRAMMES
        for(int i = 30; i <= 520; i += 70){
            g2.draw(new Line2D.Float(i,160, i, 580));
        }//for
        
        //ORIZONTIES GRAMMES
        for(int i = 160; i <= 580; i += 70){
            g2.draw(new Line2D.Float(30,i,520,i));
        }//for
        
        //SXEDIASE POULIA
        g.setColor(Color.WHITE);
        for(int i = 0; i < 7; i ++){
            for(int j = 0; j < 6; j ++){
                int tempX = 45+(i*70);
                int tempY = 175+(j*70);
                if(table[i][j] != 0){
                    if(table[i][j] == 1){
                        g.setColor(new Color(128,0,0));
                    }//if
                    else if(table[i][j] == 2){
                        g.setColor(new Color(128,128,0));
                    }//else if
                }//if
                g.drawOval(tempX, tempY, 40, 40);
                g.fillOval(tempX, tempY, 40, 40);
                g.setColor(Color.WHITE);
            }//for
        }//for
        
        //EMFANISE TO KATALLHLO MHNYMA ANALOGA APO POIO EIDOS PAIXNIDIOU EPILEX8HKE
        g.setColor(Color.WHITE);
        if(mode == 3){
            String s = "Human vs Human:";
            String s1 = "Red vs Yellow";
            
            g.setFont(new Font("Helvetica", Font.ITALIC, 20));
            g.drawString(s, 50, 50);
            g.setFont(new Font("Helvetica", Font.ITALIC, 16));
            g.drawString(s1, 50, 80);
        }//if
        else if(mode == 2){
            String s = "Human vs Computer:";
            String s1 = "Human is Red";
            String s2 = "Min is Yellow";
            
            g.setFont(new Font("Helvetica", Font.ITALIC, 20));
            g.drawString(s, 50, 50);
            g.setFont(new Font("Helvetica", Font.ITALIC, 16));
            g.drawString(s1, 50, 80);
            g.drawString(s2, 50, 110);
        }//if
        else if(mode == 1){
            String s = "Computer vs Computer:";
            String s1 = "Max is Red";
            String s2 = "Min is Yellow";
            
            g.setFont(new Font("Helvetica", Font.ITALIC, 20));
            g.drawString(s, 50, 50);
            g.setFont(new Font("Helvetica", Font.ITALIC, 16));
            g.drawString(s1, 50, 80);
            g.drawString(s2, 50, 110);
        }//else
        
        //ANANAEOSH GRAFIKON
        repaint();
    }//paint
    
}//class
