package type.proy.com.inventarioandroid.servicio;

import java.util.List;

/**
 * Created by munoz on 20/12/14.
 */
public class RestLinks {

    private List<RestLink> links;
    public List<RestLink> getLinks() {
        return links;
    }

    public void setLinks(List<RestLink> links) {
        this.links = links;
    }
    private Object extensions;

    public Object getExtensions() {
        return extensions;
    }

    public void setExtensions(Object extensions) {
        this.extensions = extensions;
    }
}
