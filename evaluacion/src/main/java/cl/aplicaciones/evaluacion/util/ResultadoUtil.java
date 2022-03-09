package cl.aplicaciones.evaluacion.util;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class ResultadoUtil<T> {

    public static final Integer OK = 200;
    public static final Integer ERROR = 203;
    public static final Integer LAPSE = 305;

    private String mensaje;
    private int status;
    private T resultado;

    public ResultadoUtil(T resultado){
        status = OK;
        this.resultado = resultado;
    }

}
