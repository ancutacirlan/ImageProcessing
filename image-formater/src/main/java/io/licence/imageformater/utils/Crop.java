package io.licence.imageformater.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Crop implements Serializable {

    String pathImage;
    int x;
    int y;
    int width;
    int height;

    public Crop(@JsonProperty("pathImage") String pathImage, @JsonProperty("x")int x,
                @JsonProperty("y")int y, @JsonProperty("width")int width,
                @JsonProperty("height")int height) {
        this.pathImage = pathImage;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public String toString() {
        return "Crop [pathImage=" + pathImage + ", x=" + x +", y=" + y +", width=" + width +", height=" + height + "]";
    }
}
