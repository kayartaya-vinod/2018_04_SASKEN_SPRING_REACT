import React from 'react';
import { render } from 'react-dom';

class HelloWorld extends React.Component {
	render() {
		return <div className="container">
            <h1 className="alert alert-info">Hello, World!</h1>
            <hr />
            <p className="lead">My name is {this.props.name}, How's weather in {this.props.city}?</p>
        </div>
	}
}

render(<HelloWorld name="Don" city="Bangalore" />, document.querySelector('#main'))
