package com.diego.gestorcasino.services;

import com.diego.gestorcasino.models.Empleado;
import com.diego.gestorcasino.repositories.EmpleadoRepository;
import com.diego.gestorcasino.repositories.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private EmpresaRepository empresaRepository; // Agrega el repositorio de Empresa

    // Obtener todos los empleados
    public List<Empleado> obtenerTodosLosEmpleados() {
        return empleadoRepository.findAll();
    }

    // Obtener un empleado por cédula
    public Empleado obtenerEmpleadoPorCedula(String cedula) {
        return empleadoRepository.findByCedula(cedula)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado con cédula: " + cedula));
    }

    // anadir un nuevo empleado
    public Empleado anadirEmpleado(Empleado empleado) {
        // Validar si la empresa existe por NIT
        empresaRepository.findByNit(empleado.getEmpresaNIT())
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada con NIT: " + empleado.getEmpresaNIT()));

        if (empleadoRepository.findByCedula(empleado.getCedula()).isPresent()) {
            throw new RuntimeException("Ya existe un empleado con la cédula: " + empleado.getCedula());
        }

        return empleadoRepository.save(empleado);
    }

    // Actualizar un empleado
    public Empleado actualizarEmpleado(String cedula, Empleado detallesEmpleado) {
        Empleado empleadoExistente = empleadoRepository.findByCedula(cedula)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado con cédula: " + cedula));


        // Validar si la empresa existe
        empresaRepository.findByNit(detallesEmpleado.getEmpresaNIT())
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada con NIT: " + detallesEmpleado.getEmpresaNIT()));

        empleadoExistente.setNombre(detallesEmpleado.getNombre());
        empleadoExistente.setEmpresaNIT(detallesEmpleado.getEmpresaNIT());
        empleadoExistente.setTelefono(detallesEmpleado.getTelefono());

        return empleadoRepository.save(empleadoExistente);
    }

    // Borrar un empleado
    public void eliminarEmpleado(String cedula) {
        Empleado empleado = empleadoRepository.findByCedula(cedula)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado con cédula: " + cedula));
        empleadoRepository.delete(empleado);
    }
}


