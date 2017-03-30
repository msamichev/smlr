package ru.sam.smlr.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.sam.smlr.SmlrApplication;
import ru.sam.smlr.service.KeyMapperService;


/**
 * Created by msamichev on 30.03.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SmlrApplication.class)
@WebAppConfiguration
public class RedirectControllerTest {

    private static final String PATH = "aAbBcCdD";
    private static final int REDIRECT_STATUS = 302;
    private static final String HEADER_NAME = "Location";
    private static final String HEADER_VALUE = "http://www.google.com";
    private static final String BAD_PATH = "TDtDTD";
    private static final int NOT_FOUND_STATUS = 404;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Mock
    private KeyMapperService service;

    @Autowired
    @InjectMocks
    private RedirectController controller;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
        Mockito.when(service.getLink(PATH)).thenReturn(new KeyMapperService.Get.Link(HEADER_VALUE));
        Mockito.when(service.getLink(BAD_PATH)).thenReturn(new KeyMapperService.Get.NotFound(BAD_PATH));
    }

    @Test
    public void redirectTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/" + PATH))
                .andExpect(MockMvcResultMatchers.status().is(REDIRECT_STATUS))
                .andExpect(MockMvcResultMatchers.header().string(HEADER_NAME, HEADER_VALUE));
    }

    @Test
    public void get404Test() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/" + BAD_PATH))
                .andExpect(MockMvcResultMatchers.status().is(NOT_FOUND_STATUS));
    }
}
