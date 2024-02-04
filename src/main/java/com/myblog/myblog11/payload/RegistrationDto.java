package com.myblog.myblog11.payload;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto {

    private long id;

    private String name;
    private String city;
    private String email;
    private long mobile;
}
