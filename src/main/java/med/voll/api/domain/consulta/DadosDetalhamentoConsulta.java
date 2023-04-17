package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(
        long id,
        long idMedico,
        long idPaciente,
        LocalDateTime data
){
}
