package fr.adaming.forum.service;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.forum.dao.IAddressDao;
import fr.adaming.forum.entity.Address;

@Transactional
public class AddressServiceImpl implements IAddressService{

	Logger log = Logger.getLogger("AddressServiceImpl");
	
	@Autowired
	private IAddressDao addressDao;
	
	public void setAddressDao(IAddressDao addressDao) {
		this.addressDao = addressDao;
		log.info("<---dao Address injected------>");
	}
	
	@Override
	public Address addAdresse(Address address) {
		return addressDao.addAdresse(address);
	}

	@Override
	public Address getAdresseById(Long idAddress) {
		return addressDao.getAdresseById(idAddress);
	}

	@Override
	public Address updateAdresse(Address address) {
		return addressDao.updateAdresse(address);
	}

	@Override
	public Address deleteAdresse(Long idAddress) {
		return addressDao.deleteAdresse(idAddress);
	}

}
