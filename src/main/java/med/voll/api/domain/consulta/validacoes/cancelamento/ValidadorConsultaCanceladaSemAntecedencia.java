package med.voll.api.domain.consulta.validacoes.cancelamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ValidadorConsultaCanceladaSemAntecedencia implements ValidadorCancelamentoDeConsultas {
    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public void validar(DadosCancelamentoConsulta dadosCancelamentoConsulta) {
        Consulta consulta = consultaRepository.getReferenceById(dadosCancelamentoConsulta.idConsulta());
        LocalDateTime dataConsulta = consulta.getData();

        if(!LocalDateTime.now().plusHours(24).isBefore(dataConsulta)){
            throw new ValidacaoException("Uma consulta somente poderá ser cancelada com antecedência mínima de 24 horas.");
        }
    }
}
