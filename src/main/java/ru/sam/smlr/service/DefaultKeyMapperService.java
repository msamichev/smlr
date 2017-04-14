package ru.sam.smlr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.sam.smlr.model.Link;
import ru.sam.smlr.model.repository.LinkRepository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by msamichev on 30.03.2017.
 */
@Component
public class DefaultKeyMapperService implements KeyMapperService {

    @Autowired
    private KeyConverterService converter;

    @Autowired
    private LinkRepository repository;


    @Override
    @Transactional
    public String add(String link) {
        return converter.idToKey(repository.save(new Link(link)).getId());
    }

    @Override
    public Get getLink(String key) {
        Optional<Link> linkOptional = repository.findOne(converter.keyToId(key));

        if (linkOptional.isPresent()) return new Get.Link(linkOptional.get().getText());
        else return new Get.NotFound(key);

    }
}
