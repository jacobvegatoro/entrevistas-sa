package cl.puertoesperanza.entrevistassa.busqueda;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import cl.puertoesperanza.entrevistassa.modelo.User;

public class UsuarioHashmap {

	private HashMap<String, User> usuarios = new HashMap<String, User>();
	
	public UsuarioHashmap() {
		
	}
	
	public void add(User user) {
		usuarios.put(user.getUsername(), user);
	}

	public String toString() {
		String ret = "";
		for (User user:usuarios.values())
			ret += user.toString()+"\n";
		return ret;
	}
	
	public User busca(String username) {
		return usuarios.get(username);
	}
	
	public Collection<User> getList(){
		return usuarios.values();
	}
	
	public void llenar(List<User> listausuarios) {
		for (User us:listausuarios) {
			usuarios.put(us.getUsername(), us);
		}
	}
	
	public User obtener(String username) {
		User user = new User();
		
		for (User us:getList()) {
			if (us.getUsername().equals(username)) {
				user = us;
			}
		}
		
		return user;
	}
	
}
