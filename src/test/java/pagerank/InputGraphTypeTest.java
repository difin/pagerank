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
public class InputGraphTypeTest {

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
    public void inboundDegreeOneOutboundDegreeOneTest(){
        runTest("data_files", "inbound_1_outbound_1" ,"input_graph_type_test_output",  "inbound_1_outbound_1.output.txt",0.85f, 100);
    }

    @Test
    public void allNodesAreDanglingTest(){
        runTest("data_files", "all_nodes_dangling","input_graph_type_test_output",  "all_nodes_dangling.output.txt",0.85f, 100);
    }


    @Test
    public void inboundDegreeNMinusOneOutboundDegreeMinusOneTest(){
        runTest("data_files", "inbound_n-1_outbound_n-1","input_graph_type_test_output",  "inbound_n-1_outbound_n-1.output.txt",0.85f, 100);
    }
}