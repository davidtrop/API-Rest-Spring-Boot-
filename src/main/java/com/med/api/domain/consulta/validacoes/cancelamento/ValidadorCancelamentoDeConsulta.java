package com.med.api.domain.consulta.validacoes.cancelamento;

import com.med.api.domain.consulta.DadosCancelamentoConsulta;

public interface ValidadorCancelamentoDeConsulta {
    void validar(DadosCancelamentoConsulta dados);
}
