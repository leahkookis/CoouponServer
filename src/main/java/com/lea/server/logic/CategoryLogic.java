package com.lea.server.logic;
import com.lea.server.beans.CategoryDto;
import com.lea.server.constanse.Consts;
import com.lea.server.dal.ICategoryDal;
import com.lea.server.entity.Category;
import com.lea.server.enums.ErrorType;
import com.lea.server.utils.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryLogic {
    private ICategoryDal categoryDal;


    @Autowired
    public CategoryLogic(ICategoryDal categoryDal) {
        this.categoryDal = categoryDal;
    }

    public Long createCategory(Category category) throws ServerException {
        categoryValidation(category);
        categoryDal.save(category);
        return category.getId();
    }

    public void updateCategory(Category category) throws ServerException {
        categoryValidation(category);
        categoryDal.save(category);
    }

    public void removeCategory(long categoryId) throws ServerException {
        categoryDal.deleteById(categoryId);
    }

    public CategoryDto getCategory(long categoryId) throws ServerException {
        CategoryDto categoryDto=categoryDal.findById(categoryId);
        return categoryDto;
    }

    public Boolean isCategoryExist(long categoryId) throws ServerException {
        return categoryDal.findById(categoryId) != null;
    }

    public List<CategoryDto> getAllCategories(int page) throws ServerException {
        Pageable pageable = PageRequest.of(page-1, Consts.LIMITPERPAGE);
        return  categoryDal.findAll(pageable);
    }

    private void categoryValidation(Category category) throws ServerException {
        if(category.getName().isEmpty()){
            throw new ServerException(ErrorType.INVALID_NAME);
        }
        if (category.getName().length()<2){
            throw new ServerException(ErrorType.INVALID_NAME);
        }
        if (category.getName().length()>20){
            throw new ServerException(ErrorType.INVALID_NAME);
        }
    }

}

