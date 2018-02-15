package ua.com.topshop.service.implementation;


import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;






import ua.com.topshop.dao.GraphicDao;
import ua.com.topshop.dao.UserDao;
import ua.com.topshop.entity.Role;
import ua.com.topshop.entity.User;
import ua.com.topshop.service.UserService;

@Service("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private GraphicDao graphicDao;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public void save(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
 		user.setRole(Role.ROLE_USER);
		userDao.save(user);
	}

	public void update(User user) {
		userDao.save(user);
	}

	public List<User> findAll() {
		return userDao.findAll();
	}

	public User findOne(int id) {
		return userDao.findOne(id);
	}

	public void delete(String email) {
		userDao.delete(findByEmail(email));
		
	}

	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}
	
	public User findByName(String name){
		return userDao.findByName(name);
	}

	public void delete(int id) {
		userDao.delete(id);
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return userDao.findByEmail(username);
	}
	
	@PostConstruct
	public void addAdmin(){
		User user = userDao.findByEmail("admin");
		if(user==null){
			user = new User();
			user.setEmail("admin");
			user.setPassword(encoder.encode("admin"));
			user.setRole(Role.ROLE_ADMIN);
			userDao.save(user);
		}
	}
}
