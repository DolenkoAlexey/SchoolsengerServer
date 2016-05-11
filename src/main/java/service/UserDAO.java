package service;

import json.UsersMapJson;
import json.userJson.UserJson;
import modeles.Schoolkid;
import modeles.Teacher;

import java.util.List;

public interface UserDAO {

	List selectAll();

	UserJson selectUserByEmail(String email);

	//UsersDataJson selectUsersDataById(Integer id);

	//UsersDataListJson getUsersDataListByIds(List<Integer> ids);

	void addUser(Schoolkid schoolkid);

	void addUser(Teacher teacher);
}
