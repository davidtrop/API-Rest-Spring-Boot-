package com.med.api.domain.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    boolean existsByPacienteIdAndDataBetween(Long pacienteId, LocalDateTime inicio, LocalDateTime fim);

    boolean existsByMedicoIdAndData(Long aLong, @NotNull @Future LocalDateTime data);
}
