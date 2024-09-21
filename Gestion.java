import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Gestion {
    private List<Empleado> empleados;
    private List<Administrador> administradores;
    private List<Paciente> pacientes;
    private List<Tecnico> tecnicos;
    private List<Estudiante> estudiantes;
    private Hospitalizacion hospital;
    private List<Aula> aulas;
    private List<Cita> citas;

    public Gestion() {
        empleados = new ArrayList<>();
        pacientes = new ArrayList<>();
        estudiantes = new ArrayList<>();
        tecnicos = new ArrayList<>();
        hospital = new Hospitalizacion();
        citas = new ArrayList<>();
        aulas = new ArrayList<>();
    }

    public boolean buscarAdministrador(int id) {
        boolean encontrado = false;
        int i = 0;
        while (!encontrado && i < administradores.size()) {
            if (administradores.get(i).getId() == id){
                encontrado = true;
            }
            i++;
        }
        return encontrado;
    }


    public boolean buscarEmpleado(int id) {
        boolean encontrado = false;
        int i = 0;
        while (!encontrado && i < empleados.size()) {
            if (empleados.get(i).getId() == id) {
                encontrado = true;
            }
            i++;
        }
        return encontrado;
    }

    public boolean isMedicoEnfermero(int id){
        if(buscarEmpleado(id)){
            if(getEmpleado(id) instanceof Medico){
                return true;
            } else if (getEmpleado(id) instanceof Enfermero) {
                return true;
            }
        }
        return false;
    }

    public boolean agregarEmpleado(Empleado emp) {
        if (!buscarEmpleado(emp.getId())) {
            empleados.add(emp);
            return true;
        }
        return false;
    }

    //Los mismo metodos para estudiantes
    private boolean buscarEstudiante(int id) {
        boolean encontrado = false;
        int i = 0;
        while (!encontrado && i < estudiantes.size()) {
            if (estudiantes.get(i).getId() == id) {
                encontrado = true;
            }
            i++;
        }
        return encontrado;
    }

    public boolean agregarEstudiante(Estudiante est) {
        if (!buscarEstudiante(est.getId())) {
            estudiantes.add(est);
            return true;
        }
        return false;
    }


    //Y el mismo bloque de codigo para pacientes
    public boolean buscarPaciente(int id) {
        boolean encontrado = false;
        int i = 0;
        while (!encontrado && i < pacientes.size()) {
            if (pacientes.get(i).getId() == id) {
                encontrado = true;
            }
            i++;
        }
        return encontrado;
    }

    public boolean agregarPaciente(Paciente p) {
        if (!buscarPaciente(p.getId())) {
            pacientes.add(p);
            return true;
        }
        return false;
    }


    public boolean existPaciente(int id) {
        for (int i = 0; i < pacientes.size(); i++) {
            if (pacientes.get(i).getId() == id) {
                return true;
            }
        }
        return false;
    }
    public boolean buscarTecnico(int id) {
        boolean encontrado = false;
        int i = 0;
        while (!encontrado && i < tecnicos.size()) {
            if (tecnicos.get(i).getId() == id) {
                encontrado = true;
            }
            i++;
        }
        return encontrado;
    }
    public boolean agregarTecnico(Tecnico t){
        if(!buscarTecnico(t.getId())){
            tecnicos.add(t);
            return true;
        }
        return false;
    }

    //A partir de un id que me devuelva el empleado con ese id
/*-------------------------Getters a partir de Id---------------------------------------*/
    public Empleado getEmpleado(int id) {
        //if(isMedicoEnfermero()){
            for (Empleado emp : empleados) {
                if (emp.getId() == id) {
                    return emp;
                }
            }
       // }
        return null;
    }

    public Paciente getPaciente(int id) {
        for (Paciente p : pacientes) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public Estudiante getEstudiante(int id){
        for(Estudiante e : estudiantes){
            if(e.getId() == id){
                return e;
            }
        }
        return null;
    }
    public Tecnico getTecnico(int id){
        for(Tecnico t : tecnicos){
            if(t.getId() == id){
                return t;
            }
        }
        return null;
    }
    public Aula getAula(int id){
        for(Aula a : aulas){
            if(a.getNumero() == id){
                return a;
            }
        }
        return null;
    }

    /*-----------------------------Metodos para hospitalizacion-------------------------------*/
    public boolean realizarAlta(int idPaciente) {
        if (buscarPaciente(idPaciente)) {
            Paciente paciente = getPaciente(idPaciente);
            return hospital.pacienteLiberado(paciente);
        }
        return false;
    }

    public boolean realizarBaja(int idPaciente) {
        if (buscarPaciente(idPaciente)) {
            if (!hospital.pacienteIngresado(getPaciente(idPaciente))) {
                return hospital.asignarHabitacion(getPaciente(idPaciente));
            }
        }
    return false; //si el paciente no existe previamente en el sistema, devolvera falso
    }

   /*------------------------Metodos para visualizacion------------------------------------*/
    public String toStringMedicos() {
        String cadena = "Empleados en nuestro centro:\n";
        for (int i = 0; i < empleados.size(); i++) {
            if(isMedicoEnfermero(empleados.get(i).getId())){
            cadena = cadena + empleados.get(i).toString()+ "\n";
            }
        }
        return cadena + "\n";
    }
    public String toStringMedicosAgenda(){
        String cadena = "Agendas de cada empleado: \n";
        for (int i = 0; i < empleados.size(); i++) {
            if(isMedicoEnfermero(empleados.get(i).getId())) {
                cadena = cadena + empleados.get(i).mostrarAgenda() + "\n";
            }
        }
        return cadena + "\n";
    }
    public String toStringMedicosUnidad(){
        String cadena = "Empleados por cada unidad:\n";
        String cadena1 = "Empleados en UCI\n";
        for(int i = 0; i < empleados.size(); i++){
            if (isMedicoEnfermero(empleados.get(i).getId()) && empleados.get(i).getUnidad().equals(TipoUnidad.uci)){
                cadena1 = cadena1 + empleados.get(i).toString() + "\n";
            }
        }
        String cadena2 = "Empleado en consultas externas\n";
        for(int i = 0; i < empleados.size(); i++){
            if (isMedicoEnfermero(empleados.get(i).getId()) && empleados.get(i).getUnidad().equals(TipoUnidad.consultasExternas)){
                cadena2 = cadena2 + empleados.get(i).toString() + "\n";
            }
        }
        String cadena3 = "Empleados en unidades especializadas\n";
        for(int i = 0; i < empleados.size(); i++){
            if ( isMedicoEnfermero(empleados.get(i).getId()) && empleados.get(i).getUnidad().equals(TipoUnidad.unidadesEspecializadas)){
                cadena3 = cadena3 + empleados.get(i).toString() + "\n";
            }
        }
        String cadena4 = "Empleados en urgencias\n";
        for(int i = 0; i < empleados.size(); i++){
            if (isMedicoEnfermero(empleados.get(i).getId()) && empleados.get(i).getUnidad().equals(TipoUnidad.urgencias)){
                cadena4 = cadena4 + empleados.get(i).toString() + "\n";
            }
        }
        String cadena5 = "Empleados en formacion\n";
        for(int i = 0; i < empleados.size(); i++){
            if (isMedicoEnfermero(empleados.get(i).getId()) && empleados.get(i).getUnidad().equals(TipoUnidad.formacion)){
                cadena5 = cadena5 + empleados.get(i).toString() + "\n";
            }
        }
        //cadena = cadena + cadena1;
        return cadena1 + cadena2 + cadena3 + cadena4 + cadena5 + "\n";
    }

    public String toStringPacientes() {
        String cadena = "Pacientes en nuestro centro:\n";
        for (int i = 0; i < pacientes.size(); i++) {
            cadena = cadena + pacientes.get(i).toString() + "\n";
        }
        return cadena + "\n";
    }

    public String toStringEstudiantes(){
        String cadena = "Estudiantes en nuestro sistema:\n";
        for(int i = 0; i < estudiantes.size(); i++){
            cadena = cadena + estudiantes.get(i).toString() + "\n";
        }
        return cadena + "\n";
    }
    public String toStringTecnicos(){
        String cadena = "Tecnicos de nuestro sistema\n";
        for(int i = 0; i < tecnicos.size(); i ++){
            cadena = cadena + tecnicos.get(i).toString()+ "\n";
        }
        return cadena + "\n";
    }

    public String toStringCitas(){
        String cadena = "Citas en el sistema:\n";
        for (int i = 0; i < citas.size(); i++) {
            cadena = cadena + citas.get(i).toString() + "\n";
        }
        return cadena + "\n";
    }
    public String toStringClases(){
        String cadena = "Clases en la unidad formación: \n";
        for(int i = 0; i < aulas.size(); i++){
            cadena = cadena + aulas.get(i).toString() + "\n";
        }
        return cadena + "\n";

    }
   public String toStringHospitalizados(){
      return hospital.toString();
   }
   public String toStringHabitaciones(){
        return hospital.toStringHabitacionesOcupadas();
   }
/*------------------------------Busquedas------------------------------------*/
    public String busquedas(int id){
        String cadena;
        if(buscarPaciente(id)){
            Paciente paciente = getPaciente(id);
           cadena = "Paciente: " + paciente.toString();
           return cadena;
        }else if(buscarEmpleado(id) && isMedicoEnfermero(id)){
            Empleado empleado = getEmpleado(id);
          cadena =  "Empleado: " + empleado.toString();
          return cadena;
        }
        cadena = "No existe empleado o paciente con ese identificador en el sistema";
        return cadena;
    }

/*-------------------------------Agenda---------------------------------------*/

    public String consultaAgendaMedico(int idEmpleado){
        String agenda = " ";
        if (buscarEmpleado(idEmpleado) && isMedicoEnfermero(idEmpleado)){
            Empleado empleado = getEmpleado(idEmpleado);
            empleado.getAgenda();
            agenda = empleado.mostrarAgenda();
            return agenda;
        }
        return "No existe empleado con ese Id en el sistema";
    }
//Este metodo solo se usa cuando estas en el login de medico
    public String consultaTuAgenda(int id){
        Empleado empleado = getEmpleado(id);
        return empleado.mostrarAgenda();

    }
    public String consultaAgendaEnUnaFecha(int id){
        String agenda = "No tienes citas hoy";
        Empleado empleado = getEmpleado(id);
       // List<Cita> a = empleado.getAgenda();
        LocalDate hoy;
        hoy = LocalDate.now();
        System.out.println("La fecha de hoy es " + hoy);

        for(int i = 0; i < empleado.getAgenda().size(); i++){
            if(empleado.getCita(i).getDia() == hoy.getDayOfYear()
            && empleado.getCita(i).getYear() == hoy.getYear() &&
            empleado.getCita(i).getMonth() == hoy.getDayOfMonth()) {
                agenda = empleado.getCita(i).toString();
            }

        }

        return agenda;
    }

    public String consultaCitasEnUnDiaConcreto(int dia, int month, int year){
        String agenda = "Citas en este periodo:\n";
        for(int i = 0; i < citas.size(); i++){
            if(citas.get(i).getInicio().getDayOfMonth() == dia  &&
            citas.get(i).getYear() == year && citas.get(i).getInicio().getMonthValue() == month){
                agenda = agenda + citas.get(i).toString();
            }
        }
        return agenda;
    }

    public boolean actualizacionAgendaMedico_remove(int idEmpleado, int idCita){
        if(buscarEmpleado(idEmpleado) && isMedicoEnfermero(idEmpleado)){
            Empleado empleado = getEmpleado(idEmpleado);
            return empleado.removeCita(idCita);
        }
        return false;
    }

    public boolean actualizacionAgendaMedico_add(int idEmpleado, Cita cita){
        if(buscarEmpleado(idEmpleado) && isMedicoEnfermero(idEmpleado)){
            Empleado empleado = getEmpleado(idEmpleado);
            citas.add(cita);
            return empleado.asignarCita(cita);
        }
        return false;
    }

    /*-----------------------------Expediente----------------------------------*/

    public String mostrarExpediente(int idPaciente){
        if(buscarPaciente(idPaciente)){
            Paciente paciente = getPaciente(idPaciente);
            return paciente.getExpediente().toString();
        }
        return "No hay paciente con ese ID en el sistema";
    }
    public String buscarUsuario(int id, String password){
        Empleado emp;
        if(buscarEmpleado(id)){
            emp = getEmpleado(id);
            if(emp.getPassword().equals(password) && getEmpleado(id) instanceof Medico ){
                return "Medico";
            } else if (emp.getPassword().equals(password) && getEmpleado(id) instanceof Enfermero) {
                return "Enfermero";
            } else if (emp.getPassword().equals(password) && getEmpleado(id) instanceof Administrador) {
                return "Administrador";
            }
        }
        return "No se ha encontrado empleado con ese Id";
    }


/*------------------------------------------ASIGNAR MEDICO A ESTUDIANTES---------------------------------------- */
    public String empleadosFormacion(){
        String cadena = "Empleados en la unidad de formación: " + "\n";
        for(int i = 0; i < empleados.size(); i++){
            if (isMedicoEnfermero(empleados.get(i).getId()) && empleados.get(i).getUnidad().equals(TipoUnidad.formacion)){
                cadena = cadena + empleados.get(i).toString() + "\n";
            }
        }
        return cadena + "\n";
    }
    public boolean crearAula(Empleado emp){
        Aula aula;
        if(emp.getUnidad().equals(TipoUnidad.formacion)){
            aula = new Aula(emp);
            aulas.add(aula);
            return true;
        }
        return false;
    }
    /*
    public boolean asignarClasesEstudiante(Estudiante est, int id){
       Empleado emp;
       Aula clase;
       emp = getEmpleado(id);
        if(emp.getUnidad().equals(TipoUnidad.formacion)){
          clase = new Aula(emp);
          aulas.add(clase);
          return true;
        }
        return false;
}*/
//nuevo aula con un formador

public boolean asignarCitaEstudiante(Estudiante est, int id){
        for(int i = 0; i< citas.size(); i ++){
            if(citas.get(i).getId() == id){
                System.out.println("Va a acompañar al empleado con el id " + citas.get(i).getIdEmpleado() + "\n" +
                        "el día " + citas.get(i).getInicio().getDayOfMonth() + " / " + citas.get(i).getInicio().getMonthValue() +
                        " / " + citas.get(i).getInicio().getYear()
                        + "\n" + "en la unidad " + citas.get(i).getUnidad() + ".");
                return true;
            }
        }
        System.out.println("Lo siento. No hemos encontrado cita con ese id");
        return false;
}




}