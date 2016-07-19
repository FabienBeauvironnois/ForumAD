package fr.adaming.forum.service;

import fr.adaming.forum.entity.Address;

public interface IAddressService {
	
	public Address addAddress(Address address);
	public Address getAddressById(Long idAddress);
	public Address updateAddress(Address address);
	public Address deleteAddress(Long idAddress);

}
