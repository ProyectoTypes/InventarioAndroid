package type.proy.com.inventarioandroid.dom.soporte;

/**
 * Created by Proyect Types on 20/12/14.
 */
public class Computadora {


    private DatoTitle value;

    public DatoTitle getValue() {
        return value;
    }

    public void setValue(DatoTitle value) {
        this.value = value;
    }

    Members members;

    public Members getMembers() {
        return members;
    }

    public void setMembers(Members members) {
        this.members = members;
    }

    // nested class
    public class Members {
        private Dato usuario;
        public Dato getUsuario() {
            return usuario;
        }

        public void setUsuario(Dato usuario) {
            this.usuario = usuario;
        }

        private Dato tecnico;
        public Dato getTecnico() {
            return tecnico;
        }
        
        public void setTecnico(Dato tecnico) {
            this.tecnico = tecnico;
        }

        private DatoString nombreEquipo;
        public DatoString getNombreEquipo() {
            return nombreEquipo;
        }

        public void setNombreEquipo(DatoString nombreEquipo) {
            this.nombreEquipo = nombreEquipo;
        }

        private Dato placaDeRed;
        public Dato getPlacaDeRed() {
            return placaDeRed;
        }

        public void setPlacaDeRed(Dato placaDeRed) {
            this.placaDeRed = placaDeRed;
        }
;
        private Dato motherboard;
        public Dato getMotherboard() {
            return motherboard;
        }

        public void setMotherboard(Dato motherboard) {
            this.motherboard = motherboard;
        }

        private Dato procesador;
        public Dato getProcesador() {
            return procesador;
        }

        public void setProcesador(Dato procesador) {
            this.procesador = procesador;
        }

        private Dato disco;
        public Dato getDisco() {
            return disco;
        }

        public void setDisco(Dato disco) {
            this.disco = disco;
        }

        private Dato impresora;
        public Dato getImpresora() {
            return impresora;
        }

        public void setImpresora(Dato impresora) {
            this.impresora = impresora;
        }

        private Dato memoria;
        public Dato getMemoria() {
            return memoria;
        }
        public void setMemoria(Dato memoria) {
            this.memoria = memoria;
        }
    }

}
