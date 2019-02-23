package ru.otus.hw14.dao;

import org.hibernate.Session;
import ru.otus.hw14.dataSet.AddressDataSet;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class AddressDataDAO {
    private Session session;
    public AddressDataDAO(Session session) {
        this.session =session;
    }

    public List<AddressDataSet> readAll() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<AddressDataSet> criteria = builder.createQuery(AddressDataSet.class);
        criteria.from(AddressDataSet.class);
        return session.createQuery(criteria).list();
    }
}
