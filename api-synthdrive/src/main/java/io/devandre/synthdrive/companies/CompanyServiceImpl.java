package io.devandre.synthdrive.companies;

import io.devandre.synthdrive.shared.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = true)
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    public CompanyServiceImpl(CompanyRepository companyRepository, CompanyMapper companyMapper) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
    }


    @Override
    @Transactional(readOnly = false)
    public CompanyResponse createCompany(CompanyRequest company) {
        return null;
    }

    @Override
    public CompanyResponse getCompany(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with id " + id));
        return companyMapper.toResponse(company);
    }

    @Override
    public List<CompanyResponse> getCompanies() {
        return List.of();
    }


}
