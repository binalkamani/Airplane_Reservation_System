    /*
     * To change this template, choose Tools | Templates
     * and open the template in the editor.
     */
    package airplane;

    import java.sql.*;
    import java.util.Scanner;
import java.util.StringTokenizer;
    //import java.awt.*;
    //import javax.swing.*;

    /**
     *
     * @author binalkamani
     */
    public class Airplane{

        /**
         * @param args the command line arguments
         */
    public static Connection conn = null;
    public static Scanner user_ip = new Scanner(System.in);
    //public static String input = null;
    public static boolean jframe_sel_flag = false;
    public int con_flag = 0;
    /**
         * @param args
         */
        public static void main(String[] args) throws SQLException {

                Airplane_GUI main_gui = new Airplane_GUI();                   
        }

          /* This is method to execute related
           * mysql query
           */
        public void execute_query(String input1, String input2, int flag) throws SQLException {

            // Initialize variables for fields by data type

           // Airplane_GUI gui = new Airplane_GUI();
            String flight_number = null;
            String flight_num1, flight_num2 = null;
            String Weekdays, fare_code, source, dest, stop1, stop2 = null;
            String Scheduled_departure_time, Scheduled_arrival_time = null;
            String date, dept_time, arrv_time, seat_num, name = null;
            String delims;
            String[] tokens;
            int amount=0;
            int WD_count=0;
            int avail_seat_check, avail_seat_calc=0;
            int linect = 0;
            JFrame_flight_no fn1 = new JFrame_flight_no();
             // Create a SQL statement object and execute the query.
             Statement stmt = conn.createStatement();
             Statement stmt_1 = conn.createStatement();
             fn1.setVisible(false);
              // Set the current database, if not already set in the getConnection
              // Execute a SQL statement
              // String chooseDatabase = "use company;"
      
              stmt.execute("use airline;");
               

            switch(flag)
            {
                case 11:
                {

                if (!input1.isEmpty() && !input2.isEmpty())
                    {
                        ResultSet rs = stmt.executeQuery("SELECT flight_number, Weekdays FROM flight WHERE Departure_airport_code='" + input1 + "'and Arrival_airport_code='" + input2 + "';");

                    // Iterate through the result set using ResultSet class's next() method
                    while (rs.next()) {
                        // Keep track of the line/tuple count
                        linect++;
                        // Populate field variables

                        flight_number = rs.getString("flight_number");
                        Weekdays = rs.getString("Weekdays");
                        
                        delims = "_";
                        tokens = Weekdays.split(delims);
                        int tokenCount = tokens.length;
                        for (int j=0; j<tokenCount; j++){
                            WD_count = WD_count + int_val(tokens[j]);                          
                         }
                            

                        // Do something with the data
                        fn1.tx1.append(linect + ".\t");
                        fn1.tx1.append(flight_number + "\t");
                        fn1.tx1.append(Weekdays + "\t");
                        fn1.tx1.append("\n");
                        
                    } // End while(rs.next())
                    fn1.setVisible(true);
                    // Always close the recordset and connection.
                    rs.close();
                    }
                return;
                }

                case 12:
                {

                if (!input1.isEmpty() && !input2.isEmpty())
                    {
                        ResultSet rs = stmt.executeQuery("Select F1.flight_number, F2.flight_number, F1.Weekdays, F1.Departure_airport_code, F2.Departure_airport_code, F1.Arrival_airport_code, F2.Arrival_airport_code, F1.Scheduled_arrival_time, F2.Scheduled_departure_time  "
                                                        + "from flight F1, flight F2 "
                                                        + "where F1.Departure_airport_code = '" + input1 + "' and F2.Arrival_airport_code = '" + input2 + "' "
                                                        + "and F1. Arrival_airport_code = F2.Departure_airport_code and F1.Weekdays=F2.Weekdays "
                                                        + "and TIMEDIFF(F2.Scheduled_departure_time, F1.Scheduled_arrival_time) > '07:00:00' "
                                                        + "and F2.Scheduled_departure_time > F1.Scheduled_arrival_time");

                    // Iterate through the result set using ResultSet class's next() method
                    while (rs.next()) {
                            // Keep track of the line/tuple count
                            linect++;
                            // Populate field variables
                            flight_number = rs.getString("F1.flight_number");
                            flight_num1 = rs.getString("F2.flight_number");
                            //stop1 = rs.getString("F1.Arrival_airport_code");
                            Weekdays = rs.getString("F1.Weekdays");
                            Scheduled_arrival_time = rs.getString("F1.Scheduled_arrival_time");
                            Scheduled_departure_time = rs.getString("F2.Scheduled_departure_time");
                            source = rs.getString("F1.Departure_airport_code");
                            stop1 = rs.getString("F1.Arrival_airport_code");
                            stop2 = rs.getString("F2.Departure_airport_code");
                            dest = rs.getString("F2.Arrival_airport_code");

                             fn1.tx1.append(flight_number + "\t");
                             fn1.tx1.append(source + "\t");
                             fn1.tx1.append(stop1 + "\t");
                             fn1.tx1.append(flight_num1 + "\t");
                             fn1.tx1.append(stop2 + "\t");
                             fn1.tx1.append(dest + "\t");
                             fn1.tx1.append(Weekdays + "\t");
                             fn1.tx1.append(Scheduled_arrival_time + "\t");
                             fn1.tx1.append(Scheduled_departure_time + "\t");
                             fn1.tx1.append("\n");
                        
                    } // End while(rs.next())
                    fn1.setVisible(true);
                    // Always close the recordset and connection.
                    rs.close();
                    }
                return;
                }
    
                case 13:
                {

                if (!input1.isEmpty() && !input2.isEmpty())
                    {
                        ResultSet rs = stmt.executeQuery("Select F1.flight_number, F2.flight_number, F3.flight_number, F1.Weekdays, "
                                + "F1.Departure_airport_code, F1.Arrival_airport_code, F2.Arrival_airport_code, F3.Departure_airport_code, F3.Arrival_airport_code " 
                                + "from flight F1, flight F2, flight F3 " 
                                + "where F1.Departure_airport_code = '" +  input1 + "' and F3.Arrival_airport_code = '" + input2 + "' and "
                                + "F1. Arrival_airport_code = F2.Departure_airport_code and F1.Departure_airport_code!=F2.Arrival_airport_code and F2.Arrival_airport_code=F3.Departure_airport_code and "
                                + "F2.Departure_Airport_code!=F3.Arrival_airport_code and F1.Weekdays=F2.Weekdays=F3.Weekdays and "
                                + "F2.Scheduled_departure_time>F1.Scheduled_arrival_time and F3.Scheduled_departure_time>F2.Scheduled_arrival_time and "
                                + "TIMEDIFF(F2.Scheduled_departure_time, F1.Scheduled_arrival_time) > '02:00:00' and TIMEDIFF(F3.Scheduled_departure_time, F2.Scheduled_arrival_time) < '02:00:00';");

                    // Iterate through the result set using ResultSet class's next() method
                    while (rs.next()) {
                        // Keep track of the line/tuple count
                        linect++;
                        // Populate field variables

                        flight_number = rs.getString("F1.flight_number");
                        flight_num1 = rs.getString("F2.flight_number");
                        flight_num2 = rs.getString("F3.flight_number");
                        Weekdays = rs.getString("Weekdays");
                        stop1 = rs.getString("F1.Arrival_airport_code");
                        stop2 = rs.getString("F3.Departure_airport_code");

                        // Do something with the data
                        fn1.tx1.append(linect + ".\t");
                        fn1.tx1.append(flight_number + "\t");
                        fn1.tx1.append(flight_num1 + "\t");
                        fn1.tx1.append(flight_num2 + "\t");
                        fn1.tx1.append(Weekdays + "\t");
                        fn1.tx1.append(stop1 + "\t");
                        fn1.tx1.append(stop2 + "\t");                        
                        fn1.tx1.append("\n");
                        
                    } // End while(rs.next())
                    fn1.setVisible(true);
                    // Always close the recordset and connection.
                    rs.close();
                    }
                return;
                }
                
                case 2:
                {
                if (!input1.isEmpty() && !input2.isEmpty())
                    {
                        System.out.println("Inside case 2");
                        ResultSet rs1 = stmt.executeQuery("select F.Number_of_Available_seats, A.Total_number_of_seats - count(*), F.flight_number "
                                                        + "from Airplane A, Flight_instance F , Seat_reservation S  "
                                                        + "where F.Flight_number=S.Flight_number and A.Airplane_id=F.Airplane_id and F.Flight_number='" 
                                                        + input1 + "' and F.Date='" + input2 
                                                        + "' GROUP BY S.flight_number;");
                        //ResultSet rs1 = stmt.executeQuery("SELECT Number_of_available_Seats FROM flight_instance WHERE Flight_number='" + input1 + "' and Date='" + input2 + "';");
                        fn1.tx2.append(" Seat Information: \n");
                        while (rs1.next()) {
                            //No_of_avail_Seats = rs1.getInt("Number_of_available_Seats");
                            avail_seat_check = rs1.getInt("Number_of_Available_seats");
                            avail_seat_calc = rs1.getInt("A.Total_number_of_seats - count(*)");
                            
                           // Do something with the data  
                        // Do something with the data
                        fn1.tx1.append(" Available Seats: ");
                        if (avail_seat_check == avail_seat_calc){
                                fn1.tx1.append(avail_seat_calc+ "\t");
                            }
                        else{
                            System.out.println("Inside else, going to update flight_instance");
                                stmt_1.executeUpdate("UPDATE flight_instance SET Number_of_available_seats='" + avail_seat_calc + "' WHERE Flight_number='" + input1 + "';");
                                fn1.tx1.append(avail_seat_calc+ "\t");
                            }
                        }
                    fn1.setVisible(true);    
                    // Always close the recordset and connection.
                    //rs1.close();    

                    }
                    return;
                }


                case 3:
                {

                    if (!input1.isEmpty())
                    {
                    ResultSet rs2 = stmt.executeQuery("SELECT flight_number, fare_code, amount FROM fare WHERE flight_number='" + input1 + "';");
                    fn1.tx1.append(" Fare Information: \n");
                    // Iterate through the result set using ResultSet class's next() method
                    while (rs2.next()) {

                        flight_number = rs2.getString("flight_number");
                        fare_code = rs2.getString("fare_code");
                        amount = rs2.getInt("amount");
                        // Do something with the data  

                        fn1.tx1.append(flight_number+ "\t");
                        fn1.tx1.append(fare_code + "\t");
                        fn1.tx1.append(amount + "\t");
                        fn1.tx1.append("\n");


                    } // End while(rs2.next())
                    fn1.setVisible(true);
                    // Always close the recordset and connection.
                    rs2.close();
                    }
                    return;
                }
                    
                case 4:
                {
                   if (!input1.isEmpty())
                    {
                    ResultSet rs3 = stmt.executeQuery("SELECT Customer_name, Seat_number FROM Seat_Reservation WHERE Flight_number = '" + input1 + "';");
                    fn1.tx1.append("Passenger List: \n");
                    // Iterate through the result set using ResultSet class's next() method
                    while (rs3.next()) {

                        name = rs3.getString("Customer_name");
                        seat_num = rs3.getString("Seat_number");
            
                        // Do something with the data  

                        fn1.tx1.append(name+ "\t");
                        fn1.tx1.append(seat_num + "\t");              
                        fn1.tx1.append("\n");
                    } // End while(rs2.next())
                    fn1.setVisible(true);
                    // Always close the recordset and connection.
                    rs3.close();
                    }
                    return;
                }
                    
          case 5:
                {
                    if (!input1.isEmpty())
                    {
                    ResultSet rs4 = stmt.executeQuery("SELECT S.Flight_Number, S.Date, S.Seat_number, F.Departure_time, F.Arrival_time"
                                                    + " FROM Flight_Instance F, Seat_Reservation S"
                                                    + " WHERE S.Flight_number=F.Flight_number AND F.Date=S.Date AND Customer_name='" + input1 + "';");
                    fn1.tx1.append("Passenger Flight Information: \n");
                    // Iterate through the result set using ResultSet class's next() method
                    while (rs4.next()) {

                        flight_number = rs4.getString("S.flight_number");
                        date = rs4.getString("S.Date");
                        seat_num = rs4.getString("S.Seat_number");
                        dept_time = rs4.getString("F.Departure_time");
                        arrv_time = rs4.getString("F.Arrival_time");
            
                        // Do something with the data  

                        fn1.tx1.append(flight_number+ "\t");
                        fn1.tx1.append(date + "\t");
                        fn1.tx1.append(seat_num + "\t");
                        fn1.tx1.append(dept_time + "\t");
                        fn1.tx1.append(arrv_time + "\t");              
                        fn1.tx1.append("\n");


                    } // End while(rs2.next())
                    fn1.setVisible(true);
                    // Always close the recordset and connection.
                    rs4.close();
                    }
                    return;
                }
                    
            }

        }

         /* This is method to load drivers
          * and get connection between
          * netbeans and mysql
          */
        public void get_javasql_connection() throws SQLException {
             //Load Driver
                
                
                try {
                    // The newInstance() call is a work around for some
                    // broken Java implementations
                    System.out.println("Loading driver...");
                    Class.forName("com.mysql.jdbc.Driver");
                    System.out.println("Driver loaded!");

                } catch (ClassNotFoundException e) {
                    // handle the error
                    throw new RuntimeException("Cannot find the driver in the classpath!", e);
                }

                // Create a connection to the local MySQL server, with the "company" database selected.
                //        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "mypassword");
                // Create a connection to the local MySQL server, with the NO database selected.
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "dove3101");

        }

         /*
         * Closes java sql connection
         */
        public void close_javasql_connection() throws SQLException {
            conn.close();
            System.out.println("Success!!");
        }

        /*
         *
         */
        static void newln() {
            System.out.println();
        }


    public int int_val(String string) {
        int count = 0;
        switch(string){  
                        case "Mon":
                            count = count+0b00000001;;
                            break;
                        case "Tue":
                            count = count+0b00000010;
                            break;
                        case "Wed":
                            count = count+0b00000100;
                            break;   
                        case "Thu":
                            count = count+0b00001000;
                            break;
                        case "Fri":
                            count = count+0b00010000;
                            break;
                        case "Sat":
                            count = count+0b00100000;
                            break;
                        case "Sun":
                            count = count+0b01000000;
                            break; 
                    }
                    return count;
    }

    }
