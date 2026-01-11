package com.sree.ai.demo;

import jakarta.validation.constraints.NotBlank;

public record ChatRequest(@NotBlank String prompt) {}
