package com.company.mapper;

import com.company.dto.CardDTO;
import com.company.dto.TerminalDTO;
import com.company.dto.TransferDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransferListMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        TransferDTO transfer = new TransferDTO();
        transfer.setId(rs.getInt("t_id"));
        transfer.setTime(rs.getTimestamp("time"));
        transfer.setAmount(rs.getDouble("amount"));

        CardDTO card = new CardDTO();
        card.setId(rs.getInt("cardId"));
        card.setNumber(rs.getString("cardNum"));

        TerminalDTO terminal = new TerminalDTO();
        terminal.setId(rs.getInt("terId"));
        terminal.setTerNum(rs.getString("ter_num"));
        terminal.setLocation(rs.getString("location"));

        transfer.setCard(card);
        transfer.setTerminal(terminal);

        return transfer;
    }
}
