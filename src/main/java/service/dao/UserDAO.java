package service.dao;

import json.usersDataJson.UsersDataMapJson;
import json.userJson.UsersMapJson;
import json.userJson.UserJson;
import json.usersDataJson.UsersDataJson;
import modeles.Schoolkid;
import modeles.Teacher;

import java.util.List;

public interface UserDAO {

	UsersMapJson selectAll();

	UserJson selectUserByEmail(String email);

	UsersDataJson selectUsersDataById(Integer id);

	UsersDataMapJson getUsersDataListByIds(List<Integer> ids);

	void addUser(Schoolkid schoolkid);

	void addUser(Teacher teacher);
}
