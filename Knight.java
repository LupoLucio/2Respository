import java.util.LinkedList;

public class Knight extends Piece {

    public Square[] controlled;
    int index;

    public Knight(int xp, int yp, boolean isWhite, String name, LinkedList<Piece> ps) {
        super(xp, yp, isWhite, name, ps);
        // TODO Auto-generated constructor stub
        controlled = new Square[8];
        index = 0;
        generateControlledSquares();
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

        // tutte le possibili controllabili a destra del cavallo
        if (isIn(this.xp + 1, this.yp - 2)) {
            controlled[index++] = new Square(this.xp + 1, this.yp - 2);
        }
        if (isIn(this.xp + 2, this.yp - 1)) {
            controlled[index++] = new Square(this.xp + 2, this.yp - 1);
        }
        if (isIn(this.xp + 2, this.yp + 1)) {
            controlled[index++] = new Square(this.xp + 2, this.yp + 1);
        }
        if (isIn(this.xp + 1, this.yp + 2)) {
            controlled[index++] = new Square(this.xp + 1, this.yp + 2);
        }

        // tutte le possibili controllabili a sinistra del cavallo
        if (isIn(this.xp - 1, this.yp + 2)) {
            controlled[index++] = new Square(this.xp - 1, this.yp + 2);
        }
        if (isIn(this.xp - 2, this.yp + 1)) {
            controlled[index++] = new Square(this.xp - 2, this.yp + 1);
        }
        if (isIn(this.xp - 2, this.yp - 1)) {
            controlled[index++] = new Square(this.xp - 2, this.yp - 1);
        }
        if (isIn(this.xp - 1, this.yp - 2)) {
            controlled[index++] = new Square(this.xp - 1, this.yp - 2);
        }

    }

    public boolean isControlled(Square cella) {

        for (int i = 0; i < getControlledSquaresSize(); i++) {

            if (controlled[i].getX() == cella.getX() && controlled[i].getY() == cella.getY()) {
                return true;
            }

        }

        return false;
    }

    public boolean isIn(int xp, int yp) {

        if (xp >= 0 && xp < 8 && yp >= 0 && yp < 8) {
            return true;
        }
        return false;
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
