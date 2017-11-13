package pagerank;

import java.util.ArrayList;
import java.util.List;

public class Page {

    private String name;
    private List<String> referencedPages;

    public Page(String name){
        this.name = name;
        referencedPages = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<String> getReferencedPages() {
        return referencedPages;
    }

    public void addReferencedPage(String page){
        referencedPages.add(page);
    }
}
