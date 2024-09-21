import java.util.ArrayList;
public class Hospitalizacion { //quiza lo hago publico
    //cada vez que se de de alta hay que ver si hay habitaciones libres y asignarlas
    private static int MAX_HABITACIONES = 90;
    private ArrayList<Habitacion> habitaciones;

    public Hospitalizacion(){
        habitaciones = new ArrayList<Habitacion>();
    }


    public boolean asignarHabitacion(Paciente paciente) {
        if (habitaciones.size() < MAX_HABITACIONES) { // si hay hueco
            habitaciones.add(new Habitacion(paciente));
            return true;
        }
        return false;
    }

    public boolean pacienteIngresado(Paciente paciente) {
        for (Habitacion hab: habitaciones) {
            if (hab.getPaciente().equals(paciente)) { // hay que implementar el equals de Paciente
                return true;
            }
        }
        return false;
    }

   public boolean pacienteLiberado(Paciente paciente){
        if(pacienteIngresado(paciente)){
            for (Habitacion habitacion : habitaciones){
                if(habitacion.getPaciente().equals(paciente)){
                    habitacion.vaciarHabitacion();
                    return true;
                }
            }
        }
        return false;
    }

    public String toString(){
        String cadena = "Pacientes hospitalizados:\n";
        for(int i = 0; i < habitaciones.size(); i++){
            cadena = cadena + habitaciones.get(i).toString() + "\n";
        }
        return cadena + "\n";
    }

    public String toStringHabitacionesOcupadas(){
        String cadena = "Habitaciones ocupadas:\n";
        for(int i = 0; i < habitaciones.size(); i++){
            if (habitaciones.get(i).isOcupada()){
                cadena = cadena + habitaciones.get(i).toString() + "\n";
            }
        }
        return cadena + "\n";
    }


  }
