package med.voll.api.domain.consultas_canceladas;

import jakarta.validation.constraints.NotNull;

public record DadosCancelamento(
        @NotNull
        Long id,
        @NotNull
        Motivo motivo
) {
}
