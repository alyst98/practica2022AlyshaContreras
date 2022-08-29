package sistema.administrativo;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
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
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class ventana extends JFrame {

    usuario usuSistema[] = new usuario[10];
    JPanel panelInicioSesion;
    JPanel panelControl;
    JPanel panelCrearUsuario;
    JPanel panelControlProd;
    JPanel panelControlClientes;
    clientes cliente[] = new clientes[100];
    productos prod[] = new productos[100];
    int controlClientes = 1;
    int controlCliente = 0;
    int control = 2;
    int producto =1;
    

    //metodo constructor
    public ventana() {
        objetos();
        crearAdmin();
        crearClientes();
//        crearProductos();
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
    public void crearProd() {
        prod[0] = new productos();
        prod[0].nombre = "Producto 1";
        prod[0].cantidad = 5;
        prod[0].precio = 32;
    }
    public void objetos() {
        panelInicioSesion = new JPanel();
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
        panelControl = new JPanel();
        this.getContentPane().add(panelControl);
        panelControl.setLayout(null);
        this.setSize(260, 200);
        this.setTitle("Control Principal");
        panelInicioSesion.setVisible(false);

        JButton btnAdminClientes = new JButton("Administracion de clientes");
        btnAdminClientes.setBounds(30, 15, 200, 25);
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
        btnAdminProd.setBounds(30, 50, 200, 25);
        panelControl.add(btnAdminProd);
        ActionListener adinistrarProd =new ActionListener (){
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelControlProd();
                panelControlProd.setVisible(true);
            }
        };
        btnAdminProd.addActionListener(adinistrarProd);

//        JButton btnRepor = new JButton("Reportes");
//        btnRepor.setBounds(50, 210, 270, 40);
//        panelControl.add(btnRepor);
    }

    public void panelCrearUsuario() {
        panelCrearUsuario = new JPanel();
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
            JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente, total de usurarios: " + control);
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
        panelControlClientes = new JPanel();
        this.getContentPane().add(panelControlClientes);
        panelControlClientes.setLayout(null);
        this.setSize(590, 450);
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
        
        DefaultPieDataset datos = new DefaultPieDataset();
        datos.setValue("Masculino", totalHombres());
        datos.setValue("Femenino", totalMujeres());
        JFreeChart graficoCircular = ChartFactory.createPieChart("Genero en el sistema", datos);
        ChartPanel panelCircular = new ChartPanel (graficoCircular);
        panelCircular.setBounds(10, 170, 250, 200);
        panelControlClientes.add(panelCircular);
        
        //Grafico de columnas
        DefaultCategoryDataset datos2 = new DefaultCategoryDataset();
        datos2.addValue(rango18a30(), "18 a 30", "");
        datos2.addValue(rango31a45(), "31 a 45", "");
        datos2.addValue(rangomas45(), "Más de 45", "");
        JFreeChart graficoColumnas = ChartFactory.createBarChart("Rangos de edad", "Rangos", "No. de clientes", datos2, PlotOrientation.VERTICAL, true, true, false);
        ChartPanel panelColumnas = new ChartPanel(graficoColumnas);
        panelColumnas.setBounds(275, 170, 270, 200);
        panelControlClientes.add(panelColumnas);

        JButton btnRegresar = new JButton("Volver al menu principal");
        btnRegresar.setBounds(330, 10, 200, 20);
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

        JButton btnCargarArchivo = new JButton("Buscar archivo CSV");
        btnCargarArchivo.setBounds(330, 38, 200, 20);
        panelControlClientes.add(btnCargarArchivo);
        ActionListener buscarArchivo = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                File archivoSeleccionado;
                JFileChooser ventanaSeleccion = new JFileChooser();
                ventanaSeleccion.showOpenDialog(null);
                archivoSeleccionado = ventanaSeleccion.getSelectedFile();
                System.out.println("La ubicacion del archivo es " + archivoSeleccionado.getPath());
                leerArchivoCSV(archivoSeleccionado.getPath());
                panelControlClientes.setVisible(false);
                panelControlClientes();
            }
        };
        btnCargarArchivo.addActionListener(buscarArchivo);
        
        JButton btnReporte =new JButton("Crear reporte HTML");
        btnReporte.setBounds(330, 68, 200, 20);
        panelControlClientes.add(btnReporte);        
        ActionListener crearHTML = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
              crearReporte();
            }
        };
        btnReporte.addActionListener(crearHTML);
    }
    
    public void panelControlProd (){
        panelControlProd = new JPanel();
        this.getContentPane().add(panelControlProd);
        panelControlProd.setLayout(null);
        this.setSize(590, 450);
        this.setTitle("Administracion de productos");
        panelControl.setVisible(false);
        
        DefaultTableModel datosTabla = new DefaultTableModel();
        datosTabla.addColumn("Nombre");
        datosTabla.addColumn("Precio");
        datosTabla.addColumn("Cantidad");

        for (int i = 0; i < 100; i++) {
            if (prod[i] != null) {
                String fila[] = {prod[i].nombre, String.valueOf(prod[i].precio), String.valueOf(prod[i].cantidad)};
                datosTabla.addRow(fila);
            }
        }
        JTable tablaProductos = new JTable(datosTabla);
        JScrollPane barraTablaProductos = new JScrollPane(tablaProductos);
        barraTablaProductos.setBounds(10, 10, 300, 150);
        panelControlProd.add(barraTablaProductos);
//        
//        DefaultCategoryDataset datos2 = new DefaultCategoryDataset();
//        datos2.addValue(rango18a30(), "18 a 30", "");
//        datos2.addValue(rango31a45(), "31 a 45", "");
//        datos2.addValue(rangomas45(), "Más de 45", "");
//        JFreeChart graficoColumnas = ChartFactory.createBarChart("Rangos de productos", "Precio", "Cantidad", datos2, PlotOrientation.VERTICAL, true, true, false);
//        ChartPanel panelColumnas = new ChartPanel(graficoColumnas);
//        panelColumnas.setBounds(275, 170, 270, 200);
//        panelControlProd.add(panelColumnas);

        JButton btnRegresar = new JButton("Volver al menu principal");
        btnRegresar.setBounds(330, 10, 200, 20);
        panelControlProd.add(btnRegresar);
        ActionListener volverMenu = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelControl.setVisible(true);
                panelControlProd.setVisible(false);
                volverMenu();
            }
        };
        btnRegresar.addActionListener(volverMenu);
    }
    
    public void ordenar (){
        clientes auxiliar;
        for(int i=0;i<99; i++){
            for(int j=0; j<99; j++){
                if(cliente[j+1] == null){
                    break;
                }else{
                    if(cliente[j].edad>cliente[j+1].edad){
                        auxiliar = cliente[j+1];
                        cliente[j+1] = cliente[j];
                        cliente[j] = auxiliar;
                    }
                }
            }
        }
    }
    
    public void crearReporte(){
        try{
          ordenar ();
          PrintWriter escribirCSS = new PrintWriter("reportes/destilo.css","UTF-8");  
          escribirCSS.print("");
          
          escribirCSS.close();
            
          PrintWriter escribir = new PrintWriter("reportes/index.html","UTF-8");
          escribir.println("<!doctype html>");
          escribir.println("<html>");
          escribir.println("<head>");
          escribir.println("<title>Reporte del sistema</title>");
          escribir.println("</head>");
          escribir.println("<body>");
          escribir.println("<h1>Listado de clientes en el sistema</h1>");
          escribir.println("<br>");
          
          escribir.println("<table border =1>");
          escribir.println("<tr>");
          escribir.println("<td>NIT</td> <td>Nombre</td> <td>Edad</td> <td>Sexo</td> ");
          escribir.println("</tr");
          
          for(int i=0; i<99; i++){
              if(cliente[i] != null){
                  escribir.println("<tr>");
                  escribir.println("<td>" + cliente[i].nit + "</td><td>" + cliente[i].nombre + "</td><td>" + cliente[i].edad + "</td><td>" + cliente[i].sexo + "</td>");
                  escribir.println("</tr");
              }
          }
          escribir.println("</table>");
          
          
          escribir.println("</body>");
          escribir.println("</html>");
          escribir.close();
          JOptionPane.showMessageDialog(null, "Reporte creado con exito, este se encuentra en la carperta REPORTES");
        }catch(IOException error){
            JOptionPane.showMessageDialog(null, "No se pudo crear el reporte");
        }
    }
    
    public int totalHombres(){
        int total = 0;
        for(int i =0; i<100; i++){
            if(cliente[i] != null){
                if(cliente[i].sexo == 'M'){
                    total++;
                }
            }      
        }
        return total;
    }
    
    public int totalMujeres(){
        int total = 0;
        for(int i =0; i<100; i++){
            if(cliente[i] != null){
                if(cliente[i].sexo == 'F'){
                    total++;
                }
            }      
        }
        return total;
    }
    
    public int rango18a30(){
        int total = 0;
        for(int i =0; i<100; i++){
            if(cliente[i] != null){
                if(cliente[i].edad >= 18 && cliente[i].edad <=30){
                    total++;
                }
            }      
        }
        return total;
    }
    
    public int rango31a45(){
        int total = 0;
        for(int i =0; i<100; i++){
            if(cliente[i] != null){
                if(cliente[i].edad >=31 && cliente[i].edad <=45){
                    total++;
                }
            }      
        }
        return total;
    }
    
    public int rangomas45(){
        int total = 0;
        for(int i =0; i<100; i++){
            if(cliente[i] != null){
                if(cliente[i].edad >45){
                    total++;
                }
            }      
        }
        return total;
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
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pueden registrar mas clientes");
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Clientes registrado exitosamente, total de clientes: " + controlCliente);
            archivoTemporal.close();
        } catch (IOException error) {
            JOptionPane.showMessageDialog(null, "no se pudo abrir el archivo CSV");
        }
    }
}