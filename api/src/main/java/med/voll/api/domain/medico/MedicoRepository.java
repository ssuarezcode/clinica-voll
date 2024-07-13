package med.voll.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findByActivoTrue(Pageable paginacion);

    @Query("""
            select m from Medico m
            where m.activo = true
            and m.especialidad = :especialidad
            and m.id not in (
                select c.medico.id from Consulta c
                where c.fecha = :fecha
            )
            """)
    List<Medico> findMedicosConEspecialidadEnFecha(@Param("especialidad") Especialidad especialidad, @Param("fecha") LocalDateTime fecha, Pageable pageable);

    @Query("""
            select m from Medico m
            where m.activo = true
            and m.especialidad = :especialidad
            and m.id not in (
                select c.medico.id from Consulta c
                where c.fecha = :fecha
            )
            order by rand()
            """)
    Medico seleccionarMedicoConEspecialidadEnFecha(@Param("especialidad") Especialidad especialidad, @Param("fecha") LocalDateTime fecha);

    @Query("""
            select m.activo
            from Medico m
            where m.id = :idMedico
            """)
    Boolean findActivoById(@Param("idMedico") Long idMedico);
}

