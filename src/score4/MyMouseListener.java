package score4;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//KLASH MyMouseLisener, LEITOYRGEI OS THREAD
//POU AKOUEI TIS KINHSEIS POU GINONTAI APO TO MOUSE
public class MyMouseListener extends Thread implements MouseListener{
    //METAVLHTES SYNTETAGMENES
    private int x = -1;
    private int y = -1;
    
    public int getX(){
        return this.x;
    }//getX
    public int getY(){
        return this.y;
    }//getY
    
    //YLOPOIHSH THS RUN EPEIDH EINAI THREAD
    @Override
    public void run(){
        synchronized(this){
            while(x == -1 && y == -1){
                try{
                    this.sleep(100);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }//try-catch
                //VRES TO SHMEIO POU EXO PATHSEI ME TO MOUSE
                //VRES AN EINAI MESA STO ORIO TOU TAMPLO
                if(x > 30 && x < 520){
                    if(y > 160 && y < 580){
                        break;
                    }//if
                }//if
                x = -1;
                y = -1;
            }//while
            
            notify();
        }//synchronize
    }//run
    
    //Override TON METHODON THS MouseListener POU PREPEI NA EXOUN YLOPOIHSH
    @Override
    public void mouseClicked(MouseEvent arg0) {
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        this.x = arg0.getX();
        this.y = arg0.getY();
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
    }
    
    //EPISTREFEI TH STHLH POU EPILEX8HKE APO TO MOUSE
    public int useMouse(Display display, MyMouseListener listener){
        display.addMouseListener(listener);
        
        listener.start();
        
        synchronized(listener){
            try{
                listener.wait();
            }catch(InterruptedException e){
                e.printStackTrace();
            }//try-catch
        }//synchronize
        
        int column = 0;
        for(int i = 30; i <= 520; i += 70){
            if(listener.getX() < i){
                break;
            }//if
            else{
                column++;
            }//else
        }//for
        display.removeMouseListener(listener);
        
        //TIMH APOLYTH STO ARRAY
        column--;
        return column;
    }//useMouse()
    
}//class
