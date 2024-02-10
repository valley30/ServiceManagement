package com.repairs.service;

import com.repairs.service.Entity.Repair;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RepairFunctionalTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateAndRetrieveRepair() {
        Repair newRepair = new Repair();

        newRepair.setStatus("Nowy");
        newRepair.setCustomerId(1L);
        newRepair.setDeviceId(1L);

        ResponseEntity<Repair> createResponse = restTemplate.postForEntity("/api/repairs/add", newRepair, Repair.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.OK);


        Repair createdRepair = createResponse.getBody();
        ResponseEntity<Repair> retrieveResponse = restTemplate.getForEntity("/api/repairs/" + createdRepair.getRepairID(), Repair.class);
        assertThat(retrieveResponse.getBody())
                .usingRecursiveComparison()
                .isEqualTo(createdRepair);

    }


}
