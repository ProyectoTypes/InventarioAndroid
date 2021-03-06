/*
 * This is a software made for inventory control
 *
 * Copyright (C) 2014, ProyectoTypes
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 *
 *
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 */
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
