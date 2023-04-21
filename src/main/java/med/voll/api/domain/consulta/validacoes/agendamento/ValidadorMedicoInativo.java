package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ValidadorMedicoInativo implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private MedicoRepository medicoRepository;

    @Override
    public void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta){
        if (dadosAgendamentoConsulta.idMedico() == null){
            return;
        }
        boolean medicoEstaAtivo = medicoRepository.existsByIdAndAtivoTrue(dadosAgendamentoConsulta.idMedico());
        if (!medicoEstaAtivo){
            throw new ValidacaoException("Consulta não pode ser agendada para um médico excluído");
        }
    }
}
