package com.tavisca.citysearch.resources;

import com.tavisca.citysearch.models.LocationSearchRequest;
import com.tavisca.citysearch.services.LocationSearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static java.lang.String.format;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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

    @MockBean
    private LocationSearchService locationSearchService;

    private final String query = "Pune";
    private final String category = "Historic Site";

    @Test
    public void shouldReturnLocationSearchResponse() throws Exception {
        mvc.perform(
                get(format("/search?query=%s&category=%s", query, category)))
                .andExpect(status().isOk());

        verify(locationSearchService).search(new LocationSearchRequest(query, category));
    }

    @Test
    public void shouldReturnErrorWhenServiceReturnsError() throws Exception {
        String errorMessage = "Error fetching location information.";

        when(locationSearchService.search(any())).thenThrow(new RuntimeException(errorMessage));

        mvc.perform(
                get(format("/search?query=%s&category=%s", query, category)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(errorMessage));

        verify(locationSearchService).search(new LocationSearchRequest(query, category));
    }
}