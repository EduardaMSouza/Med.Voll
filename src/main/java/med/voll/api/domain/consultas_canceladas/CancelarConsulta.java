package med.voll.api.domain.consultas_canceladas;


import jakarta.transaction.Transactional;
import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consultas.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CancelarConsulta {

    @Autowired
    private ConsultaCanceladaRepository consultaCanceladaRepository;
    @Autowired
    private ConsultaRepository consultaRepository;

    @Transactional
    public void cancelarConsulta(DadosCancelamento dados) {
        if(!consultaRepository.existsById(dados.id())) {
            throw new ValidacaoException("Consulta nao existe");
        }
        var consulta = consultaRepository.findById(dados.id());
        LocalDateTime agora = LocalDateTime.now().plusHours(24);
        if( consulta.get().getData().isBefore(agora) ) {
            throw new ValidacaoException("so pode ser cancelada 24 horas antes da consulta");
        }
        var consultaCancelada = new ConsultaCancelada(consulta.get().getId(),
                consulta.get().getIdMedico(),
                consulta.get().getId_paciente(),
                consulta.get().getData(),
                dados.motivo());
        System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkk" + consultaCancelada.getMotivo());
        consultaCanceladaRepository.save(consultaCancelada);
        consultaRepository.deleteById(dados.id());
    }

}
