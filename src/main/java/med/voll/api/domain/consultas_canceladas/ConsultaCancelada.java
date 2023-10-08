package med.voll.api.domain.consultas_canceladas;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "consultas_canceladas")
@Entity(name = "ConsultaCancelada")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ConsultaCancelada {
    @Id
    private Long id;

    private Long idMedico;
    private Long id_paciente;
    private LocalDateTime data;
    @Enumerated(EnumType.STRING)
    private Motivo motivo;

}
