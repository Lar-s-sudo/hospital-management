public abstract class Persona {
        private int id;
        private String dni;
        private String nombre;

        public Persona(){}

        public Persona(int id, String dni, String nombre){
            this.id = id;
            this.dni = dni;
            this.nombre = nombre;
        }

        public int getId(){
            return id;
        }
        public void setId(int id){
            this.id = id;
        }

        public String getDni(){
            return dni;
        }

        public String getNombre(){
            return nombre;
        }

        //set id?

        public void setNombre(String nombre){
            this.nombre = nombre;
        }

        public void setDni(String dni){
            this.dni = dni;
        }

        // Sobreescribir el toString de su clase padre (Object por defecto)
        public String toString(){
            return dni + " " +  id + " " +  nombre + " ";
        }
    }


