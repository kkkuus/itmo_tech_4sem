package repository.mybatis;

import dao.CatDao;
import database.MyBatisUtil;
import entities.Cat;
import org.apache.ibatis.session.SqlSession;
import repository.mybatis.mappers.CatMapper;

import java.util.ArrayList;

public class CatMybatisService implements CatDao {
    public Cat save(Cat cat) {
        SqlSession session = MyBatisUtil.getSqlSession().openSession();
        CatMapper catMapper = session.getMapper(CatMapper.class);
        catMapper.save(cat);
        session.commit();
        session.close();
        return cat;
    }
    public void deleteById(long id) {
        SqlSession session = MyBatisUtil.getSqlSession().openSession();
        CatMapper catMapper = session.getMapper(CatMapper.class);
        catMapper.deleteById(id);
        session.commit();
        session.close();
    }
    public void deleteByEntity(Cat cat) {
        SqlSession session = MyBatisUtil.getSqlSession().openSession();
        CatMapper catMapper = session.getMapper(CatMapper.class);
        catMapper.deleteByEntity(cat);
        session.commit();
        session.close();
    }
    public void deleteAll() {
        SqlSession session = MyBatisUtil.getSqlSession().openSession();
        CatMapper catMapper = session.getMapper(CatMapper.class);
        catMapper.deleteAll();
        session.commit();
        session.close();
    }
    public Cat update(Cat cat) {
        SqlSession session = MyBatisUtil.getSqlSession().openSession();
        CatMapper catMapper = session.getMapper(CatMapper.class);
        catMapper.update(cat);
        session.commit();
        session.close();
        return cat;
    }
    public Cat getById(long id) {
        SqlSession session = MyBatisUtil.getSqlSession().openSession();
        CatMapper catMapper = session.getMapper(CatMapper.class);
        return catMapper.getById(id);
    }
    public ArrayList<Cat> getAll() {
        SqlSession session = MyBatisUtil.getSqlSession().openSession();
        CatMapper catMapper = session.getMapper(CatMapper.class);
        return catMapper.getAll();
    }
}
