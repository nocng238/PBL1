import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ProcessDataFromFile extends JFrame  {
    ImageIcon successIcon = new ImageIcon("D:\\home\\Java\\PPT\\images\\success.png","Success");
    ImageIcon errorIcon = new ImageIcon("D:\\home\\Java\\PPT\\images\\fail.jpg","Error");

    public ProcessDataFromFile(){
        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Menu();
            }
        });
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        JTextField dataField = new JTextField(10);

        JLabel dataFile = new JLabel("Enter name of data file(.txt)");

        JLabel dataExport = new JLabel("Enter name of result file(.txt)");
        JTextField resultField = new JTextField(10);
        JLabel kLabel = new JLabel("Enter number of cluster");
        JTextField kField = new JTextField(10);
        JButton processBtn = new JButton("Export");

        processBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ProcessDataset haha = new ProcessDataset();
                haha.data.clear();
                Image succesImageScaled = successIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                ImageIcon successScaledIcon = new ImageIcon(succesImageScaled);
                Image errorImageScaled = errorIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                ImageIcon errorScaledIcon = new ImageIcon(errorImageScaled);

                try {
                    try {
                        haha.read(dataField.getText());
                         k_means kmeans = new k_means(getData(haha.data), Integer.parseInt(kField.getText()));
                         while(kmeans.kmeanSlow() == true);
                         int[][] finalResult = new int[kmeans.k][3];
                        for (int i = 0; i < kmeans.data.length; i++) {
                            String type = haha.data.get(i)[haha.data.get(i).length -1];
                            if (type.equals("Iris-setosa")) finalResult[kmeans.id[i]][0]++;
                            if (type.equals("Iris-versicolor")) finalResult[kmeans.id[i]][1]++;
                            if (type.equals("Iris-virginica")) finalResult[kmeans.id[i]][2]++;
                        }
                        haha.write(resultField.getText(), kmeans.data, kmeans.k, kmeans.id,finalResult);
                    } catch (NumberFormatException | IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    JLabel successMessage = new JLabel("Success export");
                    successMessage.setFont(new Font("Arial",Font.BOLD,16));
                    JOptionPane.showMessageDialog(processBtn,successMessage ,"Success", getDefaultCloseOperation(), successScaledIcon);
                } catch (Exception a) {
                    JLabel errorMessage = new JLabel("Cannot export!"+'\n'+"Please make sure u type right name of datafile");
                    errorMessage.setFont(new Font("Arial",Font.BOLD,16));
                    JOptionPane.showMessageDialog(processBtn, errorMessage ,"Error", getDefaultCloseOperation(), errorScaledIcon);
                }

            }
        });
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(4,2));
        panel1.add(dataFile);
        panel1.add(dataField);
        panel1.add(kLabel);
        panel1.add(kField);
        panel1.add(dataExport);
        panel1.add(resultField);
        panel1.add(processBtn);
        panel1.add(backBtn);
        mainPanel.add(panel1,BorderLayout.CENTER);
        add(mainPanel);
        setTitle("Read File");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    public double[][] getData(List<String[]> data){
        double [][] res = new double[data.size()][data.get(0).length-1];
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++) {
                res[i][j] = Double.parseDouble(data.get(i)[j]);
            }
        }
        return res;
    }
}

