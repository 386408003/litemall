package org.linlinjava.litemall.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.linlinjava.litemall.db.domain.TianyuOrder;
import org.linlinjava.litemall.db.domain.TianyuOrderExample;

public interface TianyuOrderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tianyu_order
     *
     * @mbg.generated
     */
    long countByExample(TianyuOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tianyu_order
     *
     * @mbg.generated
     */
    int deleteByExample(TianyuOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tianyu_order
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tianyu_order
     *
     * @mbg.generated
     */
    int insert(TianyuOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tianyu_order
     *
     * @mbg.generated
     */
    int insertSelective(TianyuOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tianyu_order
     *
     * @mbg.generated
     */
    TianyuOrder selectOneByExample(TianyuOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tianyu_order
     *
     * @mbg.generated
     */
    TianyuOrder selectOneByExampleSelective(@Param("example") TianyuOrderExample example, @Param("selective") TianyuOrder.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tianyu_order
     *
     * @mbg.generated
     */
    List<TianyuOrder> selectByExampleSelective(@Param("example") TianyuOrderExample example, @Param("selective") TianyuOrder.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tianyu_order
     *
     * @mbg.generated
     */
    List<TianyuOrder> selectByExample(TianyuOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tianyu_order
     *
     * @mbg.generated
     */
    TianyuOrder selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") TianyuOrder.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tianyu_order
     *
     * @mbg.generated
     */
    TianyuOrder selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tianyu_order
     *
     * @mbg.generated
     */
    TianyuOrder selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tianyu_order
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") TianyuOrder record, @Param("example") TianyuOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tianyu_order
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") TianyuOrder record, @Param("example") TianyuOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tianyu_order
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TianyuOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tianyu_order
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TianyuOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tianyu_order
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") TianyuOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tianyu_order
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}