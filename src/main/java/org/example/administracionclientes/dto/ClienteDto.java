package org.example.administracionclientes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto {


    private String nombre;
    private String apellido;
    private String identificacion;
    private String email;
    private String telefono;

}
