package com.project.server.controllers;

import com.project.server.beans.CategoryDto;
import com.project.server.entity.Category;
import com.project.server.logic.CategoryLogic;
import com.project.server.utils.JWTUtils;
import com.project.server.utils.ServerException;
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
    public long createCategory(@RequestHeader String authorization, @RequestBody Category category) throws Exception {
        JWTUtils.validateToken(authorization);
        return categoryLogic.createCategory(category);

    }

    @DeleteMapping("/{categoryId}")
    public void removeCategory(@RequestHeader String authorization, @PathVariable("categoryId") int categoryId) throws Exception {
        JWTUtils.validateToken(authorization);
        categoryLogic.removeCategory(categoryId);
    }

    @PutMapping
    public void updateCategory(@RequestHeader String authorization, @RequestBody Category category) throws Exception {
        JWTUtils.validateToken(authorization);
        categoryLogic.updateCategory(category);
    }

    @GetMapping
    public List<CategoryDto> getAllCategories() throws ServerException {
       return categoryLogic.getAllCategories();
    }

    @GetMapping("/{categoryId}")
    public CategoryDto getCategoryByCategoryID(@PathVariable("categoryId") int categoryId) throws ServerException {
        return categoryLogic.getCategory(categoryId);
    }

}
