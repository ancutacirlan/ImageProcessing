package io.licence.webapp.features.folder;

import org.modelmapper.PropertyMap;

public class Utils {

    private Utils() {
    }

    static PropertyMap<FolderDto, Folder> mapping = new PropertyMap<>() {
        @Override
        protected void configure() {
            map().getUserId().setId(source.getUserId());
        }
    };

    static PropertyMap<Folder, FolderDto> fieldMapping = new PropertyMap<>() {
        @Override
        protected void configure() {
            map().setUserId(source.getUserId().getId());
        }
    };

}

