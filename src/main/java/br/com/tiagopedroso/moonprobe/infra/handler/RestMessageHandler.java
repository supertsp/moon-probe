package br.com.tiagopedroso.moonprobe.infra.handler;

/**
 * Class imported from https://github.com/supertsp/livraria-online-api
 */

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Map;
import java.util.stream.Collectors;

public final class RestMessageHandler {

    private RestMessageHandler() {}

    private static final String
            KEY_STATUS = "status",
            KEY_STATUS_CODE = "statusCode",
            KEY_CONTENT = "content"
    ;

    public static <T> ResponseEntity<?> ok(T responseObject) {
        final Map<String, Object> body;

        if (responseObject instanceof Page) {
            final var page = (Page) responseObject;
            final var sortList = page.getSort().toList().stream()
                    .map(order -> order.getDirection() + "," + order.getProperty())
                    .collect(Collectors.toList());

            body = OrderedMapHandler.create(
                    KEY_STATUS, "OK",
                    KEY_STATUS_CODE, 200,
                    KEY_CONTENT, page.getContent(),
                    "pageNumber", page.getNumber(),
                    "pageSize", page.getSize(),
                    "totalPages", page.getTotalPages(),
                    "totalElements", page.getTotalElements(),
                    "sorting", sortList
            );
        } else {
            body = OrderedMapHandler.create(
                    KEY_STATUS, "OK",
                    KEY_STATUS_CODE, 200,
                    KEY_CONTENT, responseObject
            );
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    public static <T> ResponseEntity<?> resourceCreated(T responseObject, Long id) {
        final var body = OrderedMapHandler.create(
                KEY_STATUS, "OK",
                KEY_STATUS_CODE, 201,
                KEY_CONTENT, responseObject
        );

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(uri).body(body);
    }

    public static <T> ResponseEntity<?> resourceUpdated(T responseObject) {
        final var body = OrderedMapHandler.create(
                KEY_STATUS, "OK",
                KEY_STATUS_CODE, 200,
                KEY_CONTENT, responseObject
        );

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    public static <T> ResponseEntity<?> resourceDeleted(Long id) {
        final var body = OrderedMapHandler.create(
                KEY_STATUS, "OK",
                KEY_STATUS_CODE, 200,
                KEY_CONTENT, "The resource id '" + id + "' was deleted successfully"
        );

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

}
