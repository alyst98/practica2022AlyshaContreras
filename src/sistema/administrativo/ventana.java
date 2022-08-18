package sistema.administrativo;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ventana extends JFrame {

    usuario usuSistema[] = new usuario[10];
    JPanel panelInicioSesion = new JPanel();
    JPanel panelControl = new JPanel();
    JPanel panelCrearUsuario = new JPanel();
    int control = 2;
    clientes cliente[] = new clientes[100];
    int controlCliente = 0;
    JPanel panelControlClientes = new JPanel();
    int controlClientes =2;

    //metodo constructor
    public ventana() {
        objetos();
        crearAdmin();
        crearClientes();
    }

    public void crearAdmin() {
        usuSistema[0] = new usuario();
        usuSistema[0].usuario = "admin";
        usuSistema[0].nombreUsuario = "administrador";
        usuSistema[0].contra = "1234";
//        //usuario temporal para pruebas
//        usuSistema[1] = new usuario();
//        usuSistema[1].nombreUsuario = "juan20";
//        usuSistema[1].usuario = "Juan Perez";
//        usuSistema[1].contra = "Jperez20"; 
    }

    public void crearClientes() {
        cliente[0] = new clientes();
        cliente[0].nombre = "cliente 1";
        cliente[0].edad = 22;
        cliente[0].sexo = 'F';
        cliente[0].nit = 150;

        cliente[1] = new clientes();
        cliente[1].nombre = "cliente 2";
        cliente[1].edad = 32;
        cliente[1].sexo = 'M';
        cliente[1].nit = 123;
    }

    public void objetos() {
        this.getContentPane().add(panelInicioSesion);
        panelInicioSesion.setLayout(null);

        JLabel lblUsu = new JLabel("Usuario");
        lblUsu.setBounds(20, 20, 100, 15);
        panelInicioSesion.add(lblUsu);

        JLabel lblContra = new JLabel("Contraseña");
        lblContra.setBounds(20, 45, 100, 15);
        panelInicioSesion.add(lblContra);

        JTextField txtUsu = new JTextField();
        txtUsu.setBounds(140, 15, 150, 25);
        panelInicioSesion.add(txtUsu);

        JPasswordField txtContra = new JPasswordField();
        txtContra.setBounds(140, 45, 150, 25);
        panelInicioSesion.add(txtContra);

        JButton btnIngre = new JButton("Ingresar");
        btnIngre.setBounds(170, 90, 100, 25);
        panelInicioSesion.add(btnIngre);
        ActionListener ingresar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (txtUsu.getText().equals("") || txtContra.getPassword().equals("")) {
                    JOptionPane.showMessageDialog(null, "Debe de llenar todos los espacios.");
                } else {
                    recorrerUsuarios(txtUsu.getText(), txtContra.getText());
                }
            }
        };
        btnIngre.addActionListener(ingresar);

        JButton btnCrearUsuario = new JButton("Nuevo usuario");
        btnCrearUsuario.setBounds(50, 90, 115, 25);
        panelInicioSesion.add(btnCrearUsuario);
        ActionListener crearUsuario = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelCrearUsuario();
                panelCrearUsuario.setVisible(true);
            }
        };
        btnCrearUsuario.addActionListener(crearUsuario);
    }

    public void recorrerUsuarios(String usuario, String contra) {
        boolean encontrado = false;
        for (int i = 0; i < 10; i++) {
            if (usuSistema[i].usuario.equals(usuario) && usuSistema[i].contra.equals(contra)) {
                JOptionPane.showMessageDialog(null, "Bienvenido al sistema " + " " + usuario);
                panelControl();
                encontrado = true;
                break;

            } else {
                encontrado = false;

            }
        }
        if (encontrado == false) {
            JOptionPane.showMessageDialog(null, "Datos incorrectos");
        }
    }
    public void panelControl() {

        this.getContentPane().add(panelControl);
        panelControl.setLayout(null);
        this.setSize(400, 300);
        this.setTitle("Control Principal");
        panelInicioSesion.setVisible(false);

        JButton btnAdminClientes = new JButton("Administracion de clientes");
        btnAdminClientes.setBounds(50, 10, 270, 40);
        panelControl.add(btnAdminClientes);
        ActionListener administrarClientes = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelControlClientes();
                panelControlClientes.setVisible(true);
            }
        };
        btnAdminClientes.addActionListener(administrarClientes);

        JButton btnAdminProd = new JButton("Administracion de productos");
        btnAdminProd.setBounds(50, 65, 270, 40);
        panelControl.add(btnAdminProd);

        JButton btnRepor = new JButton("Reportes");
        btnRepor.setBounds(50, 210, 270, 40);
        panelControl.add(btnRepor);
    }

    public void panelCrearUsuario() {
        this.getContentPane().add(panelCrearUsuario);
        panelCrearUsuario.setLayout(null);
        this.setSize(340, 200);
        this.setTitle("Crear nuevo usuario");
        panelInicioSesion.setVisible(false);

        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setBounds(20, 20, 100, 15);
        panelCrearUsuario.add(lblUsuario);

        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(20, 45, 100, 15);
        panelCrearUsuario.add(lblNombre);

        JLabel lblContra = new JLabel("Contraseña");
        lblContra.setBounds(20, 70, 100, 15);
        panelCrearUsuario.add(lblContra);

        JLabel lblConfContra = new JLabel("Conf. Contraseña");
        lblConfContra.setBounds(20, 95, 100, 15);
        panelCrearUsuario.add(lblConfContra);

        JTextField txtUsuario = new JTextField();
        txtUsuario.setBounds(130, 20, 150, 20);
        panelCrearUsuario.add(txtUsuario);

        JTextField txtNombre = new JTextField();
        txtNombre.setBounds(130, 44, 150, 20);
        panelCrearUsuario.add(txtNombre);

        JTextField txtContra = new JTextField();
        txtContra.setBounds(130, 70, 150, 20);
        panelCrearUsuario.add(txtContra);

        JTextField txtConfContra = new JTextField();
        txtConfContra.setBounds(130, 95, 150, 20);
        panelCrearUsuario.add(txtConfContra);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(150, 130, 120, 20);
        panelCrearUsuario.add(btnRegistrar);
        ActionListener registro = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (txtUsuario.getText().equals("") || txtNombre.getText().equals("") || txtContra.getText().equals("") || txtConfContra.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Debe de llenar todos los campos");

                } else {
                    if (txtContra.getText().equals(txtConfContra.getText())) {
                        guardarUsuario(txtUsuario.getText(), txtNombre.getText(), txtContra.getText());
                        txtUsuario.setText("");
                        txtNombre.setText("");
                        txtContra.setText("");
                        txtConfContra.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
                    }
                }
            }
        };
        btnRegistrar.addActionListener(registro);

        JButton btnRegresar = new JButton("Volver al inicio");
        btnRegresar.setBounds(20, 130, 120, 20);
        panelCrearUsuario.add(btnRegresar);
        ActionListener volverInicio = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelInicioSesion.setVisible(true);
                panelCrearUsuario.setVisible(false);
                volverInicio();
            }
        };
        btnRegresar.addActionListener(volverInicio);
    }

    public void volverInicio() {
        this.setTitle("Login");
        this.setSize(340, 210);
    }

    public void guardarUsuario(String usuario, String Nombre, String contra) {
        int posicion = 0;
        if (control < 10) {
            for (int i = 0; i < 9; i++) {
                if (usuSistema[i] == null) {
                    posicion = i;
                    break;
                }
            }
            usuSistema[posicion] = new usuario();
            usuSistema[posicion].usuario = usuario;
            usuSistema[posicion].nombreUsuario = Nombre;
            usuSistema[posicion].contra = contra;
            JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente, total de usuarios" + control);
            for (int i = 0; i < 5; i++) {
                if (usuSistema[i] != null) {
                    System.out.println(usuSistema[i].usuario);
                    System.out.println(usuSistema[i].nombreUsuario);
                    System.out.println(usuSistema[i].contra);
                }

            }
        } else {
            JOptionPane.showMessageDialog(null, "no se pueden registrar mas usuarios");

        }
    }

    public void panelControlClientes() {
        this.getContentPane().add(panelControlClientes);
        panelControlClientes.setLayout(null);
        this.setSize(500, 450);
        this.setTitle("Administracion de clientes");
        panelControl.setVisible(false);

        DefaultTableModel datosTabla = new DefaultTableModel();
        datosTabla.addColumn("Nombre");
        datosTabla.addColumn("Edad");
        datosTabla.addColumn("Sexo");
        datosTabla.addColumn("NIT");

        for (int i = 0; i < 100; i++) {
            if (cliente[i] != null) {
                String fila[] = {cliente[i].nombre, String.valueOf(cliente[i].edad), String.valueOf(cliente[i].sexo), String.valueOf(cliente[i].nit)};
                datosTabla.addRow(fila);
            }
        }
        JTable tablaClientes = new JTable(datosTabla);
        JScrollPane barraTablaClientes = new JScrollPane(tablaClientes);
        barraTablaClientes.setBounds(10, 10, 300, 150);
        panelControlClientes.add(barraTablaClientes);

        JButton btnRegresar = new JButton("Volver al menu");
        btnRegresar.setBounds(330, 10, 140, 20);
        panelControlClientes.add(btnRegresar);
        ActionListener volverMenu = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelControl.setVisible(true);
                panelControlClientes.setVisible(false);
                volverMenu();
            }
        };
        btnRegresar.addActionListener(volverMenu);

        JButton btnCargarArchivo = new JButton("Buscar archivo");
        btnCargarArchivo.setBounds(330, 38, 140, 20);
        panelControlClientes.add(btnCargarArchivo);
        ActionListener buscarArchivo = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                File archivoSeleccionado;
                JFileChooser ventanaSeleccion = new JFileChooser();
                ventanaSeleccion.showOpenDialog(null);
                archivoSeleccionado = ventanaSeleccion.getSelectedFile();
                System.out.println("La ubicacion del archivo es" + archivoSeleccionado.getPath());
                leerArchivoCSV(archivoSeleccionado.getPath());
            }
        };
        btnCargarArchivo.addActionListener(buscarArchivo);
    }

    public void volverMenu() {
        this.setTitle("Control Principal");
        this.setSize(400, 300);
    }

    public void leerArchivoCSV(String ruta) {
        try {
            BufferedReader archivoTemporal = new BufferedReader(new FileReader(ruta));
            String lineaLeida = "";
            while (lineaLeida != null) {
                lineaLeida = archivoTemporal.readLine();
                if (lineaLeida != null) {
                    String datosSeparados[] = lineaLeida.split(",");
                    
                    int posicion = 0;
                    if (control < 100) {
                        for (int i = 0; i < 99; i++) {
                            if (cliente[i] == null) {
                                posicion = i;
                                break;
                            }
                        }
                        cliente[posicion] = new clientes();
                        cliente[posicion].nombre = datosSeparados[0];
                        cliente[posicion].edad = Integer.parseInt(datosSeparados[1]);
                        cliente[posicion].sexo = datosSeparados[2].charAt(0);
                        cliente[posicion].nit = Integer.parseInt(datosSeparados[3]);
                        controlCliente++;
                        JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente, total de usuarios " + controlClientes);
                    } else {
                        JOptionPane.showMessageDialog(null, "no se pueden registrar mas clientes");

                    }
                }
            }
            archivoTemporal.close();
        } catch (IOException error) {
            JOptionPane.showMessageDialog(null, "no se pudo abrir el archivo CSV");
        }
    }
}
