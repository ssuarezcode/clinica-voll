package med.voll.api.domain.paciente;

public record DatosDePacientes(Long id,
                               String nombre,
                               String email,
                               String documentoIdentidad) {
    public DatosDePacientes(Paciente paciente) {
        this(
                paciente.getId(),
                paciente.getNombre(),
                paciente.getEmail(),
                paciente.getDocumentoIdentidad());
    }
}