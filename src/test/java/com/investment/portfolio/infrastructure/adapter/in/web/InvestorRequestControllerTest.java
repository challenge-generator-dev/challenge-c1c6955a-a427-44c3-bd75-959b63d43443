package com.investment.portfolio.infrastructure.adapter.in.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.investment.portfolio.application.service.InvestorRequestService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(InvestorRequestController.class)
class InvestorRequestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private InvestorRequestService investorRequestService;

    @Test
    void createRequest_shouldReturn201_whenRequestIsValid() throws Exception {
        // Given: a valid request payload
        // When: POST /api/investor-requests is called
        // Then: it should return 201 Created with the request details
    }

    @Test
    void createRequest_shouldReturn400_whenRequestIsInvalid() throws Exception {
        // Given: an invalid request payload (missing required fields)
        // When: POST /api/investor-requests is called
        // Then: it should return 400 Bad Request
    }

    @Test
    void getRequestById_shouldReturn200_whenRequestExists() throws Exception {
        // Given: a request ID that exists
        // When: GET /api/investor-requests/{id} is called
        // Then: it should return 200 OK with the request details
    }

    @Test
    void getRequestById_shouldReturn404_whenRequestNotFound() throws Exception {
        // Given: a request ID that does not exist
        // When: GET /api/investor-requests/{id} is called
        // Then: it should return 404 Not Found
    }

    @Test
    void getRequestByIdempotencyKey_shouldReturn200_whenKeyExists() throws Exception {
        // Given: an idempotency key that exists
        // When: GET /api/investor-requests/by-key/{key} is called
        // Then: it should return 200 OK with the request details
    }

    @Test
    void createRequest_shouldReturn409_whenIdempotencyKeyIsDuplicate() throws Exception {
        // Given: a request with an existing idempotency key
        // When: POST /api/investor-requests is called
        // Then: it should return 409 Conflict
    }

    @Test
    void createRequest_shouldReturn500_whenInternalErrorOccurs() throws Exception {
        // Given: a valid request but service throws exception
        // When: POST /api/investor-requests is called
        // Then: it should return 500 Internal Server Error
    }
}