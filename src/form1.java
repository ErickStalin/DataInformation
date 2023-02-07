import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class form1 {
    PreparedStatement ps;
    private JTextField celular;
    private JTextField nombre;
    private JTextField id;
    private JTextField correo;
    private JButton GUARDARButton;
    private JPanel p1;
    public static Connection getConecction() {
        Connection cn = null;
        String base = "Estudiantes"; //Nombre de la BD
        String url = "jdbc:mysql://localhost:3306/" + base; //Direccion, puerto y nombre BD
        String user = "root"; //Usuario
        String pass = "0986167219"; //Contraseña
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection(url,user,pass);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return cn;
    }
    public form1() {
        GUARDARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection cn;
                try {
                    cn = getConecction();
                    ps = cn.prepareStatement("INSERT INTO datosEstudiantes (Id, Nombre, celular, email) values (?,?,?,?)");
                    ps.setString(1, id.getText());
                    ps.setString(2, nombre.getText());
                    ps.setString(3, celular.getText());
                    ps.setString(4, correo.getText());
                    System.out.println(ps);
                    int res = ps.executeUpdate();
                    if (res > 0) {
                        JOptionPane.showMessageDialog(null,"Persona guardada");
                    } else {
                        JOptionPane.showMessageDialog(null,"Error");
                    }
                    cn.close();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame fr = new JFrame("form1");
        fr.setContentPane(new form1().p1);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.pack();
        fr.setVisible(true);
        fr.setResizable(false);
        fr.setLocationRelativeTo(null);
    }

}
