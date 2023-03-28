package com.company.repository;

import com.company.container.ComponentContainer;
import com.company.entity.CardEntity;
import com.company.enums.CardStatus;
import com.company.util.HibernateUtil;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class CardRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveCard(CardEntity entity) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(entity);

        session.getTransaction().commit();
        session.clear();
    }

    public CardEntity log(String num, String password) {
        if (!password.equals("1")) {
            return null;
        }

        String sql = "from CardEntity where number = '" + num + "'";

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List<CardEntity> list = session.createQuery(sql).getResultList();

        session.getTransaction().commit();
        session.close();

        if (list.size() != 0) {
            return list.get(0);
        }
        return null;
    }

    public boolean isExistCard(String num) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String sql = "select id from CardEntity where number = '" + num + "'";

        Query query = session.createQuery("select count(id) FROM CardEntity where number = :num");
        query.setParameter("num", num);
        Long count = (Long) query.getSingleResult();

        Query query1 = session.createQuery(sql);
        Integer id = query1.getFirstResult();

        session.getTransaction().commit();
        session.clear();
        if (count == 0) {
            return false;
        }
        ComponentContainer.currentCard.setId(id);
        return true;
    }

    public List<CardEntity> selectAllCard() {
        String sql = "from CardEntity ";

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List<CardEntity> list = session.createQuery(sql).getResultList();

        session.getTransaction().commit();
        session.clear();
        return list;
    }

    public CardEntity getCardByNum(String num) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String sql = "from CardEntity where number ='" + num + "'";

        List<CardEntity> list = session.createQuery(sql).getResultList();

        session.getTransaction().commit();
        session.close();

        if (list.size() != 0) {
            return list.get(0);
        }
        return null;
    }

    public void changeStatus(Integer id, CardStatus cardStatus) {

        String sql = "UPDATE CardEntity  SET cardStatus = :status where id = :id ";

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery(sql);
        query.setParameter("status", cardStatus);
        query.setParameter("id", id);

        query.executeUpdate();

        session.getTransaction().commit();
        session.close();

    }

    public CardEntity selectCard(String num) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String sql = "from CardEntity where number = '" + num + "'";

        List<CardEntity> list = session.createQuery(sql).getResultList();

        session.getTransaction().commit();
        session.close();

        if (list.size() != 0) {
            return list.get(0);
        }
        return null;
    }

    public CardEntity showAdminCard() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String sql = "from CardEntity where id = 1";

        List<CardEntity> list = session.createQuery(sql).getResultList();

        session.getTransaction().commit();
        session.close();

        if (list.size() != 0) {
            return list.get(0);
        }
        return null;
    }

    public double checkBalance(String cardNum) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String sql = "select balance from CardEntity where number = '" + cardNum + "'";

        Query query = session.createQuery(sql);
        Double balance = (Double) query.getSingleResult();

        session.getTransaction().commit();
        session.clear();
        return balance;
    }

    public void transfer(String cardNum) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String sql = "update CardEntity set balance = (balance - 1400) where number = '" + cardNum + "'";

        Query query = session.createQuery(sql);

        query.executeUpdate();

        session.getTransaction().commit();
        session.clear();
    }

    public void profitByTransfer() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String sql = "update CardEntity set balance = (balance + 1400) where id =" + 1;

        Query query = session.createQuery(sql);
        query.executeUpdate();

        session.getTransaction().commit();
        session.clear();
    }

    public void fillBalance(String cardNum, double amount) {
        String sql = "update CardEntity set balance = (balance + :b) where number = :n";

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query query = session.createQuery(sql);
        query.setParameter("b", amount);
        query.setParameter("n", cardNum);

        query.executeUpdate();

        session.getTransaction().commit();
        session.clear();

    }

    public boolean isBlockedCard(String cardNum) {
        String sql = "select cardStatus from CardEntity where number = '" + cardNum + "'";
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query query = session.createQuery(sql);
        String s = query.toString();

        session.getTransaction().commit();
        session.clear();

        return s.equals("BLOCK");
    }
}
