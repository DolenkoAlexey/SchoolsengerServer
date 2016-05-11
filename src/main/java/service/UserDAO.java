package service;

import json.UsersMapJson;
import json.userJson.SchoolkidJson;
import json.userJson.UserJson;
import modeles.Schoolkid;
import modeles.Teacher;

import java.util.List;

public interface UserDAO {

	UsersMapJson selectAll();

	UserJson selectUserByEmail(String email);

	//UsersDataJson selectUsersDataById(Integer id);

	//UsersDataListJson getUsersDataListByIds(List<Integer> ids);

	void addUser(Schoolkid schoolkid);

	void addUser(Teacher teacher);
}
