package med.voll.api.domain.consultas_canceladas;

import med.voll.api.domain.consultas.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaCanceladaRepository extends JpaRepository<ConsultaCancelada, Long> {

}
