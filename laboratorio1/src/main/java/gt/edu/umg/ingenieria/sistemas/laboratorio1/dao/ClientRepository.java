package gt.edu.umg.ingenieria.sistemas.laboratorio1.dao;

import gt.edu.umg.ingenieria.sistemas.laboratorio1.model.Client;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Josué Barillas (jbarillas)
 */

@Repository
public interface ClientRepository extends CrudRepository<Client, Long>{
    @Nullable
    Client findClientByNit(@Nullable String nit);
    
    @Query("select c from Client c where upper(concat(firstName,' ',lastName)) like upper(?1)")
    List<Client> findClientByName(String nombre);
}
