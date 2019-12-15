package daos;

import java.util.List;
import java.util.Set;

public interface DAO<T> {
    public T findById(int id);
    public Set findAll();
    public T update(T dto);
    public T create(T dto);
    public Boolean delete(int id);
}
