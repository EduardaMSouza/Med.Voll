package med.voll.api.domain.consultas;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medico.Especialidade;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(

        Long idMedico,

        @NotNull
        Long id_paciente,

        @NotNull
        @Future
        LocalDateTime data,
        Especialidade especialidade
) {
}
