/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Cuboz
 */
public class Deuda{
    private String CI;
    private int anio ;
    private Impuesto impuesto;
    private double monto;

    public Deuda(String CI, int anio, Impuesto impuesto, double monto) {
        this.CI = CI;
        this.anio = anio;
        this.impuesto = impuesto;
        this.monto = monto;
    }

    public String getCI() {
        return CI;
    }

    public void setCI(String CI) {
        this.CI = CI;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public Impuesto getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(Impuesto impuesto) {
        this.impuesto = impuesto;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        return "Deuda{" + "CI=" + CI + ", anio=" + anio + ", impuesto=" + impuesto + ", monto=" + monto + '}';
    }



}
