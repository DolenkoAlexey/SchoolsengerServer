package service;

import java.util.List;

import entities.UserEntity;
import json.UsersDataJson;
import json.UsersDataListJson;
import json.UsersListJson;

public interface UserDAO {
	
	public UsersListJson selectAll();
	
	public UserEntity selectByEmail(String email);
	
	public UsersDataJson selectUsersDataById(Integer id);
	
	public UsersDataListJson getUsersDataListByIds(List<Integer> ids);
	
	public void add(UserEntity user);
	
	public void delete(int id);
}
