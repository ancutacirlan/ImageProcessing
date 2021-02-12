package io.licence.imageformater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequestMapping("/images")
public class ImageController {

    private ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping(path = "/crop", produces = MediaType.APPLICATION_JSON_VALUE,params={"pathImage","x","y","width","height"})
    public String getCropImage(@RequestParam(value = "pathImage") String pathImage, @RequestParam(value = "x") int x,
                               @RequestParam(value = "y") int y, @RequestParam(value = "width") int width,
                               @RequestParam(value = "height") int height) throws IOException {
        return imageService.crop(pathImage,x,y,width,height);
    }


    @GetMapping(path = "/rotate", produces = MediaType.APPLICATION_JSON_VALUE,params={"pathImage","angle"})
    public String getRotateImage(@RequestParam(value = "pathImage") String pathImage,
                                 @RequestParam(value = "angle") int angle ) throws IOException {
        return imageService.rotate(pathImage,angle);
    }

    @GetMapping(path = "/resize", produces = MediaType.APPLICATION_JSON_VALUE,params={"pathImage","width","height"})
    public String getResizeImage(@RequestParam(value = "pathImage") String pathImage,
                                 @RequestParam(value = "width") int width,
                                 @RequestParam(value = "height") int height ) throws IOException {
        return imageService.resize(pathImage,width,height);
    }

    @GetMapping(path = "/gray/filter", produces = MediaType.APPLICATION_JSON_VALUE,params={"pathImage"})
    public String getGrayFilter(@RequestParam(value = "pathImage") String pathImage) throws IOException {
        return imageService.grayFilter(pathImage);

    }

    @GetMapping(path = "/sepia/filter", produces = MediaType.APPLICATION_JSON_VALUE,params={"pathImage"})
    public String getSepiaFilter(@RequestParam(value = "pathImage") String pathImage) throws IOException {
        return imageService.sepiaFilter(pathImage);

    }

    @GetMapping(path = "/green/filter", produces = MediaType.APPLICATION_JSON_VALUE,params={"pathImage"})
    public String getGreenFilter(@RequestParam(value = "pathImage") String pathImage) throws IOException {
        return imageService.greenFilter(pathImage);

    }

    @GetMapping(path = "/red/filter", produces = MediaType.APPLICATION_JSON_VALUE,params={"pathImage"})
    public String getRedFilter(@RequestParam(value = "pathImage") String pathImage) throws IOException {
        return imageService.redFilter(pathImage);

    }

    @GetMapping(path = "/blue/filter", produces = MediaType.APPLICATION_JSON_VALUE,params={"pathImage"})
    public String getBlueFilter(@RequestParam(value = "pathImage") String pathImage) throws IOException {
        return imageService.blueFilter(pathImage);

    }

    @GetMapping(path = "/negative/filter", produces = MediaType.APPLICATION_JSON_VALUE,params={"pathImage"})
    public String getNegativeFilter(@RequestParam(value = "pathImage") String pathImage) throws IOException {
        return imageService.negativeFilter(pathImage);

    }

    @GetMapping(path = "/mirror/two/filter", produces = MediaType.APPLICATION_JSON_VALUE,params={"pathImage"})
    public String getMirrorTwoFilter(@RequestParam(value = "pathImage") String pathImage) throws IOException {
        return imageService.mirrorTwoFilter(pathImage);

    }

    @GetMapping(path = "/mirror/one/filter", produces = MediaType.APPLICATION_JSON_VALUE,params={"pathImage"})
    public String getMirrorOneFilter(@RequestParam(value = "pathImage") String pathImage) throws IOException {
        return imageService.mirrorOneFilter(pathImage);

    }

    @GetMapping(path = "/blur/filter", produces = MediaType.APPLICATION_JSON_VALUE,params={"pathImage","intensity"})
    public String getBlurImage(@RequestParam(value = "pathImage") String pathImage,
                               @RequestParam(value = "intensity") int intensity) throws IOException {
        return imageService.blurImage(pathImage,intensity);

    }

    @GetMapping(path = "/brightness/filter", produces = MediaType.APPLICATION_JSON_VALUE,params={"pathImage","alpha","beta"})
    public String getBlurImage(@RequestParam(value = "pathImage") String pathImage,
                               @RequestParam(value = "alpha") int alpha,
                               @RequestParam(value = "beta") int beta) throws IOException {
        return imageService.brightness(pathImage,alpha,beta);

    }

    @GetMapping(path = "/sharped/filter", produces = MediaType.APPLICATION_JSON_VALUE,params={"pathImage"})
    public String getShapedImage(@RequestParam(value = "pathImage") String pathImage) throws IOException {
        return imageService.sharped(pathImage);

    }

    @GetMapping(path = "/dilatation/filter", produces = MediaType.APPLICATION_JSON_VALUE,params={"pathImage","increase"})
    public String getDilatationImage(@RequestParam(value = "pathImage") String pathImage,
                                     @RequestParam(value = "increase") int increase) throws IOException {
        return imageService.dilatation(pathImage,increase);

    }

    @GetMapping(path = "/erosion/filter", produces = MediaType.APPLICATION_JSON_VALUE,params={"pathImage","increase"})
    public String getErosionImage(@RequestParam(value = "pathImage") String pathImage, @RequestParam(value = "increase") int increase) throws IOException {
        return imageService.erosion(pathImage,increase);

    }

    @GetMapping(path = "/phat/filter", produces = MediaType.APPLICATION_JSON_VALUE,params={"pathImage","increase"})
    public String getToPhatImage(@RequestParam(value = "pathImage") String pathImage,
                                 @RequestParam(value = "increase") int increase) throws IOException {
        return imageService.phat(pathImage,increase);

    }

    @GetMapping(path = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public String hello() {
        return "Hello";

    }


}
