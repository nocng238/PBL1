
    import java.awt.*;
    import java.awt.Font;
    import java.awt.Graphics;
    import java.awt.Point;
    import java.awt.event.MouseEvent;
    import java.awt.event.MouseListener;
    import java.util.List;
    import java.util.Vector;


    import javax.swing.JPanel;

    public class K_means_visualize extends JPanel implements MouseListener {

        // public static void main(String[] args) {
        // 	new K_means_visualize();
        // }
        //public static final String[] symbols = {"x","o","a","b","c"};
        public static final Color[] colors = {Color.BLACK,Color.blue,Color.red,Color.yellow,Color.green,Color.ORANGE,Color.CYAN,Color.MAGENTA,Color.pink};
        int n = 15;
        int s = 30;
        int os = 50;
        boolean flag = true;
        List<Point> dadanh = new Vector<Point>();
        k_means km;
        public K_means_visualize() {

            this.addMouseListener(this);


        }


        public void paint(Graphics g) {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            g.setColor(Color.black);

            g.drawLine(os, os , os + 2*n * s, os );//top
            g.drawLine(os , os, os , os + n * s);// left
            g.drawLine(os, os + n * s, os + 2*n * s, os + n * s);//bottom
            g.drawLine(os + 2* n * s, os, os + 2* n * s, os + n * s);// right


            // Ve o day
            int radius = 10;
            if (!flag) {
                for (int i = 0 ; i<dadanh.size();i++) {

                    int x = dadanh.get(i).x;
                    int y = dadanh.get(i).y;
                    g.setColor(colors[km.id[i]]);
                    g.fillOval(x , y ,radius,radius);

                }
                for (int i = 0; i < km.cluster.length; i++) {
                    g.setFont(new Font("arial",Font.BOLD,25));
                    g.setColor(colors[i]);
                    g.drawString("x",(int)km.cluster[i][0], (int) km.cluster[i][1]);
                }
            }else{
                for (int i = 0 ; i<dadanh.size();i++) {

                    int x = dadanh.get(i).x;
                    int y = dadanh.get(i).y;
                    x = x-5;
                    y = y-2;
                    g.fillOval( x, y, radius,radius);
                }
            }

        }

        @Override
        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            if (x < os || x >= os + 2*n * s)//
                return;
            if (y < os || y >= os + n * s)
                return;
            if (flag == false) return;


            for (Point d : dadanh) {
                if (d.x == x && d.y == y) return;
            }
            //System.out.println(x + "," + y);
            dadanh.add(new Point(x,y));
            this.repaint();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub

        }

    }
