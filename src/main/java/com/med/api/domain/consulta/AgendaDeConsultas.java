package com.med.api.domain.consulta;


import com.med.api.domain.medico.Medico;
import com.med.api.domain.medico.MedicoRepository;
import com.med.api.domain.paciente.PacienteRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository repository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public void  agendar(DadosAgendamentoConsulta dados){

        if ((dados.idMedico() != null) && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidationException("Id do médico informado não existe.");
        }

        if (!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidationException("Id do paciente informado não existe.");
        }

        var medico   = escolharMedico(dados);
        var paciente = pacienteRepository.findById(dados.idPaciente()).get();

        var consulta = new Consulta(null, medico, paciente, dados.data(), null);

        repository.save(consulta);
    }

    private Medico escolharMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null){
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if (dados.especialidade() == null){
            throw new ValidationException("Especialidade é obrigatória quando médico não for escolhido!");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }

    public void cancelar(DadosCancelamentoConsulta dados) {
        if (!repository.existsById(dados.idConsulta())) {
            throw new ValidationException("Id da consulta informado não existe!");
        }

        var consulta = repository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());
    }
}
