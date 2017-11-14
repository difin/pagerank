package pagerank;

import java.util.ArrayList;
import java.util.List;

public class Page {

    private String name;
    private List<Page> outboundPages;
    private float rank;

    public Page(String name){
        this.name = name;
        outboundPages = new ArrayList<>();
        rank = 1.0f;
    }

    public String getName() {
        return name;
    }

    public List<Page> getOutboundPages() {
        return outboundPages;
    }

    public void addOutboundPage(Page page){
        outboundPages.add(page);
    }

    public float getRank() {
        return rank;
    }

    public void setRank(float rank) {
        this.rank = rank;
    }

    public int getNumberOfOutboundPages(){
        return outboundPages.size();
    }
}
