package med.voll.api.domain.consultas.Validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consultas.AgendaDeConsultas;
import med.voll.api.domain.consultas.Consulta;
import med.voll.api.domain.consultas.ConsultaRepository;
import med.voll.api.domain.consultas.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteDia implements ValidadorAgendamentoDeConsultas{

    @Autowired
    private ConsultaRepository consultaRepository;
    public void validar(DadosAgendamentoConsulta dados) {
        var dia = validarDia(dados);
        if(dia) {
            throw new ValidacaoException("Consultas no mesmo dia sao inavlidas");
        }
    }
    public boolean validarDia(DadosAgendamentoConsulta dados) {
        var consultaMarcada = consultaRepository.findAllById_Paciente(dados.id_paciente());
        for (Consulta consulta: consultaMarcada) {
            if(dados.data().getDayOfYear() == consulta.getData().getDayOfYear() ) {
                return true;
            }
        }
        return false;
    }
}
