package io.devandre.synthdrive.companies;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller()
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    // QueryMapping registers getCompany method as a DataFetcher for the company query
    @QueryMapping
    public CompanyResponse getCompany(@Argument Long id) {
        return companyService.getCompany(id);
    }

    @MutationMapping
    public CompanyResponse createCompany(@Argument CompanyRequest input) {
        return companyService.createCompany(input);
    }

}
