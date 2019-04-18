package gt.edu.umg.ingenieria.sistemas.laboratorio1.controller;

import gt.edu.umg.ingenieria.sistemas.laboratorio1.model.Client;
import gt.edu.umg.ingenieria.sistemas.laboratorio1.service.ClientService;
import gt.edu.umg.ingenieria.sistemas.laboratorio1.service.ReportService;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    
    @Autowired
    private ReportService reportService;
    
    private static String server = "";
    private static String extension = ".html";
    
    @PostMapping("/creaCliente")
    public Client loader(@RequestBody(required = true) Client c1) throws Exception {
        
        c1.setFirstName(FirstName(c1.getFirstName()));
        c1.setLastName(LastName(c1.getLastName()));
        
        if(!Age(c1.getBirthDate()))
            throw new Exception("Lo Sentimos el sistema solo permite registro e usuarios mayores de edad");
        
        if(!Nit(c1.getNit()))
            throw new Exception("Nit Invalido.");
            
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
    public Client editarCliente(@PathVariable(required = true) long id, @RequestBody(required = true) Client c1) throws Exception{
        
        c1.setFirstName(FirstName(c1.getFirstName()));
        c1.setLastName(LastName(c1.getLastName()));
        
        if(!Age(c1.getBirthDate()))
            throw new Exception("Lo Sentimos el sistema solo permite registro e usuarios mayores de edad");
        
        if(!Nit(c1.getNit()))
            throw new Exception("Nit Invalido.");
        
        return this.clientService.editarCliente(c1);
    }
    
    @PutMapping("/editarCliente/{id}/{nit}")
    public Client editarClienteNit(@PathVariable(required = true) long id, @PathVariable(required = true) String nit) throws Exception{
        if(!Nit(nit))
            throw new Exception("Nit Invalido.");
        
        return this.clientService.editarClienteNit(id, nit);
    }
    
    @PutMapping("/editarCliente/{id}/{nit}/{nombre}/{apellido}")
    public Client editarClienteNombre(@PathVariable(required = true) long id, @PathVariable(required = true) String nit, @PathVariable(required = true) String nombre, @PathVariable(required = true) String apellido) throws Exception{
        nombre = FirstName(nombre);
        apellido = LastName(apellido);
        
        if(!Nit(nit))
            throw new Exception("Nit Invalido.");
        
        return this.clientService.editarClienteNombre(id, nit, nombre, apellido);
    }
    
    @GetMapping("/generarReporteClientes")
    public String generarReporteClientes() throws IOException{
        String server = Paths.get(".").toAbsolutePath().normalize().toString();
        //int num = new File(path).listFiles().length;
        
        List<String> filenames = new ArrayList<>();
        int htmls = 1;
        
        DirectoryStream<Path> directorio = Files.newDirectoryStream(Paths.get(server));
        
        for(Path path : directorio){
            filenames.add(path.toString());
            
            if(path.toString().toLowerCase().contains("clientes_"))
                htmls++;
        }
        
        File reporte = new File("Clientes_" + htmls + ".html");
        BufferedWriter writer = new BufferedWriter(new FileWriter(reporte));
        writer.write(this.reportService.generarReporteClientes());
        writer.close();
        
        return "Se ha generado el reporte "+ "Clientes_" + htmls + ".html en: " + server ;
    }
    
    private Boolean Age(Date birthDate){
        LocalDate date = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int age = Period.between(date, LocalDate.now()).getYears();
        
        if(age < 18)
            return false;
        else
            return true;
    }
    
    private Boolean Nit(String nit){
        if(nit.matches("[0-9]{1,10}"))
            return true;
        else
            return false;
    }
    
    private String FirstName(String name){
        String firstName = name.substring(0,1).toUpperCase()+name.substring(1).toLowerCase();
        return firstName;
    }
    
    private String LastName(String name){
        String lastName = name.substring(0,1).toUpperCase()+name.substring(1).toLowerCase();
        return lastName;
    }
}