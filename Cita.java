import java.time.LocalDate;
import java.util.Date;
import java.time.LocalDateTime;
public class Cita {
        private int id;
        private static int cont = 1; // atributo común para todos los objetos Cita
        private int idPaciente;
        private int idEmpleado;
        private LocalDateTime inicio;
        private int duracion;
        private TipoUnidad unidad;
        private boolean asignada;
        public Cita(int idPaciente, int idEmpleado, LocalDateTime inicio, int duracion, TipoUnidad unidad) {
            id = Cita.cont;
            Cita.cont++; // común a todos
            this.inicio = inicio;
            this.duracion = duracion;
            this.idPaciente = idPaciente;
            this.idEmpleado = idEmpleado;

            this.unidad = unidad;
            this.asignada = false;

        }

        // Getters
         public int getDia(){
            return inicio.getDayOfYear();
         }
         public int getYear(){
            return inicio.getYear();
         }
         public int getMonth(){
            return inicio.getDayOfMonth();
         }


        public int getId(){
            return id;
        }
        public LocalDateTime getInicio(){
            return inicio;
        }
        public int getDuracion(){
            return duracion;
        }
        public TipoUnidad getUnidad(){
            return unidad;
        }
        public int getIdEmpleado(){
            return idEmpleado;
        }
        public int getIdPaciente(){
            return idPaciente;
        }
        public boolean getAsignada(){
            return asignada;
        }
        //faltan setters
        public void setAsignada(boolean asignada){
            this.asignada = asignada;
        }
        public void setDuracion(int duracion){
            this.duracion = duracion;
        }
        public void setInicio(LocalDateTime inicio){
            this.inicio = inicio;
        }
        public String toString(){
            return "Id Cita " +id + " " + "Id paciente " +idPaciente + " " + "Id empleado " +  idEmpleado + " " + inicio + " " + "duracion: " + duracion + " " + "min " + unidad + " ";
        }
    }

