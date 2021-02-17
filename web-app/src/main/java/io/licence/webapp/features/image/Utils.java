package io.licence.webapp.features.image;

import io.licence.webapp.features.folder.Folder;
import io.licence.webapp.features.folder.FolderDto;
import org.modelmapper.PropertyMap;

public class Utils {

    private Utils() {
    }

    static PropertyMap<ImageDto, Image> mapping = new PropertyMap<>() {
        @Override
        protected void configure() {
            map().getUserId().setId(source.getUserId());
            map().getFolderId().setId(source.getFolderId());
        }
    };

    static PropertyMap<Image, ImageDto> fieldMapping = new PropertyMap<>() {
        @Override
        protected void configure() {
            map().setUserId(source.getUserId().getId());
            map().setFolderId(source.getFolderId().getId());
        }
    };

}

