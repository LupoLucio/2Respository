import java.util.ArrayList;
import java.util.LinkedList;

public class Bishop extends Piece {

    public ArrayList<Square> controlled;

    public Bishop(int xp, int yp, boolean isWhite, String name, LinkedList<Piece> ps) {
        super(xp, yp, isWhite, name, ps);
        // TODO Auto-generated constructor stub
        controlled = new ArrayList<Square>();
        generateControlledSquares();
    }

    // metodo boolean che dice se l'alfiere si puo' muovere o no nella posizione
    // data
    public boolean canMove(int xp, int yp) {

        // differenza fra la cord y della mossa e quella y attuale
        int diffY = yp - this.yp;
        System.out.println(diffY);
        // differenza fra la cord x della mossa e quella x attuale
        int diffX = xp - this.xp;
        System.out.println(diffX);

        if (diffX == 0 || diffY == 0) {

            return false;
        }

        // coefficente angolare è il rapporto fra la differenza in y e la differenza in
        // x

        int pendenza = diffY / diffX;
        int resto = diffY % diffX;
        System.out.println(pendenza + " " + resto);
        // tutti i coefficienti angolari dal bishop devono avere valore 1 o -1;
        if (pendenza != 1 && pendenza != -1 || resto != 0) {

            return false;
        }
        // controllo che le caselle del viaggio diagonale dell alfiere siano occupate da
        // altri pezzi o no

        for (int i = 1; i <= diffX; i++) {

            if (i < diffX) {
                // in tutte le caselle prima della destinazione se c'è qualcosa in mezzo ritorno
                // false
                if (diffX < 0 && diffY < 0) {
                    if (ChessGame.getPiece(this.xp - i, this.yp - i) != null)
                        return false;
                } else if (diffX > 0 && diffY < 0) {
                    if (ChessGame.getPiece(this.xp + i, this.yp - i) != null)
                        return false;
                } else if (diffX < 0 && diffY > 0) {
                    if (ChessGame.getPiece(this.xp - i, this.yp + i) != null)
                        return false;
                } else if (diffX > 0 && diffY > 0) {
                    if (ChessGame.getPiece(this.xp + i, this.yp + i) != null)
                        return false;
                }

            } // nel caso si tratti della casella di destinazione ritorno false solo se è
              // occupata da un pezzo dello stesso colore
              // perchè se è del colore opposto posso mangiare
            else {

                if (diffX < 0 && diffY < 0) {
                    if (ChessGame.getPiece(this.xp - i, this.yp - i).isWhite == this.isWhite)
                        return false;
                } else if (diffX > 0 && diffY < 0) {
                    if (ChessGame.getPiece(this.xp + i, this.yp - i).isWhite == this.isWhite)
                        return false;
                } else if (diffX < 0 && diffY > 0) {
                    if (ChessGame.getPiece(this.xp - i, this.yp + i).isWhite == this.isWhite)
                        return false;
                } else if (diffX > 0 && diffY > 0) {
                    if (ChessGame.getPiece(this.xp + i, this.yp + i).isWhite == this.isWhite)
                        return false;
                }
            }

        }

        System.out.println("mossa valida");
        return true;

    }

    // metodo che genera le caselle controllate dal'alfiere nella sua posizione
    public void generateControlledSquares() {

        // diagonale basso a destra
        int i = 1;
        while (this.xp + i <= 7 && this.yp + i <= 7) {
            controlled.add(new Square(this.xp + i, this.yp + i));
            // se dopo aver aggiunto la casella scopro che è occupata da qualcosa mi fermo
            if (ChessGame.getPiece((this.xp + i) * 64, (this.yp + i) * 64) != null) {
                break;
            }

            i++;
        }
        // diagonale basso a sinistra
        i = 1;
        while (this.xp - i >= 0 && this.yp + i <= 7) {
            controlled.add(new Square(this.xp - i, this.yp + i));
            // se dopo aver aggiunto la casella scopro che è occupata da qualcosa mi fermo
            if (ChessGame.getPiece((this.xp - i) * 64, (this.yp + i) * 64) != null) {
                break;
            }

            i++;
        }
        // diagonale alto destra
        i = 1;
        while (this.xp + i <= 7 && this.yp - i >= 0) {
            controlled.add(new Square(this.xp + i, this.yp - i));
            // se dopo aver aggiunto la casella scopro che è occupata da qualcosa mi fermo
            if (ChessGame.getPiece((this.xp + i) * 64, (this.yp - i) * 64) != null) {
                break;
            }

            i++;
        }
        // diagonale alto a sinistra
        i = 1;
        while (this.xp - i >= 0 && this.yp - i >= 0) {
            controlled.add(new Square(this.xp - i, this.yp - i));
            // se dopo aver aggiunto la casella scopro che è occupata da qualcosa mi fermo
            if (ChessGame.getPiece((this.xp - i) * 64, (this.yp - i) * 64) != null) {
                break;
            }

            i++;
        }

    }

    public ArrayList getControlledSquares() {

        return controlled;

    }

    public void move(int xp, int yp) {
        if (canMove(xp, yp)) {

            super.move(xp, yp);

        } else {
            System.out.println(" non puo muoversis");
        }
        this.removeAllControlled();
        generateControlledSquares();
    }

    public void removeAllControlled() {

        for (int i = 0; i < controlled.size(); i++) {
            controlled.remove(i);
        }

    }

    public String toString() {

        String s = "";

        for (int i = 0; i < controlled.size(); i++) {
            s += controlled.get(i).getX() + " " + controlled.get(i).getY() + "\n";
        }

        return s;
    }

}
