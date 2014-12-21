package type.proy.com.inventarioandroid.dom.soporte;

/**
 * Created by munoz on 20/12/14.
 */
public class Soporte {
    Members members;

    public Members getMembers() {
        return members;
    }

    public void setMembers(Members members) {
        this.members = members;
    }

    // nested class
    public class Members{
        private DatoCurso insumos;//Insumos
        private DatoCurso estado;
        private DatoCurso tecnico;//Tecnico
        private DatoString fecha;


        private DatoString observaciones;

        public DatoString getObservaciones() {
            return observaciones;
        }

        public void setObservaciones(DatoString observaciones) {
            this.observaciones = observaciones;
        }

        public DatoCurso getInsumos() {
            return insumos;
        }

        public void setInsumos(DatoCurso insumos) {
            this.insumos = insumos;
        }

        public DatoCurso getEstado() {
            return estado;
        }

        public void setEstado(DatoCurso estado) {
            this.estado = estado;
        }



        public DatoString getFecha() {
            return fecha;
        }

        public void setFecha(DatoString fecha) {
            this.fecha = fecha;
        }


        //private DatoString habilitado;
        //private DatoString time_system;
        //private DatoCurso creadoPor;
        //private DatoCurso reparando;
        //private DatoCurso recepcionando;
        //private DatoCurso cancelando;
        //private DatoCurso entregando;
        //private DatoCurso esperando;


        // nested classes

        public class DatoString {
            String value;

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }






        public class DatoCurso {

            DatoTitle value;

            public DatoTitle getValue() {
                return value;
            }

            public void setValue(DatoTitle value) {
                this.value = value;
            }

            public class DatoTitle {
                String title;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }
        }
        /**
         * Recuperando datos de Computadora
         *
         */
        private DatoComputadora computadora;//Computadora

        public DatoComputadora getComputadora() {
            return computadora;
        }

        public void setComputadora(DatoComputadora  computadora) {
            this.computadora = computadora;
        }
        public class DatoComputadora{
            DatoTitle value;

            public DatoTitle getValue() {
                return value;
            }

            public void setValue(DatoTitle value) {
                this.value = value;
            }

            public class DatoTitle {
                String title;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }
        }



    }
}

