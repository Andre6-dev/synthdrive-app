package io.devandre.synthdrive.companies;

import java.util.List;

public interface CompanyService {

    CompanyResponse createCompany(CompanyRequest company);
    CompanyResponse getCompany(Long id);

    List<CompanyResponse> getCompanies();
}
