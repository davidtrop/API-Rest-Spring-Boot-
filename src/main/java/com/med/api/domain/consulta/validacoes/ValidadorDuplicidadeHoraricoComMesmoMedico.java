package com.med.api.domain.consulta.validacoes;

import com.med.api.domain.consulta.ConsultaRepository;
import com.med.api.domain.consulta.DadosAgendamentoConsulta;
import jakarta.validation.ValidationException;

public class ValidadorDuplicidadeHoraricoComMesmoMedico {

    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
        // O médico possui outra consulta nesse mesmo horário
        var duplicidade = repository.existsByMedicoIdAndData(dados.idMedico(), dados.data());
        if (duplicidade) {
            throw new ValidationException("O médico já possui uma consulta nesse mesmo horário.");
        }
    }
}
