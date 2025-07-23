package com.med.api.paciente;

import com.med.api.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPaciente(@NotNull Long id, String nome, String telefone, DadosEndereco endereco) {
}
