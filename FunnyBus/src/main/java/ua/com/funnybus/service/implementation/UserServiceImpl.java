package ua.com.funnybus.service.implementation;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ua.com.funnybus.dao.UserDao;
import ua.com.funnybus.entity.Role;
import ua.com.funnybus.entity.User;
import ua.com.funnybus.service.UserService;
@Service("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	public void save(User user) {
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

	public void delete(int id) {
		userDao.delete(id);
	}

	@Override
	public User findByPhone(String phone) {
		return userDao.findByPhone(phone);
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		return userDao.findByPhone(username);
	}
	
	@PostConstruct
	public void addAdmin(){
		User user = userDao.findByPhone("FunnyBus");
		if(user==null){
			user = new User();
			user.setPhone("FunnyBus");
			user.setPassword(encoder.encode("le;tdtctkbqfdnj,ec"));
			user.setRole(Role.ROLE_ADMIN);
			userDao.save(user);
		}
	}

	@Override
	public User findUniqueOrCreate(String phone, String firstName, String secondName) {
		if(userDao.findUnique(phone, firstName, secondName)==null){
			User user = new User();
			user.setPhone(phone);
			user.setFirstName(firstName);
			user.setSecondName(secondName);
			user.setRole(Role.ROLE_USER);
			userDao.saveAndFlush(user);
			return user;
		}else return userDao.findUnique(phone, firstName, secondName);
	}

	@Override
	public User findForReviewOrCreate(String firstName, String secondName) {
		if(userDao.findForReview(firstName, secondName).size()==0){
			User user = new User();
			user.setFirstName(firstName);
			user.setSecondName(secondName);
			user.setRole(Role.ROLE_USER);
			userDao.saveAndFlush(user);
			return user;
		}else{
			List<User> users= userDao.findForReview(firstName, secondName);
			User user=users.get(0);
			return user;
		}
	}
}
