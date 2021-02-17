package io.licence.webapp.features.image;

import io.licence.webapp.config.communication.ApiClient;
import io.licence.webapp.config.communication.WebCommunicationConfig;
import io.licence.webapp.features.folder.Folder;
import io.licence.webapp.features.folder.FolderDto;
import io.licence.webapp.utils.unsplash.Results;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ImageService implements ImageContract {

    private final ImageRepository imageRepository;
    private final ApiClient apiClient;
    private final ModelMapper modelMapper;
    private final WebCommunicationConfig webConfigurer;
    private final ImageContract imageContract;
    private static final String PATH_ID = "/images/save/id/";
    private static final String PATH_QUERY = "/images/save/query/";
    private static final String PATH_SEARCH_QUERY = "images/search/";



    @Autowired
    public ImageService(ImageRepository imageRepository, ModelMapper modelMapper, ApiClient apiClient,
                        WebCommunicationConfig webConfigurer, @Lazy ImageContract imageContract) {
        this.imageRepository = imageRepository;
        this.apiClient = apiClient;
        this.modelMapper = modelMapper;
        this.webConfigurer = webConfigurer;
        this.imageContract = imageContract;
        this.modelMapper.addMappings(Utils.fieldMapping);
        this.modelMapper.addMappings(Utils.mapping);
    }

    Results search (String query){
        return apiClient.invokeApiResults(PATH_SEARCH_QUERY+query,webConfigurer.getAuthorization());
    }

    String saveImageFromUnsplash(String idImage) {
        var url = apiClient.invokeApi(PATH_ID+idImage,webConfigurer.getAuthorization());
        return url;
    }

    ImageDto create(ImageDto dto) throws IOException {
        Image image =modelMapper.map(dto,Image.class);
        BufferedImage originalImage = ImageIO.read(new URL(image.getUrl()));
        image.setHeight(originalImage.getHeight());
        image.setWidth(originalImage.getWidth());
        return modelMapper.map(imageRepository.save(image), ImageDto.class);
    }


    List<ImageDto> getAll() {
        return imageRepository
                .findAllByIsDeletedFalse()
                .stream()
                .map(item -> modelMapper.map(item, ImageDto.class))
                .collect(Collectors.toList());
    }

    ImageDto getById(@NotNull Integer id) {
        return imageRepository
                .findById(id)
                .map(result -> modelMapper.map(result, ImageDto.class))
                .orElseThrow(EntityNotFoundException::new);
    }


    List<ImageDto> getByName(@NotNull String name) {
        return imageRepository
                .findByNameAndIsDeletedFalse(name)
                .stream()
                .map(item -> modelMapper.map(item, ImageDto.class))
                .collect(Collectors.toList());
    }

    ImageDto update(@NotNull ImageDto dto) {
        return imageRepository
                .findById(dto.getId())
                .map(result -> {
                    Image toBeUpdated = modelMapper.map(dto, Image.class);
                    BufferedImage originalImage = null;
                    try {
                        originalImage = ImageIO.read(new URL(toBeUpdated.getUrl()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    toBeUpdated.setHeight(originalImage.getHeight());
                    toBeUpdated.setWidth(originalImage.getWidth());
                    return modelMapper.map(imageRepository.save(toBeUpdated), ImageDto.class);
                })
                .orElseThrow(EntityNotFoundException::new);
    }

    Boolean delete(@NotNull Integer id) {
        return imageRepository
                .findById(id)
                .map(result -> {
                    result.setIsDeleted(true);
                    return imageRepository.save(result).getIsDeleted();
                })
                .orElseThrow(EntityNotFoundException::new);
    }

//    public List<Image> createAll(String query) {
//        var variable = apiClient.invokeApiArray(PATH_QUERY+query,webConfigurer.getAuthorization());
//        List<String> img = Arrays.asList(variable);
//        return img.stream().map(item-> imageRepository.save(new Image(item))).collect(Collectors.toList());
//    }

    @Override
    public Image getImageById(@NotNull Integer id) {
        return imageRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}
