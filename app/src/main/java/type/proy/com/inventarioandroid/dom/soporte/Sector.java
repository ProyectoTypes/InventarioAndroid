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
