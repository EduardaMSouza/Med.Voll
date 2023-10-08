package med.voll.api.domain.consultas.Validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consultas.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorario implements ValidadorAgendamentoDeConsultas{
    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesAbertura = dataConsulta.getHour() < 7;
        var depoisAbertura = dataConsulta.getHour() > 18;
        if(domingo || antesAbertura || depoisAbertura) {
            throw new ValidacaoException("Clinica fechada em horario solicitado");
        }
    }
}
