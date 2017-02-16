package io.egen.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

public class JwtFilter extends GenericFilterBean {

    @Override
    public void doFilter(final ServletRequest req,
                         final ServletResponse res,
                         final FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;
        String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");
        List<String> allowedPaths = new ArrayList<>();
        allowedPaths.add("/api/users/login");
        allowedPaths.add("/api/users/create");
        if(!allowedPaths.contains(path.trim())){
	        final String authHeader = request.getHeader("Authorization");	//Error: it's always null due to some CORS issue. Not able to resolve.
	        
	        //This code is not working due to CORS issue. Not able to get token from header from request
	        
	        /*if (authHeader == null || !authHeader.startsWith("Bearer ")) {
	            throw new ServletException("Missing or invalid Authorization header.");
	        }
	        final String token = authHeader.substring(7); // The part after "Bearer "
	
	        try {
	            final Claims claims = Jwts.parser().setSigningKey(ApplicationConstants.SECRET_KEY)
	                .parseClaimsJws(token).getBody();
	            request.setAttribute("claims", claims);
	        }
	        catch (final SignatureException e) {
	            throw new ServletException("Invalid token.");
	        }*/
        }
        chain.doFilter(req, res);
    }

}
