package ru.sam.smlr.service;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

/**
 * Created by msamichev on 31.03.2017.
 */
public class DefaultKeyConverterServiceTest {

    private KeyConverterService service = new DefaultKeyConverterService();

    @Test
    public void convertTest() {
        Random random = new Random();

        for (int i = 0; i < 1000; i++) {
            Long initId = Math.abs(random.nextLong());
            String key = service.idToKey(initId);
            Long id = service.keyToId(key);
            Assert.assertEquals(initId, id);
        }
    }

}