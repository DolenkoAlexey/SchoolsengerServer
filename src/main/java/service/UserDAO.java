package service;

import java.util.List;

import entities.UserEntity;
import json.UsersDataJson;
import json.UsersDataListJson;
import json.UsersListJson;
import modeles.User;

public interface UserDAO {

	UsersListJson selectAll();

	UserEntity selectByEmail(String email);

	UsersDataJson selectUsersDataById(Integer id);

	UsersDataListJson getUsersDataListByIds(List<Integer> ids);

	void add(User user);

	void delete(int id);
}
