package com.example.physiocore.demo.security.jwt;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.physiocore.demo.model.AppUser;
import com.example.physiocore.demo.services.CustomUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Log
@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {
	
	private final JwtProvider tokenProvider;
	private final CustomUserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String token = getJwtFromRequest(request);
			
			if(StringUtils.hasText(token) && tokenProvider.validateToken(token)) {
				Long userId = tokenProvider.getUserIdFromJWT(token);
				AppUser user = (AppUser) userDetailsService.loadUserById(userId);

				UsernamePasswordAuthenticationToken authentication =
						new UsernamePasswordAuthenticationToken(user, user.getRoles(), user.getAuthorities());
				
				// Establece detalles adicional de autenticación si los hubiera (no en nuestr caso)
				authentication.setDetails(new WebAuthenticationDetails(request));	
				
				// Guardamos la autenticación en el contexto de seguridad
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
			
		} catch(Exception ex) {
			log.info("No se ha podido establecer la autenticación en el contexto de seguridad");
		}
		
		// Necesario para que la petición continua la cadena de filtros
		filterChain.doFilter(request, response);
	}
	
	private String getJwtFromRequest(HttpServletRequest request) {
		if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("jwt".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }

        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
		return null;
	}

}
