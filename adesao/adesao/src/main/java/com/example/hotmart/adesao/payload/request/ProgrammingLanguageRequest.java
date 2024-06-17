package com.example.hotmart.adesao.payload.request;

import jakarta.validation.constraints.NotBlank;

public class ProgrammingLanguageRequest {
    @NotBlank
    private String title;
}
