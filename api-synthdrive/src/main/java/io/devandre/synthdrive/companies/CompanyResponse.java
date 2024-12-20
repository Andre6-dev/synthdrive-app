package io.devandre.synthdrive.companies;

import java.io.Serializable;
import java.time.OffsetDateTime;

/**
 * DTO for {@link Company}
 */
public record CompanyResponse(
        Long id,
        String displayName,
        String address,
        String description,
        OffsetDateTime createdAt) implements Serializable {
}