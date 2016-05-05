package service;

import java.util.List;

import model.User;

public interface UserDAO {
	
	public List<User> selectAll();
	
	public User selectByEmail(String email);
	
	public void add(User user);
	
	public void delete(int id);
	
	public void edit(User user);
}
