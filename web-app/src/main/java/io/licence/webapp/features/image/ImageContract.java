package io.licence.webapp.features.image;

import javax.validation.constraints.NotNull;

public interface ImageContract {

    Image getImageById(@NotNull Integer id);

}
