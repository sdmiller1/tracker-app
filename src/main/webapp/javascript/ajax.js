const addMovieToCollection = (movieId, collectionId) => {
    let url = `/tracker_app/services/collections/id=${movieId}&collection=${collectionId}&user=${username}`;
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
