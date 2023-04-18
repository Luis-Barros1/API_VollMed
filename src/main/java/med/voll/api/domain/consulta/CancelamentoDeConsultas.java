package med.voll.api.domain.consulta;


import med.voll.api.domain.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CancelamentoDeConsultas {

    @Autowired
    ConsultaRepository consultaRepository;
    public void cancelarConsulta(DadosCancelamentoConsulta dados){
        Consulta consulta = consultaRepository.getReferenceById(dados.idConsulta());
        LocalDateTime dataConsulta = consulta.getData();

        if(!LocalDateTime.now().plusHours(24).isBefore(dataConsulta)){
            consultaRepository.delete(consulta);
        }
        else{
            throw new ValidacaoException("Uma consulta somente poderá ser cancelada com antecedência mínima de 24 horas.");
        }

    }
}
