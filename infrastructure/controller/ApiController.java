package api.shared.infrastructure.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.shared.domain.response.OnResponse;

@RestController
@RequestMapping("/")
public class ApiController {

    @GetMapping
    public ResponseEntity<?> getRoot() {
        return OnResponse.onSuccess(TimeUnit.MILLISECONDS.toMicros(System.currentTimeMillis()), HttpStatus.OK);
    }
}
