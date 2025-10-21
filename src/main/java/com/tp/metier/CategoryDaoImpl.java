package com.tp.metier;

import com.tp.dao.ICategoryDao;
import com.tp.entities.Category;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CategoryDaoImpl implements ICategoryDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean create(Category category) {
        sessionFactory.getCurrentSession().save(category);
        return true;
    }

    @Override
    public boolean delete(Category category) {
        sessionFactory.getCurrentSession().delete(category);
        return true;
    }

    @Override
    public boolean update(Category category) {
        sessionFactory.getCurrentSession().update(category);
        return true;
    }

    @Override
    public Category findById(int id) {
        return sessionFactory.getCurrentSession().get(Category.class, id);
    }

    @Override
    public List<Category> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Category", Category.class)
                .list();
    }

    @Override
    public Category findByIdWithProducts(int id) {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT c FROM Category c LEFT JOIN FETCH c.products WHERE c.id = :id", Category.class)
                .setParameter("id", id)
                .uniqueResult();
    }
}