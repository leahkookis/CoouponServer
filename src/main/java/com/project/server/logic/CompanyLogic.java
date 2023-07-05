package com.project.server.logic;



import com.project.server.enums.ErrorType;
import com.project.server.utils.ServerException;
import com.project.server.beans.CompanyDto;
import com.project.server.constanse.Consts;
import com.project.server.dal.ICompanyDal;
import com.project.server.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CompanyLogic {
    private ICompanyDal companyDal;

    @Autowired
    public CompanyLogic(ICompanyDal companyDal) {
        this.companyDal = companyDal;
    }

    public Long createCompany(Company company) throws ServerException {
        companyValidation(company);
        if (!companyDal.existsByName(company.getName())){
            throw new ServerException(ErrorType.COMPANY_ALREADY_EXIST);
        }
        companyDal.save(company);
        return company.getId();
    }



    public void updateCompany(Company company) throws ServerException {
        companyValidation(company);
        companyDal.save(company);
    }

    public void removeCompany(long companyId) throws ServerException {
        companyDal.deleteById(companyId);
    }

    public CompanyDto getCompany(long companyID) throws ServerException {
      return companyDal.findById(companyID);

    }

    public List<CompanyDto> getAllCompanies(int page) throws  ServerException {
        Pageable pageable = PageRequest.of(page-1, Consts.LIMITPERPAGE);
        return companyDal.findAll(pageable);
    }

    private void companyValidation(Company company) throws ServerException {

        if (company.getName().isEmpty()){
            throw new ServerException(ErrorType.INVALID_NAME);
        }
        if (company.getName().length()<2){
            throw new ServerException(ErrorType.INVALID_NAME);

        }
        if (!company.getPhoneNumber().contains("a-z")){
            throw new ServerException(ErrorType.INVALID_PHONE_NUMBER);
        }
    }



}

