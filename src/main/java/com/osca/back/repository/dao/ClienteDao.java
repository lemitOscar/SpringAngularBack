package com.osca.back.repository.dao;

import com.osca.back.dto.ClienteDto;
import jdk.jfr.Registered;

import java.util.List;

public interface ClienteDao {

    List<ClienteDto> findAll();

    ClienteDto findById(Integer id);

    Integer updateClient(Integer id, ClienteDto clienteDto);

    Integer deleteClient(Integer id);

    Integer save(ClienteDto clienteDto);
}
