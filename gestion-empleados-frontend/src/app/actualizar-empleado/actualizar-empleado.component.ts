import { Component, OnInit } from '@angular/core';
import {Empleado} from "../empleado";
import {EmpleadoService} from "../empleado.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Observable} from "rxjs";

@Component({
  selector: 'app-actualizar-empleado',
  templateUrl: './actualizar-empleado.component.html',
  styleUrls: ['./actualizar-empleado.component.css']
})
export class ActualizarEmpleadoComponent implements OnInit {

  id: number;
  empleado: Empleado = new Empleado();

  constructor(private empleadoService:EmpleadoService, private router:Router, private route:ActivatedRoute) { }

  ngOnInit(): void {
    //Necesitamos obtener el id del empleado list-empleados.html indicado en la ruta para poder actualizar el empleado
    this.id = this.route.snapshot.params['id'];
    this.empleadoService.obtenerEmpleadoPorId(this.id).subscribe(dato =>{
      this.empleado = dato;
    },error => console.log(error));
  }

  onSubmit(){
    this.empleadoService.actualizarEmpleado(this.id,this.empleado).subscribe(dato => {
      this.irAListaEmpleados();
    },error => console.log(error));
  }

  irAListaEmpleados(){
    this.router.navigate(['/empleados']);
    console.log(`Empleado actualizado. El empleado ${this.empleado.name} ha sido actualizado con exito`);
  }

}
