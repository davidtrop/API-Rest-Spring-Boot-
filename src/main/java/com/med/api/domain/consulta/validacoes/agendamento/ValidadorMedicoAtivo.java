package com.med.api.domain.consulta.validacoes.agendamento;

import com.med.api.domain.consulta.DadosAgendamentoConsulta;
import com.med.api.domain.medico.MedicoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoConsulta {

    @Autowired
    private MedicoRepository repository;

    public void validar(DadosAgendamentoConsulta dados){

        if(dados.idMedico() == null){ // Escolha opcional do medico
            return;
        }

        var medicoAtivo = repository.findAtivoById(dados.idMedico());
        if (!medicoAtivo) {
            throw new ValidationException("Consulta não pode ser agendado com o médico informado.");
        }
    }
}
