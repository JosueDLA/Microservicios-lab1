package gt.edu.umg.ingenieria.sistemas.laboratorio1.controller;

import gt.edu.umg.ingenieria.sistemas.laboratorio1.model.Client;
import gt.edu.umg.ingenieria.sistemas.laboratorio1.service.ClientService;
import gt.edu.umg.ingenieria.sistemas.laboratorio1.service.ReportService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Josué Barillas (jbarillas)
 */

@RestController
@RequestMapping("/clientes")
public class ClientController {
    
    @Autowired
    private ClientService clientService;
    
    @PostMapping("/creaCliente")
    public Client loader(@RequestBody(required = true) Client c1){
        return this.clientService.insertar(c1);
    }
    
    @GetMapping("/buscarTodos")
    public List<Client> buscarTodos(){
        return this.clientService.buscarTodos();
    }
    
    @GetMapping("/buscarPorNit")
    public Client buscarPorNit(String nit){
        return this.clientService.buscarPorNit(nit);
    }
    
    @GetMapping("/buscarPorNombreApellido")
    public List<Client> buscarPorNombreApellido(String nombre){
        return this.clientService.buscarPorNombreApellido(nombre);
    }
    
    @PutMapping("/editarCliente/{id}")
    public Client editarCliente(@PathVariable(required = true) long id, @RequestBody(required = true) Client c1){
        return this.clientService.editarCliente(c1);
    }
    
    @PutMapping("/editarCliente/{id}/{nit}")
    public Client editarClienteNit(@PathVariable(required = true) long id, @PathVariable(required = true) String nit){
        return this.clientService.editarClienteNit(id, nit);
    }
    
    @PutMapping("/editarCliente/{id}/{nit}/{nombre}/{apellido}")
    public Client editarClienteNombre(@PathVariable(required = true) long id, @PathVariable(required = true) String nit, @PathVariable(required = true) String nombre, @PathVariable(required = true) String apellido){
        return this.clientService.editarClienteNombre(id, nit, nombre, apellido);
    }
}
