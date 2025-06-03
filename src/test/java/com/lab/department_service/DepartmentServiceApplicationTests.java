package com.lab.department_service;

import com.lab.department_service.Department;
import com.lab.department_service.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class DepartmentServiceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DepartmentRepository repository;

    @BeforeEach
    void setup() {
        repository.deleteAll();
        repository.save(new Department("HR", "First Floor"));
    }

    @Test
    void contextLoads() {
    }

    @Test
    void testGetDepartmentByName() throws Exception {
        mockMvc.perform(get("/departments/name/HR"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("HR"))
                .andExpect(jsonPath("$.location").value("First Floor"));
    }
	
}
