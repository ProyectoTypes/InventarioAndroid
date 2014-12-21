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

        private Computadora computadora;//Computadora

        public Computadora getComputadora() {
            return computadora;
        }

        public void setComputadora(Computadora  computadora) {
            this.computadora = computadora;
        }




    }
}

