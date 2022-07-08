
import java.util.Random;
public class k_means {
    double[][] data;
    int k;
    int id[];
    double[][] cluster;
    Random rand = new Random();

    public k_means(double[][] data, int k) {
        this.data = data;
        this.k = k;
        this.id = new int[data.length];
        this.cluster = new double[k][data[0].length];


        for (int i = 0; i < data.length; i++)
            id[i] = rand.nextInt(k);

        // Buoc 2-4

        // Buoc 2
        int[] count = new int[k];
        //set all clusters and numbers of point belong to each cluster back to (0,0);
        for (int i = 0; i < k; i++) {
            count[i] = 0;
            for (int j = 0; j < cluster[i].length; j++)
                cluster[i][j] = 0;
        }
        /// counting the numbers of point in each cluster and sum of all points in each cluster coordinate
        for (int i = 0; i < data.length; i++) {
            count[id[i]]++;
            for (int j = 0; j < data[i].length; j++)
                cluster[id[i]][j] += data[i][j];
        }

        for (int i = 0; i < k; i++) {
            if (count[i] != 0) {
                for (int j = 0; j < cluster[i].length; j++)
                    cluster[i][j] /= count[i];//counting cluster's center coordinate
            } else{
                int x = rand.nextInt(data.length);
                for (int j = 0; j < cluster[i].length; j++)
                    cluster[i][j] = data[x][j];
            }
        }
    }
    public boolean kmeanSlow(){
        int[] count = new int[k];
        //set all clusters and numbers of point belong to each cluster back to (0,0);
        for (int i = 0; i < k; i++) {
            count[i] = 0;
            for (int j = 0; j < cluster[i].length; j++)
                cluster[i][j] = 0;
        }
        /// counting the numbers of point in each cluster and sum of all points in each cluster coordinate
        for (int i = 0; i < data.length; i++) {
            count[id[i]]++;
            for (int j = 0; j < data[i].length; j++)
                cluster[id[i]][j] += data[i][j];
        }

        for (int i = 0; i < k; i++) {
            if (count[i] != 0) {
                for (int j = 0; j < cluster[i].length; j++)
                    cluster[i][j] /= count[i];//counting cluster's center coordinate
            } else{
                int x = rand.nextInt(data.length);
                for (int j = 0; j < cluster[i].length; j++)
                    cluster[i][j] = data[x][j];
            }
        }

        // Buoc 3
         boolean thaydoi = false;
        for (int i = 0; i < data.length; i++) {
            int newid = Chia(data[i]);
            if (id[i] != newid)
                thaydoi = true;
            id[i] = newid;
        }
        return  thaydoi;

        // Buoc 4

    }


    public int Chia(double[] x) {
        int id = 0;
        double min = Integer.MAX_VALUE;
        for (int i = 0; i < k; i++) {
            double d = dis(x, cluster[i]);
            if (min > d) {
                min = d;
                id = i;
            }
        }
        return id;
    }

    private double dis(double[] x, double[] y) {
        double dis = 0;
        for (int i = 0; i < x.length; i++)
            dis += (x[i] - y[i]) * (x[i] - y[i]);
        return dis;
    }
}
