public class Habitacion {
        private boolean ocupada;
        private Paciente paciente;
        private int numero;
        private static int cont = 1;

        public Habitacion() {
            this.ocupada = false;
            this.paciente = null;
            numero = Habitacion.cont;
            Habitacion.cont++;
        }
        public Habitacion(Paciente paciente){
            this.ocupada = true;
            this.paciente = paciente;
            numero = Habitacion.cont;
            Habitacion.cont++;

        }
        public int getNumero(){
            return numero;
        }

        public boolean isOcupada() {
            return ocupada;
        }

        public void setOcupada(boolean ocupada) {
            this.ocupada = ocupada;
        }

        public Paciente getPaciente() {
            return paciente;
        }
        public void vaciarHabitacion(){//quiza no hace falta?
            this.paciente = null;
            this.ocupada = false;
        }

        public void setPaciente(Paciente paciente) {
            this.paciente = paciente;
            this.ocupada = true;
        }

        public String toString(){
            return "Habitación " + numero + "\n" + paciente + " ";
        }




}