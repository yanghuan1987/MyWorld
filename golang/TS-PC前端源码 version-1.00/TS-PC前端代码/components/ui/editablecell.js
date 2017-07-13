import React from 'react'
import { Table, Input, Popconfirm,Select,Tooltip } from 'antd';

class EditableCell extends React.Component {
  state = {
    cause:this.props.cause,
    value: this.props.value,
    isValid:this.props.isValid,
    editable: this.props.editable || false,
  }
  
  componentWillReceiveProps(nextProps) {
    if (nextProps.editable !== this.state.editable) {
      this.setState({ editable: nextProps.editable });
      if (nextProps.editable) {
        this.cacheValue = this.state.value;
      }
    }

    if (nextProps.status && nextProps.status !== this.props.status) {
     
      if (nextProps.status === 'save') {
        //this.props.onChange(this.state.value);
      } else if (nextProps.status === 'cancel') {
        this.setState({ value: this.cacheValue });
        //this.props.onChange(this.cacheValue);
      }
    }
    //从服务器端获取的数据优先
    if(nextProps.value!==this.state.value||nextProps.cause!==this.state.cause||nextProps.isValid!==this.state.isValid){
      this.setState({ value: nextProps.value,cause:nextProps.cause,isValid:nextProps.isValid });
    }
    
  }
  shouldComponentUpdate(nextProps, nextState) {

    return nextProps.editable !== this.state.editable ||
           nextState.value !== this.state.value||
           nextState.cause !== this.state.cause||
           nextProps.value !== this.state.value||
           nextProps.isValid !== this.state.isValid;
  }
  handleChange(e) {
    const value = e.target.value;
    this.props.onChange(value);
    this.setState({ value });
  }
  formatValue(value){
    if(value<0){
      return '—'
    }
    return value
  }
  render() {
    
    const { editable,isValid } = this.state;
    let value=this.state.value
    let cause=this.state.cause
    if(cause==""){
      cause="无说明"
    }
    if(value%1!=0){
      value=value.toFixed(3)
    }
    return (
      
      <div>
        {
          editable ?
            <div>
              <Input
              disabled
                style={{ width: 50 }}
                value={value}
                onChange={e => this.handleChange(e)}
              />
              
            </div>
            :
            <div className={isValid==1?"editable-row-text valid":"editable-row-text invalid"}>
              {
                cause==undefined?this.formatValue(value):
                 <Tooltip title={cause}>
                  {this.formatValue(value)}
                </Tooltip>
              }
             
            </div>
        }
      </div>
    );
  }
}

export default EditableCell