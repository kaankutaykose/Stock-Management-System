package com.Softito.StockManagement.Service;

import com.Softito.StockManagement.Entity.Role;
import com.Softito.StockManagement.Repository.IRepositoryService;
import com.Softito.StockManagement.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements IRepositoryService<Role> {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role add(Role entity) {
        return roleRepository.save(entity);
    }

    @Override
    public String delete(Long id) {
        return null;
    }

    @Override
    public String update(Long id, Role entity) {
        return null;
    }

    @Override
    public Optional<Role> getById(Long id) {
        return roleRepository.findById(id)!=null?roleRepository.findById(id):null;
    }

    @Override
    public List<Role> getAll() {
        return null;
    }
}
