package type.proy.com.inventarioandroid.dom.soporte;

/**
 * Created by PROYECT TYPES on 22/12/14.
 */
public class Sector {
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

        private DatoString nombre;
        public DatoString getNombre() {
            return nombre;
        }

        public void setNombre(DatoString nombre) {
            this.nombre = nombre;
        }
    }
}
