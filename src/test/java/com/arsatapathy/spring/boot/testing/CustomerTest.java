package com.arsatapathy.spring.boot.testing;

import com.arsatapathy.spring.boot.testing.model.Customer;
import com.arsatapathy.spring.boot.testing.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerService customerService;

    @Test
    public void home() throws Exception {
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Welcome!")));
    }

    @Test
    public void getCustomer() throws Exception {

        Customer ashish = new Customer((long) 123, "Ashish", "Bengaluru", (long) 123);
        Customer snighda = new Customer((long) 456, "Snighda", "Bengaluru", (long) 123);

        customerService.addCustomer(ashish);
        customerService.addCustomer(snighda);

        mockMvc.perform(get("/customers").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void addCustomer() throws Exception {
        mockMvc.perform(post("/customers/customer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"customerId\":123,\"customerName\":\"Ashish\",\"customerAddress\":\"Bengaluru\",\"customerPhone\":123}"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
