package com.conscifora.common.contoller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Контроллер запросов к корневому URL ("/")
 */
@RestController
public class HomeController {

    /**
     * Перенаправляет на страницу Swagger UI.
     */
    @GetMapping("/")
    public void getStartPage(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui/index.html");
    }
}
