package com.example.demomodsen.database.repository;

import com.example.demomodsen.database.entity.Organizer;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Repository
public class OrganizerRepository extends HibernateDaoSupport implements CrudRepository<Integer, Organizer>{

    @Autowired
    public void setSessionFactory(@Qualifier("sessionFactory") LocalSessionFactoryBean sessionFactory){
        setSessionFactory(Objects.requireNonNull(sessionFactory.getObject()));
    }


    @Override
    public Organizer save(Organizer entity) {
        getHibernateTemplate().save(entity);
        return entity;
    }

    @Override
    public void delete(Integer id) {
        Optional<Organizer> entity = findById(id);
        entity.ifPresent(organizer -> getHibernateTemplate().delete(organizer));
        getHibernateTemplate().flush();
    }

    @Override
    public Organizer update(Organizer entity) {
        getHibernateTemplate().update(entity);
        return entity;
    }

    @Override
    public Optional<Organizer> findById(Integer id) {
        return Optional.ofNullable(getHibernateTemplate().get(Organizer.class,id));
    }

    @Override
    public List<Organizer> findAll() {
        DetachedCriteria dc=DetachedCriteria.forClass(Organizer.class);
        List<Organizer> list = (List<Organizer>) getHibernateTemplate().findByCriteria(dc);
        return list;    }
}
