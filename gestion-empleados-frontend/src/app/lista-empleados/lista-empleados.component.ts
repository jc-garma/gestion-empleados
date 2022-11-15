import { Component, OnInit } from '@angular/core';
import { Empleado } from "../empleado";
import {EmpleadoService} from "../empleado.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-lista-empleados',
  templateUrl: './lista-empleados.component.html',
  styleUrls: ['./lista-empleados.component.css']
})
export class ListaEmpleadosComponent implements OnInit {

  empleados: Empleado[];

  constructor(private empleadoServicio:EmpleadoService,private router:Router) { }

  ngOnInit(): void {
    this.obtenerEmpleados();
  }

  private obtenerEmpleados(){
    this.empleadoServicio.obtenerListaEmpleados().subscribe(dato => {
        this.empleados = dato;
    });
  }

  actualizarEmpleado(id:number){
    this.router.navigate(['actualizar-empleado',id]);
  }

  eliminarEmpleado(id:number){
    //No es necesario un componente
    this.empleadoServicio.eliminarEmpleadoPorId(id).subscribe(dato => {
      console.log(dato);
      this.obtenerEmpleados();
    });
  }

  verDetallesEmpleado(id:number){
    this.router.navigate(['empleado-detalles',id]);
  }

}
