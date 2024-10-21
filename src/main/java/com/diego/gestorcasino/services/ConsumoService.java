package com.diego.gestorcasino.services;
import com.diego.gestorcasino.models.Plato;
import com.diego.gestorcasino.models.Consumo;
import com.diego.gestorcasino.models.Empleado;
import com.diego.gestorcasino.repositories.ConsumoRepository;
import com.diego.gestorcasino.repositories.EmpleadoRepository;
import com.diego.gestorcasino.repositories.PlatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumoService {

    @Autowired
    private ConsumoRepository consumoRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private PlatoRepository platoRepository;

    // Obtener todos los consumos
    public List<Consumo> obtenerTodosLosConsumos() {
        return consumoRepository.findAll();
    }

    // Obtener los consumos por empleado
    public List<Consumo> obtenerConsumosPorEmpleado(Long cedula) {
        return consumoRepository.findByEmpleadoCedula(cedula);
    }

    // Obtener un consumo por su ID
    public Consumo obtenerConsumoPorId(Long id) {
        return consumoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consumo no encontrado con id: " + id));
    }

    // anadir un nuevo consumo
    public Consumo anadirConsumo(Long empleadoCedula, Consumo consumo) {
        Empleado empleado = empleadoRepository.findById(empleadoCedula)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado con cédula: " + empleadoCedula));
        consumo.setEmpleado(empleado);

        double total = consumo.getPlatosConsumidos()
                .stream()
                .mapToDouble(Plato::getPrecio)
                .sum();
        consumo.setTotal(total);

        return consumoRepository.save(consumo);
    }

    // Actualizar un consumo
    public Consumo actualizarConsumo(Long id, Consumo detallesConsumo) {
        Consumo consumoExistente = consumoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consumo no encontrado con id: " + id));

        consumoExistente.setFecha(detallesConsumo.getFecha());
        consumoExistente.setPlatosConsumidos(detallesConsumo.getPlatosConsumidos());

        double total = consumoExistente.getPlatosConsumidos()
                .stream()
                .mapToDouble(Plato::getPrecio)
                .sum();
        consumoExistente.setTotal(total);

        return consumoRepository.save(consumoExistente);
    }

    // Eliminar un consumo
    public void eliminarConsumo(Long id) {
        Consumo consumo = consumoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consumo no encontrado con id: " + id));
        consumoRepository.delete(consumo);
    }
}

