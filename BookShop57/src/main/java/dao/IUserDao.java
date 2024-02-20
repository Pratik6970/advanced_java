package dao;

import pojos.User;

public interface IUserDao {
	
	User validateUser(String email ,String pwd)throws Exception;

}
