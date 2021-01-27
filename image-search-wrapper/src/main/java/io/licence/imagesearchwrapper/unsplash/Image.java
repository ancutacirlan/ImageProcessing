package io.licence.imagesearchwrapper.unsplash;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Image {

    private String id;
    private Integer width;
    private Integer height;
    private String color;
    private Links urls;

}
