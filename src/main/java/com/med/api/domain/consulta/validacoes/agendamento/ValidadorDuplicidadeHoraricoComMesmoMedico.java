package com.med.api.domain.consulta.validacoes.agendamento;

import com.med.api.domain.consulta.ConsultaRepository;
import com.med.api.domain.consulta.DadosAgendamentoConsulta;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorDuplicidadeHoraricoComMesmoMedico implements ValidadorAgendamentoConsulta {

    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
        // O médico possui outra consulta nesse mesmo horário
        var duplicidade = repository.existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(dados.idMedico(), dados.data());
        if (duplicidade) {
            throw new ValidationException("O médico já possui uma consulta nesse mesmo horário.");
        }
    }
}
