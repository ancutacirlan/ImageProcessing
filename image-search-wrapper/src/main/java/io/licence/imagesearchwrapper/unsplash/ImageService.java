package io.licence.imagesearchwrapper.unsplash;

import io.licence.imagesearchwrapper.amazons3.S3Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

@Service
public class ImageService {

    @Value("${unsplash.access.key}")
    private String apiKey;

    @Value("${unsplash.url}")
    private String url;

    private final RestTemplate restTemplate;
    private final S3Services s3Services;

    @Autowired
    public ImageService(RestTemplate restTemplate, S3Services s3Services) {
        this.restTemplate = restTemplate;
        this.s3Services = s3Services;
    }

    Results getImageInfo(String query) {
        return restTemplate.getForObject(url + "/search/photos?query=" + query + "&client_id=" + apiKey, Results.class);
    }

    Image getImageById(String imageId) {
        return restTemplate.getForObject(url + "/photos/" + imageId + "?client_id=" + apiKey, Image.class);
    }

    String saveImages(String query) throws IOException {
        Image image = getImageById(query);

        String url = image.getUrls().getRaw();
        URL imageUrl = new URL(url);
        BufferedImage img = ImageIO.read(imageUrl);
        File file = new File("unsplash.jpg");
        ImageIO.write(img, "jpg", file);

        //MultipartFile multipartFile = new MockMultipartFile("test.xlsx", new FileInputStream(new File("/home/admin/test.xlsx")));

       // s3Services.uploadFile(file);
        return new String("fse");
    }

}
