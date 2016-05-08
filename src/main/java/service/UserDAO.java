package service;

import java.util.List;

import json.UsersDataJson;
import json.UsersDataListJson;
import json.UsersListJson;
import modeles.User;

public interface UserDAO {

	UsersListJson selectAll();

	User selectByEmail(String email);

	//UsersDataJson selectUsersDataById(Integer id);

	//UsersDataListJson getUsersDataListByIds(List<Integer> ids);

	void add(User user);
}
