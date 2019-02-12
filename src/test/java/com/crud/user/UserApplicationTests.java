package com.crud.user;

import static com.crud.user.Constants.ALICEID;
import static com.crud.user.Constants.ALICENAME;
import static com.crud.user.Constants.INCORRECTID;
import static com.crud.user.Constants.INITIALSIZEOFUSERS;
import static com.crud.user.Constants.USERNOTEXIST;
import static com.crud.user.Constants.ZERO;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.crud.user.exceptions.UserNotFoundException;
import com.crud.user.model.User;
import com.crud.user.repository.UserRepository;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserApplicationTests {
	@Autowired
	private UserRepository userRepository;
	@Test
	public void retrieveAllUsers() {
	    final List<User> users = userRepository.findAll();
		assertTrue(!users.isEmpty());
		assertTrue(users.size() == INITIALSIZEOFUSERS);
		assertTrue(ALICENAME.equals(users.get(ZERO).getName()));
	}

	@Test(expected = UserNotFoundException.class)
	public void retrieveUser_Fail() throws UserNotFoundException {
	    final Optional<User> user = userRepository.findById(INCORRECTID);
	    if (!user.isPresent()) {
	        throw new UserNotFoundException(USERNOTEXIST);
	    }
	}
	
	@Test
    public void retrieveUser_Succes() throws UserNotFoundException {
        final Optional<User> user = userRepository.findById(ALICEID);
        assertTrue(user.isPresent());
    }
}

