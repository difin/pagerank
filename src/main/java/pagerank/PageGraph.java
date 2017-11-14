package pagerank;

import java.util.*;

public class PageGraph {

    private Map<String, Page> pages;

    public PageGraph(){
        pages = new HashMap<>();
    }

    public void build(List<String> links){

        for (String link : links){

            String pageName = link.split(",")[0].trim();
            String referencedPageName = link.split(",")[1].trim();

            if (!pages.containsKey(referencedPageName)){
                Page referencedPage = new Page(referencedPageName);
                pages.put(referencedPageName, referencedPage);
            }

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

    public Collection<Page> getPages(){
        return pages.values();
    }

    public Page getPage(String name){
        return pages.get(name);
    }

    public List<String> getReportLines(){

        List<String> output = new ArrayList<>();

        for (Page page : getPages()){
            String line = page.getName() + ", " + page.getRank();
            output.add(line);
        }

        return output;
    }
}
