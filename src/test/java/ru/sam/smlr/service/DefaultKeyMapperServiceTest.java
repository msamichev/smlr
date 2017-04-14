package ru.sam.smlr.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.sam.smlr.model.Link;
import ru.sam.smlr.model.repository.LinkRepository;

import java.util.Optional;

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
    private static final Link LINK_OBJ_A = new Link(ID_A, LINK_A);
    private static final Link LINK_OBJ_B = new Link(ID_B, LINK_B);

    @InjectMocks
    private KeyMapperService service = new DefaultKeyMapperService();

    @Mock
    private KeyConverterService converter;

    @Mock
    private LinkRepository repository;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        Mockito.when(converter.keyToId(KEY_A)).thenReturn(ID_A);
        Mockito.when(converter.idToKey(ID_A)).thenReturn(KEY_A);
        Mockito.when(converter.keyToId(KEY_B)).thenReturn(ID_B);
        Mockito.when(converter.idToKey(ID_B)).thenReturn(KEY_B);
        Mockito.when(repository.findOne(Mockito.anyObject())).thenReturn(Optional.empty());
        Mockito.when(repository.save(new Link(LINK_A))).thenReturn(LINK_OBJ_A);
        Mockito.when(repository.save(new Link(LINK_B))).thenReturn(LINK_OBJ_B);
        Mockito.when(repository.findOne(ID_A)).thenReturn(Optional.of(LINK_OBJ_A));
        Mockito.when(repository.findOne(ID_B)).thenReturn(Optional.of(LINK_OBJ_B));
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
