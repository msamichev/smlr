package ru.sam.smlr.service;

/**
 * Created by msamichev on 31.03.2017.
 */
public interface KeyConverterService {
    String idToKey(Long id);

    Long keyToId(String key);
}
