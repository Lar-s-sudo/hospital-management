public class Paciente extends Persona {
    private int id;
    private boolean seguridadSocial;
    private Expediente expediente;
   

    public Paciente(int id, String dni, String nombre, boolean seguridadSocial) {
        super(id, dni, nombre);
        this.id = id;
        this.seguridadSocial = seguridadSocial;
        this.expediente = new Expediente();

    }

    public Expediente getExpediente(){
        return expediente;
    }
    public void setExpediente(Expediente expediente){
        this.expediente = expediente;
    }

    public boolean getSeguridadSocial(){
        return this.seguridadSocial;
    }
    public void setInformacionSeguro(boolean seguridadSocial){
        this.seguridadSocial = seguridadSocial;
    }

    public String toString(){
        return super.toString() + " " + seguridadSocial;
    }

    public boolean equals(Paciente pac){
        return this.id == pac.id;
    }
}


