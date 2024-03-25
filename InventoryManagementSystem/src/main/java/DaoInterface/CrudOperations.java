package DaoInterface;


import java.sql.SQLException;
import java.util.List;

public interface CrudOperations<T> {
	int save() throws SQLException;
    T getById() throws SQLException;
    List<T> getAll() throws SQLException;
    boolean update() throws SQLException;
    boolean delete() throws SQLException;
    boolean deleteAll() throws SQLException;
}