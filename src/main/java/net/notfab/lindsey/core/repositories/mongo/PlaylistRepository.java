package net.notfab.lindsey.core.repositories.mongo;

import net.notfab.lindsey.core.framework.models.PlayList;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PlaylistRepository extends MongoRepository<PlayList, String> {

    long countAllByOwner(long owner);

    Optional<PlayList> findByOwnerAndNameLike(long owner, String name);

}
