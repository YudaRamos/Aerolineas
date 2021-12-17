/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeropuerto;

/**
 *
 * @author yuda
 */
public class Pasajero {

    String numero;
    String codigoVuelo;
    String tipoPlaza;
    boolean fumador;

    public Pasajero(String numero, String codigoVuelo, String tipoPlaza, boolean fumador) {
        this.numero = numero;
        this.codigoVuelo = codigoVuelo;
        this.tipoPlaza = tipoPlaza;
        this.fumador = fumador;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCodigoVuelo() {
        return codigoVuelo;
    }

    public void setCodigoVuelo(String codigoVuelo) {
        this.codigoVuelo = codigoVuelo;
    }

    public String getTipoPlaza() {
        return tipoPlaza;
    }

    public void setTipoPlaza(String tipoPlaza) {
        this.tipoPlaza = tipoPlaza;
    }

    public boolean isFumador() {
        return fumador;
    }

    public void setFumador(boolean fumador) {
        this.fumador = fumador;
    }

}
