package com.company.repository;

import com.company.container.ComponentContainer;
import com.company.dto.TerminalDTO;
import com.company.entity.TerminalEntity;
import com.company.util.HibernateUtil;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class TerminalRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean isExistTerminal(String terNum) {
//        String sql = "select count(*) from terminal where ter_num = '" + terNum + "'";
//        String query = "select id from terminal where ter_num = '" + terNum + "'";
//        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
//        if (count == 1) {
//            ComponentContainer.currentTerminal.setId(jdbcTemplate.queryForObject(query, Integer.class));
//            return true;
//        }
//        return false;

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        String s2 = "select id from TerminalEntity where terNum = '" + terNum + "'";

        Query query = session.createQuery("select count(id) FROM TerminalEntity where terNum = :num");
        query.setParameter("num", terNum);

        Long count = (Long) query.getSingleResult();

        Query query1 = session.createQuery(s2);
        Integer id = (Integer) query1.getSingleResult();

        session.getTransaction().commit();
        session.clear();
        if (count == 1) {
            ComponentContainer.currentTerminal.setId(id);
            return true;
        }
        return false;
    }

    public void saveTerminal(TerminalEntity entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(entity);

        session.getTransaction().commit();
        session.close();
    }

    public List<TerminalEntity> selectAllTerminal() {
        String sql = "from TerminalEntity";
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List<TerminalEntity> list = session.createQuery(sql).getResultList();

        session.getTransaction().commit();
        session.close();

        return list;
    }
}
