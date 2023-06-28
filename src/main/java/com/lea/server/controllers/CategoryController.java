package com.lea.server.controllers;

import com.lea.server.beans.CategoryDto;
import com.lea.server.entity.Category;
import com.lea.server.logic.CategoryLogic;
import com.lea.server.utils.JWTUtils;
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

    @GetMapping("/all")
    public List<CategoryDto> getAllCategories(@RequestParam("page") int page) throws ServerException {
       return categoryLogic.getAllCategories(page);
    }

    @GetMapping("/{categoryId}")
    public CategoryDto getCategoryByCategoryID(@PathVariable("categoryId") int categoryId) throws ServerException {
        return categoryLogic.getCategory(categoryId);
    }

}
