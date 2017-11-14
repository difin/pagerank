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
            String outboundPageName = link.split(",")[1].trim();

            if (!pages.containsKey(outboundPageName)){
                Page referencedPage = new Page(outboundPageName);
                pages.put(outboundPageName, referencedPage);
            }

            if (pages.containsKey(pageName)){
                pages.get(pageName).addOutboundPage(pages.get(outboundPageName));
            }
            else{
                Page page = new Page(pageName);
                page.addOutboundPage(pages.get(outboundPageName));
                pages.put(pageName, page);
            }
        }

        List<Page> danglingPages = findDanglingPages();

        for (Page danglingPage : danglingPages){
            for (Page page : getPages()){
                if (!danglingPage.getName().equals(page.getName())){
                    danglingPage.addOutboundPage(page);
                }
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

    private List<Page> findDanglingPages(){

        List<Page> output = new ArrayList<>();

        for (Page page : getPages()){
            if (page.getNumberOfOutboundPages() == 0){
                output.add(page);
            }
        }

        return output;
    }
}
