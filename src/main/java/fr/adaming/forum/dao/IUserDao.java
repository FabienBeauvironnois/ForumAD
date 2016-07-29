package fr.adaming.forum.dao;

import java.util.List;

import fr.adaming.forum.entity.Address;
import fr.adaming.forum.entity.Skill;
import fr.adaming.forum.entity.User;

public interface IUserDao {
	
	public User addUser(User user);
	public User updateUser(User user);
	public User deleteUser(Long idUser);
	
	public List<User> getAllUser();
	public User getUserById(Long idUser);
	public List<User> getUserByKeyWord(String keyWord);
	List<User> getUserByAddress(Address address);
	List<User> getUsersBySkill(String skillName, Integer level);

}
