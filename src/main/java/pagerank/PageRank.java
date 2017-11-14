package pagerank;

public class PageRank {

    public PageRank(){

    }

    public void runPageRank(PageGraph pageGraph, float dumpingFactor, int iterations){

        for (int i=0; i<iterations; i++){
            for (Page page : pageGraph.getPages()){

                float rank = (1-dumpingFactor);

                for (String referencedPageName : page.getReferencedPageNames()){
                    Page referencedPage = pageGraph.getPage(referencedPageName);
                    rank = rank + dumpingFactor*referencedPage.getRank()/referencedPage.getNumberOfReferencedPages();
                }

                page.setRank(rank);
            }
        }
    }
}
