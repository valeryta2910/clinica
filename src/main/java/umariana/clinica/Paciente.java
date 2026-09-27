/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package umariana.clinica;
import java.io.Serializable;

/**
 *
 * @author Tatiana Acosta
 */
public class Paciente{
    private int idPaciente;
    private String nombre;
    private String apellido;
    private String diagnostico;

    public Paciente() {
    }

    public Paciente(int idPaciente, String nombre, String apellido, String diagnostico) {
        
        if(idPaciente<=0){
            throw new IllegalArgumentException("El id del paciente no puede ser negativo");
        }
        if(nombre==null || nombre.trim().isEmpty()){
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if(apellido==null || apellido.trim().isEmpty()){
            throw new IllegalArgumentException("El apellido no puede estar vacío");
        }
        if(diagnostico==null || diagnostico.trim().isEmpty()){
            throw new IllegalArgumentException("El diagnostico no puede estar vacío");
        }
           
        this.idPaciente = idPaciente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.diagnostico = diagnostico;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDiagnostico() {
        return diagnostico;
    }
    
    public void actualizarNombre(String nuevoNombre){
        if(nuevoNombre==null || nuevoNombre.trim().isEmpty()){
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        this.nombre=nuevoNombre;
    }
    
    public void actualizarApellido(String nuevoApellido){
        if(nuevoApellido==null || nuevoApellido.trim().isEmpty()){
            throw new IllegalArgumentException("El apellido no puede estar vacío");
        }
        this.apellido=nuevoApellido;
    }
    
    public void actualizarDiagnostico(String nuevoDiagnostico){
        if(nuevoDiagnostico==null || nuevoDiagnostico.trim().isEmpty()){
            throw new IllegalArgumentException("El diagnostico no puede estar vacío");
        }
        this.diagnostico=nuevoDiagnostico;
    }
}
