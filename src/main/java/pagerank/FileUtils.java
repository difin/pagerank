package pagerank;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dfingerman on 9/23/17.
 */
public class FileUtils {

    public static List<String> fileToList(String fileName){

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))){

            String line;

            List<String> lines = new ArrayList<String>();
            while((line = bufferedReader.readLine()) != null){
                lines.add(line);
            }

            return lines;

        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }

    public static void listToFile(String fileName, List<String> outputList){

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))){

            for (int i=0; i<outputList.size(); i++){
                String line = outputList.get(i);
                bufferedWriter.write(line);

                if (i<outputList.size()-1){
                    bufferedWriter.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}