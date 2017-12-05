/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mii.api.repo;

import java.io.Serializable;
import mii.api.entity.Admin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author User
 */
public interface AdminRepo extends CrudRepository<Admin, Long>{
    @Query("SELECT a FROM Admin a WHERE LOWER(a.email) = LOWER(:email)")
    public Admin findByEmail(@Param("email") String email);
}
