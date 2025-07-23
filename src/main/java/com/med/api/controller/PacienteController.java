package com.med.api.controller;

import com.med.api.medico.DadosListagemMedico;
import com.med.api.paciente.DadosCadastroPaciente;
import com.med.api.paciente.DadosListagemPaciente;
import com.med.api.paciente.Paciente;
import com.med.api.paciente.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroPaciente dados) {
        repository.save(new Paciente(dados));
    }

    @GetMapping
    public Page<DadosListagemPaciente> listarPaciente(
            @PageableDefault(size = 10, sort = "nome") Pageable paginacao
    ) {
        return repository.findAll(paginacao).map(DadosListagemPaciente::new);
    }
}