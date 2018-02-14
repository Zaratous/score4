package score4;
//CLASS move
//APO8IKEYEI row, column, value
//GIA KATHE KINHSH
public class Move {
    private int row;
    private int col;
    private int value;
    
    Move(){
        row = -1;
        col = -1;
        value = 0;
    }//constructor
    
    Move(int col, int row){
        this.col = col;
        this.row = row;
        this.value = -1;
    }//constructor
    
    Move(int col, int row, int value){
        this.col = col;
        this.row = row;
        this.value = value;
    }//constructor
    
    Move(int value){
        this.col = -1;
        this.row = -1;
        this.value = value;
    }//constructor
    
    Move(Move move){
        this.col = move.getCol();
        this.row = move.getRow();
        this.value = move.getValue();
    }//constructor
    
    public int getRow(){
        return row;
    }//getRow
    
    public int getCol(){
        return col;
    }//getCol
    
    public int getValue(){
        return value;
    }//getValue
    
    public void setRow(int row){
        this.row = row;
    }//setRow
    
    public void setCol(int col){
        this.col = col;
    }//setCol
    
    public void setValue(int value){
        this.value = value;
    }//setValue
}//class
