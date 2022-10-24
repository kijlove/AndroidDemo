package com.kijlee.android.demo.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.DaoException;
import com.kijlee.android.demo.entity.greendao.DaoSession;
import com.kijlee.android.demo.entity.greendao.ChinaCityDao;

/**
 * @ProjectName:    AndroidDemo
 * @Package:        com.kijlee.android.demo.entity
 * @ClassName:      ChinaTown
 * @Author:     kij
 * @Description:  中国城镇
 * @Date:    2022/1/27 6:24 下午
 * @Version:    1.0
 * schema：告知GreenDao当前实体属于哪个schema
 * active：标记一个实体处于活跃状态，活动实体有更新、删除和刷新方法
 * nameInDb：在数据库中使用的别名，默认使用的是实体的类名
 * indexes：定义索引，可以跨越多个列
 * createInDb：标记创建数据库表
 * greendao
 * @Id：主键 Long 型，可以通过@Id(autoincrement = true)设置自增长
 * @Property：设置一个非默认关系映射所对应的列名，默认是使用字段名，例如：@Property(nameInDb = "name")
 * @NotNull：设置数据库表当前列不能为空
 * @Transient：添加此标记后不会生成数据库表的列
 * @Index：使用@Index作为一个属性来创建一个索引，通过name设置索引别名，也可以通过unique给索引添加约束
 * @Unique：向数据库添加了一个唯一的约束
 * @ToOne：定义与另一个实体（一个实体对象）的关系
 * @ToMany：定义与多个实体对象的关系
 *
 */
@Entity
public class ChinaCity {
    //@Id：主键 Long 型，可以通过@Id(autoincrement = true)设置自增长
    @Id(autoincrement = true)
    private long _id;
    //@NotNull：设置数据库表当前列不能为空
    @NotNull
    private String code;
    @NotNull
    private String name;
    private String url;
    private long city_id;
    private long county_id;
    private long town_id;

    @ToMany(referencedJoinProperty = "city_id")
    private List<ChinaCity> city;
    @ToMany(referencedJoinProperty = "county_id")
    private List<ChinaCity> county;
    @ToMany(referencedJoinProperty = "town_id")
    private List<ChinaCity> town;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 49925616)
    private transient ChinaCityDao myDao;
    @Generated(hash = 1448753218)
    public ChinaCity(long _id, @NotNull String code, @NotNull String name,
            String url, long city_id, long county_id, long town_id) {
        this._id = _id;
        this.code = code;
        this.name = name;
        this.url = url;
        this.city_id = city_id;
        this.county_id = county_id;
        this.town_id = town_id;
    }
    @Generated(hash = 2047360224)
    public ChinaCity() {
    }
    public long get_id() {
        return this._id;
    }
    public void set_id(long _id) {
        this._id = _id;
    }
    public String getCode() {
        return this.code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public long getCity_id() {
        return this.city_id;
    }
    public void setCity_id(long city_id) {
        this.city_id = city_id;
    }
    public long getCounty_id() {
        return this.county_id;
    }
    public void setCounty_id(long county_id) {
        this.county_id = county_id;
    }
    public long getTown_id() {
        return this.town_id;
    }
    public void setTown_id(long town_id) {
        this.town_id = town_id;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 2029269898)
    public List<ChinaCity> getCity() {
        if (city == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ChinaCityDao targetDao = daoSession.getChinaCityDao();
            List<ChinaCity> cityNew = targetDao._queryChinaCity_City(_id);
            synchronized (this) {
                if (city == null) {
                    city = cityNew;
                }
            }
        }
        return city;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1352737435)
    public synchronized void resetCity() {
        city = null;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 711507539)
    public List<ChinaCity> getCounty() {
        if (county == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ChinaCityDao targetDao = daoSession.getChinaCityDao();
            List<ChinaCity> countyNew = targetDao._queryChinaCity_County(_id);
            synchronized (this) {
                if (county == null) {
                    county = countyNew;
                }
            }
        }
        return county;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 7090382)
    public synchronized void resetCounty() {
        county = null;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 584455166)
    public List<ChinaCity> getTown() {
        if (town == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ChinaCityDao targetDao = daoSession.getChinaCityDao();
            List<ChinaCity> townNew = targetDao._queryChinaCity_Town(_id);
            synchronized (this) {
                if (town == null) {
                    town = townNew;
                }
            }
        }
        return town;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1494151981)
    public synchronized void resetTown() {
        town = null;
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 65230395)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getChinaCityDao() : null;
    }
}
