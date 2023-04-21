package med.voll.api.domain.consulta;


import med.voll.api.domain.consulta.validacoes.cancelamento.ValidadorCancelamentoDeConsultas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CancelamentoDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private List<ValidadorCancelamentoDeConsultas> validadores;

    public void cancelarConsulta(DadosCancelamentoConsulta dados){
        validadores.forEach(v -> v.validar(dados));
        consultaRepository.deleteById(dados.idConsulta());
    }
}
