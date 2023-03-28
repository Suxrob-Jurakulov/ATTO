package com.company.repository;

import com.company.entity.TransferEntity;
import com.company.mapper.TransferListMapper;
import com.company.dto.TransferDTO;
import com.company.util.HibernateUtil;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class TransferRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void writeHistory(Integer cardId, Integer terId) {
        String sql = "insert into transfer(card_id, ter_id, amount) values (?, ?, ?)";
        PreparedStatementSetter statement = ps -> {
            ps.setInt(1, cardId);
            ps.setInt(2, terId);
            ps.setInt(3, 1400);
        };
        jdbcTemplate.update(sql, statement);
    }

    public List<TransferEntity> transferList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String sql = "select c.id as cardId, c.number as cardNum, ter.id as terID, " +
                "ter.terNum, ter.location, t.id as t_id, t.time, t.amount\n" +
                "from TransferEntity t \n" +
                "join CardEntity c on c.id = t.card_id\n" +
                "join TerminalEntity ter on ter.id = t.ter_id";

        List<TransferEntity> list = session.createQuery(sql).getResultList();

        session.getTransaction().commit();
        session.close();

       return list;
    }


    public List<TransferEntity> transferList(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String sql = "select c.id as cardId, c.number as cardNum, ter.id as terID, ter.terNum, ter.location, " +
                "t.id as t_id, t.time, t.amount\n" +
                "from TransferEntity t \n" +
                "join CardEntity c on c.id = t.card_id\n" +
                "join TerminalEntity ter on ter.id = t.ter_id\n" +
                "where t.card_id =" + id;
        List<TransferEntity> list = session.createQuery(sql).getResultList();

        session.getTransaction().commit();
        session.close();

        return list;
    }

    public List<TransferListMapper> transferListByTerminal(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String sql = "select c.id as cardId, c.number as cardNum, ter.id as terID, ter.terNum, ter.location, t.id as t_id, t.time, t.amount\n" +
                "from TransferEntity t \n" +
                "join CardEntity c on c.id = t.card_id\n" +
                "join TerminalEntity ter on ter.id = t.ter_id\n" +
                "where t.ter_id = " + id;
        List<TransferListMapper> list = session.createQuery(sql).getResultList();

        session.getTransaction().commit();
        session.close();

        return list;
    }

}
