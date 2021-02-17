package io.licence.webapp.features.folder;

import io.licence.webapp.features.user.UserContract;
import io.licence.webapp.features.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FolderService {

    private final FolderRepository folderRepository;
    private final ModelMapper modelMapper;
    private final UserContract userContract;
    @Autowired
    public FolderService(FolderRepository folderRepository, ModelMapper modelMapper, UserContract userContract) {
        this.folderRepository = folderRepository;
        this.modelMapper = modelMapper;
        this.userContract = userContract;
        this.modelMapper.addMappings(Utils.fieldMapping);
        this.modelMapper.addMappings(Utils.mapping);
    }

    FolderDto create(@NotNull FolderDto dto) {
        Folder folder = modelMapper.map(dto, Folder.class);
        return modelMapper.map(folderRepository.save(folder), FolderDto.class);
    }


    List<FolderDto> getAllByUser(Long userId) {
        return folderRepository
                .findAllByUserIdAndIsDeletedFalse(userContract.getUserById(userId))
                .stream()
                .map(item -> modelMapper.map(item, FolderDto.class))
                .collect(Collectors.toList());
    }


    FolderDto getById(@NotNull Integer id) {
        return folderRepository
                .findById(id)
                .map(result -> modelMapper.map(result, FolderDto.class))
                .orElseThrow(EntityNotFoundException::new);
    }


    FolderDto getByName(@NotNull String name) {
        return folderRepository
                .findByNameAndIsDeletedFalse(name)
                .map(result -> modelMapper.map(result, FolderDto.class))
                .orElseThrow(EntityNotFoundException::new);
    }

    FolderDto update(@NotNull FolderDto dto) {
        return folderRepository
                .findById(dto.getId())
                .map(result -> {
                    Folder toBeUpdated = modelMapper.map(dto, Folder.class);
                    return modelMapper.map(folderRepository.save(toBeUpdated), FolderDto.class);
                })
                .orElseThrow(EntityNotFoundException::new);
    }

    Boolean delete(@NotNull Integer id) {
        return folderRepository
                .findById(id)
                .map(result -> {
                    result.setIsDeleted(true);
                    return folderRepository.save(result).getIsDeleted();
                })
                .orElseThrow(EntityNotFoundException::new);
    }

}
