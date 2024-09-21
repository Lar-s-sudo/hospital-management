
import java.util.ArrayList;
import java.util.List;
public class Aula {
    private int numero;
    private static int cont = 1;
    private List<Estudiante> estudiantes;
    private Empleado formador;

    public Aula(Empleado formador) {
        numero = Aula.cont;
        Aula.cont++;
        this.formador = formador;
        estudiantes = new ArrayList<Estudiante>();
    }
    public int getNumero(){
        return numero;
    }

    public Empleado getFormador(){
        return formador;
    }

    public List<Estudiante> getEstudiantes(){
        return estudiantes;
    }

    public void agregarEstudiante(Estudiante est){
        estudiantes.add(est);
    }

    public String toString() {
        String cadena = "Aula " + numero + "\nFormador: " + formador.toString() + "\n" + "Alumnos:\n";
        for (int i = 0; i < estudiantes.size(); i++){
            cadena = cadena + estudiantes.get(i).toString() + "\n";
        }
        return cadena;
    }

}