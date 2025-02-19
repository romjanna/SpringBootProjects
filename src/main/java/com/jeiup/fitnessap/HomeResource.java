package com.jeiup.fitnessap;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeResource {

    @GetMapping("/home")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<String> home() {
        return ResponseEntity.ok()
            .contentType(MediaType.TEXT_HTML)
            .body("<h1>Welcome!</h1>");
    }

    @GetMapping("/user-dashboard")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<String> user() {
        return ResponseEntity.ok()
            .contentType(MediaType.TEXT_HTML)
            .body("<h1>Welcome User!</h1>");
    }

    @GetMapping("/admin-dashboard")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<String> admin() {
        return ResponseEntity.ok()
            .contentType(MediaType.TEXT_HTML)
            .body("<h1>Welcome Admin!</h1>");
    }

}
