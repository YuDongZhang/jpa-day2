package cn.itcast.dao;

import cn.itcast.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 符合SpringDataJpa的dao层接口规范
 *      JpaRepository<操作的实体类类型，实体类中主键属性的类型>
 *          * 封装了基本CRUD操作
 *      JpaSpecificationExecutor<操作的实体类类型>
 *          * 封装了复杂查询（分页）
 */
public interface CustomerDao extends JpaRepository<Customer,Long> , JpaSpecificationExecutor<Customer> {
    /**
     * 案例：根据客户名称查询客户
     *      使用jpql的形式查询
     *  jpql：from Customer where custName = ?
     *
     *  配置jpql语句，使用的@Query注解
     */
    @Query(value="from Customer where custName = ?")
    public Customer findJpql(String custName);

    /**
     * 案例：根据客户名称和客户id查询客户
     *      jpql： from Customer where custName = ? and custId = ?
     *
     *  对于多个占位符参数
     *      赋值的时候，默认的情况下，占位符的位置需要和方法参数中的位置保持一致
     *
     *  可以指定占位符参数的位置
     *      ? + 索引的方式，指定此占位的取值来源
     */
//    @Query(value = "from Customer where custId = ? and custName = ?")
    @Query(value = "from Customer where custName = ?2 and custId = ?1")
    public Customer findCustNameAndId(Long id,String name);

    /**
     * 使用jpql完成更新操作
     *      案例 ： 根据id更新，客户的名称
     *          更新4号客户的名称，将名称改为“黑马程序员”
     *
     *  sql  ：update cst_customer set cust_name = ? where cust_id = ?
     *  jpql : update Customer set custName = ? where custId = ?
     *
     *  @Query : 代表的是进行查询
     *      * 声明此方法是用来进行更新操作
     *  @Modifying
     *      * 当前执行的是一个更新操作
     *
     */
    @Query(value = " update Customer set custName = ?2 where custId = ?1 ")
    @Modifying
    public void updateCustomer(long custId,String custName);



}
