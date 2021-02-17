package io.licence.webapp.features.folder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FolderDto {

    private Integer id;
    private Long userId;
    private String name;

}
