package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioRegistracion;
import ar.edu.unlam.tallerweb1.domain.usuarios.ServicioRegistracionImpl;
import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

public class ControladorRegistracionTest {
    private static final String CORREO = "can@gmail.com";
    private static final String CLAVE = "hola";

    private ServicioRegistracion servicioRegistracion;
    private ControladorRegistracion controladorRegistracion;
    private DatosRegistracion datosregistracion;
    private DatosRegistracion datosregistracion1;

    @Before
    public void init(){
        this.datosregistracion = new DatosRegistracion(CORREO, CLAVE);
        this.datosregistracion1 = new DatosRegistracion("can", CLAVE);
        this.servicioRegistracion = mock(ServicioRegistracionImpl.class);
        //this.servicioRegistracion = new ServicioRegistracionImpl();
        this.controladorRegistracion = new ControladorRegistracion(this.servicioRegistracion);


    }
    @Test
    public void queAlIngresarARegistrarmeMeMuestraLaPantallaDeRegistro(){
        dadoQueNoExisteElUsuario();
        ModelAndView mav = cuandoMeQuieroRegistrar();
        meLllevaALaPantallaDERegistro(mav);
    }

    @Test
    public void alIngresarCredencialesCorrectasMeRegistroYLllevaAlLogin(){
       //correo y una contraseña valida
        //prep
        dadoQueNoExisteElUsuario();
        //ejec
        ModelAndView mav = cuandoMeRegistro(datosregistracion);
        //verif
        entoncesElRegistroEsExitoso(mav);
    }

    @Test
    public void alIngresarCredencialesInvalidasNoMePermiteRegistrarme(){
        dadoQueNoExisteElUsuario();
        dadoQueLasCredencialesSonInvalidas();
        ModelAndView mav = cuandoMeRegistro(datosregistracion1);
        entoncesElRegistroFalla(mav);
    }
    private void dadoQueLasCredencialesSonInvalidas(){
       when(this.servicioRegistracion.esValido(datosregistracion.getCorreo())).thenReturn(false);
       when(this.servicioRegistracion.registrarUsuario(this.datosregistracion.getCorreo(), this.datosregistracion.getClave())).thenReturn(false);

    }

    private void entoncesElRegistroFalla(ModelAndView mav) {
        assertThat(mav.getViewName()).isEqualTo("registro-usuario");
        assertThat(mav.getModel().get("msg")).isEqualTo("Registro fallido");
    }

    private void dadoQueNoExisteElUsuario() {
        when(this.servicioRegistracion.esValido(datosregistracion.getCorreo())).thenReturn(true);
        when(this.servicioRegistracion.registrarUsuario(this.datosregistracion.getCorreo(), this.datosregistracion.getClave())).thenReturn(true);
    }

    private void entoncesElRegistroEsExitoso(ModelAndView mav) {
        assertThat(mav.getViewName()).isEqualTo("login");
        assertThat(mav.getModel().get("msg")).isEqualTo("registro exitoso");
    }

    private ModelAndView cuandoMeRegistro(DatosRegistracion datosRegistracion) {
        return controladorRegistracion.registrarUsuario(datosRegistracion);
    }
    private ModelAndView cuandoMeQuieroRegistrar() {
        return controladorRegistracion.registrarme();
    }
    private void meLllevaALaPantallaDERegistro(ModelAndView mav) {
        assertThat(mav.getViewName()).isEqualTo("registro-usuario");
    }


}
