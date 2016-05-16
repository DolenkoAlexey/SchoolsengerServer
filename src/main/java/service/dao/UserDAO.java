package service.dao;

import entities.UserEntity;
import json.TokenJson;
import json.usersDataJson.UsersDataMapJson;
import json.userJson.UsersMapJson;
import json.userJson.UserJson;
import json.usersDataJson.UsersDataJson;
import modeles.Schoolkid;
import modeles.Teacher;

import java.util.List;
import java.util.Map;

public interface UserDAO {

	UsersMapJson selectAll();

	Map<Class, List<? extends UserJson>> selectUserByEmail(String email);

	UsersDataJson selectUsersDataById(Integer id);

	UsersDataMapJson getUsersDataListByIds(List<Integer> ids);

	void addToken(TokenJson token);

	List<TokenJson> selectAllTokens();

	void addUser(Schoolkid schoolkid);

	void addUser(Teacher teacher);

	void delete(Integer id);
}
