package io.licence.webapp.features.folder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/folder")
public class FolderController {

    private final FolderService folderService;

    @Autowired
    public FolderController(FolderService folderService) {
        this.folderService = folderService;
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FolderDto> create(@RequestBody FolderDto dto) {
        return ResponseEntity.ok(folderService.create(dto));
    }
    @GetMapping(path = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FolderDto> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(folderService.getById(id));
    }

    @GetMapping(path = "/userId/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FolderDto>> getByUserId(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(folderService.getAllByUser(userId));
    }

    @GetMapping(path = "/name", produces = MediaType.APPLICATION_JSON_VALUE, params = "name")
    public ResponseEntity<FolderDto> getByName(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok(folderService.getByName(name));
    }

    @PutMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FolderDto> update(@RequestBody FolderDto dto) {
        return ResponseEntity.ok(folderService.update(dto));
    }

    @DeleteMapping(path = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(folderService.delete(id));
    }

}
