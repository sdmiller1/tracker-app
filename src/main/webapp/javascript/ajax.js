const makeAJAXRequest = (url, callback) => {
    let xhr = new XMLHttpRequest();

    let method = "get";

    xhr.open(method, url);

    xhr.send();

    xhr.addEventListener("readystatechange", () => {
        if(xhr.readyState == 4 && xhr.status == 200) {
            if(xhr.responseXML) {
                callback(xhr.responseXML);
            } else {
                callback(xhr.responseText);
            }
        }
    })
};

const addMovieToCollection = (movieId) => {

    let url = '/tracker-app/services/update-movie-collection/' + movieId;

    const callback = data => {
        data = JSON.parse(data);
        alert(data);

        displayMessage(data, "success");
    }

    makeAJAXRequest(url, callback);
}

const getMoviesToDisplay = (title) => {

    let url = '/tracker-app/services/movies/title=' + title;

    const callback = data => {
        data = JSON.parse(data);
        alert(data);

        let movieGrouping = document.querySelector("#movie-grouping");

        for (movie in data) {
            let movieElement = document.createElement("div");
            let image = document.createElement("img");
            let addButton = document.createElement("button");
            let plusSign = document.createElement("i");

            movieElement.className = "movie";
            image.src = movie.image;
            image.alt = movie.title;
            addButton.setAttribute("onclick", "addMovieToCollection(" + movie.imdbId + ")")
            plusSign.className = "fa fa-plus";

            addButton.appendChild(plusSign);
            movieElement.appendChild(image);
            movieElement.appendChild(addButton);

            movieGrouping.appendChild(movieElement);
        }
    }

    makeAJAXRequest(url, callback);
}