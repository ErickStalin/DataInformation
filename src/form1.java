import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class form1 {
    PreparedStatement ps;
    private JTextField textField4;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton GUARDARButton;
    private JPanel p1;
    public form1() {

        GUARDARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection con;

                try{
                    con = getConecction();
                    ps = con.prepareStatement("INSERT INTO datosPersona(IdPerson,NomPerson,CelPerson,MailPerson) VALUES(?,?,?,?)");
                    ps.setString(1,textField2.getText());
                    ps.setString(2,textField1.getText());
                    ps.setString(3,textField4.getText());
                    ps.setString(4,textField3.getText());
                    System.out.println(ps);
                    int res = ps.executeUpdate();
                    if(res > 0){
                        JOptionPane.showMessageDialog(null,"Persona guardada");
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Error");
                    }
                    con.close();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("form1");
        frame.setContentPane(new form1().p1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    public static Connection getConecction() {
        Connection con = null;
        String base= "datos";
        String url= "jdbc:mysql://localhost"+base;
        String user = "root";
        String password = "0986167219";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,user,password);
        }catch (ClassNotFoundException | SQLException e){
            System.err.println(e);
        }
        return con;
    }

}
