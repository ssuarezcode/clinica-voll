package med.voll.api.paciente;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public void registraPaciente (@RequestBody @Valid DatosRegistroPaciente datosRegistroPaciente) {
        pacienteRepository.save(new Paciente(datosRegistroPaciente));
    }

    @GetMapping
    public Page<DatosListadoPacientes> listadoPacientes(@PageableDefault(size = 4) Pageable paginacion) {
        //return repository.findAll(paginacion).map(DatosListadoPacientes::new);
        return pacienteRepository.findByActivoTrue(paginacion).map(DatosListadoPacientes::new);
    }

    @PutMapping
    @Transactional
    public void atualizaPaciente(@RequestBody @Valid DatosActualizarPaciente datosActualizarPaciente) {
        Paciente paciente = pacienteRepository.getReferenceById(datosActualizarPaciente.id());
        paciente.atualizarInformacion(datosActualizarPaciente);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void eliminarPaciente(@PathVariable Long id) {
        Paciente paciente = pacienteRepository.getReferenceById(id);
        paciente.desactivarPaciente();
    }


}