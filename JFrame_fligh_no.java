/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package airplane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;

/**
 *
 * @author binalkamani
 */
public class JFrame_flight_no extends JFrame{
    
        JFrame f1 = new JFrame("Flight Information");
        JPanel p1 = new JPanel(new GridBagLayout());
        JTextArea tx1 = new JTextArea(20, 40);
        JTextArea tx2 = new JTextArea();
        JTextArea tx3 = new JTextArea();
        JScrollPane scroll = new JScrollPane(tx1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        public JFrame_flight_no(){
        setTitle("Flight Information");
        setSize(800,500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);        
        p1.setBackground(Color.WHITE);
        
        Font font = new Font("Verdana", Font.PLAIN, 14);
        tx1.setFont(font);
        tx1.setForeground(Color.BLUE);
        tx1.setLineWrap(true);
        tx1.setWrapStyleWord(true);
        
        tx2.setFont(font);
        tx2.setForeground(Color.BLUE);
        tx3.setFont(font);
        tx3.setForeground(Color.BLUE);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(2, 2, 2, 2);
        //tx1.append(" Here is the flight information requested by you!!! \n"); 
        //tx2.append(" Seat Information: \n");
        
        c.gridx = 0;
        c.gridy = 0;
        p1.add(scroll, c);
        add(p1, BorderLayout.NORTH);
        //setVisible(true);       
        }
}
