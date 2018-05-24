// src/components/contact-card.js

import React from 'react';
import $ from 'jquery';
import { changeCurrentSelection } from '../actions';

export class ContactCard extends React.Component {

    render() {
        var c = this.props.contact;

        let imgSrc = 'https://vinod.co/randomdata/images/';
        imgSrc += c.gender == 'Male' ? 'men/' : 'women/';
        imgSrc += c.id;
        imgSrc += '.jpg';

        return <div className="row">
            <div className="col-sm-3">
                <img className="img img-circle img-responsive"
                    src={imgSrc} />
            </div>
            <div className="col-sm-9">
                <h3 style={{ cursor: 'pointer' }}
                    onClick={() => {
                        changeCurrentSelection(c.id);
                        $('body, html').animate({
                            scrollTop: '0'
                        });
                    }}>

                    {c.gender == 'Male' ? 'Mr.' : 'Ms.'}
                    {c.firstName} {c.lastName}
                </h3>
                <p>{c.email}</p>
                <p>{c.phone}</p>
            </div>
        </div>

    }
}