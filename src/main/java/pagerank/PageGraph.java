package pagerank;

import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class PageGraph {

    private Map<String, Page> pages;

    public PageGraph(){
        pages = new HashMap<>();
    }

    public void build(List<Pair<String, String>> links){

        for (Pair<String, String> link : links){

            String pageName = link.getLeft();
            String outboundPageName = link.getRight();

            if (!pages.containsKey(outboundPageName)){
                Page outboundPage = new Page(outboundPageName);
                pages.put(outboundPageName, outboundPage);
            }

            if (!pages.containsKey(pageName)){
                Page page = new Page(pageName);
                pages.put(pageName, page);
            }

            pages.get(pageName).addOutboundPage(pages.get(outboundPageName));
            pages.get(outboundPageName).addInboundPage(pages.get(pageName));
        }

        List<Page> danglingPages = findDanglingPages();

        for (Page danglingPage : danglingPages){
            for (Page page : getPages()){
                danglingPage.addOutboundPage(page);
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
            String line = String.format("%s, %f", page.getName(), page.getRank());
            output.add(line);
        }

        output.sort(new OutputLinesComparator());

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

    public class OutputLinesComparator implements Comparator<String>{

        @Override
        public int compare(String o1, String o2) {

            float rank1 = Float.parseFloat(o1.split(",")[1].trim());
            float rank2 = Float.parseFloat(o2.split(",")[1].trim());

            return rank2 < rank1 ? -1 : rank2 == rank1 ? 0 : 1;
        }
    }

    public void resetRankChangeFlags(){

        for (Page page : getPages()){
            page.setRankChanged(true);
        }
    }

    public boolean isRanksChanged(){

        for (Page page : getPages()){
            if (page.isRankChanged()){
                return true;
            }
        }

        return false;
    }
}
