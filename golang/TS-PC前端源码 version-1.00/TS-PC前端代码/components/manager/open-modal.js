import React from 'react'
import { Modal, Button } from 'antd';
import { TweenOneGroup } from 'rc-tween-one'

class Openmodal extends React.Component {
	constructor(props) {
		super(props);
	}

	success() {
	  const modal = Modal.success({
	    title: 'This is a notification message',
	    content: 'This modal will be destroyed after 1 second',
	  });
	  setTimeout(() => modal.destroy(), 1000);
	}
	render(){
		return(
			<Button onClick={success}>Success</Button>
		)
	}
}
export default Openmodal