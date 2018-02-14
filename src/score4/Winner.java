package score4;

import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;

public class Winner extends JFrame {
    private int winner;
    
    Winner(){
        this.winner = 0;
    }//constructor
    
    Winner(int winner){
        super("Winner Window");
        
        this.winner = winner;
        this.setVisible(true);
        this.setSize(300, 120);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }//constructor
    
    public void setWinner(int winner){
        this.winner = winner;
    }//setWinner
            
    public int getWinner(){
        return this.winner;
    }//getWinner
    
    public void paint(Graphics g){
        repaint();
        
        String s;
        if(winner == 1){
            s = new String("Red player won!");
        }//if
        else if(winner == 2){
            s = new String("Yellow player won!");
        }//else if
        else{
            s = new String("INVALID VALUE OF winner");
        }//else
        
        g.setFont(new Font("Helvetica", Font.ITALIC, 20));
        g.drawString(s, 50, 80);
        
        repaint();
    }//paint
}//class
