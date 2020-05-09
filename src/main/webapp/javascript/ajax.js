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

const removeMovieFromCollection = (movieId, collectionId, buttonElement) => {

    let movieCard = buttonElement.parentNode.parentNode.parentNode;

    let url = `/tracker_app/services/collections/id=${movieId}&collection=${collectionId}&user=${username}`;
    let parameters = {
        "method": "delete"
    };

    fetch(url, parameters)
        .then(result => {
            return result.text();
        }).then(result => {
        console.log(result);
        if (result == "Successfully removed the Movie") {
            movieCard.remove();
        } else {
            buttonElement.innerHTML = "Error";
        }
    }).catch(error => {
        console.log(error);
        buttonElement.innerHTML = "Error";
    });
}

const submitRating = (imdbid, rating) => {

    let url = `/tracker_app/services/ratings/id=${imdbid}&rating=${rating}&user=${username}`;
    let parameters = {
        "method": "post"
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

const getMovieByTitle = (title, callback) => {
    let url = `/tracker_app/services/movies/title=${title}`;
    let parameters = {
        "method": "get"
    };

    fetch(url, parameters)
        .then(result => {
            return result.json();
        }).then(result => {
        console.log(result);
        callback(result);
    }).catch(error => {
        console.log(error);
    });
}
