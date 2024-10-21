package com.diego.gestorcasino.controllers;

import com.diego.gestorcasino.models.Consumo;
import com.diego.gestorcasino.services.ConsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/consumos")
public class ConsumoController {

    @Autowired
    private ConsumoService consumoService;

    // Obtener todos los consumos
    @GetMapping
    public List<Consumo> obtenerTodosLosConsumos() {
        return consumoService.obtenerTodosLosConsumos();
    }

    // Obtener los consumos por empleado
    @GetMapping("/empleado/{cedula}")
    public List<Consumo> obtenerConsumosPorEmpleado(@PathVariable Long cedula) {
        return consumoService.obtenerConsumosPorEmpleado(cedula);
    }

    // Obtener un consumo por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Consumo> obtenerConsumoPorId(@PathVariable Long id) {
        Consumo consumo = consumoService.obtenerConsumoPorId(id);
        return ResponseEntity.ok(consumo);
    }

    // anadir un nuevo consumo
    @PostMapping("/empleado/{cedula}")
    public ResponseEntity<Consumo> anadirConsumo(@PathVariable Long cedula, @RequestBody Consumo consumo) {
        Consumo nuevoConsumo = consumoService.anadirConsumo(cedula, consumo);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoConsumo);
    }

    // Actualizar un consumo existente
    @PutMapping("/{id}")
    public ResponseEntity<Consumo> actualizarConsumo(@PathVariable Long id, @RequestBody Consumo detallesConsumo) {
        Consumo consumoActualizado = consumoService.actualizarConsumo(id, detallesConsumo);
        return ResponseEntity.ok(consumoActualizado);
    }

    // Eliminar un consumo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarConsumo(@PathVariable Long id) {
        consumoService.eliminarConsumo(id);
        return ResponseEntity.noContent().build();
    }
}
