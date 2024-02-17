package oleg_learn_spring;

import com.example.demo.DemoApplication;
import com.example.demo.presentation.user.UserController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest(classes = DemoApplication.class)
public class LearnUsersControllerTests {

    MockMvc mockMvc;
    
    @Autowired UserController uc;
    
    @BeforeEach void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(uc).build();
    }
    
    @Test void returnAllUsers() {
        var allUsers = uc.getAll();
        assertTrue(allUsers.size() == 3);
    }
    
    @Test void returnAllUsersMVC_statusOK() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk());
    }
    
    @Test void returnAllUsersMVC_sizeCorrect() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test void getOneUser() throws Exception {
        mockMvc.perform(get("/users/123")).andExpectAll(
                jsonPath("$.id").value("123"),
                jsonPath("$.name").value("dasdas"));
    }
    
    @Test void POST_a_User() throws Exception {
        String body = "{\"id\": 1337,\"name\": \"leet\"}";
        mockMvc.perform(
                post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                
        .andExpectAll(
              jsonPath("$.name").value("leet")
        );
    }
    
    @Test void PUT_a_User() throws Exception {
        String body = "{\"name\": \"leet\"}";
        mockMvc.perform(
                put("/users/123")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                
        .andExpectAll(
              jsonPath("$.id").value("123"),
              jsonPath("$.name").value("leet")
        );
    }
    
    @Test void DELETE_a_User() throws Exception {
        int sizeBefore = uc.getAll().size();
        mockMvc.perform(delete("/users/123"));
        int sizeAfter = uc.getAll().size();
        assertEquals(sizeBefore - 1, sizeAfter);
    }
}
