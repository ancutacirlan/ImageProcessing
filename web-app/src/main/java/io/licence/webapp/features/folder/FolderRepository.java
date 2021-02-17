package io.licence.webapp.features.folder;

import io.licence.webapp.features.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FolderRepository extends JpaRepository<Folder,Integer> {

    List<Folder> findAllByUserIdAndIsDeletedFalse(User userId);
    Optional<Folder> findByNameAndIsDeletedFalse(String name);
}
