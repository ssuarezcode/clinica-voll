package med.voll.api.medico;



//Patrón DTO, Data Transfer Object.
//Usar a nivel de controller un objeto como intermediario,
//para mapear la información que llega desde el cliente hasta la API.


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired //Object injection with Spring (no recommended for testing purposes).
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public void registraMedico(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico) {
        medicoRepository.save(new Medico(datosRegistroMedico));
    }

    @GetMapping
    public Page<DatosListadoMedicos> listadoMedicos(@PageableDefault(size = 4) Pageable paginacion) {
        //return medicoRepository.findAll(paginacion).map(DatosListadoMedicos::new);
        return medicoRepository.findByActivoTrue(paginacion).map(DatosListadoMedicos::new);
    }

    @PutMapping
    @Transactional
    public void actualizaMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico) {
        Medico medico = medicoRepository.getReferenceById(datosActualizarMedico.id());
        medico.actualizarDatos(datosActualizarMedico);
    }

    //Logic exclusion
    @DeleteMapping("/{id}")
    @Transactional
    public void eliminarMedico(@PathVariable Long id) {
        Medico medico = medicoRepository.getReferenceById(id);
        medico.desactivarMedico();

        //Hard delete a registry from the DB (not recommended)
//    public void eliminarMedico(@PathVariable Long id){
//        Medico medico = medicoRepository.getReferenceById(id);
//        medicoRepository.delete(medico);
//    }
    }
}
