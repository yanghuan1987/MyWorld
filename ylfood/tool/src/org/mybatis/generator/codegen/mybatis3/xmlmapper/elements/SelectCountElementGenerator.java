/*
 *  Copyright 2009 The Apache Software Foundation
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.mybatis.generator.codegen.mybatis3.xmlmapper.elements;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;

/**
 * 
 * @author Jeff Butler
 * 
 */
public class SelectCountElementGenerator extends
        AbstractXmlElementGenerator {

    public SelectCountElementGenerator() {
        super();
    }

    @Override
    public void addElements(XmlElement parentElement) {
        XmlElement answer = new XmlElement("select"); //$NON-NLS-1$

        answer.addAttribute(new Attribute("id", "selectCount")); //$NON-NLS-1$

        answer.addAttribute(new Attribute("resultType", //$NON-NLS-1$
                "java.lang.Long"));
        
        String parameterType;

        if (introspectedTable.getRules().generateRecordWithBLOBsClass()) {
            parameterType = introspectedTable.getRecordWithBLOBsType();
        } else {
            parameterType = introspectedTable.getBaseRecordType();
        }
        answer.addAttribute(new Attribute("parameterType", //$NON-NLS-1$
                parameterType));

        context.getCommentGenerator().addComment(answer);

        StringBuilder sb = new StringBuilder();

        sb.append("select count( "); //$NON-NLS-1$
        for (IntrospectedColumn introspectedColumn : introspectedTable.getPrimaryKeyColumns()) {
        	sb.append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn)).append(",");
        }
        if(sb.charAt(sb.length() - 1) == ',') {
        	sb.deleteCharAt(sb.length() - 1);
        }
        sb.append(")");
        
        answer.addElement(new TextElement(sb.toString()));
        
        sb.setLength(0);
        sb.append(" from ");
        sb.append(introspectedTable.getFullyQualifiedTableNameAtRuntime());
        answer.addElement(new TextElement(sb.toString()));
        
        XmlElement includeElement = new XmlElement("include"); //$NON-NLS-1$
        includeElement.addAttribute(new Attribute("refid", "Base_Where_Case"));
        answer.addElement(includeElement);

        if (context.getPlugins()
                .sqlMapUpdateByPrimaryKeySelectiveElementGenerated(answer,
                        introspectedTable)) {
            parentElement.addElement(answer);
        }
    }
}
