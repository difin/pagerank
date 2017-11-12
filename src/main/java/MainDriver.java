import org.apache.commons.lang3.time.StopWatch;

import java.io.IOException;
import java.util.List;

/**
 * Created by dfingerman on 9/23/17.
 */
public class MainDriver {

    private String stringFile;
    private String outputFile;

    private List<Integer> output;

    public MainDriver(){
        stringFile = "links.txt";
        outputFile = "output.txt";
    }

    public MainDriver(String directory, String outputFile) {
        this.stringFile = directory + "/" + "string.txt";
        this.outputFile = directory + "/" + outputFile;
    }

    public static void main(String[] args) throws IOException {

        MainDriver mainDriver = new MainDriver();
        mainDriver.runPageRank();
    }

    public void runPageRank(){
        readFilesIntoMemory();
        createOutputFile(output);
    }

    private void readFilesIntoMemory(){

        StopWatch timer = new StopWatch();
        timer.start();

        List<String> links = FileUtils.fileToList(stringFile);

        timer.stop();
        System.out.println("Files reading time: " + timer.toString());
    }

    private void createOutputFile(List<Integer> output){

        StopWatch timer = new StopWatch();
        timer.start();

        FileUtils.listToFile(outputFile, output);

        timer.stop();
        System.out.println("Output file writing time: " + timer.toString());
    }
}