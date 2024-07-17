package com.appsWave.RahahlehApps.dto;

import com.appsWave.RahahlehApps.entities.Role;
import com.appsWave.RahahlehApps.util.ValidLocalDate;
import com.appsWave.RahahlehApps.util.ValidRole;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignUpRequestDTO {
    @NotBlank
    private String fullName;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @ValidLocalDate
    private String dateOfBirth;

    @ValidRole
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
