import React, {PropTypes} from 'react'
import {connect} from 'dva'
import { Row, Col, Card} from 'antd'

function Dashboard ({dashboard, dispatch,app}) {
    const { data} = dashboard;

    return(
      <Row gutter={24}>
      
          <Col lg={6} md={8} sm={12} xs={12}>
                    <Card style={{marginBottom:10}} bordered={false}>
                        <h2 style={{fontWeight:'normal'}}>自提点编码</h2>
                        <br/>
                        <h3>{data.siteId}</h3>
                    </Card>
            </Col>
             
             <Col lg={6} md={8} sm={12} xs={12}>
                    <Card style={{marginBottom:10}} bordered={false}>
                        <h2 style={{fontWeight:'normal'}}>冷柜数量</h2>
                        <br/>
                        <h3>{data.icebox}</h3>
                    </Card>
            </Col>
            
          
      </Row>
    )
}
function mapStateToProps({app,dashboard}){
	return {app,dashboard}
}

export default connect(mapStateToProps)(Dashboard)