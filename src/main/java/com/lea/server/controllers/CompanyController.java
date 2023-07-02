package com.lea.server.controllers;

import com.lea.server.beans.CompanyDto;
import com.lea.server.entity.Company;
import com.lea.server.logic.CompanyLogic;
import com.lea.server.utils.JWTUtils;
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
    public long createCompany(@RequestHeader String authorization, @RequestBody Company company) throws Exception {
        JWTUtils.validateToken(authorization);
        return companyLogic.createCompany(company);

    }

    @DeleteMapping("/{companyId}")
    public void removeCompany(@RequestHeader String authorization, @PathVariable("companyId") int companyId) throws Exception {
        JWTUtils.validateToken(authorization);
        companyLogic.removeCompany(companyId);
    }

    @PutMapping
    public void updateCompany(@RequestHeader String authorization, @RequestBody Company company) throws Exception {
        JWTUtils.validateToken(authorization);
        companyLogic.updateCompany(company);
    }

    @GetMapping
    public List<CompanyDto> getAllCompaniesByPage(@RequestHeader String authorization, @RequestParam("page") int page) throws Exception {
        JWTUtils.validateToken(authorization);
        return companyLogic.getAllCompanies(page);
    }

    @GetMapping("/{companyId}")
    public CompanyDto getCompanyByCompanyId(@RequestHeader String authorization, @PathVariable("companyId") long companyId) throws Exception {
        JWTUtils.validateToken(authorization);
        return companyLogic.getCompany(companyId);
    }

}
