package com.med.api.domain.consulta.validacoes;

import com.med.api.domain.consulta.DadosAgendamentoConsulta;
import jakarta.validation.ValidationException;

import java.time.DayOfWeek;

public class ValidadorHorarioFuncionamento {

    public void validar(DadosAgendamentoConsulta dados){
        var dataConsulta = dados.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAbertura  = dataConsulta.getHour() < 7;
        var depoisDoEncerramento = dataConsulta.getHour() > 18;

        if (domingo || antesDaAbertura || depoisDoEncerramento){
            throw  new ValidationException("Consulta fora do horário de funcionamento da clínica.");
        }
    }
}
