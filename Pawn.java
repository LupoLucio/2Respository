import java.util.LinkedList;

public class Pawn extends Piece {

    // array di oggetti Square che rappresenta le caselle controllate dal pezzo Pawn
    public Square[] controlled;
    // boolean che ci dice se il pedone è mai stato mosso prima
    boolean hasMoved = false;
    // boolean che dice se il pedone ha quennato
    boolean hasQueened = false;

    public Pawn(int xp, int yp, boolean isWhite, String name, LinkedList<Piece> ps) {
        super(xp, yp, isWhite, name, ps);
        controlled = new Square[2];
        generateControlledSquares();
        // TODO Auto-generated constructor stub
    }

    // metodo booleano dice se il pedone puo' muoversi o meno in una determinata
    // posizone
    public boolean canMove(int xp, int yp) {

        /*
         * se è bianco e la cord x è la stessa attuale e la cord y è o "avanti di uno" o
         * "avanti di 2 e la sua pos iniziale è sulla riga 6(cioè non ha mai mosso prima)"
         * allora restituiso true
         */
        if (isWhite && xp == this.xp && (yp == this.yp - 1 || (yp == this.yp - 2 && this.yp == 6))
                && ChessGame.getPiece(xp * 64, yp * 64) == null) {
            return true;

        } /*
           * se è nero e la cord x è la stessa attuale e la cord y è o "avanti di uno" o
           * "avanti di 2 e la sua pos iniziale è sulla riga 2(cioè non ha mai mosso prima)"
           * allora restituiso true
           */
        else if (!isWhite && xp == this.xp && (yp == this.yp + 1 || (yp == this.yp + 2 && this.yp == 1))
                && ChessGame.getPiece(xp * 64, yp * 64) == null) {
            return true;
        } /*
           * controllo se la posizione data è o meno in una posizione dove il pedone puo'
           * mangiare
           */
        else if ((isWhite && (xp == this.xp - 1 || xp == this.xp + 1) && yp == this.yp - 1)
                || (!isWhite && (xp == this.xp - 1 || xp == this.xp + 1) && yp == this.yp + 1)) {
            // se lo è (condizione esterna) allora controllo se il mio pedone puo' mangiare
            if (this.canEat()) {
                // se puo' mangiare ritorno true
                return true;
            }
        }

        // System.out.println("false");
        // in tutti gli altri casi ritorno false
        return false;

    }

    // metodo boolean che ritorna se il pedone puo' mangiare o no nella sua
    // determinata posizione
    private boolean canEat() {

        // se è bianco
        if (this.isWhite) {
            /*
             * quello che sta da vanti a lui alla sua destra o alla sua sisitra è dell'altro
             * colore ritorno true
             */
            if ((ChessGame.getPiece((xp - 1) * 64, (yp - 1) * 64) != null
                    && ChessGame.getPiece((xp - 1) * 64, (yp - 1) * 64).isWhite != this.isWhite)
                    || (ChessGame.getPiece((xp + 1) * 64, (yp - 1) * 64) != null
                            && ChessGame.getPiece(xp + 1, yp - 1).isWhite != this.isWhite)) {
                return true;
            }
        } // se è nero
        else {
            /*
             * quello che sta da vanti a lui alla sua destra o alla sua sisitra è dell'altro
             * colore ritorno true
             */
            if ((ChessGame.getPiece((xp - 1) * 64, (yp + 1) * 64) != null
                    && ChessGame.getPiece((xp - 1) * 64, (yp + 1) * 64).isWhite != this.isWhite)
                    || (ChessGame.getPiece((xp + 1) * 64, (yp + 1) * 64) != null
                            && ChessGame.getPiece((xp + 1) * 64, (yp + 1) * 64).isWhite != this.isWhite)) {
                return true;
            }
        }

        // in tutti gli altri casi ritorno false visto che i pedoni mangiano a estra o a
        // sinistra
        return false;

    }

    // meotodo che vuove il pedone
    public void move(int xp, int yp) {

        // chiamo il metodo move della classe madre Piece
        if (canMove(xp, yp)) {
            super.move(xp, yp);
            super.upDateSquares();
        }
        // aggiorno le case controllate dopo che l'ho mosso

    }

    // metodo che genera le case controllate dal pedone, al massimo sono 2
    public void generateControlledSquares() {

        // se è bianco
        if (this.isWhite) {
            // se il pedone non è sulla riga di promozione
            if (yp > 0) {
                // se il pedone non è nè sulla prima nè sulla ultima colonna
                if (xp > 0 && xp < 7) {
                    // assegno al pedone le 2 case che controlla avanti a lui a destra e a sinistra
                    controlled[0] = new Square(xp - 1, yp - 1);
                    controlled[1] = new Square(xp + 1, yp - 1);

                } // se si trova sulla prima colonna gli assegno come controllata solo quella
                  // avanti alla sua destra
                else if (xp == 0) {
                    controlled[0] = new Square(xp + 1, yp - 1);
                } // se si trova sull'ultima colonna gli assegno come controllata solo quella
                  // avanti a sinistra
                else if (xp == 7) {
                    controlled[0] = new Square(xp - 1, yp - 1);
                }
            }
        } // se è nero
        else {
            // se il pedone non è sulla riga di promozione
            if (yp < 7) {
                // se il pedone non è nè sulla prima nè sulla ultima colonna
                if (xp > 0 && xp < 7) {
                    // assegno al pedone le 2 case che controlla avanti a lui a destra e a sinistra
                    controlled[0] = new Square(xp - 1, yp + 1);
                    controlled[1] = new Square(xp + 1, yp + 1);
                } // se si trova sulla prima colonna gli assegno come controllata solo quella
                  // avanti alla sua destra
                else if (xp == 0) {
                    controlled[0] = new Square(xp + 1, yp + 1);
                } // se si trova sull'ultima colonna gli assegno come controllata solo quella
                  // avanti alla sua sinistra
                else if (xp == 7) {
                    controlled[0] = new Square(xp - 1, yp + 1);
                }
            }
        }
    }

    // metodo che ritorna un array di Square cioè un array che rappresenta tutte le
    // varie celle controllate dal pedone
    public Square[] getControlledSquares() {

        Square[] array = new Square[this.getControlledSquaresSize()];
        System.arraycopy(this.controlled, 0, array, 0, this.getControlledSquaresSize());

        return array;

    }

    public void removeAll() {

        for (int i = 0; i < getControlledSquaresSize(); i++) {

            controlled[i] = null;
        }

    }

    // metodo che ritorna quante caselle controlla il pedone
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
