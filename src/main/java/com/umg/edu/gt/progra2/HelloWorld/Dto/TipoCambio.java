package com.umg.edu.gt.progra2.HelloWorld.Dto;

public class TipoCambio {

        private String monedaOrigen;
        private String monedaDestino;
        private Double valor;

        public TipoCambio() {
            super();
        }

        public TipoCambio(String monedaOrigen, String monedaDestino, Double valor) {
            super();
            this.monedaOrigen = monedaOrigen;
            this.monedaDestino = monedaDestino;
            this.valor = valor;
        }

        public String getMonedaOrigen() {
            return monedaOrigen;
        }

        public void setMonedaOrigen(String monedaOrigen) {
            this.monedaOrigen = monedaOrigen;
        }

        public String getMonedaDestino() {
            return monedaDestino;
        }

        public void setMonedaDestino(String monedaDestino) {
            this.monedaDestino = monedaDestino;
        }

        public Double getValor() {
            return valor;
        }

        public void setValor(Double valor) {
            this.valor = valor;
        }
}
