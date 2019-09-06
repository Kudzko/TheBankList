package by.devincubator.firstTask.model.dao.daointerface;

import by.devincubator.firstTask.model.dao.daoexception.DAOException;

import java.util.List;

public interface IAbstractDAO<T,PK> {
    /**
     * Create record in DB according to object
     *
     * @throws DAOException
     */
    T persist(T entity) throws DAOException;

    /**
     * Saves state of object in DB
     *
     * @throws DAOException
     */
    void update(T entity) throws DAOException;

    /**
     * Delete record about object from DB
     */
    void delete(T entity) throws DAOException;

    /**
     * Return object by PK
     */
    T getByPK(PK pk) throws DAOException;

    /**Return list of objects according all records in DB*/
    List<T> getAll() throws DAOException;

}
