package com.osca.back.service;

import com.osca.back.dto.ClienteDto;
import com.osca.back.repository.dao.imple.ClienteImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {


    private ClienteImpl serClient;

    @Autowired
    public void setSerClient(ClienteImpl serClient) {
        this.serClient = serClient;
    }

    public List<ClienteDto> getAll() {
        return serClient.findAll();
    }//lista client

    public ClienteDto findByid(Integer id) {
        return serClient.findById(id);
    }//buscar por id

    public Integer save(ClienteDto clienteDto) {
        return serClient.save(clienteDto);
    }//crear

    public Integer update(Integer id, ClienteDto dto) {
        return serClient.updateClient(id, dto);
    }//update

    public Integer delete(Integer id) {
        return serClient.deleteClient(id);
    }//delete
}
