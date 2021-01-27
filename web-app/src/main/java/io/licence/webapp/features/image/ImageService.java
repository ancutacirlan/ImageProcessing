package io.licence.webapp.features.image;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class ImageService {

    private ImageRepository imageRepository;
    private ModelMapper modelMapper;
    private RestTemplate restTemplate;

    @Autowired
    public ImageService(ImageRepository imageRepository, ModelMapper modelMapper, RestTemplate restTemplate) {
        this.imageRepository = imageRepository;
        this.modelMapper = modelMapper;
        this.restTemplate = restTemplate;
    }

    Image create(String idImage) {
        var variable = restTemplate.getForObject("http://localhost:8082/images/save/id/" + idImage, String.class);
        Image image = new Image();
        image.setUrl(variable);
        return imageRepository.save(image);
    }


    List<Image> getAll() {
        return imageRepository.findAll();
    }

    public List<Image> createAll(String query) {
        var variable = restTemplate.getForObject("http://localhost:8082/images/save/query/" + query, String[].class);
        List<String> img = Arrays.asList(variable);
        return img.stream().map(item-> imageRepository.save(new Image(item))).collect(Collectors.toList());
    }
}
