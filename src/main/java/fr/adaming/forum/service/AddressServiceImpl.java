package fr.adaming.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.forum.dao.IAddressDao;
import fr.adaming.forum.entity.Address;

@Transactional
public class AddressServiceImpl implements IAddressService{

	@Autowired
	private IAddressDao addressDao;
	
	@Override
	public Address addAdresse(Address address) {
		return addressDao.addAdresse(address);
	}

	@Override
	public Address getAdresseById(int idAddress) {
		return addressDao.getAdresseById(idAddress);
	}

	@Override
	public Address updateAdresse(Address address) {
		return addressDao.updateAdresse(address);
	}

	@Override
	public Address deleteAdresse(int idAddress) {
		return addressDao.deleteAdresse(idAddress);
	}

}
