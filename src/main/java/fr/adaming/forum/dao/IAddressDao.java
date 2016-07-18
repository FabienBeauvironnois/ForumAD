package fr.adaming.forum.dao;

import fr.adaming.forum.entity.Address;

public interface IAddressDao {
		
		public Address addAdresse(Address address);
		public Address getAdresseById(Long idAddress);
		public Address updateAdresse(Address address);
		public Address deleteAdresse(Long idAddress);
}
