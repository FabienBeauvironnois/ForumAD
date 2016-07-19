package fr.adaming.forum.dao;

import fr.adaming.forum.entity.Address;

public interface IAddressDao {
		
		public Address addAddress(Address address);
		public Address getAddressById(Long idAddress);
		public Address updateAddress(Address address);
		public Address deleteAddress(Long idAddress);
}
