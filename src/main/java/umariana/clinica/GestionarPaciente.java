/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package umariana.clinica;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 *
 * @author Tatiana Acosta
 */
public class GestionarPaciente {
    ArrayList<Paciente> misPacientes = new ArrayList<>();
    
    public void agregarPaciente(int idPaciente, String nombre, String apellido, String diagnostico){
        Paciente miPaciente = new Paciente(idPaciente, nombre, apellido, diagnostico);
        misPacientes.add(miPaciente);
    }
    
    private Paciente buscarPorId(int idPaciente){
        for(Paciente p: misPacientes){
            if(idPaciente == p.getIdPaciente()){
                return p;
            }
        }
        return null;
    }
    
    public boolean editarPaciente(int idPaciente, String nombre, String apellido, String diagnostico){
        if(misPacientes.isEmpty()){
            return false;
        }
        Paciente encontrado = buscarPorId(idPaciente);
        if(encontrado==null){
            return false;
        }
        
        try{
            encontrado.actualizarNombre(nombre);
            encontrado.actualizarApellido(apellido);
            encontrado.actualizarDiagnostico(diagnostico);
            return true;
        }catch(IllegalArgumentException e){
            return false;
        }
    }
    
    public boolean eliminarPaciente(int idPaciente){
            if(misPacientes.isEmpty()){
                return false;
            }
            Paciente encontrado = buscarPorId(idPaciente);
            
            if(encontrado==null){
                return false;
            }
            
            misPacientes.remove(encontrado);
            return true;
    }
    
    public ArrayList<Paciente> obtenerPacientes(){
        return misPacientes;
    }
    
    public Paciente obtenerPaciente(int idPaciente){
        return buscarPorId(idPaciente);
    }
    
    public void guardarArchivo(){
        try(PrintWriter pw = new PrintWriter(new FileWriter("pacientes.txt"))){
            for(Paciente p: misPacientes){
                pw.println(
                p.getIdPaciente() + ";" +
                p.getNombre() + ";" +
                p.getApellido() + ";" +
                p.getDiagnostico());
            }
        }catch(IOException e){
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }
    
    public void cargarArchivo(){
        misPacientes.clear();
        try(BufferedReader br = new BufferedReader(new FileReader("pacientes.txt"))){
            String linea;
            while((linea = br.readLine()) != null){
                String[]datos=linea.split(";");
                
                int idPaciente = Integer.parseInt(datos[0]);
                String nombre = datos[1];
                String apellido = datos[2];
                String diagnostico = datos[3];
                Paciente p = new Paciente (idPaciente, nombre, apellido, diagnostico);
                misPacientes.add(p);
            }
        }catch(FileNotFoundException e){
            System.out.println("No hay archivo previo, se inicializa con lista vacía");
        }catch(IOException e){
            System.out.println("Error al cargar: " + e.getMessage());
        }
    }
}