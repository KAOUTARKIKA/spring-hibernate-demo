package com.tp.dao;

import com.tp.entities.Category;

public interface ICategoryDao extends IDao<Category> {
    Category findByIdWithProducts(int id);
}