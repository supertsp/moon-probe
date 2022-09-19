package br.com.tiagopedroso.moonprobe.entrypoint.controller;

import br.com.tiagopedroso.moonprobe.entrypoint.dto.probe.ProbeCreate;
import br.com.tiagopedroso.moonprobe.infra.handler.RestMessageHandler;
import br.com.tiagopedroso.moonprobe.entrypoint.service.ProbeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static br.com.tiagopedroso.moonprobe.infra.config.ApiUrl.BASE_URI;

@RestController
@RequestMapping(BASE_URI + "/probes")
@AllArgsConstructor
public class ProbeController {

    ProbeService service;

    @PostMapping
    ResponseEntity<?> create(@RequestBody ProbeCreate dto) {
        final var saved = service.create(dto);
        return RestMessageHandler.resourceCreated(saved, saved.getId());
    }

    @GetMapping
    ResponseEntity<?> list() {
        return RestMessageHandler.ok(service.list());
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return RestMessageHandler.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    ResponseEntity<?> update(
            @PathVariable("id") Long id,
            @RequestBody ProbeCreate dto
    ) {
        return RestMessageHandler.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return RestMessageHandler.resourceDeleted(id);
    }

}
