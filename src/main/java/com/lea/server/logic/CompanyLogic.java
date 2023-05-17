package com.lea.server.logic;



import com.lea.server.beans.CompanyDto;
import com.lea.server.constanse.Consts;
import com.lea.server.dal.ICompanyDal;
import com.lea.server.entity.Company;
import com.lea.server.enums.ErrorType;
import com.lea.server.utils.ServerException;
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

