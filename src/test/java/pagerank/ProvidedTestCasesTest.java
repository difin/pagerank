package pagerank;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dfingerman on 9/30/17.
 */
public class ProvidedTestCasesTest {

    private String resourcesDirectoryPath = new File("src/test/resources").getAbsolutePath();

    private List<Pair<String, Float>> readOutputFromFile(String directory, String fileName){
        String outputFile = directory + "/" + fileName;
        List<Pair<String, Float>> output = new ArrayList<>();

        List<String> lines = FileUtils.fileToList(outputFile);

        for (String line : lines){

            String page = line.split(",")[0].trim();
            Float rank = Float.parseFloat(line.split(",")[1].trim());

            Pair<String, Float> pair = new ImmutablePair(page, rank);
            output.add(pair);
        }

        return output;
    }

    public void runTest(String testCaseFolder, float scalingFactor, int maxIterations){

        String testCaseFolderFullPath = resourcesDirectoryPath + "/" + testCaseFolder;
        String outputFile = "myOutput.txt";
        String referenceOutputFile = "Output.txt";

        File outpuFileOnDisk = new File(testCaseFolderFullPath + "/" + outputFile);
        outpuFileOnDisk.delete();

        MainDriver mainDriver = new MainDriver(testCaseFolderFullPath, "links.txt", outputFile, scalingFactor, maxIterations);
        mainDriver.run();

        mainDriver.verifyOutputEquality(
                readOutputFromFile(testCaseFolderFullPath, outputFile),
                readOutputFromFile(testCaseFolderFullPath, referenceOutputFile)
        );

        outpuFileOnDisk.delete();
    }

    @Rule
    public TestRule watcher = new TestWatcher() {
        protected void starting(Description description) {
            System.out.println("-------------------------------------------------------");
            System.out.println("\tStarting test: " + description.getMethodName());
            System.out.println("-------------------------------------------------------");
        }
    };

    @Test
    public void testCase1(){
        runTest("test_case1", 0.85f, 20);
    }

    @Test
    public void testCase2(){
        runTest("test_case2", 0.65f, 20);
    }
}