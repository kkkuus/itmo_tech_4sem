package repository.mybatis;

import dao.OwnerDao;
import database.MyBatisUtil;
import entities.Cat;
import entities.Owner;
import org.apache.ibatis.session.SqlSession;
import repository.mybatis.mappers.CatMapper;
import repository.mybatis.mappers.OwnerMapper;

import java.util.ArrayList;

public class OwnerMybatisService implements OwnerDao {

    public Owner save(Owner owner) {
        SqlSession session = MyBatisUtil.getSqlSession().openSession();
        OwnerMapper ownerMapper = session.getMapper(OwnerMapper.class);
        ownerMapper.save(owner);
        session.commit();
        session.close();
        return owner;
    }

    public void deleteById(long id) {
        SqlSession session = MyBatisUtil.getSqlSession().openSession();
        OwnerMapper ownerMapper = session.getMapper(OwnerMapper.class);
        ownerMapper.deleteById(id);
        session.commit();
        session.close();
    }

    public void deleteByEntity(Owner owner) {
        SqlSession session = MyBatisUtil.getSqlSession().openSession();
        OwnerMapper ownerMapper = session.getMapper(OwnerMapper.class);
        CatMapper catMapper = session.getMapper(CatMapper.class);
        catMapper.deleteByOwnerId(owner.getId());
        session.commit();
        ownerMapper.deleteByEntity(owner);
        session.commit();
        session.close();
    }

    public void deleteAll() {
        SqlSession session = MyBatisUtil.getSqlSession().openSession();
        CatMapper catMapper = session.getMapper(CatMapper.class);
        catMapper.deleteAll();
        session.commit();
        OwnerMapper ownerMapper = session.getMapper(OwnerMapper.class);
        ownerMapper.deleteAll();
        session.commit();
        session.close();
    }

    public Owner update(Owner owner) {
        SqlSession session = MyBatisUtil.getSqlSession().openSession();
        OwnerMapper ownerMapper = session.getMapper(OwnerMapper.class);
        ownerMapper.update(owner);
        session.commit();
        session.close();
        return owner;
    }

    public Owner getById(long id) {
        SqlSession session = MyBatisUtil.getSqlSession().openSession();
        OwnerMapper ownerMapper = session.getMapper(OwnerMapper.class);
        Owner owner =  ownerMapper.getById(id);
        session.commit();
        session.close();
        return owner;
    }
    
    public ArrayList<Owner> getAll() {
        SqlSession session = MyBatisUtil.getSqlSession().openSession();
        OwnerMapper ownerMapper = session.getMapper(OwnerMapper.class);
        return ownerMapper.getAll();
    }

    public ArrayList<Cat> getAllByOwnerId(long id) {
        SqlSession session = MyBatisUtil.getSqlSession().openSession();
        OwnerMapper ownerMapper = session.getMapper(OwnerMapper.class);
        return ownerMapper.getAllByOwnerId(id);
    }
}
