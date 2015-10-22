package com.merobo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.merobo.beans.UserBean;
import com.merobo.dtos.UserTo;
import com.merobo.exceptions.DuplicateUsernameException;
import com.merobo.exceptions.UserNotFoundException;
import com.merobo.exceptions.UserServiceException;
import com.merobo.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public void login(UserTo userTo) throws UserServiceException {
		UserBean userBean = userRepository.findByUsernameAndPassword(
				userTo.getUsername(), userTo.getPassword());
		if (userBean == null) {
			throw new UserNotFoundException("User not found");
		}
		userTo.setName(userBean.getName());
		userTo.setTeamName(userBean.getTeamName());
		userTo.setId(userBean.getId());
	}

	public void create(UserTo userTo) throws UserServiceException {
		UserBean userBean = userRepository.findByUsername(userTo.getUsername());
		if (userBean != null) {
			throw new DuplicateUsernameException("Username is already taken");
		}
		UserBean newUserBean = new UserBean();
		newUserBean.setName(userTo.getName());
		newUserBean.setPassword(userTo.getPassword());
		newUserBean.setTeamName(userTo.getTeamName());
		newUserBean.setUsername(userTo.getUsername());
		userRepository.save(newUserBean);
		userTo.setId(newUserBean.getId());
	}

}
