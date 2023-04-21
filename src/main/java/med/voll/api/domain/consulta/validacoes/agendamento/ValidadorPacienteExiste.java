package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ValidadorPacienteExiste implements ValidadorAgendamentoDeConsulta{

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta) {

        if(!pacienteRepository.existsById(dadosAgendamentoConsulta.idPaciente())) {
            throw new ValidacaoException("Id do paciente informado não existe");
        }

    }
}
