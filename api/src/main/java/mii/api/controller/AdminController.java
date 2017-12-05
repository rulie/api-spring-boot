/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.api.controller;

import java.util.List;
import mii.api.dto.LoginObject;
import mii.api.entity.Admin;
import mii.api.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author User
 */
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Admin> findAllAdmin() {
        //tadi salah
        return adminService.findAllAdmin();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Admin insertNewAdmin(@RequestBody Admin admin) {
        return adminService.insertNewAdmin(admin);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Admin findAdmintaById(@PathVariable("id") Long id) {
        return adminService.findAdminById(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Admin updateAdmin(@RequestBody Admin admin) {
        return adminService.updateAdmin(admin);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public boolean removeAdminById(@PathVariable("id") Long id) {
        return adminService.removeAdmin(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public Admin registerAdmin(@RequestBody Admin admin) throws Exception {
        return adminService.register(admin);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public Admin loginAdmin(@RequestBody LoginObject login) throws Exception {
        return adminService.login(login.getEmail(), login.getPassword());
    }
}
