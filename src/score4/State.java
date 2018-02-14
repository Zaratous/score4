package score4;

import java.util.ArrayList;


//CLASS State
//KATASTASH TOY TAMPLO TOY PAIXNIDIOY
public class State {
    private int table[][];
    private int color;
    private Move lastMove;
    
    //KENOS constructor
    //GEMIZEI ME 0 OLES TIS THESEIS
    State(){
        table = new int[7][6];
        color = 1;
        this.lastMove = new Move();
        for(int col = 0; col < 7; col ++){
            for(int row = 0; row < 6; row ++){
                table[col][row] = 0;
            }//for
        }//for
    }//State
    
    //constructor POU DEXETAI OS ORISMA ALLO ANTIKEIMENO STATE
    //KAI TO ANTIGRAFEI
    State(State state){
        this.color = state.color;
        this.table = new int[7][6];
        this.lastMove = state.lastMove;
        
        for(int col = 0; col < 7; col ++){
            for(int row = 0; row < 6; row ++){
                this.table[col][row] = state.table[col][row];
            }//for
        }//for
    }//State
    
    //AKOLOUTHOUN METHODOI SET KAI GET
    //GIA TIS DIAFORES GLOBAL METAVLHTES
    
    public Move getLastMove(){
        return lastMove;
    }//getLastMove
    
    public void setLastMove(Move lastMove){
        this.lastMove.setRow(lastMove.getRow());
        this.lastMove.setCol(lastMove.getCol());
        this.lastMove.setValue(lastMove.getValue());
    }//setLastMove
    
    public void makeMove(int col, int row, int color){
        table[col][row] = color;
        lastMove = new Move(col, row);
        this.color = color;
    }//makeMove
    
    public int getColor(){
        return this.color;
    }//getColor
    
    public void setColor(int color){
        this.color = color;
    }
    
    public int[][] getTable(){
        return table;
    }//getTable
    
    //ELEGXOS AN H KINSHSH EINAI EPITREPTH
    //DEXETAI OS EISODO TH STHLH POY THELO NA VALO ENA POULI
    //AN MPORO NA KANO THN KINHSH EPISTREFEI TH STHLH
    //ALLIOS EPISTREFEI -1
    public int isValidMove(int column){
        if(color != 1 && color != 2){
            return -1;
        }//if
        //AN H PANW GRAMMH EINAI GEMATH
        if(table[column][0] != 0){
            return -1;
        }//if
        for(int row = 0; row < 5; row++){
            //EISAGEI POULI STIS GRAMMES 0 WS 4
            //KOITAZONTAS APO PANW PROS TA KATW
            if(table[column][row] != 0){
                row--;
                return row;
            }//if
        }//for
        //GIA TH THESH 5
        //KOITAZONTAS PALI APO PANW PROS TA KATW
        if(table[column][5] != 0){
            return 4;
        }//if
        else{
            return 5;
        }
    }//isValid
    
    //GIA TO TREXON ANTIKEIMENO State
    //DHMIOYRGEI TA PAIDIA TOY ME VASH THN EISODO COLOR POU DEIXNEI POIOS PAIZEI
    //KAI TA EISAGEI SE ArrayList TO OPOIO KAI EPISTREFEI
    public ArrayList<State> getChildren(int color){
        ArrayList<State> children = new ArrayList<State>();
        int row;
        for(int col = 0; col < 7; col ++){
            
            row = isValidMove(col);
            if(row != -1){
                State child = new State(this);
                child.makeMove(col, row, color);
                children.add(child);
            }//if
            
        }//for
        return children;
    }//getChildren
    
    
    //AKSIOLOGHSH THS KATASTASHS State
    public int evaluate(){
        //TIMES GIA NA APO8HKEYSO TIS KINHSEIS KATHE PAIKTH
        int minVal = 0;//GIA TON MIN
        int maxVal = 0;//GIA TON MAX
        int count;//METRA POSA IDIOU XROMATOS POULIA EXEI METRHSEI
        int tempVal;//PROSORINH TIMH POU MAS VOHTHA STHN AKSIOLOGHSH
        
        //GIA KATHE GRAMMH
        //GIA KATHE STHLH
        //DHLADH GIA KATHE POULI
        for(int row = 0; row < 6; row ++){
            for(int col = 0; col < 7; col ++){
                if(table[col][row] != 0){
                    int tempColor = table[col][row];
                    /*
                     * GIA TA POULIA POU VRISKONTAI STIS MESAIES STHLES
                     * METRHSE PERISSOTEROUS VATHMOUS
                     */
                    if(col == 2 || col == 4){
                        if(tempColor == 1){
                            maxVal += 3;
                        }//if
                        else{
                            minVal -= 3;
                        }//else
                    }//if
                    else if(col == 3){
                        if(tempColor == 1){
                            maxVal += 5;
                        }//if
                        else{
                            minVal -= 5;
                        }//else
                    }//else if
                    
                    /*
                     * GIA SYGKEKRIMENO POULI ELEGXOS PROS TA DEKSIA
                     */
                    count = 0;
                    tempVal = 0;
                    for(int position = col; position < 7; position ++){
                        if(table[position][row] != 0){
                            if(table[position][row] == tempColor){
                                count ++;
                                if(count >= 2){
                                    if(count == 4){
                                        tempVal += 1000;
                                    }//if
                                    else{
                                        tempVal += 5*count;
                                    }//else
                                    
                                    if(tempColor == 1){
                                        maxVal += tempVal;
                                    }//if
                                    else{
                                        minVal -= tempVal;
                                    }//else
                                }//if
                                //System.out.println("DEKSIA: maxVal = "+maxVal+" minVal = "+minVal);
                            }//if
                            else{
                                break;
                            }//else
                        }//if
                    }//for
                    
                    /*
                     * GIA SYGKEKRIMENO POULI ELEGXOS PROS TA KATW
                     */
                    count = 0;
                    tempVal = 0;
                    for(int position = row; position < 6; position ++){
                        if(table[col][position] != 0){
                            if(table[col][position] == tempColor){
                                count ++;
                                if(count >= 2){
                                    if(count == 4){
                                        tempVal += 1000;
                                    }//if
                                    else{
                                        tempVal += 5*count;
                                    }//else
                                    
                                    if(tempColor == 1){
                                        maxVal += tempVal;
                                    }//if
                                    else{
                                        minVal -= tempVal;
                                    }//else
                                }//if
                                //System.out.println("KATW: maxVal = "+maxVal+" minVal = "+minVal);
                            }//if
                            else{
                                break;
                            }//else
                        }//if
                    }//for
                    
                    /*
                     * GIA SYGKEKRIMENO POULI ELEGXOS PROS TA KATO KAI DEKSIA
                     */
                    count = 0;
                    tempVal = 0;
                    int tempRow = row;
                    int tempCol = col;
                    do{
                        if(table[tempCol][tempRow] != 0){
                            if(table[tempCol][tempRow] == tempColor){
                                count ++;
                                if(count >= 2){
                                    if(count == 4){
                                        tempVal += 1000;
                                    }//if
                                    else{
                                        tempVal += 5*count;
                                    }//else
                                    
                                    if(tempColor == 1){
                                        maxVal += tempVal;
                                    }//if
                                    else{
                                        minVal -= tempVal;
                                    }//else
                                }//if
                            }//if
                            else{
                                count = 0;
                            }//else
                        }//if
                        tempCol ++;
                        tempRow ++;
                    }while(tempRow < 6 && tempCol < 7);
                    
                    /*
                     * GIA SYGKEKRIMENO POULI ELEGXOS PROS TA PANO KAI DEKSIA
                     */
                    count = 0;
                    tempVal = 0;
                    tempRow = row;
                    tempCol = col;
                    do{
                        if(table[tempCol][tempRow] != 0){
                            if(table[tempCol][tempRow] == tempColor){
                                count ++;
                                if(count >= 2){
                                    if(count == 4){
                                        tempVal += 1000;
                                    }//if
                                    else{
                                        tempVal += 5*count;
                                    }//else
                                    
                                    if(tempColor == 1){
                                        maxVal += tempVal;
                                    }//if
                                    else{
                                        minVal -= tempVal;
                                    }//else
                                }//if
                            }//if
                            else{
                                count = 0;
                            }//else
                        }//if
                        tempCol ++;
                        tempRow --;
                    }while(tempRow > -1 && tempCol < 7);
                    
                }//if
            }//for
        }//for
        
        int value = maxVal + minVal;
        return value;
    }//evaluate
    
    
    //ELEGXOS AN H State EINAI TERMATIKH
    //EPISTREFEI TRUE AN EINAI TERMATIKH
    public boolean isTerminal(){
        for(int y = 0; y < 6; y ++){
            for(int x = 0; x < 7; x++){
                if (table[x][y] != 0){
                    int tempColor = table[x][y];
                    
                    //GIA ENA SYGKEKRIMENO POULI
                    //KINHSH PROS TA DEKSIA
                    //WSPOU NA VRETHOUN 4 TOY IDIOY XRWMATOS
                    int count = 0;//METAVLHTH POU DEIXNEI POSA STH SEIRA IDIOU XROMATOS EXEI METRHSEI
                    int k = 0;//METAVLHTH POU VOHTHA STO METRHMA
                    for(int position = x; position < 7 && k < 4; position ++){
                        k++;
                        if (table[position][y] != 0){
                            if(table[position][y] == tempColor){
                                count ++;
                                if(count == 4){
                                    return true;
                                }//if
                            }//if
                        }//if
                        else break;
                    }//for
                    
                    //GIA ENA SYGKEKRIMENO POULI
                    //KINHSH PROS TA KATW
                    //WSPOU NA VRETOUN 4 TOY IDIOY XRWMATOS
                    count = 0;
                    k = 0;
                    for(int position = y; position < 6 && k < 4; position ++){
                        k++;
                        if(table[x][position] != 0){
                            if(table[x][position] == tempColor){
                                count ++;
                                if(count == 4){
                                    return true;
                                }//if
                            }//if
                        }//if
                        else break;
                    }//for
                    
                    //METAVLHTES POU VOHTHOUN STHN PROSPELASH TON PINAKON
                    int boundX = 6;
                    int boundY = 5;
                    
                    //GIA ENA SYGKEKRIMENO POULI
                    //KINHSH PROS TA KATW KAI TA DEKSIA
                    //WSPOU NA VRETHOUN 4 TOU IDIOU XRWMATOS
                    count = 1;
                    k = 0;
                    int positionX=x;
                    int positionY=y;
                    while(positionX < boundX && positionY < boundY && k < 3){
                        k++;
                        positionX++;
                        positionY++;
                        if(table[positionX][positionY] != 0){
                                if(table[positionX][positionY] == tempColor){
                                count ++;
                                if(count == 4){
                                    return true;
                                }//if
                            }//if
                        }//if
                        else break;
                    }//while
                    
                    //GIA ENA SYGKEKRIMENO POULI
                    //KINHSH PROS TA PANW KAI TA DEKSIA
                    //WSPOU NA VRETOUN 4 TOU IDIOU XRWMATOS
                    count = 1;
                    k = 0;
                    positionX=x;
                    positionY=y;
                    while(positionX < boundX && positionY > -1 && k < 3){
                        k++;
                        positionX++;
                        positionY--;
                        if(positionY == -1){
                            break;
                        }//if
                        if(table[positionX][positionY] != 0){
                            if(table[positionX][positionY] == tempColor){
                                count ++;
                                if(count == 4){
                                    return true;
                                }//if
                            }//if
                        }//if
                        else break;
                    }//while
                }//if
            }//for x
        }//for y
        return false;
    }//isTerminal
}//class
