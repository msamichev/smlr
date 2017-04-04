package ru.sam.smlr.model.repository;

import org.springframework.data.repository.Repository;
import ru.sam.smlr.model.Link;

import java.util.List;
import java.util.Optional;

/**
 * Created by msamichev on 04.04.2017.
 */
public interface LinkRepository extends Repository<Link, Long> {

    Optional<Link> findOne(Long id);
    Link save(Link link);
    List<Link> findAll();
}
