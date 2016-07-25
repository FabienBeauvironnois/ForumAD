package fr.adaming.forum.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import fr.adaming.forum.entity.Address;
import fr.adaming.forum.entity.Company;
import fr.adaming.forum.entity.User;

@Component
public class CompanyDaoImpl implements ICompanyDao {

	Logger log = Logger.getLogger("CompanyDaoImpl");

	@PersistenceContext
	private EntityManager em;

	@Override
	public Company addCompany(Company company) {
		em.persist(company);
		log.info("La soci�t� " + company.getCompanyName() + " � �t� ajout�");
		return company;
	}

	@Override
	public Company updateCompany(Company company) {
		em.merge(company);
		log.info("La soci�t� " + company.getCompanyName() + " � �t� mis � jour");
		return company;
	}

	@Override
	public Company deleteCompany(Long idCompany) {
		Company company = getCompanyById(idCompany);
		if (company != null) {
			em.remove(company);
			log.info("La soci�t� " + company.getCompanyName() + " � �t� supprim�");
		}
		return company;
	}

	@Override
	public Company getCompanyById(Long idCompany) {
		Company company = em.find(Company.class, idCompany);
		if (company != null) {
			log.info("La soci�t� " + company.getCompanyName() + " � �t� trouv�");
		} else {
			log.warning("La soci�t� " + idCompany + " n'a pas �t� trouv�");
		}

		return company;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Company> getAllCompany() {
		Query query = em.createQuery("From Company");
		
		List<Company> result = query.getResultList();
		log.info("il y a " + result.size() + " soci�t�");
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Company> getCompanyByMc(String keyword) {
		Query query = em.createQuery("FROM Company c WHERE c.companyName LIKE :x OR c.streetNumber LIKE :x OR c.zipCode LIKE :x OR c.city LIKE :x OR c.country LIKE :x");
		query.setParameter("x", "%" + keyword + "%");
		
		List<Company> result = query.getResultList();
		log.info(result.size() + " soci�t� ont �t� trouv�");
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Company> getCompanyByAddress(Address address) {
		Query query = em.createQuery(
				"FROM Company c WHERE c.streetNumber LIKE :streetNumber AND c.streetName LIKE :streetName AND c.zipCode LIKE :zipCode AND c.city LIKE :city AND c.country LIKE :country");

		query.setParameter("streetNumber", "%" + address.getStreetNumber() + "%");
		query.setParameter("streetName", "%" + address.getStreetName() + "%");
		query.setParameter("zipCode", "%" + address.getZipCode() + "%");
		query.setParameter("city", "%" + address.getCity() + "%");
		query.setParameter("coutry", "%" + address.getCountry() + "%");

		List<Company> result = query.getResultList();
		log.info(result.size() + " companies ont �t� trouv�es !");
		return result;
	}

}
