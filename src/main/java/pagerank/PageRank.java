package pagerank;

public class PageRank {

    public PageRank(){

    }

    public void runPageRank(PageGraph pageGraph, float dumpingFactor, int iterations){

        for (int i=0; i<iterations; i++){
            for (Page page : pageGraph.getPages()){

                float rank = (1.0f - dumpingFactor);

                for (Page inboundPage : page.getInboundPages()){
                    rank = rank + dumpingFactor*inboundPage.getRank()/inboundPage.getNumberOfOutboundPages();
                }

                page.setRank(rank);
            }
        }
    }
}
