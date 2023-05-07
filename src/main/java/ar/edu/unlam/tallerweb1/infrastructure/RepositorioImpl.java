package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.Auto;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioImpl implements RepositorioAuto{
    private SessionFactory sessionFactory; //si no tenemos las sesion no vamos a poder interactuar
    @Autowired
    public RepositorioImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    @Override
    public void guardar(Auto a) {
        this.sessionFactory.getCurrentSession().save(a);
    }

    @Override
    public Auto buscarPorId(Long id) {
        return this.sessionFactory.getCurrentSession().get(Auto.class, id);
    }

    @Override
    public Auto buscarPorPatente(String patente) {
        return (Auto) this.sessionFactory.getCurrentSession().createCriteria(Auto.class).add(Restrictions.eq("patente", patente)).uniqueResult();

    }
}
