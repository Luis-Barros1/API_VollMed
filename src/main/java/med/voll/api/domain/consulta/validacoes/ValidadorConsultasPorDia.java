package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class ValidadorConsultasPorDia implements ValidadorAgendamentoDeConsulta {

    @Autowired
    ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta){
        LocalDateTime primeiroHorario = dadosAgendamentoConsulta.data().withHour(7);
        LocalDateTime ultimoHorario = dadosAgendamentoConsulta.data().withHour(18);
        boolean duasConsultasNoMesmoDia = consultaRepository.existsByPacienteIdAndDataBetween(dadosAgendamentoConsulta.idPaciente(), primeiroHorario, ultimoHorario);

        if (duasConsultasNoMesmoDia){
            throw new ValidacaoException("O paciente não pode ter outras consultas agendadas para o mesmo dia");
        }
    }
}
