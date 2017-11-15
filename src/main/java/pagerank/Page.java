package pagerank;

import java.util.ArrayList;
import java.util.List;

public class Page {

    private String name;
    private List<Page> outboundPages;
    private List<Page> inboundPages;
    private float rank;
    private boolean rankChanged;

    public Page(String name){
        this.name = name;
        outboundPages = new ArrayList<>();
        inboundPages = new ArrayList<>();
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

        if (this.rank == rank){
            rankChanged = false;
        }
        else{
            rankChanged = true;
        }

        this.rank = rank;
    }

    public int getNumberOfOutboundPages(){
        return outboundPages.size();
    }

    public List<Page> getInboundPages() {
        return inboundPages;
    }

    public void addInboundPage(Page inboundPage) {
        inboundPages.add(inboundPage);
    }

    public boolean isRankChanged() {
        return rankChanged;
    }

    public void setRankChanged(boolean rankChanged) {
        this.rankChanged = rankChanged;
    }
}
