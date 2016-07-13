package fr.adaming.forum.service;

import java.util.List;

import fr.adaming.forum.entity.Formation;

public interface IFormationService{
	
	public Formation addFormation(Formation formation);
	public Formation getFormationById(int idFormation);
	public Formation updateFormation(Formation formation);
	public Formation deleteFormation(int idFormation);
	public List<Formation>  getAllFormations();
	public List<Formation>  getFormationByKeyWord(String keyWord);

}
