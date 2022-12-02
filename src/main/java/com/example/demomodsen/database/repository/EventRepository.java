package com.example.demomodsen.database.repository;

import com.example.demomodsen.database.entity.Event;
import com.example.demomodsen.database.entity.QEvent;
import com.example.demomodsen.database.querydsl.QPredicates;
import com.example.demomodsen.dto.EventFilter;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.example.demomodsen.database.entity.QEvent.event;

@Repository
public class EventRepository extends HibernateDaoSupport implements CrudRepository<Integer,Event>, EventFilterRepository{

    @Autowired
    public void setSessionFactory(@Qualifier("sessionFactory") LocalSessionFactoryBean sessionFactory){
        setSessionFactory(Objects.requireNonNull(sessionFactory.getObject()));
    }

    @Override
    public Event save(Event entity) {
        getHibernateTemplate().save(entity);
        return entity;    }

    @Override
    public void delete(Integer id) {
        Optional<Event> entity = findById(id);
        entity.ifPresent(event -> getHibernateTemplate().delete(event));
        getHibernateTemplate().flush();
    }

    @Override
    public Event update(Event entity) {
        getHibernateTemplate().update(entity);
        return entity;
    }

    @Override
    public Optional<Event> findById(Integer id) {
        return Optional.ofNullable(getHibernateTemplate().get(Event.class,id));
    }

    @Override
    public List<Event> findAll() {
        DetachedCriteria dc=DetachedCriteria.forClass(Event.class);
        List<Event> list = (List<Event>) getHibernateTemplate().findByCriteria(dc);
        return list;
    }

    @Override
    public List<Event> findAllByFilter(EventFilter filter) {
        var predicate = QPredicates.builder()
                .add(filter.desc(), event.desc::containsIgnoreCase)
                .add(filter.time(), event.time::before)
                .add(filter.title(), event.title::containsIgnoreCase)
                .build();

        return new JPAQuery<Event>(getSessionFactory().createEntityManager())
                .select(event)
                .from(event)
                .where(predicate)
                .fetch();
    }
}
