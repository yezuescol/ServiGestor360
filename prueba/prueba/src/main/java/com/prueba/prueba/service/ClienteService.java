package com.prueba.prueba.service; // Define el paquete donde se ubica la capa de servicio

// Importa la clase List para manejar colecciones de clientes
import java.util.List;

// Importa Optional para manejar búsquedas que pueden o no devolver un resultado
import java.util.Optional;

// Importa la anotación Service para indicar que esta clase pertenece a la capa de lógica de negocio
import org.springframework.stereotype.Service;

// Importa la entidad Cliente
import com.prueba.prueba.model.Cliente;

// Importa el repositorio ClienteRepository para acceder a la base de datos
import com.prueba.prueba.repository.ClienteRepository;

/*
    @Service indica que esta clase pertenece
    a la capa de servicios.

    Esta capa contiene la lógica del negocio
    y sirve como intermediaria entre el Controller
    y el Repository.
*/
@Service
public class ClienteService {

    /*
        Se declara una variable final del repositorio.

        El Repository permite acceder a los datos
        de la tabla cliente en MySQL.
    */
    private final ClienteRepository clienteRepository;

    /*
        Constructor de la clase ClienteService.

        Spring Boot utiliza este constructor para inyectar
        automáticamente una instancia de ClienteRepository.

        Esta es una forma recomendada de inyección de dependencias.
    */
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    /*
        Método para listar todos los clientes.

        Llama al método findAll() del Repository,
        el cual consulta todos los registros de la tabla cliente.
    */
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    /*
        Método para buscar un cliente por su ID.

        Retorna un Optional<Cliente>, porque puede ocurrir
        que el cliente exista o que no exista en la base de datos.
    */
    public Optional<Cliente> buscarClientePorId(Long idCliente) {
        return clienteRepository.findById(idCliente);
    }

    /*
        Método para guardar un cliente.

        Si el cliente no tiene ID, se crea un nuevo registro.
        Si el cliente ya tiene ID, se actualiza el registro existente.

        Aquí más adelante podemos agregar validaciones de negocio.
    */
    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    /*
        Método para eliminar un cliente por su ID.

        Antes de eliminar, se valida si el cliente existe.
        Esto evita intentar eliminar registros inexistentes.
    */
    public boolean eliminarCliente(Long idCliente) {

        // Verifica si existe un cliente con el ID recibido
        if (clienteRepository.existsById(idCliente)) {

            // Elimina el cliente por ID
            clienteRepository.deleteById(idCliente);

            // Retorna true para indicar que sí se eliminó
            return true;
        }

        // Retorna false si el cliente no existe
        return false;
    }
}
