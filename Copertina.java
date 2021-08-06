
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Copertina implements ActionListener{

    public JFrame copertina;
    public JLabel scritta;
    public JButton play,settings;
    public boolean isCopertinaOn;



    public JFrame settingsFrame;
    public JLabel labcolor1,labcolor2;
    public JTextField color1;
    public JTextField color2;
    public JButton confirm,back;;
    


    public Copertina(){

        copertina = new JFrame("CHESS");
        copertina.setSize(500,500);
        copertina.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        copertina.setLayout(null);
        copertina.getContentPane().setBackground( Color.ORANGE );
        scritta = new JLabel("CHESS");
        scritta.setBounds(200, 25, 100, 100);
        scritta.setFont(new Font("Serif",Font.PLAIN,25));
        play = new JButton("play");
        play.addActionListener(this);
        play.setFont(new Font("Arial",Font.PLAIN,20));;
        play.setFocusable(false);
        play.setBounds(100, 175, 300, 100);
        settings = new JButton("settings");
        settings.addActionListener(this);
        settings.setFont(new Font("Arial",Font.PLAIN,20));;
        settings.setFocusable(false);
        settings.setBounds(100, 300, 300, 100);
        copertina.add(scritta);
        copertina.add(play);
        copertina.add(settings);
        copertina.setVisible(true);
        isCopertinaOn = true;



        settingsFrame = new JFrame("CHESS");
        settingsFrame.setSize(500,500);
        settingsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        settingsFrame.setLayout(null);
        settingsFrame.getContentPane().setBackground( Color.GREEN );
        labcolor1 = new JLabel("color player1");
        labcolor1.setBounds(25, 25, 150, 100);
        labcolor1.setFont(new Font("Serif",Font.PLAIN,20));
        color1 = new JTextField();
        color1.setBounds(200, 50, 100, 50);
		color1.setEditable(true);

        labcolor2 = new JLabel("color player2");
        labcolor2.setBounds(25, 150, 150, 100);
        labcolor2.setFont(new Font("Serif",Font.PLAIN,20));
        color2 = new JTextField();
        color2.setBounds(200, 175, 100, 50);
		color2.setEditable(true);

        confirm = new JButton("ok");
        confirm.addActionListener(this);
        confirm.setFont(new Font("Arial",Font.PLAIN,20));;
        confirm.setFocusable(false);
        confirm.setBounds(25, 350, 100, 100);

        back = new JButton("back");
        back.addActionListener(this);
        back.setFont(new Font("Arial",Font.PLAIN,20));;
        back.setFocusable(false);
        back.setBounds(250, 350, 100, 100);


        settingsFrame.add(labcolor1);
        settingsFrame.add(color1);
        settingsFrame.add(labcolor2);
        settingsFrame.add(color2);
        settingsFrame.add(confirm);
        settingsFrame.add(back);
        settingsFrame.setVisible(false);

    }

    public boolean isActive(){
        return isCopertinaOn;
    }








    @Override
    public void actionPerformed(ActionEvent e) {
        
       if(e.getSource() == play){
           isCopertinaOn = false;
           copertina.setVisible(false);
       }

       if(e.getSource() == settings){
        copertina.setVisible(false);
        settingsFrame.setVisible(true);
       }

       if(e.getSource() == confirm){
           String s = color1.getText();
           String s2 = color2.getText();

        switch(s){
            case "red":
            ChessGame.color1 = new Color(255,0,0);
            break;
            case "green":
            ChessGame.color1 = new Color(0,255,0);
            break;
            case "blue":
            ChessGame.color1 = new Color(0,0,255);
            break;
            default:
            ChessGame.color1 = Color.red;

        }

        switch(s2){
            case "red":
            ChessGame.color2 = new Color(255,0,0);
            break;
            case "green":
            ChessGame.color2 = new Color(0,255,0);
            break;
            case "blue":
            ChessGame.color2 = new Color(0,0,255);
            break;
            default:
            ChessGame.color2 = Color.blue;

        }
        
        isCopertinaOn = false;
        settingsFrame.setVisible(false);
        

       }

       if(e.getSource() == back){
           settingsFrame.setVisible(false);
           copertina.setVisible(true);
       }


        
    }




    
}
