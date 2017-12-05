/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.api.services;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import mii.api.entity.Admin;
import mii.api.entity.AdminDetail;
import mii.api.repo.AdminRepo;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author User
 */
@Service("adminService")
@Transactional
public class AdminService implements UserDetailsService{

    @Autowired
    private AdminRepo adminRepo;

    public Admin insertNewAdmin(Admin admin) {
        return adminRepo.save(admin);
    }

    public List<Admin> findAllAdmin() {
        return IteratorUtils.toList(adminRepo.findAll().iterator());
    }

    public Admin findAdminById(Long id) {
        return adminRepo.findOne(id);
    }

    public Admin updateAdmin(Admin admin) {
        return adminRepo.save(admin);
    }

    public boolean removeAdmin(Long id) {
        adminRepo.delete(id);
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Admin admin = adminRepo.findByEmail(email);
        if (admin == null) {
            throw new UsernameNotFoundException("No user present with username: " + email);
        } else {
            List<String> userRoles = new ArrayList<String>();
            userRoles.add("USER");
            return new AdminDetail(admin, userRoles);
        }
    }

    public Admin register(Admin admin) throws java.lang.Exception {
        Admin temp = adminRepo.findByEmail(admin.getEmail());
        if (temp != null) {
            throw new Exception("Email Already Registered");
        }
        return adminRepo.save(admin);
    }

    public Admin login(String email, String password) throws Exception {
        Admin admin = adminRepo.findByEmail(email);
        if (admin != null) {
            if (!admin.getPassword().equals(DigestUtils.md5Hex(password))) {
                admin = null;
                throw new Exception("login Fail");
            } else {
                return admin;
            }
        } else {
            throw new Exception("Login Fail");
        }
    }

    public Admin findByEmail(String email) {
        return adminRepo.findByEmail(email);
    }
    
}
