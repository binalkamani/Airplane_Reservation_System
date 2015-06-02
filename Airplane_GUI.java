/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package airplane;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author binalkamani
 */
public class Airplane_GUI extends JFrame{

    private String read_t1, read_t2, read_t3, read_t4, read_t5;
    public boolean get_flag = false;
    public int flag = 0;
    JButton b1, b2, b3, b4, b5, b6;
    JLabel l1, l2, l3, l4, l5, l6;
    JRadioButton r1, r2, r3, r4;
    ButtonGroup bg;
    JCheckBox c1, c2, c3, c4;
    JTextField t1 = new JTextField("DFW", 4);
    JTextField t2 = new JTextField("DEN", 4);
    JTextField t3 = new JTextField("162", 4);
    JTextField t4 = new JTextField("2015-05-31", 6);
    JTextField t5 = new JTextField("Jimmy Cole", 8);
    //textField.addActionListener(this);
    //create frame
    JFrame f = new JFrame("Airline Reservation System"); 
    JPanel p = new JPanel(new GridBagLayout());
    
    public Airplane_GUI(){
        setTitle("Airline Reservation System");         
        setSize(500,200);
        setDefaultCloseOperation(f.EXIT_ON_CLOSE);        
        p.setBackground(Color.LIGHT_GRAY);
        GridBagConstraints c = new GridBagConstraints();  
        l1=new JLabel("Departure Code");
        l2=new JLabel("Arrival Code");
        b1=new JButton("Flights  ");     
        l3=new JLabel("flight number");
        l4=new JLabel("Date");
        l5=new JLabel("Passenger_name");
        b2=new JButton("Seats    ");
        b3=new JButton("Fare      ");
        b4=new JButton("Passengers");
        b5=new JButton("Flt_Inst ");
        r1=new JRadioButton("Direct");
        r2=new JRadioButton("1 stop");
        r3=new JRadioButton("2 Stop");
        bg = new ButtonGroup();
        
        c.insets = new Insets(2, 2, 2, 2);
        
        bg.add(r1);
        bg.add(r2);
        bg.add(r3);
       
        c.gridx = 0;
        c.gridy = 0;
        p.add(l1, c);
        c.gridx = 1;
        c.gridy = 0;            
        p.add(t1, c);
        c.gridx = 2;
        c.gridy = 0;            
        p.add(l2, c);
        c.gridx = 3;
        c.gridy = 0; 
        p.add(t2, c);
        c.gridx = 0;
        c.gridy = 1;
        p.add(r1, c);        
        c.gridx = 1;
        c.gridy = 1;
        p.add(r2, c);
        c.gridx = 2;
        c.gridy = 1;
        p.add(r3, c);
        c.gridx = 0;
        c.gridy = 2; 
        p.add(l3, c);
        c.gridx = 1;
        c.gridy = 2;         
        p.add(t3,c);
        c.gridx = 2;
        c.gridy = 2;
        p.add(l4,c);
        c.gridx = 3;
        c.gridy = 2;         
        p.add(t4,c); 
        c.gridx = 0;
        c.gridy = 3;
        p.add(l5,c);
        c.gridx = 1;
        c.gridy = 3;         
        p.add(t5,c);
        c.gridx = 4;
        c.gridy = 0;
        p.add(b1,c);
        c.gridx = 4;
        c.gridy = 1;
        p.add(b2,c);
        c.gridx=4;
        c.gridy=2;
        p.add(b3,c);
        c.gridx=3;
        c.gridy=3;
        p.add(b4,c);
        c.gridx=4;
        c.gridy=3;
        p.add(b5,c);
        
        add(p);
        setVisible(true);
        
        t1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                    setReadt1();
            }
        });    
        
            
        t2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                    setReadt2();
            }
        });
        
        b1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                disp_flight_info(flag);
            }
        });
        
        r1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                flag = 11;
            }
        });
        
        r2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                flag = 12;
            }
        });
        
        r3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                flag = 13;
            }
        });        
        
        t3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                    setReadt3();
            }
        });        
        
        t4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                    setReadt4();
            }
        });        
        
        t5.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                    setReadt5();
            }
        }); 
        
         b2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                flag = 2;
                //JOptionPane.showMessageDialog(null, "Action Listener for JButton");
                //JFrame_flight_no fn = new JFrame_flight_no();
                disp_avail_seats(flag);
            }
        });
         
            b3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                flag = 3;
                //JOptionPane.showMessageDialog(null, "Action Listener for JButton");
                //JFrame_flight_no fn = new JFrame_flight_no();
                disp_fare(flag);
            }
        });
            
         b4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                flag = 4;
                //JOptionPane.showMessageDialog(null, "Action Listener for JButton");
                //JFrame_flight_no fn = new JFrame_flight_no();
                disp_pass_list(flag);
            }
        }); 
            
            b5.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                flag = 5;
                //JOptionPane.showMessageDialog(null, "Action Listener for JButton");
                //JFrame_flight_no fn = new JFrame_flight_no();
                disp_pass_info(flag);
            }
        });            
        
    }
    
    public void setReadt1(){
        read_t1 = t1.getText();
       // JOptionPane.showMessageDialog(null, "Text entered is..." + read_t1);
       }
    
    public void setReadt2(){
        
        read_t2 = t2.getText();
       // JOptionPane.showMessageDialog(null, "Text entered is..." + read_t2);
      }    

    public void setReadt3(){
        read_t3 = t3.getText();
       // JOptionPane.showMessageDialog(null, "Text entered is..." + read_t1);
       }
    
    public void setReadt4(){
        
        read_t4 = t4.getText();
       // JOptionPane.showMessageDialog(null, "Text entered is..." + read_t2);
      }      

    public void setReadt5(){
        
        read_t5 = t5.getText();
       // JOptionPane.showMessageDialog(null, "Text entered is..." + read_t2);
      }     
    
    public void disp_flight_info(int flag){
        
        Airplane a1 = new Airplane();
         try {
            a1.get_javasql_connection();
            a1.execute_query(read_t1, read_t2, flag);
            a1.close_javasql_connection();
        } catch (SQLException ex) {
            Logger.getLogger(Airplane_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void disp_avail_seats(int flag){
        
        Airplane a2 = new Airplane();
         try {
            a2.get_javasql_connection();
            a2.execute_query(read_t3, read_t4, flag);
            a2.close_javasql_connection();
        } catch (SQLException ex) {
            Logger.getLogger(Airplane_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
        public void disp_fare(int flag){
        
        Airplane a3 = new Airplane();
        read_t4=null;
         try {
            a3.get_javasql_connection();
            a3.execute_query(read_t3, read_t4, flag);
            a3.close_javasql_connection();
        } catch (SQLException ex) {
            Logger.getLogger(Airplane_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
      public void disp_pass_info(int flag){
        
        Airplane a4 = new Airplane();
        read_t4=null;
         try {
            a4.get_javasql_connection();
            a4.execute_query(read_t5, null, flag);
            a4.close_javasql_connection();
        } catch (SQLException ex) {
            Logger.getLogger(Airplane_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
      
      public void disp_pass_list(int flag){
          Airplane a5 = new Airplane();
          
         try {
            a5.get_javasql_connection();
            a5.execute_query(read_t3, null, flag);
            a5.close_javasql_connection();
        } catch (SQLException ex) {
            Logger.getLogger(Airplane_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
      }   
      
    public String getReadt1(){
        return read_t1;
    }
   
    
}
    
