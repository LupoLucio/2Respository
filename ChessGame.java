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
        Piece brook = new Piece(0, 0, false, "rook", ps);
        Piece bkinght = new Piece(1, 0, false, "knight", ps);
        Piece bbishop = new Piece(2, 0, false, "bishop", ps);
        Piece bqueen = new Piece(3, 0, false, "queen", ps);
        Piece bking = new Piece(4, 0, false, "king", ps);
        Piece bbishop2 = new Piece(5, 0, false, "bishop", ps);
        Piece bkight2 = new Piece(6, 0, false, "knight", ps);
        Piece brook2 = new Piece(7, 0, false, "rook", ps);
        Piece bpawn1 = new Pawn(1, 1, false, "pawn", ps);
        Piece bpawn2 = new Piece(2, 1, false, "pawn", ps);
        Piece bpawn3 = new Piece(3, 1, false, "pawn", ps);
        Piece bpawn4 = new Piece(4, 1, false, "pawn", ps);
        Piece bpawn5 = new Piece(5, 1, false, "pawn", ps);
        Piece bpawn6 = new Piece(6, 1, false, "pawn", ps);
        Piece bpawn7 = new Piece(7, 1, false, "pawn", ps);
        Piece bpawn8 = new Piece(0, 1, false, "pawn", ps);

        Piece wrook = new Piece(0, 7, true, "rook", ps);
        Piece wkinght = new Piece(1, 7, true, "knight", ps);
        Piece wbishop = new Piece(2, 7, true, "bishop", ps);
        Piece wqueen = new Piece(3, 7, true, "queen", ps);
        Piece wking = new Piece(4, 7, true, "king", ps);
        Piece wbishop2 = new Piece(5, 7, true, "bishop", ps);
        Piece wkight2 = new Piece(6, 7, true, "knight", ps);
        Piece wrook2 = new Piece(7, 7, true, "rook", ps);
        Piece wpawn1 = new Piece(1, 6, true, "pawn", ps);
        Piece wpawn2 = new Piece(2, 6, true, "pawn", ps);
        Piece wpawn3 = new Piece(3, 6, true, "pawn", ps);
        Piece wpawn4 = new Piece(4, 6, true, "pawn", ps);
        Piece wpawn5 = new Piece(5, 6, true, "pawn", ps);
        Piece wpawn6 = new Piece(6, 6, true, "pawn", ps);
        Piece wpawn7 = new Piece(7, 6, true, "pawn", ps);
        Piece wpawn8 = new Piece(0, 6, true, "pawn", ps);

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

                if (selectedPiece == null) {
                    System.out.println("null");
                    selectedPiece = getPiece(e.getX(), e.getY());
                    if (selectedPiece != null && selectedPiece.isWhite == turn) {
                        selected = true;
                        selectedPiece = getPiece(e.getX(), e.getY());
                        System.out.println("acquisito");
                    } else {
                        selectedPiece = null;
                        System.out.println("null");
                    }

                } else {
                    int newX = e.getX();
                    int newY = e.getY();
                    if (selectedPiece.canMove(newX / 64, newY / 64)) {
                        selectedPiece.move(newX / 64, newY / 64);
                        turn = !turn;

                    }

                    selectedPiece = null;
                    selected = false;
                    System.out.println("mosso");

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