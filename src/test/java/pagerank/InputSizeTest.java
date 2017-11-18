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
public class InputSizeTest {

    private String resourcesDirectoryPath = new File("src/test/resources").getAbsolutePath();

    public void runTest(String inputFileFolder, String inputFileName, String outputFileFolder, float scalingFactor, int maxIterations){

        String inputFileFolderFullPath = resourcesDirectoryPath + "/" + inputFileFolder;
        String outputFileFolderFullPath = resourcesDirectoryPath + "/" + outputFileFolder;
        String outputFile = "Output.txt";

        File outpuFileOnDisk = new File(inputFileFolderFullPath + "/" + outputFile);
        outpuFileOnDisk.delete();

        MainDriver mainDriver = new MainDriver( inputFileFolderFullPath, inputFileName,
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
    public void inputFileWith33kLinksTest(){
        runTest("data_files", "links.33k.avg.10.stdev.5.txt","size_33k_scaling_factor_0.85", 0.85f, 100);
    }

    @Test
    public void inputFileWith67kLinksTest(){
        runTest("data_files", "links.67k.avg.10.stdev.5.txt","size_67k_scaling_factor_0.85", 0.85f, 100);
    }

    @Test
    public void inputFileWith100kLinksTest(){
        runTest("data_files", "links.100k.avg.10.stdev.5.txt","size_100k_scaling_factor_0.85", 0.85f, 100);
    }
}