package pagerank;

import org.apache.commons.lang3.time.StopWatch;

import java.io.IOException;
import java.util.List;

/**
 * Created by dfingerman on 9/23/17.
 */
public class MainDriver {

    private String stringFile;
    private String outputFile;

    private PageGraph pageGraph;
    private PageRank pageRank;

    private List<String> links;
    private List<String> output;

    public static void main(String[] args) throws IOException {
        MainDriver mainDriver = new MainDriver();
        mainDriver.run();
    }

    public MainDriver(){
        pageGraph = new PageGraph();
        pageRank = new PageRank();

        stringFile = "links.txt";
        outputFile = "Output.txt";
    }

    public MainDriver(String directory, String outputFile) {
        this.stringFile = directory + "/" + "string.txt";
        this.outputFile = directory + "/" + outputFile;
    }

    public void run(){
        readFilesIntoMemory();
        buildPageGraph();
        runPageRank();
        createOutputFile(output);
    }

    private void readFilesIntoMemory(){

        StopWatch timer = new StopWatch();
        timer.start();

        links = FileUtils.fileToList(stringFile);

        timer.stop();
        System.out.println("Files reading time: " + timer.toString());
    }

    private void buildPageGraph(){
        pageGraph.build(links);
    }

    private void runPageRank(){

        StopWatch timer = new StopWatch();
        timer.start();

        int iterationsDone = pageRank.runPageRank(pageGraph, 0.85f, 20);

        timer.stop();

        System.out.println("PageRank finished after " + iterationsDone + " iterations; convergence time: " + timer.toString());
    }

    private void createOutputFile(List<String> output){

        StopWatch timer = new StopWatch();
        timer.start();

        FileUtils.listToFile(outputFile, pageGraph.getReportLines());

        timer.stop();
        System.out.println("Output file writing time: " + timer.toString());
    }
}