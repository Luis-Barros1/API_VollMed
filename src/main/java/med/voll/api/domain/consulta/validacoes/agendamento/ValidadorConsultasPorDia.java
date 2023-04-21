package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ValidadorConsultasPorDia implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta){
        LocalDateTime primeiroHorario = dadosAgendamentoConsulta.data().withHour(7);
        LocalDateTime ultimoHorario = dadosAgendamentoConsulta.data().withHour(18);
        boolean duasConsultasNoMesmoDia = consultaRepository.existsByPacienteIdAndDataBetween(dadosAgendamentoConsulta.idPaciente(), primeiroHorario, ultimoHorario);

        if (duasConsultasNoMesmoDia){
            throw new ValidacaoException("O paciente não pode ter outras consultas agendadas para o mesmo dia");
        }
    }
}
