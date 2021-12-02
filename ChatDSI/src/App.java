

import javax.swing.JOptionPane;

public class App {
    
    public static void main(String[] args) {
        String  ip = (String)JOptionPane.showInputDialog("Informe o IP","192.168.0.");
        int porta = Integer.parseInt(JOptionPane.showInputDialog("Informe a Porta","5000"));
        
        Server c = new Server(ip, porta);
        
        Window j = new Window(c);
        j.setLocationRelativeTo(null);
        j.setVisible(true);
    }
}