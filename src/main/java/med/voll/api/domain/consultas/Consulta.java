package med.voll.api.domain.consultas;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.pacientes.Paciente;

import java.time.LocalDateTime;

@Table(name = "consultas")
@Entity(name = "Consulta")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idMedico;
    private Long id_paciente;
    private LocalDateTime data;

    public Consulta(Long id, Long idMedico, Long id_paciente, LocalDateTime data) {
        this.id = id;
        this.idMedico = idMedico;
        this.id_paciente = id_paciente;
        this.data = data;
    }

    public Consulta(Long id, Medico medico, Paciente paciente, LocalDateTime data) {
        this(id, medico.getId(), paciente.getId(), data);
    }
}
