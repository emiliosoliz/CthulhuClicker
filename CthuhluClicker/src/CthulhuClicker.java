import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CthulhuClicker {

    JLabel counterLabel, parSecLabel;
    JButton button1, button2, button3, button4;
    int prayerCounter, timerSpeed, cultistNumber, cultistPrice, highPriestNumber, highPriestPrice;
    double perSecond;
    boolean timerOn, highPriestUnlocked;
    Font font1, font2;
    PrayerHandler pHandler = new PrayerHandler();
    Timer timer;
    JTextArea messageText;

    MouseHandler mHandler = new MouseHandler();


    public static void main(String[] args) {

        new CthulhuClicker();

    }

    public CthulhuClicker() {

        timerOn = false;
        perSecond = 0;

        prayerCounter = 0;

        cultistNumber = 0;

        cultistPrice = 10;

        highPriestUnlocked = false;

        highPriestNumber = 0;

        highPriestPrice = 20;

        createFont();

        createUI();


    }

    public void createFont() {

        font1 = new Font("Comic Sans MS", Font.PLAIN, 28);
        font2 = new Font("Comic Sans MS", Font.PLAIN, 15);

    }

    public void createUI() {

        JFrame window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);

        JPanel cthulhuPanel = new JPanel();
        cthulhuPanel.setBounds(100, 220, 200, 200);
        cthulhuPanel.setBackground(Color.black);
        window.add(cthulhuPanel);

        // Artwork by Me 
        ImageIcon cthulhu = new ImageIcon("C:\\Users\\Unbir\\Desktop\\Java Game\\CthuhluClicker\\res\\Cthulu.png");


        JButton cthulhuButton = new JButton();
        cthulhuButton.setBackground(Color.black);
        cthulhuButton.setFocusPainted(false);
        cthulhuButton.setBorder(null);
        cthulhuButton.setIcon(cthulhu);
        cthulhuButton.setContentAreaFilled(false);
        cthulhuButton.addActionListener(pHandler);
        cthulhuButton.setActionCommand("prayer");
        cthulhuPanel.add(cthulhuButton);

        JPanel counterPanel = new JPanel();
        counterPanel.setBounds(100, 100, 200, 100);
        counterPanel.setBackground(Color.black);
        counterPanel.setLayout(new GridLayout(2, 1));
        window.add(counterPanel);

        counterLabel = new JLabel(prayerCounter + " Prayers");
        counterLabel.setForeground(Color.white);
        counterLabel.setFont(font1);
        counterPanel.add(counterLabel);

        parSecLabel = new JLabel();
        parSecLabel.setForeground(Color.white);
        parSecLabel.setFont(font2);
        counterPanel.add(parSecLabel);

        JPanel itemPanel = new JPanel();
        itemPanel.setBounds(500, 170, 250, 250);
        itemPanel.setBackground(Color.black);
        itemPanel.setLayout(new GridLayout(4, 1));
        window.add(itemPanel);

        button1 = new JButton("Cultist");
        button1.setFont(font1);
        button1.setFocusPainted(false);
        button1.addActionListener(pHandler);
        button1.setActionCommand("Cultist");
        button1.addMouseListener(mHandler);
        itemPanel.add(button1);

        button2 = new JButton("???");
        button2.setFont(font1);
        button2.setFocusPainted(false);
        button2.addActionListener(pHandler);
        button2.setActionCommand("High Priest");
        button2.addMouseListener(mHandler);
        itemPanel.add(button2);

        button3 = new JButton("???");
        button3.setFont(font1);
        button3.setFocusPainted(false);
        button3.addActionListener(pHandler);
        button3.setActionCommand("");
        button3.addMouseListener(mHandler);
        itemPanel.add(button3);

        button4 = new JButton("???");
        button4.setFont(font1);
        button4.setFocusPainted(false);
        button4.addActionListener(pHandler);
        button4.setActionCommand("");
        button4.addMouseListener(mHandler);
        itemPanel.add(button4);

        JPanel messagePanel = new JPanel();
        messagePanel.setBounds(500, 70, 250, 250);
        messagePanel.setBackground(Color.black);
        window.add(messagePanel);
        messageText = new JTextArea();
        messageText.setBounds(500, 70, 250, 150);
        messageText.setForeground(Color.white);
        messageText.setBackground(Color.black);
        messageText.setFont(font2);
        messageText.setLineWrap(true);
        messageText.setWrapStyleWord(true);
        messageText.setEditable(false);
        messagePanel.add(messageText);


        window.setVisible(true);
    }

    public void setTimer() {
        timer = new Timer(timerSpeed, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                prayerCounter++;
                counterLabel.setText(prayerCounter + " Prayers");

                if (highPriestUnlocked == false){
                    if (prayerCounter >= 100){
                        highPriestUnlocked = true;
                        button2.setText("High Priest " + "(" + highPriestNumber +")");
                    }
                }
            }
        });

    }

    public void timerUpdate() {
        if (!timerOn) {
            timerOn = true;
        } else if (timerOn == true) {
            timer.stop();
        }
        double speed = 1 / perSecond * 1000;
        timerSpeed = (int) Math.round(speed);
        String s = String.format("%.1f", perSecond);
        parSecLabel.setText("pre second: " + s);

        setTimer();
        timer.start();

    }


    public class PrayerHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            String action = e.getActionCommand();
            switch (action) {
                case "prayer":
                    prayerCounter++;
                    counterLabel.setText(prayerCounter + " Prayers");
                    break;
                case "Cultist":
                    if (prayerCounter >= cultistPrice) {
                        prayerCounter = prayerCounter - cultistPrice;
                        cultistPrice = cultistPrice + 5;
                        counterLabel.setText(prayerCounter + " Prayers");

                        cultistNumber++;
                        button1.setText("Cultist " + "(" + cultistNumber + ")");
                        messageText.setText("Cultist\n[Price: " + cultistPrice + "]\nAutoClicks once every 10 seconds.");
                        perSecond = perSecond + 0.1;
                        timerUpdate();
                    } else {
                        messageText.setText("You Need to Pray More!!");
                    }
                    break;
                case "High Priest":
                    if (prayerCounter >= highPriestPrice) {
                        prayerCounter = prayerCounter - highPriestPrice;
                        highPriestPrice = highPriestPrice + 50;
                        counterLabel.setText(prayerCounter + " Prayers");

                        highPriestNumber++;
                        button2.setText("High Priest " + "(" + highPriestNumber + ")");
                        messageText.setText("High Priest\n[Price: " + highPriestPrice + "]\nEach High Priest prays every 1 second.");
                        perSecond = perSecond + 1.0;
                        timerUpdate();
                    } else {
                        messageText.setText("You Need to Pray More!!");
                    }
                    break;



            }
        }
    }

    public class MouseHandler implements MouseListener {


        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            JButton button = (JButton) e.getSource();

            if (button == button1) {
                messageText.setText("Cultist\n[Price: " + cultistPrice + "]\nAutoClicks once every 10 seconds.");
            } else if (button == button2) {
                if (highPriestUnlocked == false) {
                    messageText.setText("This Item is Currently Locked");
                }
                else if(highPriestUnlocked == true){
                    messageText.setText("High Priest\n[Price: " + highPriestPrice + "]\nEach High Priest prays every 1 second.");
                }
            } else if (button == button3) {
                messageText.setText("This Item is Currently Locked");
            } else if (button == button4) {
                messageText.setText("This Item is Currently Locked");
            }

        }

        @Override
        public void mouseExited(MouseEvent e) {

            JButton button = (JButton) e.getSource();

            if (button == button1) {
                messageText.setText(null);
            } else if (button == button2) {
                messageText.setText(null);
            } else if (button == button3) {
                messageText.setText(null);
            } else if (button == button4) {
                messageText.setText(null);
            }
        }
    }

}
