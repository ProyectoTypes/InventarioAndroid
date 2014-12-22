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
        private Tecnico tecnico;//Tecnico

        public Tecnico getTecnico() {
            return tecnico;
        }

        public void setTecnico(Tecnico tecnico) {
            this.tecnico = tecnico;
        }

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

        private Usuario usuario;

        public Usuario getUsuario() {
            return usuario;
        }

        public void setUsuario(Usuario usuario) {
            this.usuario = usuario;
        }


    }
}

