package ru.sam.smlr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by msamichev on 30.03.2017.
 */
@Component
public class DefaultKeyMapperService implements KeyMapperService {

    @Autowired
    private KeyConverterService converter;

    private AtomicLong sequence = new AtomicLong(10000000L);

    private Map<Long, String> map = new ConcurrentHashMap<>();



    @Override
    public String add(String link) {
        long id = sequence.getAndIncrement();
        String key = converter.idToKey(id);
        map.put(id, link);
        return key;
    }

    @Override
    public Get getLink(String key) {
        long id = converter.keyToId(key);
        if (map.containsKey(id)) return new Get.Link(map.get(id));
        else return new Get.NotFound(key);
    }
}
