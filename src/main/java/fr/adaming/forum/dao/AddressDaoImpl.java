package fr.adaming.forum.dao;

import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import fr.adaming.forum.entity.Address;

@Component
public class AddressDaoImpl implements IAddressDao {

	private Logger log = Logger.getLogger("AddressDaoImpl");
	@PersistenceContext
	private EntityManager em;

	@Override
	public Address addAddress(Address address) {
		em.persist(address);
		log.info("L'adresse " + address + " a été ajouté.");
		return address;
	}

	@Override
	public Address getAddressById(Long idAddress) {
		Address a = em.find(Address.class, idAddress);
		if (a != null) {
			log.info("L'adresse " + a + " est dans la base de donnée");
		} else {
			log.warning("L'adresse " + idAddress + " n'est pas dans la base de donnée");
		}
		return a;
	}

	@Override
	public Address updateAddress(Address address) {
		em.merge(address);
		log.info("L'adresse " + address + " a été modifiée");
		return address;
	}

	@Override
	public Address deleteAddress(Long idAddress) {
		Address address = getAddressById(idAddress);
		if (address != null) {
			em.remove(address);
			log.info("L'adresse " + address + " a été supprimée de la base de donnée");
		} 
		return address;
	}
}