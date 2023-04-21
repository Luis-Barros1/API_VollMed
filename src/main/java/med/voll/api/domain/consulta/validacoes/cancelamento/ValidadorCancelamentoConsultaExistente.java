package med.voll.api.domain.consulta.validacoes.cancelamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ValidadorCancelamentoConsultaExistente implements ValidadorCancelamentoDeConsultas{

    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public void validar(DadosCancelamentoConsulta dadosCancelamentoConsulta) {
        if (!consultaRepository.existsById(dadosCancelamentoConsulta.idConsulta())){
            throw new ValidacaoException("Id de consulta informado não existe.");
        }

    }
}
