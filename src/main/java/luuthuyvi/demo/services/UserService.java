package luuthuyvi.demo.services;

import luuthuyvi.demo.entity.User;
import luuthuyvi.demo.repository.IUserRepository;
import luuthuyvi.demo.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;
    public void save (User user){
        userRepository.save(user);
        Long userId = userRepository.getUserIdByUsername((user.getUsername()));
        Long roleId = roleRepository.getRoleIdByName("User");
        if (roleId != 0 && userId != 0) {
            userRepository.addRoleToUser(userId, roleId);
        }
    }
}