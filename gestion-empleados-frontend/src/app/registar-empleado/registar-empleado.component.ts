import { Component, OnInit } from '@angular/core';
import {Empleado} from "../empleado";
import {EmpleadoService} from "../empleado.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-registar-empleado',
  templateUrl: './registar-empleado.component.html',
  styleUrls: ['./registar-empleado.component.css']
})
export class RegistarEmpleadoComponent implements OnInit {

  empleado: Empleado = new Empleado();

  constructor(private empleadoServicio:EmpleadoService, private router:Router) { }

  ngOnInit(): void {
  }

  onSubmit(){
    this.guardarEmpleado();
  }

  guardarEmpleado(){
      this.empleadoServicio.registrarEmpleado(this.empleado).subscribe(dato => {
        console.log('Empleado guardado',dato);
        //Una vez registrado redirección a la lista de empleados
        this.irAListaEmpleados();
      }, error => console.log(error));
  }

  irAListaEmpleados(){
    this.router.navigate(['/empleados']);
  }



}
