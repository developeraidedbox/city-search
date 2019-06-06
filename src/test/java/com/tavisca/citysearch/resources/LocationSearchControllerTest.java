package com.tavisca.citysearch.resources;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static java.lang.String.format;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(LocationSearchController.class)
public class LocationSearchControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    public void shouldReturnLocationSearchResponse() throws Exception {
        mvc.perform(
                get(format("/search?query=SomePlace&category=someCategory")))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"query\":\"SomePlace\",\"category\":\"someCategory\",\"data\":[{\"name\":null,\"source\":null}]}"));
    }
}