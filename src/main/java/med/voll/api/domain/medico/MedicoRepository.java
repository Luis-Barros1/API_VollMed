package med.voll.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    @Query(value = """
            SELECT *
            FROM medicos m
            WHERE m.ativo = 1
              AND m.especialidade = ?1
              AND m.id NOT IN (
                SELECT c.medico_id FROM consultas c WHERE c.data = ?2
              )
            ORDER BY rand()
            LIMIT 1
            
            """, nativeQuery = true)
    Medico escolherMedicoAleatorioLivreNaData(int especialidade, LocalDateTime data);
}
