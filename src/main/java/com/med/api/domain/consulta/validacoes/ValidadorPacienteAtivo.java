package com.med.api.domain.consulta.validacoes;

import com.med.api.domain.consulta.DadosAgendamentoConsulta;
import com.med.api.domain.paciente.PacienteRepository;
import jakarta.validation.ValidationException;

public class ValidadorPacienteAtivo {

    private PacienteRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
        var pacienteAtivo = repository.findATivoById(dados.idPaciente());
        if (pacienteAtivo) {
            throw new ValidationException("Consulta não pode ser agendada com paciente excluído.");
        }
    }
}
