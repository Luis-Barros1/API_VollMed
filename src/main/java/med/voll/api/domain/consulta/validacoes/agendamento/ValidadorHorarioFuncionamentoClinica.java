package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Service
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsulta {

    @Override
    public void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta){
        LocalDateTime dataConsulta = dadosAgendamentoConsulta.data();
        boolean domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        boolean antesAberturaDaClinica = dataConsulta.getHour()<7;
        boolean depoisFechamentoDaClinica = dataConsulta.getHour()>18;

        if(domingo || antesAberturaDaClinica || depoisFechamentoDaClinica){
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica");
        }
    }
}
