import java.util.LinkedList;

public class Rook extends Piece {

    public Rook(int xp, int yp, boolean isWhite, String name, LinkedList<Piece> ps) {
        super(xp, yp, isWhite, name, ps);
        // TODO Auto-generated constructor stub
    }

    public boolean canMove(int xp, int yp) {

        if (xp != this.xp && yp != this.yp)
            return false;

        // se arrivo qui almeno una delle 2 coordinate della mossa è la stessa della pos
        // inziale del pezzo
        // controllo quindi se è possibile lo spostamneto orizzonatale cioè non ci sono
        // pezzi in mezzo alla pos iniziale e quella finale
        if (yp == this.yp) {

            int distanceX = xp - this.xp;
            int coeff = 1;
            if (distanceX < 0) {
                coeff = coeff * (-1);
            }
            for (int i = 1; i <= distanceX; i++) {
                if (ChessGame.getPiece(xp + i * coeff, yp + i * coeff) != null && i < distanceX)
                    return false;
                // controllo che nella casella di destinazione ci sia un pezzo dello stesso
                // colore
                if (ChessGame.getPiece(xp + i, yp + i).isWhite == this.isWhite && i == distanceX)
                    return false;
            }
        }
        // controllo quindi se è possibile lo spostamneto verticale cioè non ci sono
        // pezzi in mezzo alla pos iniziale e quella finale
        if (xp == this.xp) {

            int distanceY = yp - this.yp;
            int coeff = 1;
            if (distanceY < 0) {
                coeff = coeff * (-1);
            }
            for (int i = 1; i <= distanceY; i++) {
                if (ChessGame.getPiece(xp + i * coeff, yp + i * coeff) != null && i < distanceY)
                    return false;
                // controllo che nella casella di destinazione ci sia un pezzo dello stesso
                // colore
                if (ChessGame.getPiece(xp + i, yp + i).isWhite == this.isWhite && i == distanceY)
                    return false;
            }
        }

        return false;
    }

    public void move(int xp, int yp) {
        if (canMove(xp, yp)) {
            super.move(xp, yp);
        }
    }

}
