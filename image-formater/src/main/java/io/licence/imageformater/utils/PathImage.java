package io.licence.imageformater.utils;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PathImage implements Serializable {

    String url;
    public PathImage(@JsonProperty("url") String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "pathImage [url=" + url + "]";
    }
}
