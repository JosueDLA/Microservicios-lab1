# Microservicios for Clients

## RESTful Services:
- Create new client
  - Only adults can register
  - NIT should have maximum 10 characters and only digits
  - First and last name must start with uppercase, the rest must be lowercase
- Find all clients
- Find client by NIT
- Find by first and/or Last name
  - '*' is a wildcard
- Update client
  - Update a client
  - Update only the NIT of a client
  - Update the NIT, first and last name of a client
- Customer report

## EndPoints
- https://localhost:9001/clientes/creaCliente
- https://localhost:9001/clientes/buscarTodos
- https://localhost:9001/clientes/buscarPorNit?nit=12345
- https://localhost:9001/clientes/buscarPorNombreApellido?nombre=*perez
- https://localhost:9001/clientes/editarCliente/1
- https://localhost:9001/clientes/editarCliente/1/12345
- https://localhost:9001/clientes/editarCliente/1/12345/Juan/Perez
- https://localhost:9001/clientes/generarReporteClientes

