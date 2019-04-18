package gt.edu.umg.ingenieria.sistemas.laboratorio1.service;

import gt.edu.umg.ingenieria.sistemas.laboratorio1.dao.ClientRepository;
import gt.edu.umg.ingenieria.sistemas.laboratorio1.model.Client;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Josué Barillas (jbarillas)
 */
@Service
public class ClientService {
    @Autowired
    private ClientRepository clienteRep;
    
    public Client insertar(Client c1){
        return clienteRep.save(c1);
    }
    
    public List<Client> buscarTodos(){
        List<Client> lista = (List<Client>) this.clienteRep.findAll();
        return lista;
    }
    
    public Client buscarPorNit(String nit){
        Client c1 = this.clienteRep.findClientByNit(nit);
        return c1;
    }
    
    public List<Client> buscarPorNombreApellido(String nombre){
        nombre = nombre.replace("*", "_");
        nombre = "%" + nombre + "%";
        List<Client> lista = this.clienteRep.findClientByName(nombre);
        return lista;
    }
    
    public Client editarCliente(Client c1){
        return this.clienteRep.save(c1);
    }
    
    public Client editarClienteNit(Long id, String nit){
        Client c1 = this.clienteRep.findById(id).get();
        c1.setNit(nit);
        return this.clienteRep.save(c1);
    }
    
    public Client editarClienteNombre(Long id, String nit, String nombre, String apellido){
        Client c1 = this.clienteRep.findById(id).get();
        c1.setNit(nit);
        c1.setFirstName(nombre);
        c1.setLastName(apellido);
        return this.clienteRep.save(c1);
    }
    
}