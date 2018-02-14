package score4;

import java.util.Scanner;

public class Score4 {

    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int i = 0;
        
        //MENU
        //DIALEKSE TI EIDOUS PAIXNIDI NA PAIKSEIS
        while(i == 0){
            System.out.println("Select Game Mode:");
            System.out.println("1. Human versus Computer");
            System.out.println("2. Computer versus Computer");
            System.out.println("3. Human versus Human");
            System.out.print(">>");
            i = sc.nextInt();
            
            switch(i){
                case 1: VSpcMode();
                    i = 5;
                    break;
                case 2: pcVSpcMode();
                    i = 5;
                    break;
                case 3: pvpMode();
                    i = 5;
                    break;
                default: 
                    i = 0;
                    break;
            }//switch-case
        }//while
    }//main
    
    //PC VERSUS PC
    public static void pcVSpcMode(){
        int depth = 7;
        
        //DHMIOURGIA KLASEWN POU YLOPOIOYN MINIMAX
        AI ai1 = new AI(depth);
        AI ai2 = new AI(depth);
        
        Move move;
        
        int mode = 1;
        
        //DHMIOYRGIA KENHS KATASTASHS
        State state = new State();
        //DHMIOYRGIA Display ANTIKEIMENOU GIA TA GRAFIKA TOY PAIXNIDIOY
        Display display = new Display("Score4 Window", state.getTable(), mode);
        
        //OSO DEN EINAI TERMATIKH H KATASTASH
        while(!state.isTerminal()){
            //ANALOGA ME TO POIOS PAIKTHS PAIZEI
            switch(state.getColor()){
                case 1:
                    //DHMIOURGIA ANTIKEIMENOU AI
                    //POU TREXEI OPWS O MIN
                    //APO8HKEYSH KATASTASHS POU EPILEX8HKE APO TON MINIMAX
                    move = ai1.MiniMax(state, 1);
                    state.makeMove(move.getCol(), move.getRow(), state.getColor());
                    
                    if(state.isTerminal()){
                        Winner w = new Winner(state.getColor());
                        return;
                    }//if
                    
                    state.setColor(2);
                    move = null;
                    break;
                case 2:
                    //DHMIOURGIA ANTIKEIMENOU AI
                    //POU TREXEI OPWS O MIN
                    //APO8HKEYSH KATASTASHS POU EPILEX8HKE APO TON MINIMAX
                    move = ai2.MiniMax(state, 2);
                    //KANE THN KINHSH
                    state.makeMove(move.getCol(), move.getRow(), state.getColor());
                    
                    if(state.isTerminal()){
                        Winner w = new Winner(state.getColor());
                        return;
                    }//if
                    
                    state.setColor(1);
                    move = null;
                    break;
                default:
                    break;
            }//switch
        }//while
    }//pcVSpcMode
    
    //PLAYER VERSUS PC
    public static void VSpcMode(){
        //METAVLHTES POU VOH8OUN STHN YLOPOIHSH KINHSHS
        int column, row;
        //VATHOS MEGISTO GIA TON MINIMAX
        int depth = 7;
        //DHMIOURGIA KLASHS POU YLOPOIEI MINIMAX
        AI ai2 = new AI(depth);
        
        Move move;
        
        int mode = 2;
        
        //Listener GIA NA LAMVANEI APO TO MOUSE KINHSEIS
        MyMouseListener listener;
        //DHMIOYRGIA KENHS KATASTASHS
        State state = new State();
        //DHMIOYRGIA Display ANTIKEIMENOU GIA TA GRAFIKA TOY PAIXNIDIOY
        Display display = new Display("Score4 Window", state.getTable(), mode);
        
        //OSO H KATASTASH DEN EINAI TERMATIKH
        while(!state.isTerminal()){
            //ANALOGA ME TO POIOS PAIKTHS PAIZEI
            switch(state.getColor()){
                case 1:
                    //PARE APO TO PONTIKI SYNTETAGMENES
                    listener = new MyMouseListener();
                    column = listener.useMouse(display, listener);
                    
                    //DES AN MPOREI NA GINEI H KINHSH
                    row = state.isValidMove(column);
                    if(row != -1){
                        state.makeMove(column, row, state.getColor());
                        state.evaluate();
                    }//if
                    else{
                        continue;
                    }//else
                    
                    if(state.isTerminal()){
                        Winner w = new Winner(state.getColor());
                        return;
                    }//if
                    
                    state.setColor(2);
                    listener = null;
                    break;
                case 2:
                    //DHMIOURGIA ANTIKEIMENOU AI
                    //POU TREXEI OPWS O MIN
                    //APO8HKEYSH KATASTASHS POU EPILEX8HKE APO TON MINIMAX
                    move = ai2.MiniMax(state, 2);
                    state.makeMove(move.getCol(), move.getRow(), state.getColor());
                    
                    if(state.isTerminal()){
                        Winner w = new Winner(state.getColor());
                        return;
                    }//if
                    
                    state.setColor(1);
                    move = null;
                    break;
                default:
                    break;
            }//switch
        }//while
    }//VSpcMode
    
    //PLAYER VERSUS PLAYER
    public static void pvpMode(){
        //METAVLHTES POU VOH8OUN STHN YLOPOIHSH KINHSHS
        int column,row;
        
        int mode = 3;
        
        //Listener GIA NA LAMVANEI APO TO MOUSE KINHSEIS
        MyMouseListener listener;
        //FTIAKSE NEA KENH KATASTASH
        State state = new State();
        //FTIAKSE ANTIKEIMENO Display GIA TA GRAFIKA TOU PAIXNIDIOY
        Display display = new Display("Score4 Window", state.getTable(), mode);
        
        //ANALOGA ME TO POIOS PAIKTHS PAIZEI
        while(state.getColor() != 0){
            switch(state.getColor()){
                case 1:
                    //PARE KINHSH APO TO MOUSE
                    listener = new MyMouseListener();
                    column = listener.useMouse(display, listener);
                    
                    //AN H KINHSH EPITREPETAI KANE THN
                    row = state.isValidMove(column);
                    if(row != -1){
                        state.makeMove(column, row, state.getColor());
                        state.evaluate();
                    }//if
                    else{
                        continue;
                    }//else
                    
                    if(state.isTerminal()){
                        Winner w = new Winner(state.getColor());
                        return;
                    }
                    
                    state.setColor(2);
                    listener = null;
                    break;
                case 2:
                    //PARE KINHSH APO TO MOUSE
                    listener = new MyMouseListener();
                    column = listener.useMouse(display, listener);
                    
                    //AN H KINHSH EPITREPETAI KANE THN
                    row = state.isValidMove(column);
                    if(row != -1){
                        state.makeMove(column, row, state.getColor());
                        state.evaluate();
                    }//if
                    else{
                        continue;
                    }//else
                    
                    if(state.isTerminal()){
                        Winner w = new Winner(state.getColor());
                        return;
                    }//if
                    state.setColor(1);
                    listener = null;
                    break;
                default:
                    break;
            }//switch
        }//while
    }//pvpMode
    
}//class
