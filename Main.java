import java.time.LocalDateTime;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Gestion admin = new Gestion();
        Administrador ad = new Administrador();
        /*------ADMINISTRADOR--------*/
        ad.setPassword("password");
        ad.setId(10);
        admin.agregarEmpleado(ad);

        /*---------PACIENTES---------*/
        Paciente p1 = new Paciente(1, "1111A", "Laura", true);
        admin.agregarPaciente(p1);
        Paciente p2 = new Paciente(2, "2222B", "Paula", false);
        admin.agregarPaciente(p2);
        Paciente p3 = new Paciente(3, "3333C", "Alex", true);
        admin.agregarPaciente(p3);
        
        /*----------TECNICOS------------*/
        Tecnico t1 = new Tecnico(12, "8888R", "Sara");
        admin.agregarTecnico(t1);
        Tecnico t2 = new Tecnico(13, "99999S", "Lucas");
        admin.agregarTecnico(t2);

        /*--------EMPLEADOS----------*/
        Medico m1 = new Medico(4, "4444D", "Sara","password", TipoUnidad.consultasExternas);
        admin.agregarEmpleado(m1);
        Medico m2 = new Medico(5, "5555E", "Ana","password", TipoUnidad.uci);
        admin.agregarEmpleado(m2);
        Enfermero enf1 = new Enfermero(6, "66666R", "Andrés", "password", TipoUnidad.urgencias);
        admin.agregarEmpleado(enf1);
        Enfermero enf2 = new Enfermero(7, "7777T", "Pedro", "password", TipoUnidad.formacion);
        admin.agregarEmpleado(enf2);

        /*--------ESTUDIANTES---------*/
        Estudiante est1 = new Estudiante(9, "Nacho", "88888R");
        admin.agregarEstudiante(est1);
        Estudiante est2 = new Estudiante(11, "Paula", "99999F");
        admin.agregarEstudiante(est2);

        /*--------------CLASES-----------------*/
        admin.crearAula(enf2);


        /*------------CITAS-------------*/
        LocalDateTime d1 = LocalDateTime.of(2024, 6, 22, 18, 30);
        LocalDateTime d2 = LocalDateTime.of(2024, 7, 11, 17, 40);
        LocalDateTime d3 = LocalDateTime.of(2024, 5, 13, 20, 40);
        LocalDateTime d4 = LocalDateTime.of(2024, 7,25, 16, 45);

        Cita c1 = new Cita(2, 6, d1, 30, TipoUnidad.unidadesEspecializadas);
        admin.actualizacionAgendaMedico_add(6, c1);
        Cita c2 = new Cita(3, 4, d2, 30,  TipoUnidad.consultasExternas);
        admin.actualizacionAgendaMedico_add(4, c2);
        Cita c3 = new Cita(2, 4, d3, 40,  TipoUnidad.consultasExternas);
        admin.actualizacionAgendaMedico_add(4, c3);
        Cita c4 = new Cita(1, 4, d4, 40, TipoUnidad.consultasExternas);
        admin.actualizacionAgendaMedico_add(4, c4);

        /*---------INGRESOS--------*/
        admin.realizarBaja(2);
        admin.realizarBaja(1);

        /*--------EXPEDIENTES-------*/
        Expediente exp1 = new Expediente("", PruebasMedicas.noPrueba, Tratamiento.cardiologia);
        p1.setExpediente(exp1);
        Expediente exp2 = new Expediente("Ingreso 3/4/2024",PruebasMedicas.resonancia, Tratamiento.cirugiaGeneral);
        p2.setExpediente(exp2);


        EntradaSalida prueba = new EntradaSalida(admin);
        //prueba.login();
        prueba.entradaSalida();

    }
}