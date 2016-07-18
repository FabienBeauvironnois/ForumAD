package fr.adaming.forum.service;

import java.util.List;

import fr.adaming.forum.entity.Role;

public interface IRoleService {
	public Role addRole(Role role);
	public Role updateRole(Role role);
	public Role deleteRole(Long idRole);
	
	public List<Role> getAllRole();
	public Role getRoleById(Long idRole);
}
