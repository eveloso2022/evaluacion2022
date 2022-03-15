package cl.aplicaciones.evaluacion.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

public class ValidadorPasswordUtil {

    public static final boolean RESULTADO_ERRONEO = false;
    @Value("${validar.password.cantidad.maxima.mayusculas}")
    private int cantidadMaximaMayuscula=1;

    @Value("${validar.password.cantidad.minima.minusculas}")
    private int cantidadMinimaMinuscula=1;

    @Value("${validar.password.cantidad.maxima.digitos}")
    private int cantidadMaximaDigitos=2;

    public boolean validarPassword(String password){
        boolean valid = RESULTADO_ERRONEO;
        if (StringUtils.hasLength(password)){
            return cantidadMayusculasRequerias(password) && validarDigitosRequeridos(password) && cantidadMinusculasRequerias(password);
        }
        return  valid;
    }

    private boolean validarDigitosRequeridos(String password) {
        boolean resultado = RESULTADO_ERRONEO;
        long cantidad = password.chars().mapToObj(a -> (char) a).filter(a -> Character.isDigit(a)).count();
        if(cantidad == cantidadMaximaDigitos){
            resultado = true;
        }
        return resultado;
    }

    private boolean cantidadMayusculasRequerias(String password) {
        boolean resultado = RESULTADO_ERRONEO;

        long count = password.chars().mapToObj(a -> (char) a).filter(a -> Character.isUpperCase(a)).count();

        if(count == cantidadMaximaMayuscula){
            resultado = true;
        }
        return resultado;
    }

    private boolean cantidadMinusculasRequerias(String password) {
        boolean resultado = RESULTADO_ERRONEO;

        long cantidad = password.chars().mapToObj(a -> (char) a).filter(a -> Character.isLowerCase(a)).count();
        if(cantidad >= cantidadMinimaMinuscula){
            resultado = true;
        }
        return resultado;
    }
}
