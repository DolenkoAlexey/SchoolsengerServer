package service.dao;

import entities.UserEntity;
import json.TokenJson;
import json.usersDataJson.UsersDataMapJson;
import json.userJson.UsersMapJson;
import json.userJson.UserJson;
import json.usersDataJson.UsersDataJson;
import modeles.Schoolkid;
import modeles.Teacher;
import scala.Int;

import java.util.List;
import java.util.Map;

public interface UserDAO {

	UsersMapJson selectAll();

	Map<Class, List<? extends UserJson>> selectUserByEmail(String email);

	UserJson selectUserById(Integer id);

	UsersDataJson selectUsersDataById(Integer id);

	UsersDataMapJson getUsersDataMapByIds(List<Integer> ids);

    UsersDataMapJson getUsersDataMapByUsername(String username);

	void addUser(Schoolkid schoolkid);

	void addUser(Teacher teacher);

	void deleteUser(Integer id);
}
