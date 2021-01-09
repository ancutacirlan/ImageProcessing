package io.licence.imagesearchwrapper.unsplash;

import java.util.List;

public class Results {

    List<Image> results;

    public Results(List<Image> results) {
        this.results = results;
    }

    public Results() {
    }

    public List<Image> getResults() {
        return results;
    }

    public void setResults(List<Image> results) {
        this.results = results;
    }
}
