package pagerank;

import java.util.ArrayList;
import java.util.List;

public class Page {

    private String name;
    private List<String> referencedPageNames;
    private float rank;

    public Page(String name){
        this.name = name;
        referencedPageNames = new ArrayList<>();
        rank = 1.0f;
    }

    public String getName() {
        return name;
    }

    public List<String> getReferencedPageNames() {
        return referencedPageNames;
    }

    public void addReferencedPage(String page){
        referencedPageNames.add(page);
    }

    public float getRank() {
        return rank;
    }

    public void setRank(float rank) {
        this.rank = rank;
    }

    public int getNumberOfReferencedPages(){
        return referencedPageNames.size();
    }
}
