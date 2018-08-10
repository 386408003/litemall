package org.linlinjava.litemall.db.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.linlinjava.litemall.db.domain.LitemallSearchHistory;
import org.linlinjava.litemall.db.domain.LitemallSearchHistoryExample;

public interface LitemallSearchHistoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_search_history
     *
     * @mbg.generated
     */
    long countByExample(LitemallSearchHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_search_history
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int deleteWithVersionByExample(@Param("version") Integer version, @Param("example") LitemallSearchHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_search_history
     *
     * @mbg.generated
     */
    int deleteByExample(LitemallSearchHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_search_history
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int deleteWithVersionByPrimaryKey(@Param("version") Integer version, @Param("key") Integer key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_search_history
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_search_history
     *
     * @mbg.generated
     */
    int insert(LitemallSearchHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_search_history
     *
     * @mbg.generated
     */
    int insertSelective(LitemallSearchHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_search_history
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    LitemallSearchHistory selectOneByExample(LitemallSearchHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_search_history
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    LitemallSearchHistory selectOneByExampleSelective(@Param("example") LitemallSearchHistoryExample example, @Param("selective") LitemallSearchHistory.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_search_history
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    List<LitemallSearchHistory> selectByExampleSelective(@Param("example") LitemallSearchHistoryExample example, @Param("selective") LitemallSearchHistory.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_search_history
     *
     * @mbg.generated
     */
    List<LitemallSearchHistory> selectByExample(LitemallSearchHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_search_history
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    LitemallSearchHistory selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") LitemallSearchHistory.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_search_history
     *
     * @mbg.generated
     */
    LitemallSearchHistory selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_search_history
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    LitemallSearchHistory selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_search_history
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int updateWithVersionByExample(@Param("version") Integer version, @Param("record") LitemallSearchHistory record, @Param("example") LitemallSearchHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_search_history
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int updateWithVersionByExampleSelective(@Param("version") Integer version, @Param("record") LitemallSearchHistory record, @Param("example") LitemallSearchHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_search_history
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") LitemallSearchHistory record, @Param("example") LitemallSearchHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_search_history
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") LitemallSearchHistory record, @Param("example") LitemallSearchHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_search_history
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int updateWithVersionByPrimaryKey(@Param("version") Integer version, @Param("record") LitemallSearchHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_search_history
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int updateWithVersionByPrimaryKeySelective(@Param("version") Integer version, @Param("record") LitemallSearchHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_search_history
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(LitemallSearchHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_search_history
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(LitemallSearchHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_search_history
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int logicalDeleteByExample(@Param("example") LitemallSearchHistoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_search_history
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int logicalDeleteByPrimaryKey(Integer id);
}