package score4;

import java.util.ArrayList;
import java.util.Random;

//KLASH AI POU YLOPOIEI TON ALGORITHMO MINIMAX
public class AI {
    private int maxDepth;
    
    AI(){
        this.maxDepth = 0;
    }//AI
    
    AI(int maxDepth){
        this.maxDepth = maxDepth;
    }//AI
    
    //KALESE TON ALGORITHMO MINIMAX
    public Move MiniMax(State state, int color){
        if(color == 1){
            return max(new State(state),0);
        }
        else{
            return min(new State(state),0);
        }
    }//MiniMax
    
    //PAIZEI O PAIKTHS MIN
    public Move min(State state, int depth){
        if(state.isTerminal() || depth == maxDepth){
            Move lastMove = new Move(state.getLastMove().getCol(), state.getLastMove().getRow(), state.evaluate());
            return lastMove;
        }//if
        
        //FTIAXNEI TA PAIDIA THS STATE
        //KAI TA VAZEI STO ARRAYLIST
        ArrayList<State> children = new ArrayList<State>(state.getChildren(2));
        Move minMove = new Move(Integer.MAX_VALUE);
        Random r = new Random(System.currentTimeMillis());
        
        //GIA KATHE PAIDI
        for(State child : children){
            //KALEI THN max
            Move move = max(child, depth + 1);
            
            //EPILEGEI TO PROTIMOTERO PAIDI ME VASH THN VALUE
            if(move.getValue() <= minMove.getValue()){
                if(move.getValue() == minMove.getValue()){
                    if(r.nextInt(2) == 1){
                        minMove.setRow(child.getLastMove().getRow());
                        minMove.setCol(child.getLastMove().getCol());
                        minMove.setValue(move.getValue());
                    }//if
                }//if
                else{
                    minMove.setRow(child.getLastMove().getRow());
                    minMove.setCol(child.getLastMove().getCol());
                    minMove.setValue(move.getValue());
                }//else
                
            }//if
        }//for
        return minMove;
    }//min
    
    //PAIZEI O PAIKTHS MAX
    public Move max(State state, int depth){
        
        if(state.isTerminal() || depth == maxDepth){
            Move lastMove = new Move(state.getLastMove().getCol(), state.getLastMove().getRow(), state.evaluate());
            return lastMove;
        }//if
        
        //FTIAXNEI TA PAIDIA THS STATE
        //KAI TA VAZEI STO ARRAYLIST
        ArrayList<State> children = new ArrayList<State>(state.getChildren(1));
        Move maxMove = new Move(Integer.MIN_VALUE);
        Random r = new Random(System.currentTimeMillis());
        
        //GIA KATHE PAIDI
        for(State child : children){
            //KALEI THN min
            Move move = min(child, depth + 1);
                    
            //EPILEGEI TO PROTIMOTERO PAIDI ME VASH THN VALUE
            if(move.getValue() >= maxMove.getValue()){
                
                if(move.getValue() == maxMove.getValue()){
                    if(r.nextInt(2) == 1){
                        maxMove.setRow(child.getLastMove().getRow());
                        maxMove.setCol(child.getLastMove().getCol());
                        maxMove.setValue(move.getValue());
                    }//if
                }//if
                else{
                    maxMove.setRow(child.getLastMove().getRow());
                    maxMove.setCol(child.getLastMove().getCol());
                    maxMove.setValue(move.getValue());
                }//else
                
            }//if
        }//for
        return maxMove;
    }//max
    
}//CLASS
