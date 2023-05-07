package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.Auto;

public interface RepositorioAuto {

    void guardar(Auto a);

    Auto buscarPorId(Long id);

    Auto buscarPorPatente(String patente);
}
