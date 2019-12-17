/*
 * Copyright (c) 2011-2020, baomidou (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.yonyou.einvoice.common.agile.mp.extend.methods;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;
import com.yonyou.einvoice.common.agile.mp.extend.AbstractExtMethod;
import com.yonyou.einvoice.common.agile.mp.extend.ExtTableInfo;
import org.apache.ibatis.executor.keygen.KeyGenerator;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * 扩展插入语句 删除了主键生成
 *
 * @author liuqiangm
 * @since 2019-11-11
 */
@SuppressWarnings("all")
public class ExtInsert extends AbstractExtMethod {

  @Override
  public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass,
      ExtTableInfo tableInfo) {
    KeyGenerator keyGenerator = new NoKeyGenerator();
    SqlMethod sqlMethod = SqlMethod.INSERT_ONE;
    String columnScript = SqlScriptUtils.convertTrim(tableInfo.getAllInsertSqlColumnMaybeIf(),
        LEFT_BRACKET, RIGHT_BRACKET, null, COMMA);
    String valuesScript = SqlScriptUtils.convertTrim(tableInfo.getAllInsertSqlPropertyMaybeIf(null),
        LEFT_BRACKET, RIGHT_BRACKET, null, COMMA);
    String keyProperty = tableInfo.getKeyProperty();
    String keyColumn = tableInfo.getKeyColumn();
    String sql = String
        .format(sqlMethod.getSql(), tableInfo.getTableName(), columnScript, valuesScript);
    SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
    return this.addInsertMappedStatement(mapperClass, modelClass, sqlMethod.getMethod(), sqlSource,
        keyGenerator, keyProperty, keyColumn);
  }
}