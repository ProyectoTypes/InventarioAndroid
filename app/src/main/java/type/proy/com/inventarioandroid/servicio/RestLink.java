package type.proy.com.inventarioandroid.servicio;

import java.util.List;

/**
 * Created by munoz on 20/12/14.
 */
public class RestLink {

    List<RestLink> links;
    Object extensions;

    public List<RestLink> getLinks() {
        return links;
    }

    public void setLinks(List<RestLink> links) {
        this.links = links;
    }

    public Object getExtensions() {
        return extensions;
    }

    public void setExtensions(Object extensions) {
        this.extensions = extensions;
    }
}
