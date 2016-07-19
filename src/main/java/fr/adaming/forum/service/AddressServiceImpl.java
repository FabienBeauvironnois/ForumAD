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
	public Address addAddress(Address address) {
		return addressDao.addAddress(address);
	}

	@Override
	public Address getAddressById(Long idAddress) {
		return addressDao.getAddressById(idAddress);
	}

	@Override
	public Address updateAddress(Address address) {
		return addressDao.updateAddress(address);
	}

	@Override
	public Address deleteAddress(Long idAddress) {
		return addressDao.deleteAddress(idAddress);
	}

}
