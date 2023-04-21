package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsulta {

    @Override
    public void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta){
        LocalDateTime dataConsulta = dadosAgendamentoConsulta.data();
        boolean agendadoSemAntecedencia = dataConsulta.isBefore(LocalDateTime.now().plusMinutes(30));

        if(agendadoSemAntecedencia){
            throw new ValidacaoException("A consulta deve ser agendada com um atecedência mínima de 30 minutos");
        }
    }
}
