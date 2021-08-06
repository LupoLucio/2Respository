import java.util.LinkedList;

public class Pawn extends Piece {

    public Square[] controlled;
    boolean hasMoved = false;
    boolean hasQueened = false;

    public Pawn(int xp, int yp, boolean isWhite, String name, LinkedList<Piece> ps) {
        super(xp, yp, isWhite, name, ps);
        controlled = new Square[2];
        generateControlledSquares();
        // TODO Auto-generated constructor stub
    }

    public boolean canMove(int xp, int yp) {

        if (this.canEat())
            return true;

        if (this.name.equals("pawn") && isWhite && xp == this.xp
                && (yp == this.yp - 1 || (yp == this.yp - 2 && this.yp == 6))) {
            return true;
        } else if (this.name.equals("pawn") && !isWhite && xp == this.xp
                && (yp == this.yp + 1 || (yp == this.yp + 2 && this.yp == 2))) {
            return true;
        }

        return false;

    }

    private boolean canEat() {

        if (this.isWhite) {
            if ((ChessGame.getPiece(x - 1, y - 1) != null && ChessGame.getPiece(x - 1, y - 1).isWhite != this.isWhite)
                    || (ChessGame.getPiece(x + 1, y - 1) != null
                            && ChessGame.getPiece(x + 1, y - 1).isWhite != this.isWhite)) {
                return true;
            }
        } else {
            if ((ChessGame.getPiece(x - 1, y + 1) != null && ChessGame.getPiece(x - 1, y + 1).isWhite != this.isWhite)
                    || (ChessGame.getPiece(x + 1, y + 1) != null
                            && ChessGame.getPiece(x + 1, y + 1).isWhite != this.isWhite)) {
                return true;
            }
        }

        return false;

    }

    public void move(int xp, int yp) {

        if (canMove(xp, yp)) {
            super.move(xp, yp);

        }

    }

    public void generateControlledSquares() {

        if (this.isWhite) {
            if (yp > 0) {

                if (xp == 0) {
                    controlled[0] = new Square(xp + 1, yp - 1);
                } else if (xp == 7) {
                    controlled[0] = new Square(xp - 1, yp - 1);
                } else {
                    controlled[0] = new Square(xp - 1, xp - 1);
                    controlled[1] = new Square(xp + 1, xp - 1);
                }

            }
        } else {
            if (yp < 7) {

                if (xp == 0) {
                    controlled[0] = new Square(xp + 1, yp + 1);
                } else if (xp == 7) {
                    controlled[0] = new Square(xp - 1, yp + 1);
                }
                controlled[0] = new Square(xp - 1, yp + 1);
                controlled[1] = new Square(xp + 1, yp + 1);
            }
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

    public String toString() {

        String s = "";
        Square[] array = getControlledSquares();
        for (int i = 0; i < getControlledSquaresSize(); i++) {
            s += array[i].getX() + " " + array[i].getY() + "\n";
        }

        return s;
    }

}
