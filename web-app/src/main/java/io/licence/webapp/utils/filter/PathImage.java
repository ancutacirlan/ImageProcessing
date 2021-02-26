package io.licence.webapp.utils;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PathImage implements Serializable {

    String pathImage;
    public PathImage(@JsonProperty("pathImage") String pathImage) {
        this.pathImage = pathImage;
    }

    @Override
    public String toString() {
        return "pathImage [pathImage=" + pathImage + "]";
    }

}
