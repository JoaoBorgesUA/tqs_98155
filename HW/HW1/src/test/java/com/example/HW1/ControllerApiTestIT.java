package com.example.HW1;

import com.example.HW1.Models.CovObject;
import com.example.HW1.Service.APIService;

import org.springframework.http.MediaType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Hw1Application.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class ControllerApiTestIT {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private APIService service;

    // @Test
    // public void testGetCountry() throws Exception {
    // CovObject testCov = new CovObject("2021-04-02", "Portugal", 0);

    // mvc.perform(get("/HW1/get_country/Portugal").contentType(MediaType.APPLICATION_JSON))
    // .andExpect(jsonPath("$[0].country", is(testCov.getCountry())))
    // .andExpect(jsonPath("$[0].new_cases", is(testCov.getNew_cases())));

    // }

    // @Test
    // public void testGetDay() throws Exception {
    // CovObject testCov = new CovObject("2022-04-01", null, 1168761);

    // mvc.perform(get("/HW1/reportDay/2022-04-01").contentType(MediaType.APPLICATION_JSON))
    // .andExpect(jsonPath("$[0].new_cases", is(testCov.getNew_cases())))
    // .andExpect(jsonPath("$[0].date", is(testCov.getNew_cases())));

    // }
}
