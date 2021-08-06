import java.util.LinkedList;

public class Piece {

    int xp;
    int yp;

    int x;
    int y;

    boolean isWhite;
    LinkedList<Piece> ps;
    String name;

    public Piece(int xp, int yp, boolean isWhite, String name, LinkedList<Piece> ps) {
        this.xp = xp;
        this.yp = yp;
        x = xp * 64;
        y = yp * 64;
        this.isWhite = isWhite;
        this.ps = ps;
        this.name = name;
        ps.add(this);
    }

    /*
     * public void legalMove(int xp, int yp) {
     * 
     * if (this.name.equals("pawn")) { if (xp == this.xp && yp == this.yp + 1) {
     * move(xp, yp); } System.out.println("pedonne"); } else { move(xp, yp); }
     * 
     * }
     */
    public boolean canMove(int xp, int yp) {

        if (this.name.equals("pawn") && isWhite && xp == this.xp
                && (yp == this.yp - 1 || (yp == this.yp - 2 && this.yp == 6))) {
            return true;
        } else if (this.name.equals("pawn") && !isWhite && xp == this.xp && yp == this.yp + 1) {
            return true;
        }

        return false;

    }

    public void move(int xp, int yp) {

        if (ChessGame.getPiece(xp * 64, yp * 64) != null) {
            if (ChessGame.getPiece(xp * 64, yp * 64).isWhite != isWhite) {
                ChessGame.getPiece(xp * 64, yp * 64).kill();
            } else {
                x = this.xp * 64;
                y = this.yp / 64;
                return;
            }
        }

        this.xp = xp;
        this.yp = yp;
        x = xp * 64;
        y = yp * 64;

    }

    public void kill() {
        ps.remove(this);
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }

    public Square[] getControlledSquares() {
        return null;
    }

}
