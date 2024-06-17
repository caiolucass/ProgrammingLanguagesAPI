package com.example.hotmart.adesao.exception;

import com.example.hotmart.adesao.payload.response.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.io.Serial;

public class ResponseEntityErrorException {

    @Serial
    private static final long serialVersionUID = -3156815846745801694L;

    private transient ResponseEntity<ApiResponse> apiResponse;

    public ResponseEntityErrorException(ResponseEntity<ApiResponse> apiResponse) {
        this.apiResponse = apiResponse;
    }

    public ResponseEntity<ApiResponse> getApiResponse() {
        return apiResponse;
    }
}
