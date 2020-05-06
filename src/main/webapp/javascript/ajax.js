const addMovieToCollection = (movieId, collectionId, event) => {
    let buttonElement = event.srcElement;

    let loadingSpinner = document.createElement("div");
    loadingSpinner.classList = "spinner-border spinner-border-sm";

    buttonElement.innerHTML = "";
    buttonElement.appendChild(loadingSpinner);


    let url = `/tracker_app/services/collections/id=${movieId}&collection=${collectionId}&user=${username}`;
    let parameters = {
        "method": "post"
    };

    fetch(url, parameters)
        .then(result => {
            return result.text();
        }).then(result => {
        console.log(result);
        if (result == "The Movie was added" || result == "The Movie was already in the collection") {
            buttonElement.innerHTML = "Added";
            buttonElement.classList = "btn btn-success addToCollectionButton"
        } else {
            buttonElement.innerHTML = "Error";
            buttonElement.classList = "btn btn-danger addToCollectionButton"
        }
    }).catch(error => {
        console.error(error);
        buttonElement.innerHTML = "Error";
        buttonElement.classList = "btn btn-danger addToCollectionButton"
    });
}

const removeMovieFromCollection = (movieId, collectionId) => {
    let url = `/tracker_app/services/collections/id=${movieId}&collection=${collectionId}&user=${username}`;
    let parameters = {
        "method": "delete"
    };

    fetch(url, parameters)
        .then(result => {
            return result.text();
        }).then(result => {
        console.log(result);
    }).catch(error => {
        console.log(error);
    });
}
