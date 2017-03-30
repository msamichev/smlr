package ru.sam.smlr.service;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by msamichev on 30.03.2017.
 */
@Component
public class DefaultKeyMapperService implements KeyMapperService {

    private Map<String, String> map = new ConcurrentHashMap<>();

    @Override
    public Add add(String key, String link) {
        if (map.containsKey(key)) {
            return new Add.AlreadyExist(key);
        } else {
            map.put(key, link);
            return new Add.Success(key, link);
        }
    }

    @Override
    public Get getLink(String key) {
        if (map.containsKey(key)) return new Get.Link(map.get(key));
        else return new Get.NotFound(key);
    }
}
