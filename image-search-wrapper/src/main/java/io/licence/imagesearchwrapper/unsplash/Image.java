package io.licence.imagesearchwrapper.unsplash;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Image {

    private String id;
    private String description;
    private Integer width;
    private Integer height;
    private String color;
    private Links urls;

}
