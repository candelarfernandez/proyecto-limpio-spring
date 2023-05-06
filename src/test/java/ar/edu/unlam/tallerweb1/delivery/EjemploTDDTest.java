package ar.edu.unlam.tallerweb1.delivery;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class EjemploTDDTest {

    @Test
    public void deberiaDevolverDebilCuandoLaContraseniaEsVacia(){
        EjemploTDD ejemploTdd = new EjemploTDD();
        String nivelDeSeguridad = ejemploTdd.evaluarConstrasenia("");
        assertThat(nivelDeSeguridad).isEqualTo("DEBIL");
    }
    @Test
    public void deberiaDevolverMedianaCuandoLaContraseniaTengaOchoCaracteres(){
        EjemploTDD ejemploTdd = new EjemploTDD();
        String nivelDeSeguridad = ejemploTdd.evaluarConstrasenia("12345678");
        assertThat(nivelDeSeguridad).isEqualTo("MEDIANA");
    }
    @Test
    public void deberiaDevolverMedianaCuandoLaContraseniaTengaDosLetras(){
        EjemploTDD ejemploTdd = new EjemploTDD();
        String nivelDeSeguridad = ejemploTdd.evaluarConstrasenia("11B23A");
        assertThat(nivelDeSeguridad).isEqualTo("MEDIANA");
    }
    @Test
    public void deberiaDevolverFuerte(){
        EjemploTDD ejemploTdd = new EjemploTDD();
        String nivelDeSeguridad = ejemploTdd.evaluarConstrasenia("123456AA89");
        assertThat(nivelDeSeguridad).isEqualTo("FUERTE");
    }
}
