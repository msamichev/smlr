package ru.sam.smlr.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Created by msamichev on 30.03.2017.
 */
public class DefaultKeyMapperServiceTest {



    private static final String KEY = "aAbBcCdD";
    private static final String LINK_A = "http://google.com";
    private static final String LINK_B = "http://rambler.ru";
    private static final String KEY_A = "abc";
    private static final String KEY_B = "cde";
    private static final Long ID_A = 10000000L;
    private static final Long ID_B = 10000001L;

    @InjectMocks
    private KeyMapperService service = new DefaultKeyMapperService();

    @Mock
    private KeyConverterService converter;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        Mockito.when(converter.keyToId(KEY_A)).thenReturn(ID_A);
        Mockito.when(converter.idToKey(ID_A)).thenReturn(KEY_A);
        Mockito.when(converter.keyToId(KEY_B)).thenReturn(ID_B);
        Mockito.when(converter.idToKey(ID_B)).thenReturn(KEY_B);
    }


    @Test
    public void addLinks() {
        String keyA = service.add(LINK_A);
        Assert.assertEquals(new KeyMapperService.Get.Link(LINK_A), service.getLink(keyA));
        String keyB = service.add(LINK_B);
        Assert.assertEquals(new KeyMapperService.Get.Link(LINK_B), service.getLink(keyB));
        Assert.assertNotEquals(keyA, keyB);
    }

    @Test
    public void getNotExistLink() {
        Assert.assertEquals(new KeyMapperService.Get.NotFound(KEY), service.getLink(KEY));

    }
}
