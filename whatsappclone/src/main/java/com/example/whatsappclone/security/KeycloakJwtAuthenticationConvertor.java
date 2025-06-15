package com.example.whatsappclone.security;

import org.springframework.lang.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import java.util.stream.Stream;

public class KeycloakJwtAuthenticationConvertor implements Converter<Jwt, AbstractAuthenticationToken>{
    @Override
    public AbstractAuthenticationToken convert(@NonNull Jwt source){
        return new JwtAuthentication(source,
            Stream.concat(new JwtGrantedAuthoritiesConverter().convert(source).stream(),
                extractResourceRoles(source).stream().collect(Collectors.toSet())
                
            )
        )
    }
}
