package br.com.tiagopedroso.moonprobe.controller;

import br.com.tiagopedroso.moonprobe.infra.handler.RestMessageHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.tiagopedroso.moonprobe.infra.config.ApiUrl.BASE_URI;

@RestController
@RequestMapping(BASE_URI + "/probes")
public class ProbeController {

    @GetMapping
    ResponseEntity<?> showMessage() {
        return RestMessageHandler.ok("works");
    }

}
