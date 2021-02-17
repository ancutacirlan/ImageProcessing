package io.licence.webapp.features.image;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.licence.webapp.features.folder.Folder;
import io.licence.webapp.features.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ImageDto {

    private Integer id;

    private Image originalImage;

    private Long userId;

    private Integer folderId;

    private String url;

    private String name;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer width;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer height;

}
