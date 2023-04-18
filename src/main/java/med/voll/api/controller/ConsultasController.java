package med.voll.api.controller;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
public class ConsultasController {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private AgendaDeConsultas agenda;

    @Autowired CancelamentoDeConsultas cancelamentoDeConsultas;

    @PostMapping
    @Transactional
    public ResponseEntity agendarConsulta(@RequestBody @Valid DadosAgendamentoConsulta dadosAgendamentoConsulta){
        DadosDetalhamentoConsulta consultaCriada = agenda.agendar(dadosAgendamentoConsulta);
        return ResponseEntity.ok(consultaCriada);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelarConsulta(@RequestBody @Valid DadosCancelamentoConsulta dadosCancelamentoConsulta){
        cancelamentoDeConsultas.cancelarConsulta(dadosCancelamentoConsulta);
        return ResponseEntity.noContent().build();
    }
}
