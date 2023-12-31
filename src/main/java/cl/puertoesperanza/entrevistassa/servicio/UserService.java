package cl.puertoesperanza.entrevistassa.servicio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cl.puertoesperanza.entrevistassa.modelo.UserRole;
import cl.puertoesperanza.entrevistassa.repositorio.UserRepository;

@Service("userService")
public class UserService implements UserDetailsService {

	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("Busco al usuario: " + username);
		cl.puertoesperanza.entrevistassa.modelo.User user = userRepository.findByUsername(username);
		
	    if (user == null) {
	        throw new UsernameNotFoundException("No existe un usuario con login:"+ username + ".");
	    }
	    
		System.out.println("Encuentro al usuario: " + user.isEnabled());
		List<GrantedAuthority> authorities = buildAuthorities(user.getUserRole());
		return buildUser(user, authorities);
	}

	private User buildUser(cl.puertoesperanza.entrevistassa.modelo.User user, 
			List<GrantedAuthority> authorities) {

		return new User(user.getUsername(), user.getPassword(), user.isEnabled(), 
				true, true, true, authorities);
	}
	
	private List<GrantedAuthority> buildAuthorities (Set<UserRole> userRoles){
		Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();
		
		for (UserRole userRole : userRoles) {
			auths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}
		
		return new ArrayList<GrantedAuthority>(auths);
	}
	
}
