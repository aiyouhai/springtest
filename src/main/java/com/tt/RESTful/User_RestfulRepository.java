package com.tt.RESTful;

import java.util.List;

public interface User_RestfulRepository {

	List<User_Restful> getUserRe();

	User_Restful getUserReOne(int id);

	User_Restful getUserReAndAddre(int id);

}
