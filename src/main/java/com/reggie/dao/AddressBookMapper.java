package com.reggie.dao;

import com.reggie.pojo.AddressBook;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AddressBookMapper {

    int insertAddressBook(AddressBook addressBook);

    int updateSetDefault(AddressBook addressBook);

    int updateAddress(AddressBook addressBook);

    int updateCancelDefault(AddressBook addressBook);

    AddressBook selectAddressBookById(Long id);

    List<AddressBook> selectAddressBookByUserId(Long userId);

    AddressBook selectDefaultAddressByUserId(Long userId);

    int deleteAddress(Long id);

}
