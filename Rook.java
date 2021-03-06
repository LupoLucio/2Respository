import java.util.LinkedList;

public class Rook extends Piece {

    public Square[] controlled;
    int index;

    public Rook(int xp, int yp, boolean isWhite, String name, LinkedList<Piece> ps) {
        super(xp, yp, isWhite, name, ps);
        // TODO Auto-generated constructor stub
        controlled = new Square[20];
        index = 0;
        generateControlledSquares();
    }

    /*
     * public boolean canMove(int xp, int yp) {
     * 
     * // almeno una delle 2 coordinate della nuova posizione deve essere uguale
     * alla // posizione iniziale if (xp != this.xp && yp != this.yp) return false;
     * 
     * // se arrivo qui almeno una delle 2 coordinate della mossa è la stessa della
     * pos // inziale del pezzo // controllo quindi se è possibile lo spostamneto
     * orizzonatale cioè non ci sono // pezzi in mezzo alla pos iniziale e quella
     * finale if (yp == this.yp) {
     * 
     * int distanceX = xp - this.xp; System.out.println(distanceX); int coeff = 1;
     * if (distanceX < 0) { coeff = coeff * (-1); } for (int i = 1; i <= distanceX;
     * i++) { if (ChessGame.getPiece(xp + i * coeff, yp) != null && i < distanceX)
     * return false; // controllo che nella casella di destinazione ci sia un pezzo
     * dello stesso // colore if (ChessGame.getPiece(xp + i * coeff, yp).isWhite ==
     * this.isWhite && i == distanceX) return false; } } // controllo quindi se è
     * possibile lo spostamento verticale cioè non ci sono // pezzi in mezzo alla
     * pos iniziale e quella finale if (xp == this.xp) {
     * 
     * int distanceY = yp - this.yp; System.out.println(distanceY); int coeff = 1;
     * if (distanceY < 0) { coeff = coeff * (-1); } for (int i = 1; i <= distanceY;
     * i++) { if (ChessGame.getPiece(xp, yp + i * coeff) != null && i < distanceY)
     * return false; // controllo che nella casella di destinazione ci sia un pezzo
     * dello stesso // colore if (ChessGame.getPiece(xp, yp + i * coeff).isWhite ==
     * this.isWhite && i == distanceY) return false; } }
     * 
     * System.out.println("validooo"); return true; }
     */
    public boolean canMove(int xp, int yp) {

        Square cella = new Square(xp, yp);

        if (!isControlled(cella)) {
            System.out.println("non controllata");
            return false;
        }
        if (ChessGame.getPiece((xp) * 64, (yp) * 64) == null) {
            return true;
        }
        if (ChessGame.getPiece((xp) * 64, (yp) * 64).isWhite != this.isWhite) {
            return true;
        }

        return false;
    }

    public void generateControlledSquares() {

        int i = 1;
        while (this.xp + i < 8) {

            controlled[index++] = new Square(this.xp + i, this.yp);
            if (ChessGame.getPiece((this.xp + i) * 64, (this.yp) * 64) != null) {
                break;
            }
            i++;

        }
        i = 1;
        while (this.xp - i >= 0) {

            controlled[index++] = new Square(this.xp - i, this.yp);
            if (ChessGame.getPiece((this.xp - i) * 64, (this.yp) * 64) != null) {
                break;
            }
            i++;

        }
        i = 1;
        while (this.yp + i < 8) {

            controlled[index++] = new Square(this.xp, this.yp + i);
            if (ChessGame.getPiece((this.xp) * 64, (this.yp + i) * 64) != null) {
                break;
            }
            i++;

        }
        i = 1;
        while (this.yp - i >= 0) {

            controlled[index++] = new Square(this.xp, this.yp - i);
            if (ChessGame.getPiece((this.xp) * 64, (this.yp - i) * 64) != null) {
                break;
            }
            i++;

        }

    }

    public void removeAll() {

        for (int i = 0; i < getControlledSquaresSize(); i++) {

            controlled[i] = null;
        }
        index = 0;

    }

    public Square[] getControlledSquares() {

        Square[] array = new Square[this.getControlledSquaresSize()];
        System.arraycopy(this.controlled, 0, array, 0, index);

        return array;

    }

    public int getControlledSquaresSize() {

        int size = 0;
        while (size < controlled.length && controlled[size] != null) {
            size++;
        }
        return size;
    }

    public boolean isControlled(Square cella) {

        for (int i = 0; i < getControlledSquaresSize(); i++) {

            if (controlled[i].getX() == cella.getX() && controlled[i].getY() == cella.getY()) {
                return true;
            }

        }

        return false;
    }

    public void move(int xp, int yp) {

        if (canMove(xp, yp)) {
            System.out.println("mossa valida");
            super.move(xp, yp);
            removeAll();
            super.upDateSquares();
        } else {
            System.out.println("mossa non valida");
        }

    }

    public String toString() {

        String s = "";

        for (int i = 0; i < getControlledSquaresSize(); i++) {
            s += controlled[i].getX() + " " + controlled[i].getY() + "\n";
        }

        return s;
    }

}
