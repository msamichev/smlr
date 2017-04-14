package ru.sam.smlr.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.sam.smlr.SmlrApplication;
import ru.sam.smlr.service.KeyMapperService;

import java.util.concurrent.ExecutionException;

/**
 * Created by msamichev on 14.04.2017.
 */
@TestPropertySource(locations = {"classpath:repositories-test.properties"})
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SmlrApplication.class)
@WebAppConfiguration
public class AddControllerTest {

    private static final String LINK = "link";
    private static final String KEY = "key";
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Mock
    private KeyMapperService service;

    @Autowired
    @InjectMocks
    private AddController controller;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
        Mockito.when(service.add(LINK)).thenReturn(KEY);
    }

    @Test
    public void addLinkTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(MockMvcRequestBuilders.post("/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new AddController.AddRequest(LINK))))
                .andExpect(MockMvcResultMatchers.jsonPath("$.key", Matchers.equalTo(KEY)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.link", Matchers.equalTo(LINK)));
    }
}
