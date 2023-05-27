package com.reggie.service;

import com.reggie.dao.AddressBookMapper;
import com.reggie.pojo.AddressBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddressBookService {

    @Autowired
    private AddressBookMapper addressBookMapper;

    public void insertAddressBook(AddressBook addressBook) {
        addressBookMapper.insertAddressBook(addressBook);
    }

    @Transactional
    public void updateDefault(AddressBook addressBook) {
        addressBookMapper.updateCancelDefault(addressBook);
        addressBookMapper.updateSetDefault(addressBook);
    }

    public void updateAddress(AddressBook addressBook) {
        addressBookMapper.updateAddress(addressBook);
    }

    public AddressBook findAddressBookById(Long id) {
        return addressBookMapper.selectAddressBookById(id);
    }

    public List<AddressBook> findAddressBookByUserId(Long userId) {
        return addressBookMapper.selectAddressBookByUserId(userId);
    }

    public AddressBook findDefaultAddressByUserId(Long userId) {
        return addressBookMapper.selectDefaultAddressByUserId(userId);
    }

    public void deleteAddress(Long[] ids) {
        for (Long id : ids) {
            addressBookMapper.deleteAddress(id);
        }
    }
}
