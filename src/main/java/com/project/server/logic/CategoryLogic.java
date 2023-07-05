package com.project.server.logic;
import com.project.server.entity.Category;
import com.project.server.enums.ErrorType;
import com.project.server.utils.ServerException;
import com.project.server.beans.CategoryDto;
import com.project.server.constanse.Consts;
import com.project.server.dal.ICategoryDal;
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

    public List<CategoryDto> getAllCategories() throws ServerException {
        Pageable pageable = PageRequest.of(0, Consts.LIMITPERPAGE);
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

