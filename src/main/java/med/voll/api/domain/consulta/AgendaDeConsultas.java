package med.voll.api.domain.consulta;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.validacoes.agendamento.ValidadorAgendamentoDeConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadores;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados){
        validadores.forEach(v -> v.validar(dados));

        Medico medico = escolherMedico(dados);
        Paciente paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        Consulta consulta = new Consulta(null, medico, paciente, dados.data());

        consultaRepository.save(consulta);
        return new DadosDetalhamentoConsulta(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if(dados.idMedico() != null){
            return medicoRepository.getReferenceById(dados.idMedico());
        }
        if(dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatória quando um médico não foi escolhido");
        }

        Medico medicoAleatorio = medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade().ordinal(), dados.data());

        if(medicoAleatorio == null){
            throw new RuntimeException("Não foi possível encontrar um médico com essa especialidade disponível na data e hora indicada");
        }
        return medicoAleatorio;
    }
}
