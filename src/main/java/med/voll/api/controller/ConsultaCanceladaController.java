package med.voll.api.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consultas_canceladas.CancelarConsulta;
import med.voll.api.domain.consultas_canceladas.DadosCancelamento;
import med.voll.api.domain.consultas_canceladas.DadosConsultaCanceladaSucesso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cancelar/consulta")
@SecurityRequirement(name = "bearer-key")
public class ConsultaCanceladaController {

    @Autowired
    private CancelarConsulta cancelarConsulta;

    @PostMapping
    @Transactional
    public ResponseEntity cancelamento(@RequestBody @Valid DadosCancelamento dados) {
        cancelarConsulta.cancelarConsulta(dados);
        return ResponseEntity.ok(new DadosConsultaCanceladaSucesso(dados.id()));
    }

}
