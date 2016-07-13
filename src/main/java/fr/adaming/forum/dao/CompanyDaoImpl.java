package fr.adaming.forum.dao;

import java.util.List;
import java.util.logging.Logger;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import fr.adaming.forum.entity.Company;

@Component
public class CompanyDaoImpl implements ICompanyDao {

	Logger log = Logger.getLogger("CompanyDaoImpl");
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Company addCompany(Company company) {
		em.persist(company);
		log.info("La société"+company.getCompanyName()+ " à été ajouté");
		return company;
	}

	@Override
	public Company updateCompany(Company company) {
		em.merge(company);
		log.info("La société" + company.getCompanyName()+ " à été mis à jour");
		return company;
	}

	@Override
	public Company deleteCompany(Long idCompany) {
		Company company = em.find(Company.class, idCompany);
		em.remove(company);
		log.info("La société" + company.getCompanyName()+ " à été supprimé");
		return company;
	}

	@Override
	public Company getCompanyById(Long idCompany) {
		Company company = em.find(Company.class, idCompany);
		log.info("L'utilisateur"+company.getCompanyName()+ " à été trouvé");
		return company;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Company> getAllCompany() {
		Query query = em.createQuery("From Company");
		log.info("il y a "+query.getResultList().size()+" société");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Company> getCompanyByMc(String keyword) {
		Query query = em.createQuery("From Company c like :x");
		query.setParameter("x", "%" +keyword+"%");
		log.info(query.getResultList().size()+"société ont été trouvé");
		return query.getResultList();
	}

}
