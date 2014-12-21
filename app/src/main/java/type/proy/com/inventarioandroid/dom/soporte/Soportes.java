package type.proy.com.inventarioandroid.dom.soporte;

import java.util.List;

import type.proy.com.inventarioandroid.servicio.RestLink;

/**
 * Created by ProyectType on 21/12/14.
 */
public class Soportes {
    List<RestLink> links;
    Result result;

    public List<RestLink> getLinks() {
        return links;
    }

    public void setLinks(List<RestLink> links) {
        this.links = links;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public class Result{

        List<RestLink> value;

        public List<RestLink> getValue() {
            return value;
        }

        public void setValue(List<RestLink> value) {
            this.value = value;
        }
    }
}
