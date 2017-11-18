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
public class InputSizeTests {

    private String resourcesDirectoryPath = new File("src/test/resources").getAbsolutePath();

    public void runTest(String testCaseFolder, float scalingFactor, int maxIterations){

        String testCaseFolderFullPath = resourcesDirectoryPath + "/" + testCaseFolder;
        String outputFile = "Output.txt";

        File outpuFileOnDisk = new File(testCaseFolderFullPath + "/" + outputFile);
        outpuFileOnDisk.delete();

        MainDriver mainDriver = new MainDriver(testCaseFolderFullPath, "links.txt", outputFile, scalingFactor, maxIterations);
        mainDriver.run();
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
    public void inputFileWith33kLinksTest(){
        runTest("test_33k_links", 0.85f, 100);
    }

    @Test
    public void inputFileWith67kLinksTest(){
        runTest("test_67k_links", 0.85f, 100);
    }

    @Test
    public void inputFileWith100kLinksTest(){
        runTest("test_100k_links", 0.85f, 100);
    }
}