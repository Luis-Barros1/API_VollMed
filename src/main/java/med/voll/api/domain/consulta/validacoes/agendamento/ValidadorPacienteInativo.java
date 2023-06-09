package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ValidadorPacienteInativo implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta){

        boolean pacienteEstaAtivo = pacienteRepository.existsByIdAndAtivoTrue(dadosAgendamentoConsulta.idPaciente());

        if (!pacienteEstaAtivo){
            throw new ValidacaoException("Consulta não pode ser agendada para um paciente excluído");
        }

    }
}
