package med.voll.api.domain.consultas;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consultas.Validacoes.ValidadorAgendamentoDeConsultas;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.pacientes.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    List<ValidadorAgendamentoDeConsultas> validadores;
    public void agendar(DadosAgendamentoConsulta dados) {
        if(!pacienteRepository.existsById(dados.id_paciente())) {
            throw new ValidacaoException("paciente nao exitente");
        }
        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("medico nao existente");
        }
        validadores.forEach(v -> v.validar(dados));
        var medico = escolherMedico(dados);
        if(medico == null) {
            throw new ValidacaoException("nenhum medico disponnivel para a data escolhida");
        }
        var paciente = pacienteRepository.findById(dados.id_paciente()).get();
        var consulta = new Consulta(null, medico.getId(), paciente.getId(), dados.data());
        consultaRepository.save(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if(dados.idMedico() != null ) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }
        if(dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade obrigatoria");
        }
        return medicoRepository.escolherMedicoAleatorio(dados.especialidade(), dados.data());
    }


}
