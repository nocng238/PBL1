
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Menu extends JFrame implements ActionListener {
    JButton visualizeBtn = new JButton("Visualize");
    JButton processFromFile = new JButton("Read File");
    public Menu(){
        visualizeBtn.addActionListener(this);
        processFromFile.addActionListener(this);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(1,2));
        btnPanel.add(visualizeBtn);
        btnPanel.add(processFromFile);
        panel.add(btnPanel,BorderLayout.CENTER);
        add(panel);
        setTitle("Menu");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == visualizeBtn) {

            new MainFrame().setVisible(true);
            this.setVisible(false);

        }
        if (e.getSource() == processFromFile) {
            new ProcessDataFromFile().setVisible(true);
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Menu();

    }
}

