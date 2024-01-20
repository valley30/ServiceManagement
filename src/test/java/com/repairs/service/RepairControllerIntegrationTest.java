package com.repairs.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.repairs.service.Entity.Repair;
import com.repairs.service.repository.RepairRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class RepairControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RepairRepository repairRepository;

    @Test
    public void testAddRepair() throws Exception {
        Repair newRepair = new Repair();
        newRepair.setStatus("Nowy");
        newRepair.setCustomerId(1L);
        newRepair.setDeviceId(1L);

        mockMvc.perform(post("/api/repairs/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(newRepair)))
                .andExpect(status().isOk());


        assertThat(repairRepository.findAll()).isNotEmpty();
    }


}
