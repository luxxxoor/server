package repositories;

public interface CrudRepository<E, ID> {
    void save(E entity);

    void delete(ID id);

    void update(E entity, ID id);

    Iterable<E> findAll();
}
