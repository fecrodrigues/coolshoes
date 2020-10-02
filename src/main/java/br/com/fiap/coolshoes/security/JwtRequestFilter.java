package br.com.fiap.coolshoes.security;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtUserDetailService jwtUserDetailService;
    private final JwtTokenUtil jwtTokenUtil;

    public JwtRequestFilter(JwtUserDetailService jwtUserDetailService, JwtTokenUtil jwtTokenUtil) {
        this.jwtUserDetailService = jwtUserDetailService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String token = request.getHeader("Authorization");
        String username;

        if(token != null && token.startsWith("Bearer ")) {

            try {
                username = jwtTokenUtil.getUsernameFromToken(token);

                if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = jwtUserDetailService.loadUserByUsername(username);

                    UsernamePasswordAuthenticationToken userToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    userToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(userToken);
                }

            } catch (IllegalArgumentException illegalArgumentException) {
                logger.info(illegalArgumentException.getMessage());
            } catch (ExpiredJwtException expiredJwtException) {
                logger.info(expiredJwtException.getMessage());
            }

        } else {
            logger.warn("JWT Token not passed or invalid");
        }

        filterChain.doFilter(request, response);
    }

}
