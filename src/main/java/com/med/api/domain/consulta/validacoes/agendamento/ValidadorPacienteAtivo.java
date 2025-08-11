package com.med.api.domain.consulta.validacoes.agendamento;

import com.med.api.domain.consulta.DadosAgendamentoConsulta;
import com.med.api.domain.paciente.PacienteRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoConsulta {

    @Autowired
    private PacienteRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
        var pacienteAtivo = repository.findATivoById(dados.idPaciente());
        if (!pacienteAtivo) {
            throw new ValidationException("Consulta não pode ser agendada com paciente excluído.");
        }
    }
}
