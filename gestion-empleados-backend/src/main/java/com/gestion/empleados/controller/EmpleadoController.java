package com.gestion.empleados.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import com.gestion.empleados.exceptions.ResourceNotFoundException;
import com.gestion.empleados.dto.Empleado;
import com.gestion.empleados.dao.EmpleadoRepository;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class EmpleadoController {
	
	@Autowired
	private EmpleadoRepository empleadoRepositorio;
	
	@GetMapping("/empleados")
	public List<Empleado> listarTodosEmpleados(){
		return empleadoRepositorio.findAll();
	}
	
	@PostMapping("/empleados")
	public Empleado guardarEmpleado(@RequestBody Empleado empleado) {
		return empleadoRepositorio.save(empleado);
	}
	
	@GetMapping("/empleados/{id}")
	public ResponseEntity<Empleado> obtenerEmpleadoPorId(@PathVariable Long id) {
		Empleado empleado = empleadoRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe el empleado con el ID : " + id));
		return ResponseEntity.ok(empleado);
	}
	
	@PutMapping("/empleados/{id}")
	public ResponseEntity<Empleado> actualizarEmpleado(@RequestBody Empleado detallesEmpleado, @PathVariable Long id){
		Empleado empleado = empleadoRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe el empleado con el ID : " + id));
		
		empleado.setName(detallesEmpleado.getName());
		empleado.setLastname(detallesEmpleado.getLastname());
		empleado.setEmail(detallesEmpleado.getEmail());
		
		Empleado empleadoActualizado = empleadoRepositorio.save(empleado);
		return ResponseEntity.ok(empleadoActualizado);
	}
	
	@DeleteMapping("/empleados/{id}")
	public ResponseEntity<Map<String, Boolean>> eliminarEmpleado(@PathVariable Long id){
		Empleado empleado = empleadoRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("No existe el empleado con el ID : " + id));
        
		empleadoRepositorio.delete(empleado);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
    }

}
