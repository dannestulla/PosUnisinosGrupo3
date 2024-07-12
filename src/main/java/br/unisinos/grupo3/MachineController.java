package br.unisinos.grupo3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/machines")
public class MachineController {
    private final MachineRepository machineRepository;

    public MachineController(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }

    @GetMapping
    public List<Machine> getAll() {
        return machineRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Machine> getById(@PathVariable String id) {
        Optional<Machine> machine = machineRepository.findById(id);
        if (machine.isPresent()) {
            return ResponseEntity.ok(machine.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Machine save(@RequestBody Machine machine) {
        return machineRepository.save(machine);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        Optional<Machine> machine = machineRepository.findById(id);
        if (machine.isPresent()) {
            machineRepository.delete(machine.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}