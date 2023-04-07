package ru.kata.spring.boot_security.demo.service;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.UserDaoRepository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserDaoRepository userDaoRepository;

    private static Logger logger = Logger.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserDaoRepository userDaoRepository) {
        this.userDaoRepository = userDaoRepository;
    }

    @Transactional
    @Override
    public User save(User user) {
        logger.info("Начата операция добавления");
        return userDaoRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        logger.info("Начата операция поиска всех пользователей");
        return userDaoRepository.getAllUser();
    }

    @Transactional
    @Override
    public void update(User user) {
        logger.info("Начата операция обновления пользователя");
        userDaoRepository.save(user);
    }

    @Transactional
    @Override
    public void delete(User user) {
        logger.info("Начата операция удаления пользователя");
        userDaoRepository.delete(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        logger.info("Начата операция поиска по id");
        return userDaoRepository.findUserById(id);
    }
}
