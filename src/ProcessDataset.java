
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ProcessDataset {
    public List<String[]> data =new ArrayList<>();
    public void read(String s) throws NumberFormatException, IOException {
        File file=new File("D:\\home\\Java\\PPT\\"+ s);
        try {
            BufferedReader readFile=new BufferedReader(new FileReader(file));
            String line;
            while((line=readFile.readLine()) != null)
            {
                String[] split = line.split(",");
               data.add(split);
            }
            readFile.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    public void write(String s,double[][] points,int numberOfCentroids,int[] id,int[][] finalResult) throws NumberFormatException,IOException{
        File file = new File("D:\\home\\Java\\PPT\\"+s);
        try {
            BufferedWriter writeFile = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < points.length; i++) {
                for (int j = 0; j < points[0].length; j++) {
                    writeFile.append(Double.toString(points[i][j])+"\t \t");
                }
                writeFile.append(Integer.toString(id[i]));
                writeFile.append("\n");
            }
            for (int i = 0; i < finalResult.length; i++) {
                writeFile.append("Cluster "+i+" :");
                writeFile.append("\n");
                writeFile.append("\t \t"+finalResult[i][0]+" setosa");
                writeFile.append("\n");
                writeFile.append("\t \t"+finalResult[i][1]+" versicolor");
                writeFile.append("\n");
                writeFile.append("\t \t"+finalResult[i][2]+" virginica");
                writeFile.append("\n");
            }
            writeFile.close();

        } catch (Exception e) {
            //TODO: handle exception
        }
    }
    public ProcessDataset(){

    }
}




