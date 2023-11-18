import Figures.Bishop;
import Figures.Figure;
import Figures.King;
import Figures.Knight;
import Figures.Pawn;
import Figures.Queen;
import Figures.Rook;

import java.util.ArrayList;

public class Board {
    //TODO: Список фигур и начальное положение всех фигур
    private Figure  fields[][] = new Figure[8][8];
    private ArrayList<String> takeWhite = new ArrayList(16);
    private ArrayList<String> takeBlack = new ArrayList(16);

    public char getColorGaming() {
        return colorGaming;
    }

    public void setColorGaming(char colorGaming) {
        this.colorGaming = colorGaming;
    }

    private char colorGaming;

    public void init(){
        this.fields[0] = new Figure[]{
                new Rook("R", 'w'), new Knight("N", 'w'),
                new Bishop("B", 'w'), new Queen("Q", 'w'),
                new King("K", 'w'), new Bishop("B", 'w'),
                new Knight("N", 'w'),new Rook("R", 'w')
        };
        this.fields[1] = new Figure[]{
                new Pawn("P", 'w'),    new Pawn("P", 'w'),
                new Pawn("P", 'w'),    new Pawn("P", 'w'),
                new Pawn("P", 'w'),    new Pawn("P", 'w'),
                new Pawn("P", 'w'),    new Pawn("P", 'w'),
        };

        this.fields[7] = new Figure[]{
                new Rook("R", 'b'), new Knight("N", 'b'),
                new Bishop("B", 'b'), new Queen("Q", 'b'),
                new King("K", 'b'), new Bishop("B", 'b'),
                new Knight("N", 'b'),new Rook("R", 'b')
        };
        this.fields[6] = new Figure[]{
                new Pawn("P", 'b'),    new Pawn("P", 'b'),
                new Pawn("P", 'b'),    new Pawn("P", 'b'),
                new Pawn("P", 'b'),    new Pawn("P", 'b'),
                new Pawn("P", 'b'),    new Pawn("P", 'b'),
        };
    }

    public String getCell(int row, int col){
        Figure figure = this.fields[row][col];
        if (figure == null){
            return "    ";
        }
        return " "+figure.getColor()+figure.getName()+" ";
    }

    public ArrayList<String> getTakeWhite() {
        return takeWhite;
    }

    public ArrayList<String> getTakeBlack() {
        return takeBlack;
    }

    public boolean move_figure(int row1, int col1, int row2, int col2 ){

        Figure figure =  this.fields[row1][col1];

        if (figure.canMove(row1, col1, row2, col2) && this.fields[row2][col2]==null && ItsClear(row1, col1, row2, col2)){
            System.out.println("move");
            this.fields[row2][col2] = figure;
            this.fields[row1][col1] = null;
            return true;
        } else if (figure.canAttack(row1, col1, row2, col2) && this.fields[row2][col2] != null && ItsClear(row1, col1, row2, col2)&& this.fields[row2][col2].getColor() != this.fields[row1][col1].getColor() && !(this.fields[row2][col2] instanceof King) ){
            System.out.println("attack");
            switch (this.fields[row2][col2].getColor()){
                case 'w': this.takeWhite.add(this.fields[row2][col2].getColor()+this.fields[row2][col2].getName());break;
                case 'b': this.takeBlack.add(this.fields[row2][col2].getColor()+this.fields[row2][col2].getName());break;
            }
            this.fields[row2][col2] = figure;
            this.fields[row1][col1] = null;
            return true;
        }
        return false;





    }


    public boolean ItsClear (int row1, int col1, int row2, int col2){
        Figure figure =  this.fields[row1][col1];
        if (figure instanceof Knight){
            return true;
        } else if (figure.getColor() != colorGaming){
            System.out.println("Сейчас ходят фигуры другого цвета!");
            return false;
        }else {
            int a= 1;
            int b =1;
            if (row1 < row2){
                a = -1;
            }if (col1 < col2){
                b = -1;
            }
            while (true){
                if (row1 !=row2){
                    row1 = row1 - a;
                }
                if (col1 != col2){
                    col1 = col1 - b;
                }

                if (row1 == row2 && col1 == col2){
                    return true;
                } else if(this.fields[row1][col1] != null){
                    return false;
                }
            }
        }


    }


    public boolean CheckMate ( ) {
        int r = -1;
        int c = -1;
        for (r =0; r <8; r++){ // Ищем короля текущего игрока
            for (c =0; c <8; c++){
                if (this.fields[r][c] instanceof King && this.fields[r][c].getColor() == colorGaming){
                    r = r +100;
                    break;
                }
            }
        }
        r = r-100;
        int [] row = new int[15];
        int [] col = new int [15];
        int t =0;
        for (int i = 0; i<8; i++){ // Ищем все угрозы для короля и записываем их
            for (int j = 0; j<8; j++){
                if (this.fields[i][j] != null ) {
                    if (this.fields[i][j].getColor() != colorGaming) {
                        if (this.fields[i][j].canAttack(i, j, r, c) && ItsClear(i, j, r, c)) {
                            row[t] = i;
                            col[t] = j;
                            t++;
                        }
                    }
                }
            }
        }

        if (t == 0){ // Если угроз нет, то и шаха тоже нет
            return false;
        } else { // Если есть угрозы, то можно поробовать поставить короля в безопастное место
            for (int i = -1; i<2;i++){
                for (int j =-1; j<2;j++){
                    int rr = r + i;
                    int cc = c +j;
                    if (rr==r &&cc ==c){

                    }else if (rr >=0 && rr <=7) {
                        if (cc >=0 && cc<=7){
                            if (this.fields[rr][cc] == null || (this.fields[r][c].canMove(r,c,rr,cc) || this.fields[r][c].canAttack(r,c,rr,cc)) && ItsClear(r,c,rr,cc)){
                                this.fields[rr][cc] = this.fields[r][c];

                                boolean ttt = false;
                                for (int ii = 0; ii<8; ii++){
                                    for (int jj = 0; jj<8; jj++){
                                        if (this.fields[ii][jj] != null ) {
                                            if (this.fields[ii][jj].getColor() != colorGaming) {
                                                if (this.fields[ii][jj].canAttack(ii, jj, rr, cc) && ItsClear(ii, jj, rr, cc)) {
                                                    ttt = true;
                                                    ii = 10;
                                                    jj = 10;
                                                }
                                            }
                                        }
                                    }
                                }
                                this.fields[rr][cc] = null;
                                if (ttt == false){
                                    return false;
                                }
                            }
                        }
                    }
                }

            }
        }

        if (t <1){ //  если нет безопасных мест и угроз больше 1, то шах и мат
            return true;
        } else { //  если угроза одна, то ее можно устранить или попробовать защитить короля
            int er = row[0];
            int ec = col[0];



            for (int i =0; i <8; i++){
                for (int j =0; j <8; j++){
                    if (this.fields[i][j].getColor() == colorGaming){
                        if (this.fields[i][j].canAttack(i, j, er, ec) && ItsClear(i,j,er,ec)){
                            return false;
                        }
                    } else{
                        if (!(this.fields[er][ec] instanceof Knight)){
                            for (int ii =0; ii< 8; ii++){
                                for (int jj =0; jj< 8; jj++){
                                    if (this.fields[i][j].canMove(i, j, ii, jj) && ItsClear(i,j,ii,jj)){
                                        if (!(this.fields[er][ec].canAttack(er,ec,r,c) && ItsClear(er,ec,r,c))){
                                            return false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }


            return true;

        }

    }

    public void print_board(){
        System.out.println(" +----+----+----+----+----+----+----+----+");
        for(int row = 7; row > -1; row--){
            System.out.print(row);
            for(int col = 0; col< 8; col++){
                System.out.print("|"+getCell(row, col));
            }
            System.out.println("|");
            System.out.println(" +----+----+----+----+----+----+----+----+");
        }

        for(int col = 0; col < 8; col++){
            System.out.print("    "+col);
        }


    }


}