import java.time.LocalDateTime;
import java.util.Scanner;

/*Esta clase implementa toda la gestion de los menus*/

public class EntradaSalida {
    private Gestion gestion;
    private Scanner teclado;
   
    public EntradaSalida() {
        gestion = new Gestion();
        teclado = new Scanner(System.in);
    }
    public boolean entradaSalida() {
        int opc = -1;
        while (opc != 0) {
            System.out.println("SISTEMA DE GESTION DEL HOSPITAL UNIVERSITARIO");
            System.out.println("1. Iniciar sesión en el sistema");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            try {
                opc = teclado.nextInt();
                teclado.nextLine();
                if (opc == 1) {
                    login();
                }
            } catch (Exception ex) {
                teclado.nextLine();
                opc = -1;
            }
        }
        return false;
    }

    public void login() {
        // Pedir credenciales
        int id;
        String password, acceso;
        id = pedirID("Introduzca el id : ");
        teclado.nextLine();
        System.out.print("Introduzca la contraseña: ");
        password = teclado.nextLine();

        acceso = gestion.buscarUsuario(id, password); // --> String con el tipo de empleado
        if (acceso.equals("Administrador")) {
            procesarMenuPrincipalAdmin();
        } else if (acceso.equals("Medico") || acceso.equals("Enfermero")) {
            //menu de medico
            ProcesarMenuPrincipalMedico(id);
        }
    }

    public EntradaSalida(Gestion admin) {
        this.gestion = admin;
        teclado = new Scanner(System.in);
    }
    /*-----------------------------------------MENU PRINCIPAL MÉDICO-----------------------------------*/

    public int mostrarMenuPrincipalMedico() {
        int opc;
        System.out.println("MENU PRINCIPAL PERSONAL MEDICO");
        System.out.println("1. Consultar tus citas");
      //  System.out.println("2. Listado de tus pacientes");
        System.out.println("2. Consultar/actualizar expediente de un paciente");
        System.out.println("3. Cambiar constraseña");
        System.out.println("0. Salir");
        System.out.print("Elija una opción: ");
        try {
            opc = teclado.nextInt();
            teclado.nextLine();
        } catch (Exception ex) {
            teclado.nextLine();
            opc = -1; // no válido
        }
        return opc;

    }

    public void ProcesarMenuPrincipalMedico(int id) {
        int opc;
        opc = mostrarMenuPrincipalMedico();
        while (opc != 0) {
            switch (opc) {
                case 1:
                    procesarMenuTusCitas(id);
                    break;
                case 2:
                    procesarMenuExpediente();
                    break;
                case 3:
                    procesarMenuPassword(id);
                    break;
            }
            opc = mostrarMenuPrincipalMedico();
        }
    }

    /*---------------------------Cambiar contraseña---------------------*/
    public int mostrarMenuPassword(){
        int opc;
        System.out.println("1. Cambiar contraseña");
        System.out.println("2. Consultar contraseña");
        System.out.println("0. Salir");
        System.out.print("Elige opcion: ");
        try {
            opc = teclado.nextInt();
            teclado.nextLine();
        } catch (Exception ex) {
            teclado.nextLine();
            opc = -1;
        }
        return opc;

    }
    public void procesarMenuPassword(int id){
        int opc;
        opc = mostrarMenuPassword();
        while (opc != 0){
            if(opc == 1){
                if(cambiarPassword(id)){
                    System.out.println("Contraseña cambiada.");
                }else {
                    System.out.println("No se ha podido cambiar la contraseña");
                }
            } else if (opc == 2) {
                System.out.println("Tu contraseña actual es:  " + consultarPassword(id));
            }
            opc = mostrarMenuPassword();
        }

    }
    public String consultarPassword(int id){
        Empleado emp;
        emp = gestion.getEmpleado(id);
        return emp.getPassword();
    }
    public boolean cambiarPassword(int id){
        //Empleado emp;
        String password;
        String newPassword;
        String newPassword2;
        //emp = gestion.getEmpleado(id);
        System.out.println("Introduce tu contraseña actual:");
        password = teclado.nextLine();
        if(password.equals(gestion.getEmpleado(id).getPassword())){
            System.out.println("Introduce tu nueva constraseña:");
            newPassword = teclado.nextLine();
            System.out.println("Introdúcela de nuevo: ");
            newPassword2 = teclado.nextLine();
            if(newPassword2.equals(newPassword)){
                gestion.getEmpleado(id).setPassword(newPassword);
                return true;
            }
            System.out.println("Error. Las constraseñas no son iguales");
            return false;
        }
        System.out.println("Error. Contraseña incorrecta");
        return false;
    }


    /*---------------------------MENU EXPEDIENTE--------------------------*/
    public int mostrarMenuExpediente(){
        int opc;
        System.out.println("Menú expediente paciente");
        System.out.println("1. Consultar expediente paciente");
        System.out.println("2. Actualizar expediente paciente");
        System.out.println("0. Salir");
        System.out.print("Elige una opción: ");
        try{
            opc = teclado.nextInt();
            teclado.nextLine();
        } catch (Exception ex){
            teclado.nextLine();
            opc = -1;
        }
        return opc;
    }

    public void procesarMenuExpediente(){
        int opc;
        opc = mostrarMenuExpediente();
        while (opc != 0){
            if(opc == 1){
                mostrarExpediente();
            } else if (opc == 2) {
                if(actualizarExpediente()){
                    System.out.println("Expediente actualizado correctamente");
                }else{
                    System.out.println("No se pudo actualizar el expediente");
                }
            }
            opc = mostrarMenuExpediente();
        }
    }
    public void mostrarExpediente(){
        int idPaciente;
        idPaciente = pedirID("Introduzca el ID del paciente: ");
        System.out.println(gestion.mostrarExpediente(idPaciente));
    }
    public boolean actualizarExpediente(){
        int idPaciente;
        String historial;
        PruebasMedicas prueba;
        Tratamiento tratamiento;
        Paciente paciente;
        int opc;
        int opc2;
        idPaciente =pedirID("Introduzca Id del paciente: ");
        if(gestion.buscarPaciente(idPaciente)){
            teclado.nextLine();
            System.out.println("Añada las nuevas actualizaciones al historial:");
            historial = teclado.nextLine();
            try{
                System.out.println("Indique si necesita realizar alguna prueba:");
                System.out.println("0. No pruebas");
                System.out.println("1. Analítica");
                System.out.println("2. Rayos X");
                System.out.println("3. Resonancia magnética");
                System.out.println("4. Ecografía");
                System.out.print("Selecione prueba:");
                opc = teclado.nextInt();
                if (0 <= opc && opc <= 4){
                    prueba = PruebasMedicas.values()[opc];
                    try{
                        System.out.println("Indique si necesita algun tratamiento:");
                        System.out.println("0. No tratamiento");
                        System.out.println("1. Tratamiento digestivo");
                        System.out.println("2. Tratamiento Cardiovascular");
                        System.out.println("3. Cirugía general");
                        System.out.println("4. Dermatología");
                        System.out.println("5. Medicina interna");
                        System.out.println("6. Oncología");
                        System.out.println("7. Oftalmología");
                        System.out.println("8. Psiquiatría");
                        System.out.println("9. Traumatología");
                        System.out.print("Selecione tratamiento: ");
                        opc2 = teclado.nextInt();
                        if(0 <= opc2 && opc2 <= 8){
                            tratamiento = Tratamiento.values()[opc2];
                            gestion.getPaciente(idPaciente).getExpediente().setHistorial(historial);
                            gestion.getPaciente(idPaciente).getExpediente().setPrueba(prueba);
                            gestion.getPaciente(idPaciente).getExpediente().setTratamiento(tratamiento);
                            return true;
                        }else {
                            System.out.println("Numero no válido");
                            return false;
                        }

                    }catch (Exception ex){
                        teclado.nextLine();
                        System.out.println("Error. Tipo de dato no válido");
                        return false;
                    }

                } else{
                    System.out.println("Numero no válido");
                    return false;
                }
            }catch (Exception ex){
                teclado.nextLine();
                System.out.println("Error. Tipo de dato no válido");
                return false;
            }

        }
        return false;
    }



    /*----------------------------------MENU CONSULTAR TUS CITAS--------------------------------*/
    public int mostrarMenuTusCitas() {
        int opc;
        System.out.println("Menú tus citas");
        System.out.println("1. Ver todas tus citas");
        System.out.println("2. Ver tus citas de hoy");
        System.out.println("0. Salir");
        System.out.println("Elige una opción: ");
        System.out.println("--------------------------------------");
        try {
            opc = teclado.nextInt();
            teclado.nextLine();
        } catch (Exception ex) {
            teclado.nextLine();
            opc = -1;
        }
        return opc;

    }

    public void procesarMenuTusCitas(int id) {
        int opc;
        opc = mostrarMenuTusCitas();
        while (opc != 0) {
            if (opc == 1) {
                System.out.println(consultarTuCalendario(id));
            } else if (opc == 2) {
                System.out.println(gestion.consultaAgendaEnUnaFecha(id));
            } else {
                System.out.println("Opcion seleccionada no válida");
            }

        opc = mostrarMenuTusCitas();
    }
}

    public String consultarTuCalendario(int id){
        return gestion.consultaTuAgenda(id);
    }





/*----------------------------------------MENU PRINCIPAL ADMINISTRADOR----------------------------------------*/

    public int mostrarMenuPrincipalAdmin(){
        int opc;

       // if (acceso.equals("Administrador")) {
            System.out.println("MENU ADMINISTRADOR");
            System.out.println("1. Dar de alta a un empleado");
            System.out.println("2. Dar de alta a un estudiante");
            System.out.println("3. Dar de alta a un paciente");
            System.out.println("4. Dar cita a un paciente");
            System.out.println("5. Ingresar/dar alta paciente");
            System.out.println("6. Consultar/actualizar el calendario de un empleado");
            System.out.println("7. Buscar pacientes y empleados");
           System.out.println("8. Realizar factura");
            System.out.println("9. Servicios de mantenimiento");
            System.out.println("10. Visualizacion");
            System.out.println("0. Salir");
            System.out.println("---------------------------------------------");
            System.out.println("Elige una opción: ");
      //  }
        try{
            opc = teclado.nextInt();
            teclado.nextLine();
        }catch(Exception ex){
            teclado.nextLine();
            opc = -1; // no válido
        }
        //return opc;
        return opc;

    }

    public void procesarMenuPrincipalAdmin(){
        int opc;
        opc = mostrarMenuPrincipalAdmin();
        while(opc != 0){
            switch(opc){
                case 1:
                    procesarMenuEmpleados();
                    break;
                case 2:
                    procesarMenuEstudiante();
                    break;
                case 3:
                    procesarMenuPaciente();
                    break;
                case 4:
                    procesarMenuCita();
                    break;
                case 5:
                    procesarMenuHospitalizacion();
                    break;
                case 6:
                    procesarMenuCalendario();
                    break;
                case 7:
                    procesarMenuBusquedas();
                    break;
                case 8:
                    procesarMenuFactura();
                    break;
                case 9:
                    procesarMenuMantenimiento();
                    break;
                case 10:
                    procesarMenuVisualizacion();
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;

            }
             opc = mostrarMenuPrincipalAdmin();
        }

    }

    private int pedirID(String texto){
        int id = 1;
        boolean valido = false;

        while (!valido) {
            try {
                System.out.print(texto);
                id = teclado.nextInt();
                valido = true;
            } catch (Exception ex) {
                teclado.nextLine();
                System.out.println("Error, Tipo no válido");
            }
        }
        return id;
    }


    /*----------------------------------------------MENU EMPLEADOS-----------------------------------------*/
    public int mostrarMenuEmpleados(){
        int opc;
        System.out.println("Menú alta empleados");
        System.out.println("1. Medico");
        System.out.println("2. Enfermero");
        System.out.println("3. Tecnico");
        System.out.println("0. Salir");
        System.out.println("Elige una opción: ");
        System.out.println("--------------------------------------");
        try{
            opc = teclado.nextInt();
            teclado.nextLine();
        } catch (Exception ex){
            teclado.nextLine();
            opc = -1;
        }
        return opc;

    }
    public void procesarMenuEmpleados(){
        int opc;
        opc = mostrarMenuEmpleados();
        while (opc != 0) {
            if (opc == 1) {
                    //medico
                if (darAltaMedico()) {
                    System.out.println("Medico añadido");
                } else {
                        System.out.println("No se ha podido añadir el médico");
                }
            } else if (opc == 2) {
                if (darAltaEnfermero()) {
                        System.out.println("Enfermero añadido");
                } else {
                        System.out.println("No se ha podido añadir el enfermero");
                }

            }else if(opc == 3){
                if(darAltaTecnico()){
                    System.out.println("Técnico añadido");
                } else{
                    System.out.println("No se ha podido añadir el técnico");
                }
            }
            else {
                System.out.println("Opcion seleccionada no válida");
            }
             opc = mostrarMenuEmpleados();
            }

    }

    public boolean darAltaTecnico(){
        int id = 1;
        String dni, nombre;
        Tecnico t;
        id = pedirID("Introduzca id del técnico: ");
        teclado.nextLine();
        System.out.print("DNI: ");
        dni = teclado.nextLine();
        System.out.print("Nombre: ");
        nombre = teclado.nextLine();
        t = new Tecnico(id, dni, nombre);
        return gestion.agregarTecnico(t);
    }
/*----------------------------------------Medico--------------------------------------*/
    //Tengo que mirar que hacer en caso de que se seleccione otras teclas
    public boolean darAltaMedico(){
        int id = 1;
        int unidades = -1;
        String dni, nombre;
        TipoUnidad unidad;
        Medico medico;


        boolean valido;

        valido = false;
        while (!valido){
            try{
                System.out.print("Id: ");
                id = teclado.nextInt();
                valido = true;
            } catch(Exception ex){
                teclado.nextLine();
                System.out.println("Error. Tipo de datos no válido");
            }
        }
        teclado.nextLine();
        /* comprueba que no hay otro empleado con este mismo id*/

        System.out.print("DNI: ");
        dni = teclado.nextLine();
        System.out.print("Nombre: ");
        nombre = teclado.nextLine();
        valido = false;
        while (!valido) {
            try {
                System.out.println("Selecione unidad:");
                System.out.println("1. UCI");
                System.out.println("2. Consultas Externas");
                System.out.println("3. Unidades Especializadas");
                System.out.println("4. Urgencias");
                System.out.println("5. Formacion");
                System.out.println("Selecione unidad:");
                unidades = teclado.nextInt();
                valido = true;
            } catch (Exception ex) {
                teclado.nextLine();
                unidades = -1;
            }
        /*} catch (Exception ex){
            throw new Exception("Error al leer datos");
        }*/
        }
        if(1<=unidades && unidades<=5) {
            unidad = TipoUnidad.values()[unidades - 1];
            medico = new Medico(id, dni, nombre, "password", unidad);
            return gestion.agregarEmpleado(medico);
        }
        return false;
    }

    /*----------------------------Enfermero------------------------------------*/
    public boolean darAltaEnfermero() {
        int unidades = -1;
        int id = -1;
        System.out.println("Nombre: ");
        String nombre = teclado.nextLine();

        System.out.println("DNI: ");
        String dni = teclado.nextLine();

        boolean valido;

        valido = false;
        while (!valido) {
            try {
                System.out.print("Id: ");
                id = teclado.nextInt();
                valido = true;
            } catch (Exception ex) {
                teclado.nextLine();
                System.out.println("Error. Tipo de datos no válido");
                valido = false;
            }
        }
        teclado.nextLine();

        valido = false;
        while (!valido) {
            try {
                System.out.println("Selecione unidad:");
                System.out.println("1. UCI");
                System.out.println("2. Consultas Externas");
                System.out.println("3. Unidades Especializadas");
                System.out.println("4. Urgencias");
                System.out.println("5. Formacion");
                System.out.println("Selecione unidad:");
                unidades = teclado.nextInt();
                valido = true;
            } catch (Exception ex) {
                teclado.nextLine();
                unidades = -1;
            }
        }
        if(1 <= unidades && unidades<=5) {
            TipoUnidad unidad = TipoUnidad.values()[unidades - 1];
            Enfermero enf = new Enfermero(id, dni, nombre, "password", unidad);
            return gestion.agregarEmpleado(enf);
        }
        return false;
    }
/*----------------------------------MENU ESTUDIANTE-------------------------------------------------*/
    public int mostrarMenuEstudiante(){
        int opc;
        System.out.println("Menú alta estudiantes");
        System.out.println("1. Dar alta estudiante");
        System.out.println("2. Asignar clase");
        System.out.println("3. Asignar cita con un empleado");
        System.out.println("4. Ver todas las clases");
        System.out.println("5. Listar todos los estudiantes");
        System.out.println("6. Crear aula");
        System.out.println("0. Salir");
        System.out.println("Elige una opción: ");
        System.out.println("--------------------------------------");
        try{
            opc = teclado.nextInt();
            teclado.nextLine();
        } catch (Exception ex){
            teclado.nextLine();
            opc = -1;
        }
        return opc;
    }
    public void procesarMenuEstudiante(){
        int opc;
        opc = mostrarMenuEstudiante();
            while (opc != 0) {
                if (opc == 1) {
                    if (darAltaEstudiante()) {
                        System.out.println("Estudiante añadido");
                    } else {
                        System.out.println("No se ha podido añadir el estudiante");
                    }
                } else if (opc ==  2) {
                    if(clase()){
                        System.out.println("Clase asignada");
                    } else {
                        System.out.println("No se ha podido unir a la clase");
                    }

                } else if (opc == 3) {
                    if(cita()){
                        System.out.println("Cita asignada al estudiante");
                    } else{
                        System.out.println("No se ha podido unir a la cita");
                    }

                } else if (opc == 4) {
                    System.out.println(gestion.toStringClases());
                } else if (opc == 5) {
                    System.out.println(gestion.toStringEstudiantes());

                } else if (opc == 6) {
                    if(abrirAula()){
                        System.out.println("Aula creada");
                    }else {
                        System.out.println("No se ha podido crear el aula");
                    }

                } else {
                    System.out.println("Opcion no valida");
                }
               opc = mostrarMenuEstudiante();
            }
    }

    public boolean darAltaEstudiante(){
        int id = 1;
        System.out.println("nombre:");
        String nombre = teclado.nextLine();

        System.out.println("DNI:");
        String dni = teclado.nextLine();

        boolean valido;

        valido = false;
        while (!valido){
            try{
                System.out.print("Id: ");
                id = teclado.nextInt();
                valido = true;
            } catch(Exception ex){
                teclado.nextLine();
                System.out.println("Error. Tipo de datos no válido");
                valido = false;
            }
        }
        teclado.nextLine();

        Estudiante est = new Estudiante(id, nombre, dni);

        return gestion.agregarEstudiante(est);

    }

    public boolean abrirAula(){
        int idFormador;
        Empleado formador;
        idFormador = pedirID("Introduzca el id del formador: ");
        formador = gestion.getEmpleado(idFormador);
        if(formador != null) {
            gestion.crearAula(formador);
            return true;
        }
        return false;
    }

   public boolean clase(){
        int idEstudiante, idAula;
        Estudiante est;
        Aula clase;
       System.out.println(gestion.toStringClases());
       idEstudiante = pedirID("Introduzca Id del estudiante: ");
       idAula = pedirID("Introduzca id del aula: ");
       clase = gestion.getAula(idAula);
       est = gestion.getEstudiante(idEstudiante);
       if(clase!= null && est != null){
           clase.agregarEstudiante(est);
           return true;
       }
       return false;

   }

   public boolean cita(){
        int idEstudiante, idCita;
        Estudiante est;
        System.out.println(gestion.toStringCitas());
       idEstudiante = pedirID("Introduzca su Id: ");
        idCita = pedirID("Introduzca el id de la cita a la que desea acudir: ");
        est = gestion.getEstudiante(idEstudiante);
        if(est != null){
            return gestion.asignarCitaEstudiante(est, idCita);
        }
        return false;
    }


/*-------------------------------MENU PACIENTE-----------------------------------------*/
    public int mostrarMenuPaciente(){
        int opc;
        System.out.println("Menú alta pacientes");
        System.out.println("1. dar alta paciente");
        System.out.println("0. Salir");
        System.out.println("Elige una opción: ");
        System.out.println("--------------------------------------");
        try{
            opc = teclado.nextInt();
            teclado.nextLine();
        } catch (Exception ex){
            teclado.nextLine();
            opc = -1;
        }
        return opc;

    }
    public void procesarMenuPaciente(){
        int opc;
        opc = mostrarMenuPaciente();
        while (opc != 0) {
            if (opc == 1) {
                if (darAltaPaciente()) {
                        System.out.println("Paciente añadido");
                } else {
                        System.out.println("No se ha podido añadir el paciente");
                }
            } else {
                    System.out.println("Opcion no valida");
            }
            opc = mostrarMenuPaciente();
        }
    }

    public boolean darAltaPaciente(){
        boolean seguridadSocial;
        int id = -1;
        int seguro = 0;
        System.out.println("nombre:");
        String nombre = teclado.nextLine();

        System.out.println("DNI:");
        String dni = teclado.nextLine();

        boolean valido;

        valido = false;
        while (!valido){
            try{
                System.out.print("Id: ");
                id = teclado.nextInt();
                valido = true;
            } catch(Exception ex){
                teclado.nextLine();
                System.out.println("Error. Tipo de datos no válido");
                valido = false;
            }
        }
        teclado.nextLine();
        System.out.println("Indique cual se si tiene seguridad social");
        valido = false;
        while(!valido) {
            try {
                System.out.println("1. Si  2. No");
                seguro = teclado.nextInt();
                valido = true;
            } catch (Exception ex) {
                teclado.nextLine();
                System.out.println("Error. Tipo de dato no válido");
            }
        }
        if(seguro == 1){
            seguridadSocial = true; //esto hay que cambiarlo
            } else{
            seguridadSocial = false;
             }
        Paciente paciente = new Paciente(id, dni, nombre, seguridadSocial);
        return gestion.agregarPaciente(paciente);
    }


 /*-----------------------------------MENU CITA---------------------------------------------------*/
    public int mostrarMenuCita(){
        int opc;
        System.out.println("Menú citas");
        System.out.println("1. dar cita a un paciente");
        System.out.println("2. ver todas las citas del sistema");
        System.out.println("0. Salir");
        System.out.println("Elige una opción: ");
        System.out.println("--------------------------------------");
        try{
            opc = teclado.nextInt();
            teclado.nextLine();
        } catch (Exception ex){
            teclado.nextLine();
            opc = -1;
        }
        return opc;
    }

    public void procesarMenuCita(){
        int opc;
        opc = mostrarMenuCita();
        while (opc != 0) {
            if (opc == 1) {
                if (darCitaPaciente()) {
                    System.out.println("Cita añadida");
                } else {
                    System.out.println("No se ha podido añadir la cita");
                }
            } else if (opc == 2) {
                System.out.println(gestion.toStringCitas());

            } else {
                    System.out.println("Opcion no valida");
            }
            opc = mostrarMenuCita();

            }
    }

    private LocalDateTime pedirFecha(){
        LocalDateTime inicio;
        int dia=1, mes=1, anno=1000, hora=12, minutos=00;
        boolean valido;

        valido = false;
        while(!valido) {
            try {
                System.out.print("Introduzca dia: ");
                dia = teclado.nextInt();
                System.out.print("Introduzca mes: ");
                mes = teclado.nextInt();
                System.out.print("Introduzca año: ");
                anno = teclado.nextInt();
                System.out.print("Introduzca horas: ");
                hora = teclado.nextInt();
                System.out.print("Introduzca minutos: ");
                minutos = teclado.nextInt();
                valido = true;
            }catch (Exception ex){
                teclado.nextLine();
                System.out.println("Error, Tipo no válido");
            }
        }
        inicio = LocalDateTime.of(anno, mes, dia, hora, minutos);
        return inicio;
    }

    public boolean darCitaPaciente(){
        Paciente paciente;
        Empleado empleado;
        int idPaciente, idEmpleado;
        LocalDateTime inicio;
        Cita cita;
        int duracion = 30;
        idPaciente = pedirID("Introduzca id paciente: ");
        paciente = gestion.getPaciente(idPaciente);
        if (paciente != null ){
            // continúas pidiendo datos
            idEmpleado = pedirID("Introduzca id personal medico: ");
            empleado = gestion.getEmpleado(idEmpleado);
            if (empleado != null){
                inicio = pedirFecha();
                boolean valido = false;
                while (!valido) {
                    try {
                        System.out.print("Duracion: ");
                        duracion = teclado.nextInt();
                        valido = true;
                    } catch (Exception ex) {
                        teclado.nextLine();
                        System.out.println("Error. Tipo de datos no válido");
                    }
                }
                System.out.println(inicio);
                cita = new Cita(idPaciente, idEmpleado, inicio, duracion, empleado.getUnidad());
                return gestion.actualizacionAgendaMedico_add(idEmpleado, cita);
            }
            else {
                return false;
                }
        }
        else {
            return false;
        }
    }

    /*--------------------------MENU HOSPITALIZACIÓN-------------------------*/
    private int mostrarMenuHospitalizacion(){
        int opc;

        System.out.println("Menú hospitalización");
        System.out.println("1. Ingresar paciente");
        System.out.println("2. Dar de alta a un paciente");
        System.out.println("0. Salir");
        System.out.print("Elige una opción: ");
        try{
            opc = teclado.nextInt();
            teclado.nextLine();
        } catch (Exception ex){
            teclado.nextLine();
            opc = -1;
        }
        return opc;
    }

   public void procesarMenuHospitalizacion(){
       int opc;

       opc = mostrarMenuHospitalizacion();
       while (opc != 0){
           if (opc == 1) {
               if (ingresarPaciente()) {
                   System.out.println("Paciente ingresado correctamente");
               } else {
                   System.out.println("No se ha podido ingresar al paciente");
               }
           } else if (opc == 2) {
               if (liberarPaciente()) {
                   System.out.println("El paciente ha sido dado de alta correctamente");
               } else {
                    System.out.println("No se ha podido dar de alta al paciente");
               }
            } else {
                System.out.println("Opcion no valida");
            }
           opc = mostrarMenuHospitalizacion();
       }
   }
    //Para ingresar pacientes
    public boolean ingresarPaciente(){
        int idPaciente;
        idPaciente = pedirID("Ingrese el Id del paciente: ");
       return gestion.realizarBaja(idPaciente);
    }

    //Dar alta a pacientes: des-hospitalizar
    public boolean liberarPaciente(){
        int idPaciente;
        idPaciente = pedirID("Ingrese el Id del paciente: ");
       return gestion.realizarAlta(idPaciente);
    }
    public String busquedas(){
        int id;
        String busqueda;
       id = pedirID("Introduzca el Id de la persona: ");
       busqueda = gestion.busquedas(id);
       return busqueda;
    }
    /*--------------------------------------MENU CALENDARIO------------------------------------------------*/
    public int mostrarMenuCalendario(){
        int opc;
        System.out.println("Menú agenda médico");
        System.out.println("1. Consultar agenda empleado");
        System.out.println("2. Eliminar cita empleado");
        System.out.println("3. Añadir cita a un empleado");
        System.out.println("0. Salir");
        System.out.print("Elige una opción: ");
        try{
            opc = teclado.nextInt();
            teclado.nextLine();
        } catch (Exception ex){
            teclado.nextLine();
            opc = -1;
        }
        return opc;
    }
    public void procesarMenuCalendario(){
        int opc;
        opc = mostrarMenuCalendario();
        while(opc != 0){
            if(opc == 1){
                System.out.println(consultarCalendarioMedico());
            }
            else if(opc == 2){
                if(removeCita()){
                    System.out.println("Cita eliminada");
                }else{
                    System.out.println("No se ha podido eliminar cita");
                }
            }else if(opc == 3){
                if(darCitaPaciente()){
                    System.out.println("Cita añadida a la agenda");
                }else{
                    System.out.println("No se ha podido añadir la cita");
                }

            }
            else{
                System.out.println("Error. Opción no válida");
            }
            opc = mostrarMenuCalendario();
        }
    }
    public String consultarCalendarioMedico(){
        int id;
        id = pedirID("Introduzca Id del empleado:");
        return gestion.consultaAgendaMedico(id);
    }

    public boolean removeCita(){
        int idEmpleado;
        int idCita;
        idEmpleado = pedirID("Introduzca Id del empleado: ");
        idCita = pedirID("Introduzca Id de la cita: ");
        return gestion.actualizacionAgendaMedico_remove(idEmpleado, idCita);
    }

    public static void clearScreen() {
        //System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /*--------------------------------MENU BUSQUEDAS------------------------------*/
    public int mostrarMenuBusquedas(){
        int opc;
        System.out.println("Menú expediente paciente");
        System.out.println("1. Buscar pacientes y empleados");
        System.out.println("2. Listar a todos los pacientes");
        System.out.println("3. Listar a todos los empleados");
        System.out.println("0. Salir");
        System.out.print("Elige una opción: ");
        try{
            opc = teclado.nextInt();
            teclado.nextLine();
        } catch (Exception ex){
            teclado.nextLine();
            opc = -1;
        }
        return opc;
    }
    public void procesarMenuBusquedas(){
        int opc;
        opc = mostrarMenuBusquedas();
        while (opc != 0){
            if(opc == 1){
                System.out.println(busquedas());
            } else if (opc == 2) {
                System.out.println(gestion.toStringPacientes());
            } else if (opc == 3) {
                System.out.println(gestion.toStringMedicos());
            }
            opc = mostrarMenuBusquedas();
        }
    }

    /*-------------------------MENU VISUALIZACION--------------------------------*/
    public int mostrarMenuVisualizacion(){
        int opc;
        System.out.println("Menú visualización de diferentes datos:");
        System.out.println("1. Empleados del sistema según su unidad");
        System.out.println("2. Listado de agendas de cada especialista");
        System.out.println("3. Pacientes ingresados");
       // System.out.println("4. Pacientes con citas en la agenda");
        System.out.println("4. Listado de las habitaciones ocupadas");
        System.out.println("5. Listado de citas en un dia concreto");
        System.out.println("0. Salir");
        System.out.print("Elige una opción: ");
        try{
            opc = teclado.nextInt();
            teclado.nextLine();
        } catch (Exception ex){
            teclado.nextLine();
            opc = -1;
        }
        return opc;
    }

    public void procesarMenuVisualizacion(){
        int opc;
        opc = mostrarMenuVisualizacion();
        while (opc != 0){
            if(opc == 1){
                System.out.println(gestion.toStringMedicosUnidad());
            }else if (opc == 2){
                System.out.println(gestion.toStringMedicosAgenda());
            } else if (opc == 3) {
                System.out.println(gestion.toStringHospitalizados());
            } else if (opc == 4) {
                System.out.println(gestion.toStringHabitaciones());
            } else if (opc == 5) {
                System.out.println(citasEnUnDia());

            } else {
                System.out.println("Opción incorrecta");
            }
            opc = mostrarMenuVisualizacion();
        }
    }
    public String citasEnUnDia(){
        int dia, mes, year;
        dia = pedirID("Introduzca el dia: ");
        mes = pedirID("Introduzca mes: ");
        year = pedirID("Intrduzca el año: ");
        return gestion.consultaCitasEnUnDiaConcreto(dia, mes, year);
    }

    /*--------------------------------------MENU FACTURA-----------------------------------------------*/
    public int mostrarMenuFactura(){
        int opc;
        System.out.println("Menú factura:");
        System.out.println("1. Consultar expediente de un paciente");
        System.out.println("2. Realizar factura");
        System.out.println("3. Consultar los precios");
        System.out.println("0. Salir");
        System.out.print("Elige una opción: ");
        try{
            opc = teclado.nextInt();
            teclado.nextLine();
        } catch (Exception ex){
            teclado.nextLine();
            opc = -1;
        }
        return opc;
    }
    public void procesarMenuFactura(){
        int opc;
        opc = mostrarMenuFactura();
        while (opc != 0){
            if(opc == 1) {
                mostrarExpediente();
            } else if (opc == 2) {
                System.out.println(preparacionFactura());

            } else if (opc == 3) {
                mostrarPrecios();

            } else {
                System.out.println("Opción incorrecta");
            }
            opc = mostrarMenuFactura();
        }
    }

    public String preparacionFactura(){
        int id;
        Paciente p;
        id = pedirID("Introduzca id paciente: ");
        p = gestion.getPaciente(id);
        double precio = 0.0;
        double precio1 = 0.0;
        if(p != null && !p.getSeguridadSocial()) {

            System.out.println(p.getExpediente().toString());
            //p.getExpediente().toStringPruebas();
            precio = p.getExpediente().calcularImportePruebas();
            return "precio: " + precio + " euros";
        }
        return "Este paciente tiene Seguridad Social";
    }
    public void mostrarPrecios(){
        int id;
        Paciente p;
        id = pedirID("Introduzca id paciente: ");
        p = gestion.getPaciente(id);
        if(p != null ) {
            p.getExpediente().toStringPruebas();
        }
    }

    /*-------------------------MENU MANTENIMIENTO--------------------*/

    public int mostrarMenuMantenimiento(){
        int opc;
        System.out.println("Menú Mantenimiento:");
        System.out.println("1. Listar los técnicos del sistema");
        System.out.println("2. Mantenimiento o reparacion de recurso");
        System.out.println("0. Salir");
        System.out.print("Elige una opción: ");
        try{
            opc = teclado.nextInt();
            teclado.nextLine();
        } catch (Exception ex){
            teclado.nextLine();
            opc = -1;
        }
        return opc;
    }
    public void procesarMenuMantenimiento(){
        int opc;
        opc = mostrarMenuMantenimiento();
        while (opc != 0){
            if(opc == 1) {
                System.out.println(gestion.toStringTecnicos());
            } else if (opc == 2) {
                System.out.println(mantenimientoRecurso());

            } else {
                System.out.println("Opción incorrecta");
            }
            opc = mostrarMenuMantenimiento();
        }
    }


    public String mantenimientoRecurso(){
        String recurso;
        int id;
        Tecnico t;
        System.out.println("Escribe el nombre del recurso a reparar: ");
        recurso = teclado.nextLine();
        System.out.println(gestion.toStringTecnicos());
        System.out.println("Asignar a un técnico: ");
        id = pedirID("Introduze id del técnico: ");
        t = gestion.getTecnico(id);
        if( t != null){
            return "Recurso a reparar: " + recurso + "\n" + "tecnico encargado: " + t.toString();
        }
       return "No existe técnico con ese id";
    }


    



}
