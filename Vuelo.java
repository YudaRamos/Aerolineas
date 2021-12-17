/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeropuerto;

import java.util.ArrayList;

/**
 *
 * @author yuda
 */
public class Vuelo {
    //el vuelo sera un array de pasajeros
    ArrayList<Pasajero>tripulacion;
    String codigo;
    String horaSalida;
    String destino;
    String procedencia;
    int plazaFumador;
    int plazaTurista;
    int plazaNoFumador;
    int plazaPrimera;

    public Vuelo(String codigo, String horaSalida, String destino, String procedencia, int plazaFumador, int plazaTurista, int plazaNoFumador, int plazaPrimera) {
        this.codigo = codigo;
        this.horaSalida = horaSalida;
        this.destino = destino;
        this.procedencia = procedencia;
        this.plazaFumador = plazaFumador;
        this.plazaTurista = plazaTurista;
        this.plazaNoFumador = plazaNoFumador;
        this.plazaPrimera = plazaPrimera;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    public int getPlazaFumador() {
        return plazaFumador;
    }

    public void setPlazaFumador(int plazaFumador) {
        this.plazaFumador = plazaFumador;
    }

    public int getPlazaTurista() {
        return plazaTurista;
    }

    public void setPlazaTurista(int plazaTurista) {
        this.plazaTurista = plazaTurista;
    }

    public int getPlazaNoFumador() {
        return plazaNoFumador;
    }

    public void setPlazaNoFumador(int plazaNoFumador) {
        this.plazaNoFumador = plazaNoFumador;
    }

    public int getPlazaPrimera() {
        return plazaPrimera;
    }

    public void setPlazaPrimera(int plazaPrimera) {
        this.plazaPrimera = plazaPrimera;
    }
    
}
