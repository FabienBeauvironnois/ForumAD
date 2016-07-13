package fr.adaming.forum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.forum.dao.IFormation;
import fr.adaming.forum.entity.Formation;

@Transactional
public class FormationServiceImpl implements IFormationService{

	@Autowired
	private IFormation formationDao;
	
	@Override
	public Formation addFormation(Formation formation) {
		return formationDao.addFormation(formation);
	}

	@Override
	public Formation getFormationById(int idFormation) {
		return formationDao.getFormationById(idFormation);
	}

	@Override
	public Formation updateFormation(Formation formation) {
		return formationDao.updateFormation(formation);
	}

	@Override
	public Formation deleteFormation(int idFormation) {
		return formationDao.deleteFormation(idFormation);
	}

	@Override
	public List<Formation> getAllFormations() {
		return formationDao.getAllFormations();
	}

	@Override
	public List<Formation> getFormationByKeyWord(String keyWord) {
		return formationDao.getFormationByKeyWord(keyWord);
	}

}
