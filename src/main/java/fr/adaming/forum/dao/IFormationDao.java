package fr.adaming.forum.dao;

import java.util.List;

import fr.adaming.forum.entity.Formation;

public interface IFormationDao {

	public Formation addFormation(Formation formation);
	public Formation getFormationById(Long idFormation);
	public Formation updateFormation(Formation formation);
	public Formation deleteFormation(Long idFormation);
	public List<Formation>  getAllFormations();
	public List<Formation>  getFormationByKeyWord(String keyWord);
	
}
