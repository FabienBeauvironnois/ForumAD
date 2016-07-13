package fr.adaming.forum.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.forum.dao.ICompanyDao;
import fr.adaming.forum.entity.Company;

@Transactional
public class CompanyServiceImpl implements ICompanyService{

	@Autowired
	private ICompanyDao companyDao;
	
	@Override
	public Company addCompany(Company company) {
		return companyDao.addCompany(company);
	}

	@Override
	public Company updateCompany(Company company) {
		return companyDao.updateCompany(company);
	}

	@Override
	public Company deleteCompany(Long idCompany) {
		return companyDao.deleteCompany(idCompany);
	}

	@Override
	public Company getCompanyById(Long idCompany) {
		return companyDao.getCompanyById(idCompany);
	}

	@Override
	public List<Company> getAllCompany() {
		return companyDao.getAllCompany();
	}

	@Override
	public List<Company> getCompanyByMc(String keyword) {
		return companyDao.getCompanyByMc(keyword);
	}

}
