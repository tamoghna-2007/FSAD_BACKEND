package com.klef.fsad.sdp.controller;

import java.time.Instant;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String home() {
        return """
            <!doctype html>
            <html lang="en">
            <head>
                <meta charset="utf-8">
                <meta name="viewport" content="width=device-width, initial-scale=1">
                <title>Student Project Manager API</title>
                <style>
                    body {
                        margin: 0;
                        min-height: 100vh;
                        display: grid;
                        place-items: center;
                        font-family: Arial, sans-serif;
                        background: #f6f8fb;
                        color: #172033;
                    }
                    main {
                        max-width: 720px;
                        padding: 32px;
                        text-align: center;
                    }
                    h1 {
                        margin: 0 0 12px;
                        font-size: 32px;
                    }
                    p {
                        margin: 8px 0;
                        font-size: 18px;
                        line-height: 1.5;
                    }
                    code {
                        display: inline-block;
                        margin-top: 16px;
                        padding: 10px 12px;
                        background: #e8eef7;
                        border-radius: 6px;
                    }
                </style>
            </head>
            <body>
                <main>
                    <h1>Student Project Manager Backend</h1>
                    <p>The Spring Boot API is running successfully.</p>
                    <p>Use the frontend application to login, register, and manage projects.</p>
                    <code>GET /api/health</code>
                </main>
            </body>
            </html>
            """;
    }

    @GetMapping({"/health", "/api/health"})
    public Map<String, String> health() {
        return Map.of(
            "status", "UP",
            "service", "Student Project Manager Backend",
            "timestamp", Instant.now().toString()
        );
    }
}
