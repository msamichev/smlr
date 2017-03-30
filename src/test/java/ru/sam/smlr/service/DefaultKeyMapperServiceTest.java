package ru.sam.smlr.service;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by msamichev on 30.03.2017.
 */
public class DefaultKeyMapperServiceTest {
    private static final String KEY = "aAbBcCdD";
    private static final String LINK = "http://www.google.com";
    private static final String LINK_NEW = "http://wow.ru";
    private KeyMapperService service = new DefaultKeyMapperService();

    @Test
    public void addNewKeyTest() {
        Assert.assertEquals(new KeyMapperService.Add.Success(KEY, LINK), service.add(KEY, LINK));
        Assert.assertEquals(new KeyMapperService.Get.Link(LINK), service.getLink(KEY));
    }

    @Test
    public void addExistingKeyTest() {
        service.add(KEY, LINK);
        Assert.assertEquals(new KeyMapperService.Add.AlreadyExist(KEY), service.add(KEY, LINK_NEW));
        Assert.assertEquals(new KeyMapperService.Get.Link(LINK), service.getLink(KEY));

    }

    @Test
    public void getNotExistLink() {
        Assert.assertEquals(new KeyMapperService.Get.NotFound(KEY), service.getLink(KEY));

    }
}
