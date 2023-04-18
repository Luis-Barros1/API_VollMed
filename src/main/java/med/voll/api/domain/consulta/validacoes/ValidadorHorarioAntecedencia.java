package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsulta {

    public void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta){
        LocalDateTime dataConsulta = dadosAgendamentoConsulta.data();
        boolean agendadoSemAntecedencia = dataConsulta.isBefore(LocalDateTime.now().plusMinutes(30));

        if(agendadoSemAntecedencia){
            throw new ValidacaoException("A consulta deve ser agendada com um atecedência mínima de 30 minutos");
        }
    }
}
