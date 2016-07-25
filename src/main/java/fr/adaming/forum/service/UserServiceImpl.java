package fr.adaming.forum.service;

import java.util.List;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.forum.dao.IUserDao;
import fr.adaming.forum.entity.Address;
import fr.adaming.forum.entity.User;

@Transactional
public class UserServiceImpl implements IUserService{

	Logger log = Logger.getLogger("UserServiceImpl");
	
	@Autowired
	private IUserDao userDao;
	
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
		log.info("<---dao User injected------>");
	}
	
	@Override
	public User addUser(User user) {
		return userDao.addUser(user);
	}

	@Override
	public User updateUser(User user) {
		return userDao.updateUser(user);
	}

	@Override
	public User deleteUser(Long idUser) {
		return userDao.deleteUser(idUser);
	}

	@Override
	public List<User> getAllUser() {
		return userDao.getAllUser();
	}

	@Override
	public User getUserById(Long idUser) {
		return userDao.getUserById(idUser);
	}

	@Override
	public List<User> getUserByKeyWord(String keyWord) {
		return userDao.getUserByKeyWord(keyWord);
	}
	
	@Override
	public List<User> getUserByAddress(Address address){
		return userDao.getUserByAddress(address);
	}
	
}
