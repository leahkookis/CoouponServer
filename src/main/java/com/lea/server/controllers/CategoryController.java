package com.lea.server.controllers;

import com.lea.server.beans.CategoryDto;
import com.lea.server.entity.Category;
import com.lea.server.logic.CategoryLogic;
import com.lea.server.utils.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryLogic categoryLogic;

    @Autowired
    public CategoryController(CategoryLogic categoryLogic) {
        this.categoryLogic = categoryLogic;
    }

    @PostMapping
    public long createCategory(@RequestBody Category category) throws ServerException {
        return categoryLogic.createCategory(category);

    }

    @DeleteMapping("/{categoryId}")
    public void removeCategory(@PathVariable("categoryId") int categoryId) throws ServerException {
        categoryLogic.removeCategory(categoryId);
    }

    @PutMapping
    public void updateCategory(@RequestBody Category category) throws ServerException {
        categoryLogic.updateCategory(category);
    }

    @GetMapping({"/All"})
    public List<CategoryDto> getAllCategories(@RequestParam("page") int page) throws ServerException {
       return categoryLogic.getAllCategories(page);
    }

    @GetMapping("/{categoryId}")
    public CategoryDto getCategoryByCategoryID(@PathVariable("categoryId") int categoryId) throws ServerException {
        return categoryLogic.getCategory(categoryId);
    }

}
