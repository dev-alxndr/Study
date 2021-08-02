package me.alxndr.restapi.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.jni.Local;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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
@WebMvcTest // Slicing Test
public class EventTestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    EventRepository eventRepository;

    @Test
    public void createEvent() throws Exception {

        Event event = Event.builder()
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

        /**
         * 현재 Test는 Web Slicing Test라 Repository는 Bean으로 등록되지않는다.
         * 그러므로 실행시 NPE 가 발생하므로, Mockito를 사용하여 Mocking해준다.
         */
//        event.setId(10L);
        Mockito.when(eventRepository.save(event)).thenReturn(event);

        mockMvc.perform(post("/api/events")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaTypes.HAL_JSON) // Hypertext Application Language
                    .content(objectMapper.writeValueAsString(event)))
                    .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(header().exists(HttpHeaders.LOCATION)) // Location 정보가 있는지
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE)) // header에 해당 정보가 있는지
                .andExpect(jsonPath("id").value(Matchers.not(10)))
                .andExpect(jsonPath("free").value(Matchers.not(true)))
        ;
    }

}
