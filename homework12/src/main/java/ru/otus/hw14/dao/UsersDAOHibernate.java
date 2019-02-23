package dao;

import dataSet.UserDataSet;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UsersDAOHibernate {
    private Session session;


    public UsersDAOHibernate(Session session) {
        this.session = session;
    }

    public void save(UserDataSet dataSet) {
        session.save(dataSet);
    }

    public UserDataSet read(long id) {
        return session.load(UserDataSet.class, id);
    }

    public List<UserDataSet> readAll() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserDataSet> criteria = builder.createQuery(UserDataSet.class);
        criteria.from(UserDataSet.class);
        return session.createQuery(criteria).list();
    }

    public Long count() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<UserDataSet> from = criteria.from(UserDataSet.class);
        criteria.select(builder.count(from));
        return session.createQuery(criteria).getSingleResult();
    }
}
