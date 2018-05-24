import React from 'react';

// the name of the class is used as a HTML tag
export class HelloWorld extends React.Component {

    // overriding the super class function
    render() {
        // the return value of this method represents the UI of
        // the component, which is an XML syntax. Since we are 
        // combining JavaScript and XML syntax, it is called JSX
        return <div>
            <h1 className="alert alert-success">
                Hello, World!
            </h1>
            <hr />
            <p className="lead">My name is Vinod</p>
        </div>
    }
}