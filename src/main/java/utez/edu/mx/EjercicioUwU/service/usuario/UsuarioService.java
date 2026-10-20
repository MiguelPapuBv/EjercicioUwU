package utez.edu.mx.EjercicioUwU.service.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.EjercicioUwU.controller.dto.RequestCrearUsuarioDTO;
import utez.edu.mx.EjercicioUwU.model.usuario.Usuario;
import utez.edu.mx.EjercicioUwU.repository.usuario.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Transactional
    public Usuario crearUsuario(RequestCrearUsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNombres(dto.getNombres());
        usuario.setPrimerApellido(dto.getPrimerApellido());
        usuario.setSegundoApellido(dto.getSegundoApellido());
        usuario.setActivo(dto.getActivo() != null ? dto.getActivo() : true);

        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario desactivarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        usuario.setActivo(false);
        return usuarioRepository.save(usuario);
    }
}