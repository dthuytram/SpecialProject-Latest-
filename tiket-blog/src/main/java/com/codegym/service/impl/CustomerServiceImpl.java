package com.codegym.service.impl;
import com.codegym.dto.CustomerDto;
import com.codegym.model.Customer;
import com.codegym.repository.ICustomerRepository;

import com.codegym.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private ICustomerRepository iCustomerRepository;

    @Override
    public Page<Customer> findAllCustomer(Pageable pageable) {
        return iCustomerRepository.findAllByCustomer(pageable);
    }

    @Override
    public void remove(Long id) {
        iCustomerRepository.deleteCustomerByIdCustomer(id);
    }

//    @Override
//    public List<Customer> searchCustomer(String keyword) {
//        return null;
//    }


    @Override
    public Customer findById(Long id) {
        return iCustomerRepository.findByIdCustomer(id);
    }

    @Override
    public Boolean checkIdCustomer(Long id) {
        if(iCustomerRepository.checkIdCustomer(id).orElse(null) == null) {
            return false;
        }
        return true;
    }

    @Override
    public void save(CustomerDto customerDto) {

        iCustomerRepository.saveCustomer(
                customerDto.getNameCustomer(),
                customerDto.getPhoneCustomer(),
                customerDto.getGenderCustomer(),
                customerDto.getEmailCustomer(),
                customerDto.getIdCardCustomer(),
                customerDto.getBirthdayCustomer(),
                customerDto.getAddressCustomer(),
                customerDto.getCustomerType(),
                customerDto.getCountries(),
                true);
    }

    @Override
    public void update(CustomerDto customerDto) {
        iCustomerRepository.updateCustomer(
                customerDto.getNameCustomer(),
                customerDto.getPhoneCustomer(),
                customerDto.getGenderCustomer(),
                customerDto.getEmailCustomer(),
                customerDto.getIdCardCustomer(),
                customerDto.getBirthdayCustomer(),
                customerDto.getAddressCustomer(),
                customerDto.getCustomerType(),
                customerDto.getCountries(),
                true,
                customerDto.getId());
    }

    @Override
    public Page<Customer> searchCustomerByEmail(String keyword, Pageable pageable) {
        return iCustomerRepository.searchByEmail(keyword, pageable);
    }

    @Override
    public Page<Customer> searchCustomerByName(String keyword, Pageable pageable) {

        return iCustomerRepository.searchByName(keyword, pageable);
    }

    @Override
    public Page<Customer> searchCustomerByAddress(String keyword, Pageable pageable) {
        return iCustomerRepository.searchByAddress(keyword, pageable);
    }

    @Override
    public Page<Customer> searchCustomerByCountry(String keyword, Pageable pageable) {
        return iCustomerRepository.searchByCountry(keyword, pageable);
    }

    @Override
    public Page<Customer> searchCustomerByCustomerType(String keyword, Pageable pageable) {
        return iCustomerRepository.searchByCustomerType(keyword, pageable);
    }

    @Override
    public Page<Customer> searchCustomerByPhone(String keyword, Pageable pageable) {
        return iCustomerRepository.searchByPhone(keyword, pageable);
    }

    @Override
    public Page<Customer> searchCustomerByIdCrad(String keyword, Pageable pageable) {
        return iCustomerRepository.searchByIdCard(keyword, pageable);
    }


    /*ThangDBX lấy dữ liệu của khách hàng  */
    @Override
    public Customer findCustomerById(Long id) {
        return iCustomerRepository.findCustomerByID(id);
    }



    @Override
    public Customer findByIdPersonal(Long id) {
        return iCustomerRepository.findByIdPersonal(id);
    }

    /*ThangDBX cap nhat liệu của khách hàng  */
    @Override
    public void updatePersonalInfo(Customer customer) {
        String name = customer.getNameCustomer();
        Boolean gender = customer.getGenderCustomer();
        String email = customer.getEmailCustomer();
        String phone = customer.getPhoneCustomer();
        String birth = customer.getBirthdayCustomer();
        String idCard = customer.getIdCardCustomer();
        Long idCountry = customer.getCountries().getId();
        String address = customer.getAddressCustomer();
        String image = customer.getImageCustomer();
        Long id = customer.getId();

        iCustomerRepository.updatePersonalInfo(name, gender, email, phone, birth, idCard, idCountry, address, image, id);
    }

    @Override
    public Integer finByIdCard(String idCard) {
        return iCustomerRepository.finByIdCard(idCard);
    }

    @Override
    public Integer finByEmail(String email) {
        return iCustomerRepository.finByEmail(email);

    }

    @Override
    public Integer finByPhone(String phone) {
        return iCustomerRepository.finByPhone(phone);

    }

    @Override
    public Long findByCheck(String emailCustomer,String phoneCustomer,String idCard) {
        return iCustomerRepository.findByCheck(emailCustomer,phoneCustomer,idCard);
    }

    @Override
    public Integer findByEmailNot(Long id, String emailCustomer) {
        return iCustomerRepository.findByEmailNot( id, emailCustomer);
    }

    @Override
    public Integer findByPhoneNot(Long id, String phoneCustomer) {
        return iCustomerRepository.findByPhoneNot( id,  phoneCustomer);
    }

    @Override
    public Integer findByIdCardNot(Long id, String idCardCustomer) {
        return iCustomerRepository.findByIdCardNot( id,  idCardCustomer);
    }



    @Override
    public Customer findCustomerByEmail(String email) {
        return this.iCustomerRepository.findCustomerByEmail(email);
    }

}
