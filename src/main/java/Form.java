import com.formdev.flatlaf.FlatDarculaLaf;
import javax.swing.*;

public class Form extends JFrame{
    private JPanel panel1;

    public Form() {
        initilizingForm();
    }
    private void initilizingForm(){

        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null); // center

    }

    public static void main(String[] args) {

        FlatDarculaLaf.setup(); // change dark mode
        Form form = new Form();
        form.setVisible(true);

    }

}
