import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDateTime;
import java.util.Locale;

public class Empleado extends Persona {
    private String password;
    public TipoUnidad unidad;
    private List<Cita> agenda;

    public Empleado(){}

    public Empleado(int id, String dni, String nombre, String password, TipoUnidad unidad) {
        super(id, dni, nombre);
        this.password = password;
        this.unidad = unidad;
        this.agenda = new ArrayList<Cita>();
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public TipoUnidad getUnidad() {
        return unidad;
    }

    public void setUnidad(TipoUnidad unidad) {
        this.unidad = unidad;
    }

    public String toString() {
        return super.toString()  + " " + unidad;

    }
    public List<Cita> getAgenda(){
        return this.agenda;
    }
    public Cita getCita(int pos){
        return agenda.get(pos);
    }
    /*Comprueba si la fecha y hora de la cita coincide con la de de alguna de su agenda
       o si es antes de que acabe alguna */
    private boolean estaLibre(Cita cita) {
        if (!agenda.isEmpty()) {
         for (Cita c : agenda) {
            if (c.getInicio().equals(cita.getInicio())) {
                return false;
            }
              if(c.getInicio().getDayOfMonth() == cita.getInicio().getDayOfMonth() &&
             c.getInicio().getDayOfYear() == cita.getInicio().getDayOfYear() && c.getInicio().getYear() ==
             cita.getInicio().getYear()) {
                 LocalDateTime finCita = c.getInicio().plusMinutes(c.getDuracion());
                 if (finCita.isAfter(cita.getInicio())) {
                     return false; // No se puede agregar la cita
                 }
             }
            }
         return true;
        }
            //  agenda.add(cita);
        return true;
    }
    /*Comprueba que este libre antes de asignarla, si lo esta devuelve true*/
    public boolean asignarCita(Cita cita){
        if (estaLibre(cita)){
                agenda.add(cita);
                cita.setAsignada(true);
                return true;
        }
        return false;
    }
    //Elimina cita en caso de que la tenga sino devuelve false
    public boolean removeCita(int idCita){
        for(int i = 0; i < agenda.size(); i++){
            if(agenda.get(i).getId() == idCita){
                agenda.remove(i);
                return true;
            }
        }
        return false;
    }

    public String mostrarAgenda(){
        String cadena = "Citas del empleado " + getNombre() + "\n" ;
            for (int i = 0; i < agenda.size(); i++) {
                cadena = cadena + agenda.get(i) + "\n";
            }
            return cadena + "\n";
    }
}

