import Axios from "axios";
import { EventEmitter } from "events";
import dispatcher from '../dispatcher';

// src/stores/phonebook-store.js

const baseUrl = 'http://localhost:8080/springmvc/api/contacts/';

class PhonebookStore extends EventEmitter{

    constructor() {
        super();
        this.selectedContact = null;
    }
    getSelectedContact() {
        return Promise.resolve(this.selectedContact);
    }
    getContacts(pageNo = 1) {
        return Axios.get(baseUrl + '?page=' + pageNo);
    }
    getContactById(id) {
        return Axios.get(baseUrl + id)
            .then(resp=>{
                this.selectedContact = resp.data;
                this.emit('SELECTION_CHANGED')
            });
    }
    // user defined function for handling actions from the components
    handleActions(action) {
        switch(action.type) {
            case 'SELECTION_CHANGE':
                this.getContactById(action.data.id);
                break;
            case 'DELETE_CONTACT':
                // do something
                break;
        }
    }

}

export const phonebookStore = new PhonebookStore();
dispatcher.register(phonebookStore.handleActions.bind(phonebookStore));
window.dispatcher = dispatcher;