package com.osca.back.repository.dao.mapper;

import com.osca.back.dto.ClienteDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ClienteMapper implements RowMapper<ClienteDto> {


    @Override
    public ClienteDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setId(rs.getInt("idclientes"));
        clienteDto.setName(rs.getString("nombre"));
        clienteDto.setLastName(rs.getString("apellido"));
        clienteDto.setEmail(rs.getString("email"));
        clienteDto.setDate(rs.getDate("fecha"));
        return clienteDto;
    }
}
