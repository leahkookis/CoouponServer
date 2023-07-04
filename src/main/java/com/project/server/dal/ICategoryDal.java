package com.project.server.dal;

import com.project.server.beans.CategoryDto;
import com.project.server.entity.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICategoryDal extends CrudRepository<Category, Long> {


  @Query("SELECT new com.project.server.beans.CategoryDto(cat.id, cat.name) FROM Category cat WHERE cat.id= :id")
  CategoryDto findById(@Param("id") long id);

  @Query("SELECT new com.project.server.beans.CategoryDto(cat.id, cat.name) FROM Category cat")
  List<CategoryDto> findAll(Pageable pageable);


}
