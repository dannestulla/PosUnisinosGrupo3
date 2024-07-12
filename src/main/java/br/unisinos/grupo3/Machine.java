package br.unisinos.grupo3;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "machines")
public record Machine(
        @Id
        String id,
        String name,
        byte[] qrCode,
        String dateInserted
) {

}
