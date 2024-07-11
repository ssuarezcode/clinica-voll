package med.voll.api.paciente;

import jakarta.validation.Valid;
import med.voll.api.direccion.DatosDireccion;

public record DatosActualizarPaciente(Long id,
                                      String nombre,
                                      String telefono,
                                      @Valid DatosDireccion direccion) {
}
