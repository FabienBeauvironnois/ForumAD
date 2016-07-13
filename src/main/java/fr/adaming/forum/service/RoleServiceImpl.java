package fr.adaming.forum.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.forum.dao.IRoleDao;
import fr.adaming.forum.entity.Role;

@Transactional
public class RoleServiceImpl implements IRoleService{

	Logger log = Logger.getLogger("RoleServiceImpl");
	
	@Autowired
	private IRoleDao roleDao;
	
	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
		log.info("<---dao Role injected------>");
	}
	
	@Override
	public Role addRole(Role role) {
		return roleDao.addRole(role);
	}

	@Override
	public Role updateRole(Role role) {
		return roleDao.updateRole(role);
	}

	@Override
	public Role deleteRole(Long idRole) {
		return roleDao.deleteRole(idRole);
	}

	@Override
	public List<Role> getAllRole() {
		return roleDao.getAllRole();
	}

}
