package pagerank;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dfingerman on 9/23/17.
 */
public class MainDriver {

    private static final float COMPARISON_PRECISION = 0.0001f;
    private String stringFile;
    private String outputFile;

    private PageGraph pageGraph;
    private PageRank pageRank;

    private float scalingFactor;
    private int maxIterations;

    private List<String> links;
    private List<Pair<String, String>> parsedLinks;
    private List<String> output;

    public static void main(String[] args) throws IOException {
        MainDriver mainDriver = new MainDriver();
        mainDriver.run();
    }

    public MainDriver(){

        pageGraph = new PageGraph();
        pageRank = new PageRank();

        scalingFactor = 0.85f;
        maxIterations = 20;

        stringFile = "links.txt";
        outputFile = "Output.txt";
    }

    public MainDriver(String directory, String inputFileName, String outputFileName, float scalingFactor, int maxIterations) {

        pageGraph = new PageGraph();
        pageRank = new PageRank();

        this.scalingFactor = scalingFactor;
        this.maxIterations = maxIterations;

        this.stringFile = directory + "/" + inputFileName;
        this.outputFile = directory + "/" + outputFileName;
    }

    public void run(){
        parseInputFile();
        buildPageGraph();
        runPageRank();
        createOutputFile(output);
    }

    private void parseInputFile(){

        StopWatch timer = new StopWatch();
        timer.start();

        links = FileUtils.fileToList(stringFile);
        parsedLinks = new ArrayList<>();

        for (String link : links){

            String page1 = link.split(",")[0].trim();
            String page2 = link.split(",")[1].trim();

            Pair<String, String> pair = new ImmutablePair(page1, page2);
            parsedLinks.add(pair);
        }

        timer.stop();
        System.out.println("Input file parsing time: " + timer.toString());
    }

    private void buildPageGraph(){

        StopWatch timer = new StopWatch();
        timer.start();

        pageGraph.build(parsedLinks);

        timer.stop();

        System.out.println("Page Graph build time: " + timer.toString());
    }

    private void runPageRank(){

        StopWatch timer = new StopWatch();
        timer.start();

        int iterationsDone = pageRank.runPageRank(pageGraph, scalingFactor, maxIterations);

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

    public void verifyOutputEquality(List<Pair<String, Float>> pairs1, List<Pair<String, Float>> pairs2) {

        if (pairs1.size() != pairs2.size()){
            throw new RuntimeException("Output lists have differrent length");
        }

        for (int i=0; i<pairs1.size(); i++){
            Pair<String, Float> pair1 = pairs1.get(i);
            Pair<String, Float> pair2 = pairs2.get(i);

            if (!pair1.getLeft().equals(pair2.getLeft())){
                throw new RuntimeException("Page ranking order mismatch on index " + i +
                                            " page in output 1: " + pair1.getLeft() +
                                            " page in output 2: " + pair2.getLeft());
            }

            if (Math.abs(pair1.getRight() - pair2.getRight()) > COMPARISON_PRECISION){
                throw new RuntimeException("Page ranking value mismatch on index " + i +
                        " page in output 1: " + pair1.getLeft() + " ranking: " + pair1.getRight() +
                        " page in output 2: " + pair2.getLeft() + " ranking: " + pair2.getRight());
            }
        }
    }
}