package pe.edu.cibertec.Patitas_BackEnd.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.Patitas_BackEnd.dto.LoginRequestDTO;
import pe.edu.cibertec.Patitas_BackEnd.dto.LoginResponseDTO;
import pe.edu.cibertec.Patitas_BackEnd.service.AuthenticationService;
import pe.edu.cibertec.Patitas_BackEnd.service.implementation.AuthenticationServiceImpl;

import java.io.IOException;

@RestController
@RequestMapping("/autentication")
public class AutenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/inicio")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO) {
        try {
            String[] datosUsuario = authenticationService.validarUsuario(loginRequestDTO);
            if (datosUsuario == null) {
                return new LoginResponseDTO("01", "Error: Usuario no encontrado", "", "");
            }

            // Asegúrate de usar los índices correctos
            return new LoginResponseDTO("00", "", datosUsuario[0], datosUsuario[1]); // nombre y correo

        } catch (IOException e) {
            return new LoginResponseDTO("99", "Error: Ocurrió un problema", "", "");
        }
    }
}
