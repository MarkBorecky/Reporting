package com.example.Reporting.controller;

import com.example.Reporting.controller.dto.ReportRequest;
import com.example.Reporting.service.ReportService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReportController.class)
class ReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ReportService service;

    @Test
    void shouldFailBecauseLackOfAuthentication() throws Exception {
        this.mockMvc.perform(get("/reports"))
                .andExpect(status().isUnauthorized());

    }

    @Test
    @WithMockUser
    void shouldFailBecauseLackOfIds() throws Exception {
        this.mockMvc.perform(get("/reports"))
                .andExpect(status().isBadRequest());

    }

    @Test
    @WithMockUser
    void shouldPrepareReportWhenIdsGiven() throws Exception {
        when(service.prepareReport(Mockito.any(ReportRequest.class)))
                .thenReturn(new byte[]{});

        this.mockMvc.perform(get("/reports").param("ids", "1"))
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser
    void shouldFailBecauseLackWrongDateRange() throws Exception {
        this.mockMvc.perform(
                        get("/reports")
                                .param("ids", "1")
                                .param("startDate", "2020-12-31")
                                .param("endDate", "2020-01-01")
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser
    void shouldPrepareReportWhenIdsAndOneDayRangeGiven() throws Exception {
        when(service.prepareReport(Mockito.any(ReportRequest.class)))
                .thenReturn(new byte[]{});

        this.mockMvc.perform(
                        get("/reports")
                                .param("ids", "1")
                                .param("startDate", "2020-01-01")
                                .param("endDate", "2020-01-01")
                )
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldPrepareReportWhenAllArgsGiven() throws Exception {
        when(service.prepareReport(Mockito.any(ReportRequest.class)))
                .thenReturn(new byte[]{});

        this.mockMvc.perform(
                        get("/reports")
                                .param("ids", "1")
                                .param("startDate", "2020-01-01")
                                .param("endDate", "2020-12-31")
                )
                .andExpect(status().isOk());
    }
}