package io.licence.webapp.features.image;

import io.licence.webapp.features.folder.FolderDto;
import io.licence.webapp.utils.unsplash.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping(path ="/search/query/{query}")
    public Results search(@PathVariable("query") String query) {
        return imageService.search(query);
    }

    @PostMapping(path ="/id/{idImage}")
    public String saveImageFromUnsplash(@PathVariable("idImage") String idImage) {
        return imageService.saveImageFromUnsplash(idImage);
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ImageDto> create(@RequestBody ImageDto dto) throws IOException {
        return ResponseEntity.ok(imageService.create(dto));
    }

    @GetMapping(path = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ImageDto> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(imageService.getById(id));
    }

    @GetMapping(path = "",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ImageDto> getAll(){
        return imageService.getAll();
    }

    @GetMapping(path = "/name", produces = MediaType.APPLICATION_JSON_VALUE, params = "name")
    public ResponseEntity<List<ImageDto>> getByName(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok(imageService.getByName(name));
    }

    @PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ImageDto> update(@RequestBody ImageDto dto) {
        return ResponseEntity.ok(imageService.update(dto));
    }

    @DeleteMapping(path = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(imageService.delete(id));
    }

}
