package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.Auto;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


import static org.assertj.core.api.Java6Assertions.assertThat;

public class PersistenciaAutoTest extends SpringTest {

    @Test
    @Transactional @Rollback
    public void quePuedoGuardarUnAuto(){
        Auto a = dadoQueExisteUnAuto();
        Long id = cuandoGuardoUnAuto(a);
        entoncesLoPuedoBuscarPorId(id);
    }

    private void entoncesLoPuedoBuscarPorId(Long id) {
        Auto autoBuscado = session().get(Auto.class, id);
        assertThat(autoBuscado).isNotNull();
    }

    private Long cuandoGuardoUnAuto(Auto a) {
        session().save(a);
        return a.getId();

    }

    private Auto dadoQueExisteUnAuto() {
        Auto a = new Auto();
        a.setPatente("AA123ZZ");
        return a;
    }

}
