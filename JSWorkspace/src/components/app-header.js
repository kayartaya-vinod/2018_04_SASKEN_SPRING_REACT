// src/components/app-header.js

import React from 'react';

export class AppHeader extends React.Component {
    render() {
        return <div className="navbar navbar-default navbar-fixed-top">
            <div className="navbar-header">
                <a href="#" className="navbar-brand">
                    {this.props.title}
                </a>
            </div>
        </div>
    }
}