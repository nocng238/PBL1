

    import java.awt.*;       // Using AWT's Graphics and Color
    import java.awt.event.*; // Using AWT's event classes and listener interfaces
    import java.util.*;
    import java.util.List;

    import javax.swing.*;    // Using Swing's components and containers




    public class MainFrame extends JFrame {
        public Random random = new Random();
        private K_means_visualize paintPanel;
        // Constructor to set up the GUI components and event handlers
        public MainFrame() {

            // Set up a panel for the buttons

            JPanel btnPanel = new JPanel(new GridLayout(3,1));
            JPanel first = new JPanel(new FlowLayout());
            JTextField numberOfClusterTextField = new JTextField(10);
            JLabel kLabel = new JLabel("Enter number of cluster: ");
            JButton btnInitalize = new JButton("Initalize cluster");
            JPanel second = new JPanel(new FlowLayout());
            JTextField numberOfPointField =  new JTextField(10);
            JLabel pointLabel = new JLabel("Enter number of point: ");
            JButton btnInitalizePoints = new JButton("Initalize points");
            second.add(pointLabel);
            second.add(numberOfPointField);
            second.add(btnInitalizePoints);
            btnInitalizePoints.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int numberOfPoints = Integer.parseInt(numberOfPointField.getText());
                    while(numberOfPoints-- > 0){
                        paintPanel.dadanh.add(new Point(random.nextInt(850)+70,random.nextInt(400)+70));
                    }
                    paintPanel.repaint();
                }
            });
            JPanel third = new JPanel(new FlowLayout());
            JButton btnDoKMeans = new JButton("DO-KMEANS ");
            JButton btnDoKMeansSlow = new JButton("DO-KMEANS but Slowly");
            JButton btnCreatePoint = new JButton("CREATING MORE POINT");
            JButton btnReset = new JButton("Reset");
            JButton btnBack = new JButton("Back");
            third.add(btnDoKMeansSlow);
            third.add(btnDoKMeans);
            third.add(btnCreatePoint);
            third.add(btnReset);
            third.add(btnBack);

            first.add(kLabel);
            first.add(numberOfClusterTextField);
            first.add(btnInitalize);
            btnPanel.add(first);
            btnPanel.add(second);
            btnPanel.add(third);
            btnInitalize.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    paintPanel.km = new k_means(convertData(paintPanel.dadanh), Integer.parseInt(numberOfClusterTextField.getText()));
                    paintPanel.flag = false;
                    paintPanel.repaint();
                }

            });

            btnDoKMeansSlow.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {

                    paintPanel.flag = false;
                    // TODO Auto-generated method stub
                      Thread t1 =  new Thread(new Runnable() {
                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            while (paintPanel.km.kmeanSlow() == true) {
                            try {
                                paintPanel.repaint();
                                Thread.sleep(500);
                            } catch (Exception e) {
                                System.out.println(e);
                                break;
                            }
                        }
                     }
                     });
                      t1.start();
                }
            });

            btnDoKMeans.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    while(paintPanel.km.kmeanSlow() == true){
                    }
                    paintPanel.flag = false;
                    paintPanel.repaint();
                }

            });


            //CREATING MORE POINT BUTTON
            btnCreatePoint.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    paintPanel.flag = true;
                    paintPanel.repaint();
                }
            });

            // RESET BUTTON
            btnReset.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    paintPanel.dadanh.clear();
                    numberOfClusterTextField.setText("");
                    numberOfPointField.setText("");
                    paintPanel.repaint();
                    paintPanel.flag = true;
                }
            });
            //BACK BUTTON
            btnBack.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                    //new Menu();
                }
            });

            // Set up the custom drawing JPanel
            paintPanel = new K_means_visualize();
            paintPanel.setPreferredSize(new Dimension(2* paintPanel.n * paintPanel.s + 2*paintPanel.os, paintPanel.n * paintPanel.s + 2*paintPanel.os));

            // Add panels to this JFrame
            Container cp = getContentPane();
            cp.setLayout(new BorderLayout());
            cp.add(paintPanel, BorderLayout.CENTER);

            cp.add(btnPanel, BorderLayout.SOUTH);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setTitle("K-MEANS-VISUALIZATION");
            pack();            // pack all the components in the JFrame
            setVisible(true);  // show it
            requestFocus();    // "super" JFrame requests focus to receive KeyEvent
        }
        public double[][] convertData(List<Point> a){
            double[][] res = new double[a.size()][2];
            for (int i = 0; i < res.length; i++) {
                res[i][0] = a.get(i).getX();
                res[i][1] = a.get(i).getY();
            }
            return res;
        }






    }









