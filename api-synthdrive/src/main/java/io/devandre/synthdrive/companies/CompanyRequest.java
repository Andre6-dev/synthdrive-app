package io.devandre.synthdrive.companies;

public record CompanyRequest(
        String displayName,
        String address,
        String description) {
}
