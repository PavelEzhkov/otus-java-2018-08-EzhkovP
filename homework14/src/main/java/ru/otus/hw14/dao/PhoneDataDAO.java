package ru.otus.hw14.dao;

import org.hibernate.Session;
import ru.otus.hw14.dataSet.PhoneDataSet;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class PhoneDataDAO {
    private Session session;
    public PhoneDataDAO(Session session) {
        this.session = session;
    }

    public List<PhoneDataSet> readAll() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PhoneDataSet> criteria = builder.createQuery(PhoneDataSet.class);
        criteria.from(PhoneDataSet.class);
        return session.createQuery(criteria).list();
    }
}
