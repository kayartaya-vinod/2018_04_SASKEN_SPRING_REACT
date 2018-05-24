import React from 'react';
import { phonebookStore } from '../stores/phonebook-store';

export class ContactDetails extends React.Component {

    constructor() {
        super();
        this.state = {
            contact: {}
        };
    }

    componentWillMount() {
        // register for phonebookStore events
        phonebookStore.on('SELECTION_CHANGED', ()=>{
            phonebookStore.getSelectedContact()
                .then(c=>this.setState({contact: c}));
        });
    }

    render() {
        if(!this.state.contact.id){
            return <div style={{marginTop: '200px'}} className="alert alert-danger">
                <h3 className="text-center">Please select a contact to view details</h3>
            </div>
        }

        let c = this.state.contact;
        let imgSrc = 'https://vinod.co/randomdata/images/';
        imgSrc += c.gender == 'Male' ? 'men/' : 'women/';
        imgSrc += c.id;
        imgSrc += '.jpg';

        return <div className="row">
            <h1 className="well text-center">Contact details</h1>
            <div className="col-sm-4">
                <img className="img img-thumbnail img-responsive" src={imgSrc} />
            </div>
            <div className="col-sm-8">
                <div className="form-horizontal">
                    <div className="form-group">
                        <label className="control-label col-sm-5">Name</label>
                        <div className="col-sm-7 lead">{c.gender == 'Male' ? 'Mr.' : 'Ms.'} {c.firstName} {c.lastName}</div>
                    </div>
                    <div className="form-group">
                        <label className="control-label col-sm-5">City</label>
                        <div className="col-sm-7 lead">{c.city}</div>
                    </div>
                    <div className="form-group">
                        <label className="control-label col-sm-5">State</label>
                        <div className="col-sm-7 lead">{c.state}</div>
                    </div>
                    <div className="form-group">
                        <label className="control-label col-sm-5">Country</label>
                        <div className="col-sm-7 lead">{c.country}</div>
                    </div>
                    <div className="form-group">
                        <label className="control-label col-sm-5">Email id</label>
                        <div className="col-sm-7 lead">{c.email}</div>
                    </div>
                    <div className="form-group">
                        <label className="control-label col-sm-5">Phone number</label>
                        <div className="col-sm-7 lead">{c.phone}</div>
                    </div>
                </div>
            </div>
        </div>
    }
}
