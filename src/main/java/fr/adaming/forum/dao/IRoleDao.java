package fr.adaming.forum.dao;

import java.util.List;

import fr.adaming.forum.entity.Role;

public interface IRoleDao {
	
	public Role addRole(Role role);
	public Role updateRole(Role role);
	public Role deleteRole(Long idRole);
	
	public List<Role> getAllRole();

}
