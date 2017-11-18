package pagerank;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.io.File;

/**
 * Created by dfingerman on 9/30/17.
 */
public class ScalingFactorTests {

    private String resourcesDirectoryPath = new File("src/test/resources").getAbsolutePath();

    public void runTest(String inputFileFolder, String outputFileFolder, float scalingFactor, int maxIterations){

        String inputFileFolderFullPath = resourcesDirectoryPath + "/" + inputFileFolder;
        String outputFileFolderFullPath = resourcesDirectoryPath + "/" + outputFileFolder;
        String outputFile = "Output.txt";

        File outpuFileOnDisk = new File(inputFileFolderFullPath + "/" + outputFile);
        outpuFileOnDisk.delete();

        MainDriver mainDriver = new MainDriver( inputFileFolderFullPath, "links.txt",
                                                outputFileFolderFullPath, outputFile,
                                                scalingFactor, maxIterations);
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
    public void inputFileWith33kLinksScalingFactor055Test(){
        runTest("test_33k_links", "scaling_factor_0.55", 0.55f, 100);
    }

    @Test
    public void inputFileWith33kLinksScalingFactor065Test(){
        runTest("test_33k_links", "scaling_factor_0.65", 0.65f, 100);
    }

    @Test
    public void inputFileWith33kLinksScalingFactor075Test(){
        runTest("test_33k_links", "scaling_factor_0.75", 0.75f, 100);
    }

    @Test
    public void inputFileWith33kLinksScalingFactor085Test(){
        runTest("test_33k_links", "scaling_factor_0.85", 0.85f, 100);
    }

    @Test
    public void inputFileWith33kLinksScalingFactor095Test(){
        runTest("test_33k_links", "scaling_factor_0.95", 0.95f, 100);
    }
}