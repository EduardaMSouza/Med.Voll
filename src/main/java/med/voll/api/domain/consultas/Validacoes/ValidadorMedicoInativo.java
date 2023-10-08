package med.voll.api.domain.consultas.Validacoes;

import jakarta.transaction.Transactional;
import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consultas.DadosAgendamentoConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoInativo implements ValidadorAgendamentoDeConsultas{


    @Autowired
    private MedicoRepository medicoRepository;
    @Transactional
    public void validar(DadosAgendamentoConsulta dados) {
        if(dados.idMedico() == null) {
            return;
        }
        var ativo = medicoAtivo(dados.idMedico());
        if(!ativo) {
            throw new ValidacaoException("Medico inativo");
        }
    }
    @Transactional
    public boolean medicoAtivo(Long idMedico) {
        var medico = medicoRepository.findById(idMedico).get();
        return medico.getAtivo();
    }
}
