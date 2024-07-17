package com.appsWave.RahahlehApps.dto;

import com.appsWave.RahahlehApps.entities.Role;
import lombok.Data;

@Data
public class SignUpRequestDTO {

    private String fullName;

    private String email;

    private String password;

    private String dateOfBirth;
    private String role;

    public Role getRoleAsEnumValue(){
        if(role != null && !role.isEmpty()){
           for(Role roleEnum:Role.values()){
               if(roleEnum.name().equalsIgnoreCase(getRole())){
                   return roleEnum;
               }
           }
        }
        return null;
    }
}
