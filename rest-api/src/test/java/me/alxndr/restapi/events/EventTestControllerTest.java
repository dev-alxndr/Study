package me.alxndr.restapi.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.jni.Local;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author : Alexander Choi
 * @date : 2021/08/02
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EventTestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void createEvent() throws Exception {

        Event event = Event.builder()
                .id(100L)
                .name("spring rest api")
                .description("welcome rest api")
                .beginEnrollmentDateTime(LocalDateTime.of(2021, 8, 1, 12, 0,0))
                .closeEnrollmentDateTime(LocalDateTime.of(2021, 8, 30, 12, 0, 0))
                .beginEventDateTime(LocalDateTime.of(2021, 9, 1, 12, 0, 0))
                .endEventDateTime(LocalDateTime.of(2021, 9, 30, 12,0,0))
                .basePrice(100)
                .maxPrice(200)
                .limitOfEnrollment(100)
                .location("강남역 D2 스터트업 팩토리")
                .free(true)
                .offline(false)
                .build();

        mockMvc.perform(post("/api/events")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaTypes.HAL_JSON) // Hypertext Application Language
                    .content(objectMapper.writeValueAsString(event)))
                    .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(header().exists(HttpHeaders.LOCATION)) // Location 정보가 있는지
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE)) // header에 해당 정보가 있는지
                .andExpect(jsonPath("id").value(Matchers.not(100L)))
                .andExpect(jsonPath("free").value(Matchers.not(true)))
        ;
    }

}
