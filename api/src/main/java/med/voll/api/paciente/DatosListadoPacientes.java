package med.voll.api.paciente;

public record DatosListadoPacientes(String nombre,
                                    String email,
                                    String documentoIdentidad) {
    public DatosListadoPacientes(Paciente paciente) {
        this(paciente.getNombre(),
                paciente.getEmail(),
                paciente.getDocumentoIdentidad());
    }
}
