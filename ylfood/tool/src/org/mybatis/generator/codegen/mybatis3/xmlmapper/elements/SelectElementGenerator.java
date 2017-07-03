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

import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

/**
 * 
 * @author Jeff Butler
 * 
 */
public class SelectElementGenerator extends
        AbstractXmlElementGenerator {

    public SelectElementGenerator() {
        super();
    }

    @Override
    public void addElements(XmlElement parentElement) {
    	XmlElement answer = new XmlElement("select"); //$NON-NLS-1$

        answer.addAttribute(new Attribute("id", "select")); //$NON-NLS-1$

        answer.addAttribute(new Attribute("resultMap", "BaseResultMap")); //$NON-NLS-1$
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
        sb.append("select "); //$NON-NLS-1$
        answer.addElement(new TextElement(sb.toString()));
        
        XmlElement clElement = new XmlElement("include"); //$NON-NLS-1$
        clElement.addAttribute(new Attribute("refid", "Base_Column_List"));
        answer.addElement(clElement);
        
        sb.setLength(0);
        sb.append(" from ");
        sb.append(introspectedTable.getFullyQualifiedTableNameAtRuntime());
        answer.addElement(new TextElement(sb.toString()));
        
        XmlElement includeElement = new XmlElement("include"); //$NON-NLS-1$
        includeElement.addAttribute(new Attribute("refid", "Base_Where_Case"));
        answer.addElement(includeElement);

        XmlElement isNotNullElement = new XmlElement("if"); //$NON-NLS-1$
        isNotNullElement.addAttribute(new Attribute("test", "limit > 0")); //$NON-NLS-1$
        answer.addElement(isNotNullElement);
        isNotNullElement.addElement(new TextElement(" limit #{offset},#{limit} "));
        
        if (context.getPlugins()
                .sqlMapUpdateByPrimaryKeySelectiveElementGenerated(answer,
                        introspectedTable)) {
            parentElement.addElement(answer);
        }
    }
}
