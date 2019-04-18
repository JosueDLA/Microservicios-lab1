package gt.edu.umg.ingenieria.sistemas.laboratorio1.service;

import gt.edu.umg.ingenieria.sistemas.laboratorio1.dao.ClientRepository;
import gt.edu.umg.ingenieria.sistemas.laboratorio1.model.Client;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

/**
 *
 * @author Josué Barillas (jbarillas)
 */

@Service
public class ReportService {
    @Autowired
    private ClientRepository clientRepository;
    
    public String generarReporteClientes(){
        List<Client> lista = (List<Client>) this.clientRepository.findAll();
        
        StringBuilder s1 = new StringBuilder();
        s1.append("NOMBRE \t APELLIDO \t NIT \n");
        
        for(Client c1 : lista){
            s1.append(c1.getFirstName());
            s1.append("\t");
            s1.append(c1.getLastName());
            s1.append("\t");
            s1.append(c1.getNit());
            s1.append("\n");
        }
        
        return s1.toString();
    }
}
