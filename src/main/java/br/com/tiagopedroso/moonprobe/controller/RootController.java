package br.com.tiagopedroso.moonprobe.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @Hidden
    @GetMapping
    String show() {
        return """
                <!DOCTYPE html>
                <html lang="pt-BR">
                <head>
                    <meta charset="UTF-8">
                </head>
                <body style="background-color: black; color: white;">
                <pre>
                               THE MOON PROBE API
                              __                       _
                             /\\ `\\_                   /\\`\\_
                            /  ~   \\     (.)         /  ~  \\
                ~~~~~~~~~~~/~~~~~~~~\\~/~~~|~~~~~~~~~/~~~~~~~\\~~~~~
                   .^^____  ^       -o-   | ^  ^ ___ ^ .  _ .^^  .
                .^. _/   _\\_^     _  | ___|_    |   \\ ^^ / \\  ^. ^
                 ^ |    '   \\.     \\_|/__\\_|_   \\____\\.^ \\__\\ ^ ^.
                ^.^\\____\\____\\^.^  (o):(o):(o)::::::::::::::::::::
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                                              2022-09-14, supertsp
                </pre>
                </body>
                </html>
                """;
    }

}
