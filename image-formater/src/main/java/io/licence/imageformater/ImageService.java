package io.licence.imageformater;

import io.licence.imageformater.communication.ApiClientImg;
import io.licence.imageformater.communication.WebCommunicationConfigImg;
import nu.pattern.OpenCV;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

@Service
public class ImageService {

    private final ApiClientImg apiClient;
    private final WebCommunicationConfigImg webConfigurer;

    private static final String LOCAL_PATH = "src/main/resources/";

    @Autowired
    public ImageService(ApiClientImg apiClient, WebCommunicationConfigImg webConfigurer) {
        this.apiClient = apiClient;
        this.webConfigurer = webConfigurer;
    }

    private static final String PATH = "/images/modified/image/";


    String crop(String pathImage, int x, int y, int width, int height) throws IOException {

        long startTimereadImage = System.currentTimeMillis();
        BufferedImage originalImage = ImageIO.read(new URL(pathImage));
        long endTimereadImage = System.currentTimeMillis();
        long startTimeconvertImage = System.currentTimeMillis();
        javaxt.io.Image image = new javaxt.io.Image(originalImage);
        long endTimeconvertImage = System.currentTimeMillis();
        long startTimecropImage = System.currentTimeMillis();
        image.crop(x, y, width, height);
        long endTimecropImage = System.currentTimeMillis();
        long startTimesavelocalImage = System.currentTimeMillis();
        image.saveAs(LOCAL_PATH + "crop.png");
        long endTimesaveLocalImage = System.currentTimeMillis();
        long startTimesaveBucketImage = System.currentTimeMillis();
        var variable = apiClient.invokeApi(PATH + "crop.png", webConfigurer.getAuthorization());
        long endTimesaveBucketImage = System.currentTimeMillis();
        System.out.println(endTimereadImage - startTimereadImage + ": time read image");
        System.out.println(endTimeconvertImage - startTimeconvertImage + ": time convert image");
        System.out.println(endTimecropImage - startTimecropImage + ": time crop image");
        System.out.println(endTimesaveLocalImage - startTimesavelocalImage + ": time save local image");
        System.out.println(endTimesaveBucketImage - startTimesaveBucketImage + ": time save bucket image");
        return variable;

    }

    String rotate(String pathImage, int angle) throws IOException {

        OpenCV.loadShared();
        long startTimereadImage = System.currentTimeMillis();
        BufferedImage originalImage = ImageIO.read(new URL(pathImage));
        long endTimereadImage = System.currentTimeMillis();
        long startTimeconvertImage = System.currentTimeMillis();
        Mat src = bufferedImageToMat(originalImage);
        long endTimeconvertImage = System.currentTimeMillis();
        Mat dst = new Mat(src.rows(), src.cols(), src.type());
        long startTimerotateImage = System.currentTimeMillis();
        Point center = new Point(dst.cols() / 2, dst.rows() / 2);
        Mat rotationMatrix = Imgproc.getRotationMatrix2D(center, angle, 1);
        Imgproc.warpAffine(src, dst, rotationMatrix, new Size(src.cols(), src.cols()));
        long endTimeRotateImage = System.currentTimeMillis();
        long startTimesavelocalImage = System.currentTimeMillis();
        Imgcodecs.imwrite(LOCAL_PATH + "rotate.png", dst);
        long endTimesavelocalImage = System.currentTimeMillis();
        long startTimesaveBucketImage = System.currentTimeMillis();
        var variable = apiClient.invokeApi(PATH + "rotate.png", webConfigurer.getAuthorization());
        long endTimesaveBucketImage = System.currentTimeMillis();
        System.out.println(endTimereadImage - startTimereadImage + ": time read image");
        System.out.println(endTimeconvertImage - startTimeconvertImage + ": time convert image");
        System.out.println(endTimeRotateImage - startTimerotateImage + ": time rotate image");
        System.out.println(endTimesavelocalImage - startTimesavelocalImage + ": time save local image");
        System.out.println(endTimesaveBucketImage - startTimesaveBucketImage + ": time save bucket image");
        return variable;
    }

    String resize(String pathImage, int width, int height) throws IOException {

        long startTimeloadlibrary = System.currentTimeMillis();
        OpenCV.loadShared();
        long endTimeLoadLibrary = System.currentTimeMillis();
        long startTimereadImage = System.currentTimeMillis();
        BufferedImage originalImage = ImageIO.read(new URL(pathImage));
        long endTimereadImage = System.currentTimeMillis();
        long startTimeconvertImage = System.currentTimeMillis();
        Mat src = bufferedImageToMat(originalImage);
        long endTimeconvertImage = System.currentTimeMillis();
        Mat dst = new Mat();
        long startTimeresizeImage = System.currentTimeMillis();
        Imgproc.resize(src, dst, new Size(width, height), 0, 0, Imgproc.INTER_AREA);
        long endTimeresizeImage = System.currentTimeMillis();
        long startTimesavelocalImage = System.currentTimeMillis();
        Imgcodecs.imwrite(LOCAL_PATH + "resize.png", dst);
        long endTimesavelocalImage = System.currentTimeMillis();
        long startTimesaveBucketImage = System.currentTimeMillis();
        var variable = apiClient.invokeApi(PATH + "resize.png", webConfigurer.getAuthorization());
        long endTimesaveBucketImage = System.currentTimeMillis();
        System.out.println("------------------------");
        System.out.println(endTimeLoadLibrary - startTimeloadlibrary + ": time load library");
        System.out.println(endTimereadImage - startTimereadImage + ": time read image");
        System.out.println(endTimeconvertImage - startTimeconvertImage + ": time convert image");
        System.out.println(endTimeresizeImage - startTimeresizeImage + ": time resize image");
        System.out.println(endTimesavelocalImage - startTimesavelocalImage + ": time save local image");
        System.out.println(endTimesaveBucketImage - startTimesaveBucketImage + ": time save bucket image");
        System.out.println("------------------------");
        return variable;
    }

    String grayFilter(String pathImage) throws IOException {

        long startTimeloadlibrary = System.currentTimeMillis();
        OpenCV.loadShared();
        long endTimeLoadLibrary = System.currentTimeMillis();
        long startTimereadImage = System.currentTimeMillis();
        BufferedImage originalImage = ImageIO.read(new URL(pathImage));
        long endTimereadImage = System.currentTimeMillis();
        long startTimeconvertImage = System.currentTimeMillis();
        Mat src = bufferedImageToMat(originalImage);
        long endTimeconvertImage = System.currentTimeMillis();
        Mat dst = new Mat();
        long startTimegrayImage = System.currentTimeMillis();
        Imgproc.cvtColor(src, dst, Imgproc.COLOR_RGB2GRAY);
        long endTimegrayImage = System.currentTimeMillis();
        long startTimesavelocalImage = System.currentTimeMillis();
        Imgcodecs.imwrite(LOCAL_PATH + "gray.png", dst);
        long endTimesavelocalImage = System.currentTimeMillis();
        long startTimesaveBucketImage = System.currentTimeMillis();
        var variable = apiClient.invokeApi(PATH + "gray.png", webConfigurer.getAuthorization());
        long endTimesaveBucketImage = System.currentTimeMillis();
        System.out.println("------------------------");
        System.out.println(endTimeLoadLibrary - startTimeloadlibrary + ": time load library");
        System.out.println(endTimereadImage - startTimereadImage + ": time read image");
        System.out.println(endTimeconvertImage - startTimeconvertImage + ": time convert image");
        System.out.println(endTimegrayImage - startTimegrayImage + ": time gray image");
        System.out.println(endTimesavelocalImage - startTimesavelocalImage + ": time save local image");
        System.out.println(endTimesaveBucketImage - startTimesaveBucketImage + ": time save bucket image");
        System.out.println("------------------------");
        return variable;
    }

    String sepiaFilter(String pathImage) throws IOException {

        BufferedImage originalImage = ImageIO.read(new URL(pathImage));
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p = originalImage.getRGB(x, y);
                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;
                int tr = (int) (0.393 * r + 0.769 * g + 0.189 * b);
                int tg = (int) (0.349 * r + 0.686 * g + 0.168 * b);
                int tb = (int) (0.272 * r + 0.534 * g + 0.131 * b);
                r = Math.min(tr, 255);
                g = Math.min(tg, 255);
                b = Math.min(tb, 255);
                p = (a << 24) | (r << 16) | (g << 8) | b;
                originalImage.setRGB(x, y, p);
            }
        }
        javaxt.io.Image image = new javaxt.io.Image(originalImage);
        image.saveAs(LOCAL_PATH + "sepia.png");
        var variable = apiClient.invokeApi(PATH + "sepia.png", webConfigurer.getAuthorization());
        return variable;
    }

    String redFilter(String pathImage) throws IOException {

        BufferedImage originalImage = ImageIO.read(new URL(pathImage));
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p = originalImage.getRGB(x, y);
                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                p = (a << 24) | (r << 16) | (0);
                originalImage.setRGB(x, y, p);
            }
        }

        javaxt.io.Image image = new javaxt.io.Image(originalImage);
        image.saveAs(LOCAL_PATH + "red.png");
        return apiClient.invokeApi(PATH + "red.png", webConfigurer.getAuthorization());
    }

    String greenFilter(String pathImage) throws IOException {

        BufferedImage originalImage = ImageIO.read(new URL(pathImage));
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p = originalImage.getRGB(x, y);

                int a = (p >> 24) & 0xff;
                int g = (p >> 8) & 0xff;
                p = (a << 24) | (0) | (g << 8);

                originalImage.setRGB(x, y, p);
            }
        }

        javaxt.io.Image image = new javaxt.io.Image(originalImage);
        image.saveAs(LOCAL_PATH + "green.png");
        return apiClient.invokeApi(PATH + "green.png", webConfigurer.getAuthorization());
    }

    String blueFilter(String pathImage) throws IOException {

        BufferedImage originalImage = ImageIO.read(new URL(pathImage));
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        //convert to blue image
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p = originalImage.getRGB(x, y);

                int a = (p >> 24) & 0xff;
                int b = p & 0xff;

                //set new RGB
                p = (a << 24) | (0) | b;

                originalImage.setRGB(x, y, p);
            }
        }

        javaxt.io.Image image = new javaxt.io.Image(originalImage);
        image.saveAs(LOCAL_PATH + "blue.png");
        return apiClient.invokeApi(PATH + "blue.png", webConfigurer.getAuthorization());
    }


    String negativeFilter(String pathImage) throws IOException {

        BufferedImage originalImage = ImageIO.read(new URL(pathImage));
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();


        //convert to negative
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p = originalImage.getRGB(x, y);
                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;
                //subtract RGB from 255
                r = 255 - r;
                g = 255 - g;
                b = 255 - b;
                //set new RGB value
                p = (a << 24) | (r << 16) | (g << 8) | b;
                originalImage.setRGB(x, y, p);
            }
        }

        javaxt.io.Image image = new javaxt.io.Image(originalImage);
        image.saveAs(LOCAL_PATH + "negative.png");
        return apiClient.invokeApi(PATH + "negative.png", webConfigurer.getAuthorization());
    }

    String mirrorTwoFilter(String pathImage) throws IOException {

        BufferedImage originalImage = ImageIO.read(new URL(pathImage));
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        BufferedImage mimg = new BufferedImage(width * 2, height, BufferedImage.TYPE_INT_ARGB);
        long startTimesepiaImage = System.currentTimeMillis();
        for (int y = 0; y < height; y++) {
            for (int lx = 0, rx = width * 2 - 1; lx < width; lx++, rx--) {
                int p = originalImage.getRGB(lx, y);
                mimg.setRGB(lx, y, p);
                mimg.setRGB(rx, y, p);
            }
        }
        long endTimemirror2image = System.currentTimeMillis();
        System.out.println(endTimemirror2image-startTimesepiaImage + ": mirror2");
        javaxt.io.Image image = new javaxt.io.Image(mimg);
        image.saveAs(LOCAL_PATH + "mirror2filter.png");
        return apiClient.invokeApi(PATH + "mirror2filter.png", webConfigurer.getAuthorization());
    }

    String mirrorOneFilter(String pathImage) throws IOException {

        BufferedImage originalImage = ImageIO.read(new URL(pathImage));
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        BufferedImage mimg = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++) {
            for (int lx = 0, rx = width - 1; lx < width; lx++, rx--) {
                int p = originalImage.getRGB(lx, y);
                mimg.setRGB(rx, y, p);
            }
        }

        javaxt.io.Image image = new javaxt.io.Image(mimg);
        image.saveAs(LOCAL_PATH + "mirror1filter.png");
        return apiClient.invokeApi(PATH + "miror1filter.png", webConfigurer.getAuthorization());
    }

    String blurImage(String pathImage, int intensity) throws IOException {
        OpenCV.loadShared();

        BufferedImage originalImage = ImageIO.read(new URL(pathImage));
        Mat src = bufferedImageToMat(originalImage);
        Mat dst = new Mat();
        Imgproc.blur(src, dst, new Size(intensity, intensity), new Point(10, 20), Core.BORDER_DEFAULT);
        Imgcodecs.imwrite(LOCAL_PATH + "blur.png", dst);
        return apiClient.invokeApi(PATH + "blur.png", webConfigurer.getAuthorization());
    }

    String brightness(String pathImage, int alpha, int beta) throws IOException {

        OpenCV.loadShared();
        BufferedImage originalImage = ImageIO.read(new URL(pathImage));
        Mat src = bufferedImageToMat(originalImage);
        Mat dst = new Mat(src.rows(), src.cols(), src.type());
        src.convertTo(dst, -1, alpha, beta);

        Imgcodecs.imwrite(LOCAL_PATH + "brightness.png", dst);
        return apiClient.invokeApi(PATH + "brightness.png", webConfigurer.getAuthorization());
    }

    String sharped(String pathImage) throws IOException {

        OpenCV.loadShared();
        BufferedImage originalImage = ImageIO.read(new URL(pathImage));
        Mat src = bufferedImageToMat(originalImage);
        Mat dst = new Mat(src.rows(), src.cols(), src.type());
        Imgproc.GaussianBlur(src, dst, new Size(0, 0), 10);
        Core.addWeighted(src, 1.5, dst, -0.5, 0, dst);
        Imgcodecs.imwrite(LOCAL_PATH + "sharped.png", dst);
        return apiClient.invokeApi(PATH + "sharped.png", webConfigurer.getAuthorization());
    }

    String dilatation(String pathImage, int increase) throws IOException {

        OpenCV.loadShared();
        BufferedImage originalImage = ImageIO.read(new URL(pathImage));
        Mat src = bufferedImageToMat(originalImage);
        Mat dst = new Mat();
        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT,
                new Size(increase, increase));
        Imgproc.dilate(src, dst, kernel);
        Imgcodecs.imwrite(LOCAL_PATH + "dilatation.png", dst);
        return apiClient.invokeApi(PATH + "dilatation.png", webConfigurer.getAuthorization());
    }

    String erosion(String pathImage, int increase) throws IOException {

        OpenCV.loadShared();
        BufferedImage originalImage = ImageIO.read(new URL(pathImage));
        Mat src = bufferedImageToMat(originalImage);
        Mat dst = new Mat();
        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT,
                new Size(increase, increase));
        Imgproc.erode(src, dst, kernel);
        Imgcodecs.imwrite(LOCAL_PATH + "erosion.png", dst);
        return apiClient.invokeApi(PATH + "erosion.png", webConfigurer.getAuthorization());
    }

    String phat(String pathImage, int increase) throws IOException {

        OpenCV.loadShared();
        BufferedImage originalImage = ImageIO.read(new URL(pathImage));
        Mat src = bufferedImageToMat(originalImage);
        Mat dst = new Mat();
        Mat kernel = Mat.ones(increase, increase, CvType.CV_32F);
        Imgproc.morphologyEx(src, dst, Imgproc.MORPH_TOPHAT, kernel);
        Imgcodecs.imwrite(LOCAL_PATH + "phat.png", dst);
        return apiClient.invokeApi(PATH + "phat.png", webConfigurer.getAuthorization());
    }


    public static Mat bufferedImageToMat(BufferedImage image) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", byteArrayOutputStream);
        byteArrayOutputStream.flush();
        return Imgcodecs.imdecode(new MatOfByte(byteArrayOutputStream.toByteArray()), Imgcodecs.IMREAD_UNCHANGED);
    }

}