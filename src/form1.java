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
    private JButton ACTUALIZARButton;
    private JButton BUSCARButton;
    private JButton ELIMINARButton;
    PreparedStatement pd;

    public static Connection getConecction() {
        Connection cn = null;
        String base = "Estudiantes"; //Sombre de la BD
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
        BUSCARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection cx;
                try{
                cx = getConecction();
                String qr = "select * from datosEstudiantes  where Id = "+id.getText()+";";
                Statement s = cx.createStatement();
                ResultSet rs = s.executeQuery(qr);
                System.out.println(rs);
                while(rs.next()) {
                    nombre.setText(rs.getString(2));
                    celular.setText(rs.getString(3));
                    correo.setText(rs.getString(4));
                }
                cx.close();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            }
        });
        ACTUALIZARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection ct;
                try {
                    String qr = "Update datosEstudiantes set Nombre = ?, celular = ?, email = ? where Id = "+id.getText();
                    ct = getConecction();
                    pd = ct.prepareStatement(qr);
                    pd.setString(1, nombre.getText());
                    pd.setString(2, celular.getText());
                    pd.setString(3, correo.getText());
                    pd.executeUpdate();
                    System.out.println(pd);
                    int res = pd.executeUpdate();
                    if (res > 0) {
                        JOptionPane.showMessageDialog(null,"Persona actualizada correctamente");
                    } else {
                        JOptionPane.showMessageDialog(null,"Error");
                    }
                    ct.close();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        ELIMINARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection con;
                String id = Jtid.getText();
                try {
                    con = getConnection();
                    pd eliminar;
                    eliminar = con.prepareStatement("DELETE FROM datosEstudiantes WHERE Id="+id); 
                    pd.executeUpdate();
                    int res = pd.executeUpdate();
                    if (res > 0) {
                        JOptionPane.showMessageDialog(null,"Persona actualizada correctamente");
                    } else {
                        JOptionPane.showMessageDialog(null,"Error");
                    }
                    con.close();
                } catch (HeadlessException | SQLException f) {
                    System.err.println(f);
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
