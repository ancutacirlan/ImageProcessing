package io.licence.webapp.features.image;

import io.licence.webapp.config.communication.ApiClient;
import io.licence.webapp.config.communication.WebCommunicationConfig;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ImageService {

    private ImageRepository imageRepository;
    private ModelMapper modelMapper;
    private final ApiClient apiClient;
    private final WebCommunicationConfig webConfigurer;

    private static final String PATH_ID = "/images/save/id/";
    private static final String PATH_QUERY = "/images/save/query/";


    @Autowired
    public ImageService(ImageRepository imageRepository, ModelMapper modelMapper, ApiClient apiClient, WebCommunicationConfig webConfigurer) {
        this.imageRepository = imageRepository;
        this.modelMapper = modelMapper;
        this.apiClient = apiClient;
        this.webConfigurer = webConfigurer;
    }

    Image create(String idImage) {
        var variable = apiClient.invokeApi(PATH_ID+idImage,webConfigurer.getAuthorization());
        Image image = new Image();
        image.setUrl(variable);
        return imageRepository.save(image);
    }


    List<Image> getAll() {
        return imageRepository.findAll();
    }

    public List<Image> createAll(String query) {
        var variable = apiClient.invokeApiArray(PATH_QUERY+query,webConfigurer.getAuthorization());
        List<String> img = Arrays.asList(variable);
        return img.stream().map(item-> imageRepository.save(new Image(item))).collect(Collectors.toList());
    }
}
