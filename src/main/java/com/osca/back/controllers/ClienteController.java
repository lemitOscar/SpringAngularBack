package com.osca.back.controllers;

import com.osca.back.dto.ClienteDto;
import com.osca.back.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/client")
public class ClienteController {

    private ClienteService clienteService;

    @GetMapping("/findAll")
    public ResponseEntity<List<ClienteDto>> getAll() {
        return new ResponseEntity<>(clienteService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ClienteDto> findById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(clienteService.findByid(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Integer> save (@RequestBody ClienteDto clienteDto){
        return  new ResponseEntity<>(clienteService.save(clienteDto),HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Integer> update (@RequestBody ClienteDto clienteDto, @PathVariable("id") Integer id ){
        return  new ResponseEntity<>(clienteService.update(id,clienteDto),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Integer> delete (@PathVariable("id") Integer id ){
        return  new ResponseEntity<>(clienteService.delete(id),HttpStatus.OK);
    }

    @Autowired
    public void setClienteService(ClienteService clienteService) {
        this.clienteService = clienteService;
    }
}
