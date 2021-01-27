package io.licence.webapp.features.image;

import lombok.Data;

import java.util.List;

@Data
public class StringList {

    private List<String> images;

    public StringList(List<String> images) {
        this.images = images;
    }
}