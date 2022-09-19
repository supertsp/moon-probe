package br.com.tiagopedroso.moonprobe.entrypoint.controller;

import br.com.tiagopedroso.moonprobe.entrypoint.dto.celestialarea.CelestialAreaCreate;
import br.com.tiagopedroso.moonprobe.entrypoint.dto.common.InclusionIds;
import br.com.tiagopedroso.moonprobe.infra.handler.RestMessageHandler;
import br.com.tiagopedroso.moonprobe.entrypoint.service.CelestialAreaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static br.com.tiagopedroso.moonprobe.infra.config.ApiUrl.BASE_URI;

@RestController
@RequestMapping(BASE_URI + "/celestial-areas")
@AllArgsConstructor
public class CelestialAreaController {

    CelestialAreaService service;

    @PostMapping
    ResponseEntity<?> create(@RequestBody CelestialAreaCreate dto) {
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
            @RequestBody CelestialAreaCreate dto
    ) {
        return RestMessageHandler.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return RestMessageHandler.resourceDeleted(id);
    }

    @PostMapping("/probes/include")
    ResponseEntity<?> includeProbe(@RequestBody InclusionIds inclusionIds) {
        return RestMessageHandler.ok(
                service.includeProbe(
                        inclusionIds.celestialAreaId(),
                        inclusionIds.probeId()
                )
        );
    }

}
