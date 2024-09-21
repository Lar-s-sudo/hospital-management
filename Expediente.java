import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class Expediente {
    private String historial;
    public List<PruebasMedicas> pruebas;
    public List<Tratamiento> tratamientos;
   public static HashMap<PruebasMedicas, Double> preciosPruebas;
   public static HashMap<Tratamiento, Double> preciosTratamientos;


    private void ponerPrecios(){
        preciosPruebas = new HashMap<>();
        preciosTratamientos = new HashMap<>();
        //precios pruebas
        preciosPruebas.put(PruebasMedicas.noPrueba, 000.0);
        preciosPruebas.put(PruebasMedicas.analitica, 300.0);
        preciosPruebas.put(PruebasMedicas.rayosX, 350.50);
        preciosPruebas.put(PruebasMedicas.resonancia, 400.45);
        preciosPruebas.put(PruebasMedicas.ecografia, 536.0);
        //Precios tratamientos
        preciosTratamientos.put(Tratamiento.sinTratamiento, 000.0);
        preciosTratamientos.put(Tratamiento.aparatoDigestivo, 326.40);
        preciosTratamientos.put(Tratamiento.cardiologia, 380.45);
        preciosTratamientos.put(Tratamiento.dermatologia, 350.50);
        preciosTratamientos.put(Tratamiento.cirugiaGeneral, 270.45);
        preciosTratamientos.put(Tratamiento.medicinaInterna, 350.50);
        preciosTratamientos.put(Tratamiento.oncologia, 400.45);
        preciosTratamientos.put(Tratamiento.oftalmologia, 260.45);
        preciosTratamientos.put(Tratamiento.psiquiatria, 260.30);
        preciosTratamientos.put(Tratamiento.traumatologia, 280.50);
        preciosTratamientos.put(Tratamiento.diabetes, 400.30);

    }


public double calcularImportePruebas(){
        double total = 0.0;
        double precio;
        for (PruebasMedicas p : pruebas) {
            precio = preciosPruebas.get(p);
            total += precio;
        }
        for (Tratamiento t : tratamientos){
            precio = preciosTratamientos.get(t);
            total += precio;
        }
        return total;
    }
    public void toStringPruebas(){
        for (PruebasMedicas key: preciosPruebas.keySet()){
            System.out.println(key + " " + preciosPruebas.get(key) + " euros");
        }
        for (Tratamiento key: preciosTratamientos.keySet()){
            System.out.println(key + " " + preciosTratamientos.get(key) + " euros");
        }
    }
    //Constructores
    public Expediente(String historial){
        this.historial = historial;
        pruebas = new ArrayList<PruebasMedicas>();
        tratamientos = new ArrayList<Tratamiento>();
       ponerPrecios();
    }
    public Expediente(){
        this.historial = " ";
        pruebas = new ArrayList<PruebasMedicas>();
        tratamientos = new ArrayList<Tratamiento>();
        ponerPrecios();

    }
    public Expediente(String historial, PruebasMedicas prueba, Tratamiento tratamiento){
        this.historial = historial;
        this.pruebas = new ArrayList<PruebasMedicas>();
        pruebas.add(prueba);
        this.tratamientos = new ArrayList<Tratamiento>();
        tratamientos.add(tratamiento);
        ponerPrecios();
    }

    public String getHistorial(){
        return historial;
    }
    public List<PruebasMedicas> getPruebas(){
        return pruebas;
    }
    public List<Tratamiento> getTratamiento(){
        return tratamientos;
    }

    public void setHistorial(String historial){
        this.historial = this.historial + "\n" + historial;
    }

    public void setPrueba(PruebasMedicas prueba){
        pruebas.add(prueba);
    }
    public void setTratamiento(Tratamiento tratamiento){
        tratamientos.add(tratamiento);
    }


    public String toString(){
        String cadena = "";
        String cadena1 = "";
        for(int i = 0; i < pruebas.size(); i++){
            cadena = cadena +  " " + pruebas.get(i).name() + " / ";
        }
        for(int i = 0; i < tratamientos.size(); i++){
            cadena1 = cadena1 +  " " + tratamientos.get(i).name() + " / ";
        }

        return "HISTORIAL MÉDICO: " +  historial + " " + "\n" + "Pruebas médicas:" + " " + cadena + " " + "\n" +
                "Tratamiento:" + " " + cadena1;
    }
}
