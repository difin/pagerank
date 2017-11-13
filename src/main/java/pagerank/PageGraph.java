package pagerank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageGraph {

    private Map<String, Page> pages;

    public PageGraph(){
        pages = new HashMap<>();
    }

    public void build(List<String> links){

        for (String link : links){

            String pageName = link.split(",")[0].trim();
            String referencedPageName = link.split(",")[0].trim();

            if (pages.containsKey(pageName)){
                pages.get(pageName).addReferencedPage(referencedPageName);
            }
            else{
                Page page = new Page(pageName);
                page.addReferencedPage(referencedPageName);
                pages.put(pageName, page);
            }
        }
    }
}
