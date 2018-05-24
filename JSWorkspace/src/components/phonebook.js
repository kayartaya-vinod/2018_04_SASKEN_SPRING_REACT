import React from 'react';
import { AppHeader } from './app-header';
import { store } from '../stores/phonebook-store';
import { Sidebar } from './sidebar';
import { ContactDetails } from './contact-details';

export class Phonebook extends React.Component {
    render() {
        let x = "Phonebook App";

        return <div>
            <AppHeader title={x} />
            <div style={{marginTop: "50px"}}>
                <div className="row">
                    <div className="col-sm-5"><Sidebar /></div>
                    <div className="col-sm-7"><ContactDetails /></div>
                </div>
            </div>
        </div>
    }
}