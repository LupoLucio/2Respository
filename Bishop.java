import java.util.LinkedList;

public class Bishop extends Piece {

    public Bishop(int xp, int yp, boolean isWhite, String name, LinkedList<Piece> ps) {
        super(xp, yp, isWhite, name, ps);
        // TODO Auto-generated constructor stub
    }

    public boolean canMove(int xp, int yp) {

        int diffY = yp - this.yp;
        int diffX = xp - this.xp;

        int pendenza = diffY / diffX;
        // tutti i coefficienti angolari dal bishop devono avere valore 1;
        if (pendenza != 1)
            return false;

        // controllo che le caselle del viaggio diagonale dell alfiere siano occupate da
        // pazzi dello stesso colore
        for (int i = 1; i <= diffX; i++) {
            if (ChessGame.getPiece(this.xp + i * pendenza, this.yp + i * pendenza) != null && i < diffX)
                return false;
            if (ChessGame.getPiece(this.xp + i * pendenza, this.yp + i * pendenza).isWhite == this.isWhite & i == diffX)
                return false;
        }

        return true;

    }

    public void move(int xp, int yp) {
        if (canMove(xp, yp)) {
            super.move(xp, yp);
        }
    }

}
