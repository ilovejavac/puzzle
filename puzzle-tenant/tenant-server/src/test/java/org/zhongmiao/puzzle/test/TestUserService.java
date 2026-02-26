package org.zhongmiao.puzzle.test;

import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.zhongmiao.puzzle.adapt.UserRepo;
import org.zhongmiao.puzzle.jpa.entity.PuzzleUser;
import org.zhongmiao.puzzle.jpa.repository.UserDao;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TestUserService {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserRepo userService;

    @Test
    void testReturnNull() {

        Long       userid = -3L;
        PuzzleUser user   = new PuzzleUser();

        Mockito.when(userDao.findById(userid)).thenReturn(Optional.of(user));
        Mockito.when(userDao.findById(999L)).thenReturn(Optional.empty());

        Assert.notNull(userService.loadUser(userid));
        Assert.isNull(userService.loadUser(999L));
    }

}
