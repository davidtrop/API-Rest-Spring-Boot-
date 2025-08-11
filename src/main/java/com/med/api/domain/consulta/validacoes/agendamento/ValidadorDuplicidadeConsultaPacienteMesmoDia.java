package com.med.api.domain.consulta.validacoes.agendamento;

import com.med.api.domain.consulta.ConsultaRepository;
import com.med.api.domain.consulta.DadosAgendamentoConsulta;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorDuplicidadeConsultaPacienteMesmoDia implements  ValidadorAgendamentoConsulta  {

    @Autowired
    private ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var duplicidade = repository.existsByPacienteIdAndDataBetween(dados.idPaciente(), primeiroHorario, ultimoHorario);
        if (duplicidade) {
            throw new ValidationException("Paciente já possui uma consulta nesse dia");
        }
    }
}
