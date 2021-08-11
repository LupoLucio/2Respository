import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

public class ChessGame extends Copertina {
    public static LinkedList<Piece> ps = new LinkedList<>();
    public static Piece selectedPiece = null;
    public static int height = 512, width = 512;
    public static Color color1 = new Color(255, 0, 0);
    public static Color color2 = new Color(0, 255, 0);
    public static boolean turn = true;
    public static boolean selected = false;

    public static void main(String[] args) throws IOException {

        Copertina coperta = new Copertina();
        while (coperta.isActive()) {

            System.out.println();
        }

        BufferedImage all = ImageIO.read(new File("C:\\Users\\39339\\Downloads\\chess.png"));
        Image imgs[] = new Image[12];
        int ind = 0;
        for (int y = 0; y < 400; y += 200) {
            for (int x = 0; x < 1200; x += 200) {
                imgs[ind] = all.getSubimage(x, y, 200, 200).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
                ind++;
            }
        }

        Piece bpawn1 = new Pawn(1, 1, false, "pawn", ps);
        Piece bpawn2 = new Pawn(2, 1, false, "pawn", ps);
        Piece bpawn3 = new Pawn(3, 1, false, "pawn", ps);
        Piece bpawn4 = new Pawn(4, 1, false, "pawn", ps);
        Piece bpawn5 = new Pawn(5, 1, false, "pawn", ps);
        Piece bpawn6 = new Pawn(6, 1, false, "pawn", ps);
        Piece bpawn7 = new Pawn(7, 1, false, "pawn", ps);
        Piece bpawn8 = new Pawn(0, 1, false, "pawn", ps);
        Piece bkinght = new Knight(1, 0, false, "knight", ps);
        Piece bbishop = new Bishop(2, 0, false, "bishop", ps);
        Piece bbishop2 = new Bishop(5, 0, false, "bishop", ps);
        Piece bkight2 = new Knight(6, 0, false, "knight", ps);
        Piece bking = new Piece(4, 0, false, "king", ps);
        Piece bqueen = new Queen(3, 0, false, "queen", ps);
        Piece brook = new Rook(0, 0, false, "rook", ps);
        Piece brook2 = new Rook(7, 0, false, "rook", ps);

        Piece wpawn1 = new Pawn(1, 6, true, "pawn", ps);
        Piece wpawn2 = new Pawn(2, 6, true, "pawn", ps);
        Piece wpawn3 = new Pawn(3, 6, true, "pawn", ps);
        Piece wpawn4 = new Pawn(4, 6, true, "pawn", ps);
        Piece wpawn5 = new Pawn(5, 6, true, "pawn", ps);
        Piece wpawn6 = new Pawn(6, 6, true, "pawn", ps);
        Piece wpawn7 = new Pawn(7, 6, true, "pawn", ps);
        Piece wpawn8 = new Pawn(0, 6, true, "pawn", ps);
        Piece wkinght = new Knight(1, 7, true, "knight", ps);
        Piece wbishop = new Bishop(2, 7, true, "bishop", ps);
        Piece wbishop2 = new Bishop(5, 7, true, "bishop", ps);
        Piece wkight2 = new Knight(6, 7, true, "knight", ps);
        Piece wking = new Piece(4, 7, true, "king", ps);
        Piece wqueen = new Queen(3, 7, true, "queen", ps);
        Piece wrook = new Rook(0, 7, true, "rook", ps);
        Piece wrook2 = new Rook(7, 7, true, "rook", ps);

        JFrame frame = new JFrame();
        frame.setBounds(100, 100, 512, 512);
        frame.setUndecorated(true);
        JPanel pn = new JPanel() {
            @Override
            public void paint(Graphics g) {

                boolean white = true;
                for (int y = 0; y < 8; y++) {
                    for (int x = 0; x < 8; x++) {

                        if (white) {

                            g.setColor(color1);
                        } else {
                            g.setColor(color2);

                        }

                        g.fillRect(x * 64, y * 64, 64, 64);
                        white = !white;
                    }
                    white = !white;
                }
                for (Piece p : ps) {
                    int ind = 0;
                    if (p.name.equalsIgnoreCase("king")) {
                        ind = 0;
                    }
                    if (p.name.equalsIgnoreCase("queen")) {
                        ind = 1;
                    }
                    if (p.name.equalsIgnoreCase("bishop")) {
                        ind = 2;
                    }
                    if (p.name.equalsIgnoreCase("knight")) {
                        ind = 3;
                    }
                    if (p.name.equalsIgnoreCase("rook")) {
                        ind = 4;
                    }
                    if (p.name.equalsIgnoreCase("pawn")) {
                        ind = 5;
                    }
                    if (!p.isWhite) {
                        ind += 6;
                    }
                    g.drawImage(imgs[ind], p.x, p.y, this);
                }
            }

            @Override
            public void paintComponents(Graphics g) {
                boolean white = true;
                for (int y = 0; y < 8; y++) {
                    for (int x = 0; x < 8; x++) {
                        if (selectedPiece != null && ((Bishop) selectedPiece).isControlled(new Square(x, y))) {
                            g.setColor(Color.GRAY);
                        } else if (white) {

                            g.setColor(color1);
                        } else {
                            g.setColor(color2);

                        }

                        g.fillRect(x * 64, y * 64, 64, 64);
                        white = !white;
                    }
                    white = !white;
                }
            }

        };

        frame.add(pn);
        frame.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                /*
                 * if (selectedPiece != null && selectedPiece.isWhite == turn) {
                 * 
                 * selectedPiece.x = e.getX() - 32; selectedPiece.y = e.getY() - 32;
                 * frame.repaint(); }
                 */
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }

        });
        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                // se non c'è un pezzo seslezionato
                if (selectedPiece == null) {
                    System.out.println("nessun pezzo selezionato");
                    // ci metto in pezzo dove ho clickato
                    selectedPiece = getPiece(e.getX(), e.getY());
                    // se riesco a selezionare il pezzo e è del turno giusto
                    if (selectedPiece != null && selectedPiece.isWhite == turn) {
                        selected = true;

                        // se è un pedone o alfiere per ora
                        if (selectedPiece instanceof Pawn) {
                            System.out.println("selezionato pedone");
                            System.out.println(selectedPiece.toString());
                        }
                        if (selectedPiece instanceof Bishop) {
                            System.out.println("selezionato alfiere");
                            System.out.println(selectedPiece.toString());
                        }
                        if (selectedPiece instanceof Rook) {
                            System.out.println("selezionato torre");
                            System.out.println(selectedPiece.toString());
                        }
                        if (selectedPiece instanceof Knight) {
                            System.out.println("selezionato cavallo");
                            System.out.println(selectedPiece.toString());
                        }
                        if (selectedPiece instanceof Queen) {
                            System.out.println("selezionato regina");
                            System.out.println(selectedPiece.toString());
                        }

                    } // se il pezzo dove ho clickato è null o è del colore sbagliato
                      // rimetto il selected piece a null(mossa invalida)
                    else {
                        selectedPiece = null;
                        System.out.println("nessun pezzo selezionato");
                    }

                } // se invece ho gia un pezzo selezionato
                else {
                    // mi prendo le nuove coordinate del click
                    int newX = e.getX();
                    int newY = e.getY();

                    if (selectedPiece instanceof Pawn) {

                        if (((Pawn) selectedPiece).canMove(newX / 64, newY / 64)) {

                            selectedPiece.move(newX / 64, newY / 64);

                            turn = !turn;
                        }

                    }
                    if (selectedPiece instanceof Bishop) {

                        if (((Bishop) selectedPiece).canMove(newX / 64, newY / 64)) {

                            selectedPiece.move(newX / 64, newY / 64);

                            turn = !turn;
                        }
                    }
                    if (selectedPiece instanceof Rook) {

                        if (((Rook) selectedPiece).canMove(newX / 64, newY / 64)) {
                            selectedPiece.move(newX / 64, newY / 64);
                            turn = !turn;
                        }

                    }
                    if (selectedPiece instanceof Knight) {

                        if (((Knight) selectedPiece).canMove(newX / 64, newY / 64)) {
                            selectedPiece.move(newX / 64, newY / 64);
                            turn = !turn;
                        }

                    }
                    if (selectedPiece instanceof Queen) {

                        if (((Queen) selectedPiece).canMove(newX / 64, newY / 64)) {
                            selectedPiece.move(newX / 64, newY / 64);
                            turn = !turn;
                        }

                    }
                    selectedPiece = null;
                    selected = false;
                    System.out.println(turn);
                    frame.repaint();

                }

            }

            public void mousePressed(MouseEvent e) {
                // System.out.println((getPiece(e.getX(),e.getY()).isWhite?"white ":"black
                // ")+getPiece(e.getX(),e.getY()).name);

                /*
                 * selectedPiece = getPiece(e.getX(), e.getY()); if (selectedPiece instanceof
                 * Pawn) { System.out.println(selectedPiece.toString()); }
                 */

            }

            public void mouseReleased(MouseEvent e) {

                /*
                 * if (selectedPiece.isWhite == turn) { int newX = e.getX(); int newY =
                 * e.getY(); if (selectedPiece.canMove(newX / 64, newY / 64)) {
                 * selectedPiece.move(newX / 64, newY / 64); turn = !turn;
                 * System.out.println("dio"); } frame.repaint(); }
                 */

            }

            public void mouseEntered(MouseEvent e) {

            }

            public void mouseExited(MouseEvent e) {

            }

        });

        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);

    }

    public static Piece getPiece(int x, int y) {
        int xp = x / 64;
        int yp = y / 64;
        for (Piece p : ps) {
            if (p.xp == xp && p.yp == yp) {
                return p;
            }
        }
        return null;
    }

    public void paintUpdate(Graphics g, Square[] quadrati) {

        for (int i = 0; i < quadrati.length; i++) {
            g.setColor(Color.pink);
            g.fillRect(quadrati[i].getX() * 64, quadrati[i].getY() * 64, 64, 64);
        }

    }

    public void paintToNormal(Graphics g) {

        boolean white = true;
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if (white) {

                    g.setColor(color1);
                } else {
                    g.setColor(color2);

                }
                g.fillRect(x * 64, y * 64, 64, 64);
                white = !white;
            }
            white = !white;
        }

    }

}