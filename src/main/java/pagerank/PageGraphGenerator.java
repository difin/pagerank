package pagerank;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PageGraphGenerator {

    public PageGraphGenerator(){}

    public void generate(String outputFileName, int size, int averageNumberOfOutboundPages, int standardDeviationOutbounfPages){

        List<Page> pages = new ArrayList<>();
        Random randomGenerator = new Random();

        for (int i=0; i<size; i++){

            String name = RandomStringUtils.randomAlphabetic(10);
            Page page = new Page("www." + name + ".com");
            pages.add(page);
        }

        for (Page page : pages){

            int outboundPagesCount = (int)(randomGenerator.nextGaussian()*standardDeviationOutbounfPages + averageNumberOfOutboundPages);

            for (int j=0; j<outboundPagesCount; j++){

                int pageNumber = randomGenerator.nextInt(pages.size());

                if (page.getOutboundPages().contains(pages.get(pageNumber)) ||
                        page.getName().equals(pages.get(pageNumber).getName())){
                    j--;
                    continue;
                }

                page.addOutboundPage(pages.get(pageNumber));
            }
        }

        List<String> lines = new ArrayList<>();

        for (Page page : pages){
            for (Page outboundPage : page.getOutboundPages()){
                lines.add(page.getName() + ", " + outboundPage.getName());
            }
        }

        FileUtils.listToFile(outputFileName, lines);
    }

    public static void main(String[] args){
        PageGraphGenerator pageGraphGenerator = new PageGraphGenerator();
        pageGraphGenerator.generate("src/test/resources/test_data/links.100k.txt",
                100000,
                10,
                5 );
    }
}
