package br.com.tiagopedroso.moonprobe.entrypoint.controller;

import br.com.tiagopedroso.moonprobe.entrypoint.dto.celestialarea.CelestialAreaCreate;
import br.com.tiagopedroso.moonprobe.entrypoint.dto.celestialarea.CelestialAreaResponse;
import br.com.tiagopedroso.moonprobe.entrypoint.service.CelestialAreaService;
import br.com.tiagopedroso.moonprobe.infra.handler.RestMessageHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Includes a Probe within a CelestialArea")
    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "200", description = "Inclusion successful! \uD83E\uDD70",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CelestialAreaResponse.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "The celestialAreaId does not exist! \uD83E\uDD26\u200D♂️"),
            @ApiResponse(responseCode = "404", description = "The resource id does not exist. \uD83E\uDD26\u200D♂️"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error... \uD83E\uDD74")
    })
    @PostMapping("/{id}/probes/{probeId}/include")
    ResponseEntity<?> includeProbe(
            @Parameter(description = "celestialAreaId")
            @PathVariable("id")
            Long id,

            @Parameter(description = "probeId")
            @PathVariable("probeId")
            Long probeId
    ) {
        return RestMessageHandler.ok(
                service.includeProbe(
                        id,
                        probeId
                )
        );
    }

    @Operation(summary = "Removes a Probe from a CelestialArea")
    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "200", description = "Successful removed!! \uD83E\uDD70",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CelestialAreaResponse.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "The probeId not belong to celestialAreaId OR The celestialAreaId does not exist \uD83E\uDD14"),
            @ApiResponse(responseCode = "404", description = "The resource id does not exist. \uD83E\uDD26\u200D♂️"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error... \uD83E\uDD74")
    })
    @DeleteMapping("/{id}/probes/{probeId}/remove")
    ResponseEntity<?> removeProbe(
            @Parameter(description = "celestialAreaId")
            @PathVariable("id")
            Long id,

            @Parameter(description = "probeId")
            @PathVariable("probeId")
            Long probeId
    ) {
        service.removeProbe(id, probeId);
        return RestMessageHandler.resourceWithoutContent();
    }

    @Operation(summary = "Moves a Probe within a CelestialArea")
    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "200", description = "Successful move!! \uD83E\uDD70",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CelestialAreaResponse.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "The probeId not belong to celestialAreaId OR The celestialAreaId does not exist \uD83E\uDD14"),
            @ApiResponse(responseCode = "404", description = "The resource id does not exist. \uD83E\uDD26\u200D♂️"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error... \uD83E\uDD74")
    })
    @PostMapping("/{id}/probes/{probeId}/move")
    ResponseEntity<?> moveProbe(
            @Parameter(description = "celestialAreaId")
            @PathVariable("id")
            Long id,

            @Parameter(description = "probeId")
            @PathVariable("probeId")
            Long probeId
    ) {
        return RestMessageHandler.ok(
                service.moveProbe(
                        id,
                        probeId
                )
        );
    }

    @Operation(summary = "Performs all movements of a Probe until the end within a CelestialArea")
    @ApiResponses(value = {

            @ApiResponse(
                    responseCode = "200", description = "Successful moves! \uD83E\uDD70",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CelestialAreaResponse.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "The probeId not belong to celestialAreaId OR The celestialAreaId does not exist \uD83E\uDD14"),
            @ApiResponse(responseCode = "404", description = "The resource id does not exist. \uD83E\uDD26\u200D♂️"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error... \uD83E\uDD74")
    })
    @PostMapping("/{id}/probes/{probeId}/move-all")
    ResponseEntity<?> moveProbeAllSequences(
            @Parameter(description = "celestialAreaId")
            @PathVariable("id")
            Long id,

            @Parameter(description = "probeId")
            @PathVariable("probeId")
            Long probeId
    ) {
        return RestMessageHandler.ok(
                service.moveProbeAllSequences(
                        id,
                        probeId
                )
        );
    }

    @SuppressWarnings("unchecked")
    @Operation(summary = "Performs all moves of all Probes to completion")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Successful moves! \uD83E\uDD70",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CelestialAreaResponse.class)
                            )
                    }
            ),
            @ApiResponse(responseCode = "400", description = "The celestialAreaId does not exist \uD83E\uDD14"),
            @ApiResponse(responseCode = "404", description = "The resource id does not exist. \uD83E\uDD26\u200D♂️"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error... \uD83E\uDD74")
    })
    @PostMapping("/{id}/probes/move-all")
    ResponseEntity<?> moveAllProbesUntilLastSequence(
            @Parameter(description = "celestialAreaId")
            @PathVariable("id")
            Long id
    ) {
        return RestMessageHandler.ok(
                service.moveAllProbesUntilLastSequence(id)
        );
    }

}
