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
public class ScalingFactorTest {

    private String resourcesDirectoryPath = new File("src/test/resources").getAbsolutePath();

    public void runTest(String inputFileFolder, String inputFileName, String outputFileFolder, String outputFileName, float scalingFactor, int maxIterations){

        String inputFileFolderFullPath = resourcesDirectoryPath + "/" + inputFileFolder;
        String outputFileFolderFullPath = resourcesDirectoryPath + "/" + outputFileFolder;

        File outpuFileOnDisk = new File(inputFileFolderFullPath + "/" + outputFileName);
        outpuFileOnDisk.delete();

        MainDriver mainDriver = new MainDriver( inputFileFolderFullPath, inputFileName,
                                                outputFileFolderFullPath, outputFileName,
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
        runTest("data_files", "links.33k.avg.10.stdev.5.txt","scaling_factor_tests_output","size_33k_scaling_factor_0.55.output.txt", 0.55f, 100);
    }

    @Test
    public void inputFileWith33kLinksScalingFactor065Test(){
        runTest("data_files", "links.33k.avg.10.stdev.5.txt","scaling_factor_tests_output","size_33k_scaling_factor_0.65.output.txt", 0.65f, 100);
    }

    @Test
    public void inputFileWith33kLinksScalingFactor075Test(){
        runTest("data_files", "links.33k.avg.10.stdev.5.txt","scaling_factor_tests_output","size_33k_scaling_factor_0.75.output.txt", 0.75f, 100);
    }

    @Test
    public void inputFileWith33kLinksScalingFactor085Test(){
        runTest("data_files", "links.33k.avg.10.stdev.5.txt","scaling_factor_tests_output","size_33k_scaling_factor_0.85.output.txt", 0.85f, 100);
    }

    @Test
    public void inputFileWith33kLinksScalingFactor090Test(){
        runTest("data_files", "links.33k.avg.10.stdev.5.txt","scaling_factor_tests_output","size_33k_scaling_factor_0.90.output.txt", 0.90f, 100);
    }

    @Test
    public void inputFileWith33kLinksScalingFactor095Test(){
        runTest("data_files", "links.33k.avg.10.stdev.5.txt","scaling_factor_tests_output","size_33k_scaling_factor_0.95.output.txt", 0.95f, 100);
    }

    @Test
    public void inputFileWith33kLinksScalingFactor100Test(){
        runTest("data_files", "links.33k.avg.10.stdev.5.txt","scaling_factor_tests_output","size_33k_scaling_factor_100.output.txt", 100f, 100);
    }
}