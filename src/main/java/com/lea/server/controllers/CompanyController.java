package com.lea.server.controllers;

import com.lea.server.beans.CompanyDto;
import com.lea.server.entity.Company;
import com.lea.server.logic.CompanyLogic;
import com.lea.server.utils.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private CompanyLogic companyLogic;

    @Autowired
    public CompanyController(CompanyLogic companyLogic) {
        this.companyLogic = companyLogic;
    }

    @PostMapping
    public long createCompany(@RequestBody Company company) throws ServerException {
        return companyLogic.createCompany(company);

    }

    @DeleteMapping("/{companyId}")
    public void removeCompany(@PathVariable("companyId") int companyId) throws ServerException {
        companyLogic.removeCompany(companyId);
    }

    @PutMapping
    public void updateCompany(@RequestBody Company company) throws ServerException {
        companyLogic.updateCompany(company);
    }

    @GetMapping({"/byPage"})
    public List<CompanyDto> getAllCompaniesByPage(@RequestParam("page") int page) throws ServerException {
       return companyLogic.getAllCompanies(page);
    }

    @GetMapping("/{companyId}")
    public CompanyDto getCompanyByCompanyId(@PathVariable("companyId") long companyId) throws ServerException {
        return companyLogic.getCompany(companyId);
    }

}
