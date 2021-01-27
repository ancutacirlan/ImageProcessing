package io.licence.imagesearchwrapper.unsplash;

import com.amazonaws.util.IOUtils;
import io.licence.imagesearchwrapper.amazon_s3_storage.AmazonClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageService {

    @Value("${unsplash.access.key}")
    private String apiKey;

    @Value("${unsplash.url}")
    private String url;

    private static final String LOCAL_PATH = "src/main/resources/";


    private final RestTemplate restTemplate;
    private final AmazonClientService amazonClientService;

    @Autowired
    public ImageService(RestTemplate restTemplate, AmazonClientService amazonClientService) {
        this.restTemplate = restTemplate;
        this.amazonClientService = amazonClientService;

    }

    Results getImageInfo(String query) {
        return restTemplate.getForObject(url + "/search/photos?query=" + query + "&client_id=" + apiKey, Results.class);
    }


    Image getImageById(String imageId) {
         return restTemplate.getForObject(url + "/photos/" + imageId + "?client_id=" + apiKey, Image.class);
    }


    String saveImages(String imageId) throws IOException {
        Image image = getImageById(imageId);
        String pathname = LOCAL_PATH+ imageId;
        String url = image.getUrls().getRaw();
        URL imageUrl = new URL(url);
        BufferedImage img = ImageIO.read(imageUrl);
        File file = new File(pathname);
        ImageIO.write(img, "jpg", file);

        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file",
                file.getName(), "text/plain", IOUtils.toByteArray(input));
        var variable = amazonClientService.uploadFile(multipartFile);
        file.delete();
        return variable;
    }

    List<String> saveAllImages(String query){
        Results images=  getImageInfo(query);
        return images.results.stream().map(image -> {
            try {
                return saveImages(image.getId());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
    }




}
