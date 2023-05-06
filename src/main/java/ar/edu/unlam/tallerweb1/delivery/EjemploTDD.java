package ar.edu.unlam.tallerweb1.delivery;

public class EjemploTDD {
    /*
    * Crear un metodo que nos diga si una contrseña es fuerte, mediana o debil
    * Cond 1: que tenga como minimo 8 caract
    * Cond 2: que al menos dos caracteres sea una letra
    * debil:si no cumple ninguna condicion
    * mediana:si cumple alguna de las cond
    * fuerte:si cumple ambas
    * */
    public String evaluarConstrasenia(String contrasenia){
        boolean alMenos8Caracteres = tieneAlMenos8Caracteres(contrasenia);
        boolean tieneAlMenosDosLetras = tieneAlMenosDosLetras(contrasenia);
        if(contrasenia.equals(null) || contrasenia == ""){
            return "DEBIL";
        }
        if( alMenos8Caracteres && tieneAlMenosDosLetras){
            return "FUERTE";
        }else if (alMenos8Caracteres || tieneAlMenosDosLetras){
            return "MEDIANA";
            }
        return "DEBIL";
    }
    private boolean tieneAlMenos8Caracteres(String contrasenia){
        return contrasenia.length()>=8;
    }
    private boolean tieneAlMenosDosLetras(String contrasenia){
        return contrasenia.matches(".*[a-zA-Z].*[a-zA-Z].*");
    }
}
