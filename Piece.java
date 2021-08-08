import java.util.LinkedList;

public class Piece {

    // xp e tp rappresentano le coordinate del pezzo rispetto alla scacchiera 8x8
    int xp;
    int yp;

    // x r y rappresentano le coordiante del pezzo rispetto al frame 512x512
    int x;
    int y;

    // true = bianco, false = nero
    boolean isWhite;

    // linkedList di memorizzazione dei vari pezzi
    LinkedList<Piece> ps;

    // nome del pezzo
    String name;

    // costruttore
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
    /*
     * public boolean canMove(int xp, int yp) {
     * 
     * if (this.name.equals("pawn") && isWhite && xp == this.xp && (yp == this.yp -
     * 1 || (yp == this.yp - 2 && this.yp == 6))) { return true; } else if
     * (this.name.equals("pawn") && !isWhite && xp == this.xp && yp == this.yp + 1)
     * { return true; }
     * 
     * return false;
     * 
     * }
     */

    // metodo che muove un pezzo
    public void move(int xp, int yp) {

        // controllo che nelle coordinate date dalla mossa ci sia un altro pezzo
        if (ChessGame.getPiece(xp * 64, yp * 64) != null) {
            // controllo che quel qualcosa sia o meno dello stesso colore di questo pezzo
            if (ChessGame.getPiece(xp * 64, yp * 64).isWhite != isWhite) {
                // se abbiamo colori diversi mangio il pezzo
                ChessGame.getPiece(xp * 64, yp * 64).kill();
            }
            // il questo else non è vero che hanno colori != quindi setto le cordinate del
            // pezzo a quelle attuali(non lo muovo)
            else {
                x = this.xp * 64;
                y = this.yp * 64;
                return;
            }
        }

        // qui vuol dire che le coordinate date portano a una casella libera e quindi
        // setto le nuove coordinate a quella casella
        this.xp = xp;
        this.yp = yp;
        x = xp * 64;
        y = yp * 64;

    }

    // metodo che rimuove un pezzo dalla linkedList
    public void kill() {
        ps.remove(this);
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }

}
