package com.tablefi;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@Tag(name = "Some Entity", description = "Test description")
@RestController
public class DemoApplication {



}
