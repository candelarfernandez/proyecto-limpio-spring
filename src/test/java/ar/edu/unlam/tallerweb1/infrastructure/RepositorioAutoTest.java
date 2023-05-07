package ar.edu.unlam.tallerweb1.infrastructure;
import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.domain.Auto;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Java6Assertions.assertThat;


public class RepositorioAutoTest extends SpringTest{

    @Autowired
    private RepositorioAuto repositorio;
    @Test
    @Transactional @Rollback
    public void guardarUnAutoDeberiaPersistirlo(){
        Auto a = dadoQueExisteUnAuto("AA123ZZ");
        Long id = cuandoGuardoUnAuto(a);
        entoncesLoPuedoBuscarPorSuId(id);
    }
    @Test
    @Transactional @Rollback
    public void  buscarPorPatenteDevuelveUnAutoConEsaPatente(){
        Auto auto1 = dadoQueExisteAuto("AA123ZZ");
        Auto auto2 = dadoQueExisteAuto("AA124BZ");
        Auto autoBuscado = cuandoLoBuscoPorSuPatente("AA123ZZ");
        entoncesPuedoEncontrar(autoBuscado);
    }
    @Test
    @Transactional @Rollback
    public void  buscarPorPatenteInexistenteDevuelveNull(){
        Auto auto1 = dadoQueExisteAuto("AA123ZZ");
        Auto auto2 = dadoQueExisteAuto("AA124BZ");
        Auto autoBuscado = cuandoLoBuscoPorSuPatente("XX123ZZ");
        entoncesNoExisteAuto(autoBuscado);
    }

    private void entoncesNoExisteAuto(Auto autoBuscado) {
        assertThat(autoBuscado).isNull();
    }

    private Auto dadoQueExisteAuto(String patente) {
        Auto auto = new Auto();
        auto.setPatente(patente);
        repositorio.guardar(auto);
        return auto;
    }

    private void entoncesPuedoEncontrar(Auto autoBuscado) {
        assertThat(autoBuscado).isNotNull();
        assertThat(autoBuscado.getPatente()).isEqualTo("AA123ZZ");
    }

    private Auto cuandoLoBuscoPorSuPatente(String patente) {
        return repositorio.buscarPorPatente(patente);
    }

    private void entoncesLoPuedoBuscarPorSuId(Long id) {
        Auto autoBuscado = repositorio.buscarPorId(id);
        assertThat(autoBuscado).isNotNull();
    }

    private Long cuandoGuardoUnAuto(Auto a) {
        repositorio.guardar(a);
        return a.getId();
    }

    private Auto dadoQueExisteUnAuto(String patente) {
        Auto a = new Auto();
        a.setPatente(patente);
        repositorio.guardar(a);
        return a;
    }


}
