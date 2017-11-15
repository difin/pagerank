package pagerank;

public class PageRank {

    public PageRank(){

    }

    public int runPageRank(PageGraph pageGraph, float dumpingFactor, int maxIterations){

        pageGraph.resetRankChangeFlags();

        for (int i=0; i<maxIterations; i++){
            for (Page page : pageGraph.getPages()){

                float rank = (1.0f - dumpingFactor);

                for (Page inboundPage : page.getInboundPages()){
                    rank = rank + dumpingFactor*inboundPage.getRank()/inboundPage.getNumberOfOutboundPages();
                }

                page.setRank(rank);
            }

            if (!pageGraph.isRanksChanged()){
                return i;
            }
        }

        return maxIterations;
    }
}
