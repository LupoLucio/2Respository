import java.util.LinkedList;

public class Queen extends Piece {

    public Square[] controlled;
    int index;

    public Queen(int xp, int yp, boolean isWhite, String name, LinkedList<Piece> ps) {
        super(xp, yp, isWhite, name, ps);
        // TODO Auto-generated constructor stub
        controlled = new Square[28];
        index = 0;
        generateControlledSquares();
        // generateControlledSquares();
        // generateControlledSquares();
    }

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

    public void move(int xp, int yp) {

        if (canMove(xp, yp)) {
            super.move(xp, yp);
            removeAll();
            super.upDateSquares();
        } else {
            System.out.println("non puo muoversi");
        }

    }

    public void generateControlledSquares() {

        // diagonale basso a destra
        int i = 1;
        while (this.xp + i <= 7 && this.yp + i <= 7) {
            controlled[index++] = new Square(this.xp + i, this.yp + i);
            // se dopo aver aggiunto la casella scopro che è occupata da qualcosa mi fermo
            if (ChessGame.getPiece((this.xp + i) * 64, (this.yp + i) * 64) != null) {
                break;
            }

            i++;
        }
        // diagonale basso a sinistra
        i = 1;
        while (this.xp - i >= 0 && this.yp + i <= 7) {
            controlled[index++] = new Square(this.xp - i, this.yp + i);
            // se dopo aver aggiunto la casella scopro che è occupata da qualcosa mi fermo
            if (ChessGame.getPiece((this.xp - i) * 64, (this.yp + i) * 64) != null) {
                break;
            }

            i++;
        }
        // diagonale alto destra
        i = 1;
        while (this.xp + i <= 7 && this.yp - i >= 0) {
            controlled[index++] = new Square(this.xp + i, this.yp - i);
            // se dopo aver aggiunto la casella scopro che è occupata da qualcosa mi fermo
            if (ChessGame.getPiece((this.xp + i) * 64, (this.yp - i) * 64) != null) {
                break;
            }

            i++;
        }
        // diagonale alto a sinistra
        i = 1;
        while (this.xp - i >= 0 && this.yp - i >= 0) {
            controlled[index++] = new Square(this.xp - i, this.yp - i);
            // se dopo aver aggiunto la casella scopro che è occupata da qualcosa mi fermo
            if (ChessGame.getPiece((this.xp - i) * 64, (this.yp - i) * 64) != null) {
                break;
            }

            i++;
        }

        i = 1;
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

    public Square[] getControlledSquares() {

        Square[] array = new Square[this.getControlledSquaresSize()];
        System.arraycopy(this.controlled, 0, array, 0, this.getControlledSquaresSize());

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

    public void removeAll() {

        for (int i = 0; i < getControlledSquaresSize(); i++) {

            controlled[i] = null;
        }
        index = 0;
    }

    public String toString() {

        String s = "";

        for (int i = 0; i < getControlledSquaresSize(); i++) {
            s += controlled[i].getX() + " " + controlled[i].getY() + "\n";
        }

        return s;
    }

}
