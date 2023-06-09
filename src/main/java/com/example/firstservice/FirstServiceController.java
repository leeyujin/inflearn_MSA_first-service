package com.example.firstservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

// http://localhost:8081/welcome # zuul
// http://localhost:8081/first-service/welcome # spring cloud gateway
@RestController
//@RequestMapping("/")
@RequestMapping("/first-service")
@RequiredArgsConstructor
@Slf4j
public class FirstServiceController {
    private final Environment environment;

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to the First service.";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("first-request")String header){
        log.info(header);
        return "Hello World in First Service.";
    }

    @GetMapping("/check")
    public String check(HttpServletRequest request){
        log.info("Server port={}", request.getServerPort());
        return String.format("Hi, there. This is a message from First service. on PORT %s"
                , environment.getProperty("local.server.port"));
    }
}
