
package sistema.administrativo;

import javax.swing.JFrame;


public class SistemaAdministrativo {

  
    public static void main(String[] args) {
        
        ventana log = new ventana();
        log.setVisible(true);
        log.setTitle("Login");
        log.setSize(340,210);
        log.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        log.setLocationRelativeTo(null);
    }
    
}
