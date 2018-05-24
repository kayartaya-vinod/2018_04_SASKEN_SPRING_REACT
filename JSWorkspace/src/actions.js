// src/actions.js

import dispatcher from "./dispatcher";

export function changeCurrentSelection(id) {
    dispatcher.dispatch({
        type: 'SELECTION_CHANGE',
        data: { 'id': id }
    });
}

export function deleteContact(id) {
    dispatcher.dispatch({
        type: 'DELETE_CONTACT',
        data: { 'id': id }
    });
}