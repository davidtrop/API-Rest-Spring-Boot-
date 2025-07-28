package com.med.api.domain.medico;

import com.med.api.domain.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroMedico(
        @NotBlank(message = "Nome é obrigatório")
        String nome,
        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Email inválido")
        String email,
        @NotBlank(message = "Telefone é obrigatório")
        @Pattern(regexp = "^(\\([0-9]{2}\\)\\s?)?([0-9]{4,5}-[0-9]{4})$")
        String telefone,
        @NotBlank(message = "CRM é obrigatória")
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull(message = "Especialidade é obrigatório")
        Especialidade especialidade,
        @NotNull(message = "Dados do endereço são obrigatórios")
        @Valid
        DadosEndereco endereco
) {

}
