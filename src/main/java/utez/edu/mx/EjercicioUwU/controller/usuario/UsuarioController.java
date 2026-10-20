package utez.edu.mx.EjercicioUwU.controller.usuario;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.EjercicioUwU.controller.dto.RequestCrearUsuarioDTO;
import utez.edu.mx.EjercicioUwU.model.usuario.Usuario;
import utez.edu.mx.EjercicioUwU.service.usuario.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @PostMapping
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody RequestCrearUsuarioDTO dto) {
        try {
            Usuario usuarioCreado = usuarioService.crearUsuario(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCreado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<?> desactivarUsuario(@PathVariable Long id) {
        try {
            Usuario usuarioDesactivado = usuarioService.desactivarUsuario(id);
            return ResponseEntity.ok(usuarioDesactivado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}