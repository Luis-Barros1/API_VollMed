package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ValidadorMedicoExiste implements ValidadorAgendamentoDeConsulta{

    @Autowired
    private MedicoRepository medicoRepository;

    @Override
    public void validar(DadosAgendamentoConsulta dadosAgendamentoConsulta){
        if(dadosAgendamentoConsulta.idMedico() != null && !medicoRepository.existsById(dadosAgendamentoConsulta.idMedico())){
            throw new ValidacaoException("Id do medico informado não existe");
        }
    }
}
