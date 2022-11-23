package com.osca.back.repository.dao.imple;


import com.osca.back.dto.ClienteDto;
import com.osca.back.repository.dao.ClienteDao;
import com.osca.back.repository.dao.mapper.ClienteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.lang.model.element.TypeElement;
import java.sql.Date;
import java.sql.Types;
import java.util.List;

@Repository
public class ClienteImpl implements ClienteDao {

    private static final Logger log = LoggerFactory.getLogger(ClienteImpl.class);

    private MapSqlParameterSource param;

    private NamedParameterJdbcTemplate template;

    /**
     * Método para obtener una lista de clientes
     * <p>
     * The first line of the function is the SQL query. The second line is the mapping
     *
     * @return A list of ClienteDto objects.
     */
    @Override
    public List<ClienteDto> findAll() {
        final String sql = "SELECT * FROM clientes";
        return template.query(sql, new ClienteMapper());
    }

    @Override
    public ClienteDto findById(Integer id) {
        final String sql = "SELECT * FROM clientes WHERE idclientes = :id";
        param.addValue("id", id, Types.INTEGER);
        ClienteDto clienteDto = new ClienteDto();
        try {
            clienteDto = template.queryForObject(sql, param, new ClienteMapper());
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id no encontrado", e.getCause());
        }
        return clienteDto;
    }

    @Override
    public Integer updateClient(Integer id, ClienteDto dto) {
        ClienteDto clienteDto = findById(id);
        final String sql = "UPDATE clientes SET nombre = :name, apellido = :lastName, email = :email, fecha= :date WHERE idclientes = :id";
        param.addValue("name", dto.getName(), Types.VARCHAR);
        param.addValue("lastName", dto.getLastName(), Types.VARCHAR);
        param.addValue("email", dto.getEmail(), Types.VARCHAR);
        param.addValue("date", dto.getDate(), Types.DATE);
        param.addValue("id", clienteDto.getId(), Types.INTEGER);
        return template.update(sql, param);
    }

    @Override
    public Integer deleteClient(Integer id) {
        final String sql = "DELETE FROM clientes WHERE idclientes = :id";
        param.addValue("id", id, Types.INTEGER);
        return  template.update(sql,param);
    }

    @Override
    public Integer save(ClienteDto clienteDto) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO clientes (nombre,apellido,email,fecha)").append("VALUES (:name,:lastName,:email,:date)");
        param.addValue("name", clienteDto.getName(), Types.VARCHAR);
        param.addValue("lastName", clienteDto.getLastName(), Types.VARCHAR);
        param.addValue("email", clienteDto.getEmail(), Types.VARCHAR);
        param.addValue("date", clienteDto.getDate(), Types.DATE);
        template.update(sql.toString(), param, keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Autowired
    public void setParam(MapSqlParameterSource param) {
        this.param = param;
    }

    @Autowired
    public void setTemplate(NamedParameterJdbcTemplate template) {
        this.template = template;
    }
}
