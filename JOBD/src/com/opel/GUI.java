package com.opel;

import javax.swing.* ;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GUI {


    public GUI() {
        // TODO Auto-generated constructor stub
        //Window
    }

    static JFrame MainWindow;
    static JPanel contentPanel;
    static JLabel Title;
    static JTable contentTable;
    static JLabel FooterLeft;
    static JLabel FooterCenter;
    static JLabel FooterRight;

    public static void createGui (){
        // TODO Auto-generated constructor stub
        //Window
        MainWindow = new JFrame("OBD2 Scanner");
        MainWindow.setSize(816, 518);
        MainWindow.setVisible(true);

        //WindowManager
        contentPanel = new JPanel();
        contentPanel.setOpaque(true);
        contentPanel.setBackground(Color.lightGray);
        contentPanel.setLayout(null);

        //Title
        Title = new JLabel("OBD-2 Dashboard", JLabel.CENTER);
        Title.setSize(800, 80);
        Title.setLocation(0, 0);
        Title.setFont(new Font("Serif", Font.PLAIN, 28));
        Title.setBorder(BorderFactory.createLineBorder(Color.BLACK));


        //MainShit noch nicht implementiert
        //dummy Data
        Object[][] data = new Object[][]{ {"John", "Blue", "1"}, {"Oliver", "Green", "4"}, {"Hans", "Yellow", "7"}};
        String[] header = new String [] {"1","2","3"};
        contentTable = new JTable(new DefaultTableModel(data, header));
        contentTable.setBackground(Color.WHITE);
        contentTable.setSize(800, 390);
        contentTable.setLocation(0, 80);
        contentTable.setRowHeight(130);
        contentTable.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        contentTable.setTableHeader(null);



        // FooterLinks
        FooterLeft = new JLabel("Version 0.0.1", JLabel.LEFT);
        FooterLeft.setSize(300, 10);
        FooterLeft.setFont(new Font("Serif", Font.PLAIN, 8));
        FooterLeft.setLocation(0, 470);
        FooterLeft.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //FooterMitte
        FooterCenter = new JLabel("Studienarbeit an der DHBW Mannheim", JLabel.CENTER);
        FooterCenter.setSize(200, 10);
        FooterCenter.setFont(new Font("Serif", Font.PLAIN, 8));
        FooterCenter.setLocation(300, 470);
        FooterCenter.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //FooterRight
        FooterRight = new JLabel("� Maximilian Olbort, Jonathan Seibel, Marvin Meinhard", JLabel.RIGHT);
        FooterRight.setSize(300, 10);
        FooterRight.setFont(new Font("Serif", Font.PLAIN, 8));
        FooterRight.setLocation(500, 470);
        FooterRight.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //weise dem Frame das Panel zu
        contentPanel.setPreferredSize(new Dimension(800,480));
        contentPanel.add(Title);
        contentPanel.add(FooterLeft);
        contentPanel.add(FooterCenter);
        contentPanel.add(FooterRight);
        contentPanel.add(contentTable);
        contentPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        contentPanel.setLayout(null);
        //MainWindow.setContentPane(contentPanel);
        MainWindow.getContentPane().add(contentPanel);

        MainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /* public static void updateMQTTChecker(){
         boolean newState = false;

         if (newState==true(){
             contentTable.setValueAt("neuer wert in der Zelle",0,2);
         }

     }

 */

}