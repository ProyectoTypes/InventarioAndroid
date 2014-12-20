package type.proy.com.inventarioandroid.dom.soporte;

/**
 * Created by cipoleto on 20/12/14.
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
        private DatoString observaciones;
        private DatoString habilitado;
        private DatoString fecha;
        private DatoString time_system;
        private DatoCurso creadoPor;
        private DatoCurso tecnico;//Tecnico
        private DatoCurso insumos;//Insumos
        private DatoCurso computadora;//Computadora
        private DatoCurso estado;
        private DatoCurso reparando;
        private DatoCurso recepcionando;
        private DatoCurso cancelando;
        private DatoCurso entregando;
        private DatoCurso esperando;

        public DatoString getApellido() {
            return apellido;
        }

        public void setApellido(DatoString apellido) {
            this.apellido = apellido;
        }

        public DatoString getNombre() {
            return nombre;
        }

        public void setNombre(DatoString nombre) {
            this.nombre = nombre;
        }

        public DatoString getDni() {
            return dni;
        }

        public void setDni(DatoString dni) {
            this.dni = dni;
        }

        public DatoString getFechaNacimiento() {
            return fechaNacimiento;
        }

        public void setFechaNacimiento(DatoString fechaNacimiento) {
            this.fechaNacimiento = fechaNacimiento;
        }

        public DatoCurso getCurso() {
            return curso;
        }

        public void setCurso(DatoCurso curso) {
            this.curso = curso;
        }

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



    }
}

