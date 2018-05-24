// src/components/sidebar.js

import React from 'react';
import {ContactCard} from './contact-card';
import { phonebookStore } from '../stores/phonebook-store';

export class Sidebar extends React.Component {

    constructor() {
        super(); // mandatory
        this.state = {
            contacts: []
        };
    }

    componentWillMount() {
        phonebookStore.getContacts()
            .then( resp => this.setState({contacts: resp.data }) );
    }

    render() {
        let cards = this.state.contacts.map( 
            c => <ContactCard contact={c} key={c.id} />)
        return cards;
    }
}