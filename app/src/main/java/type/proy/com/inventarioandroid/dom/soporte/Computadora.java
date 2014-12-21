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
        private Usuario usuario;
        public Usuario getUsuario() {
            return usuario;
        }

        public void setUsuario(Usuario usuario) {
            this.usuario = usuario;
        }

        private Tecnico tecnico;
        public Tecnico getTecnico() {
            return tecnico;
        }

        public void setTecnico(Tecnico tecnico) {
            this.tecnico = tecnico;
        }

        private DatoString nombreEquipo;
        public DatoString getNombreEquipo() {
            return nombreEquipo;
        }

        public void setNombreEquipo(DatoString nombreEquipo) {
            this.nombreEquipo = nombreEquipo;
        }

        private PlacaDeRed placaDeRed;
        public PlacaDeRed getPlacaDeRed() {
            return placaDeRed;
        }

        public void setPlacaDeRed(PlacaDeRed placaDeRed) {
            this.placaDeRed = placaDeRed;
        }
;
        private Motherboard motherboard;
        public Motherboard getMotherboard() {
            return motherboard;
        }

        public void setMotherboard(Motherboard motherboard) {
            this.motherboard = motherboard;
        }

        private Procesador procesador;
        public Procesador getProcesador() {
            return procesador;
        }

        public void setProcesador(Procesador procesador) {
            this.procesador = procesador;
        }

        private Disco disco;
        public Disco getDisco() {
            return disco;
        }

        public void setDisco(Disco disco) {
            this.disco = disco;
        }

        private Impresora impresora;
        public Impresora getImpresora() {
            return impresora;
        }

        public void setImpresora(Impresora impresora) {
            this.impresora = impresora;
        }

        private Memoria memoria;
        public Memoria getMemoria() {
            return memoria;
        }

        public void setMemoria(Memoria memoria) {
            this.memoria = memoria;
        }



        public class PlacaDeRed{
            private DatoString ip;

            public DatoString getIp() {
                return ip;
            }

            public void setIp(DatoString ip) {
                this.ip = ip;
            }

            private DatoString mac;

            public DatoString getMac() {
                return mac;
            }

            public void setMac(DatoString mac) {
                this.mac = mac;
            }
        }
        public class Motherboard{
            private DatoString modelo;
            public DatoString getModelo() {
                return modelo;
            }

            public void setModelo(DatoString modelo) {
                this.modelo = modelo;
            }
        }
        public class Procesador{
            private DatoString modelo;

            public DatoString getModelo() {
                return modelo;
            }

            public void setModelo(DatoString modelo) {
                this.modelo = modelo;
            }
        }
        public class Disco{
            //Faltaria CategoriaDisco
            private String marca;
        }
        public class Impresora{
            //Faltarian otros atributos.

            private DatoString fabricanteImpresora;

            public DatoString getFabricanteImpresora() {
                return fabricanteImpresora;
            }

            public void setFabricanteImpresora(DatoString fabricanteImpresora) {
                this.fabricanteImpresora = fabricanteImpresora;
            }
        }
        public class Memoria{
            private DatoString marca;
            public DatoString getMarca() {
                return marca;
            }

            public void setMarca(DatoString marca) {
                this.marca = marca;
            }

            private DatoString tamano;

            public DatoString getTamano() {
                return tamano;
            }

            public void setTamano(DatoString tamano) {
                this.tamano = tamano;
            }
        }

    }

}
