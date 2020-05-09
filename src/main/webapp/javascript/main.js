const getMoviesToDisplay = (title) => {
    let movies = getMovieByTitle(title, (movies) => {
        let outputLocation = document.querySelector("#movieOutputLocation");

        for (movie of movies) {
            let movieContainer = document.createElement("div");
            movieContainer.classList = "col-6 col-lg-3 my-2";

            let movieCard = `
                <div class="card h-100">
                    <a href="movie?id=${movie.imdbId}">
                        <img class="card-img-top" src="${movie.image}" alt="${movie.title}">
                    </a>
                    <div class="card-body">
                        <a href="movie?id=${movie.imdbId}" class="noLinkStyle"><h2 class="card-title h4">${movie.title}</h2></a>
                        
                            <button type="button" class="btn btn-primary w-100" onclick="displayCollectionModal('${movie.imdbId}')" data-toggle="modal" data-target="#collectionModal">
                                Add To Collection
                            </button>
                        
                        
                    </div>
                </div>
                `;

            movieContainer.innerHTML = movieCard;

            outputLocation.appendChild(movieContainer);

        }
    });
}

const rateMovie = (ratingButton) => {
    let rating = ratingButton.dataset.rating;
    let imdbId = ratingButton.parentNode.dataset.imdbid;

    setMovieRating(rating);

    submitRating(imdbId, rating);
}

const setMovieRating = (rating) => {
    if (rating > 0) {
        let stars = Array.from(document.querySelectorAll(".rating i"));
        let fullStars = stars.filter(x => x.dataset.rating <= rating);
        let emptyStars = stars.filter(x => x.dataset.rating > rating)

        fullStars.map(x => x.classList = "fa fa-star text-warning");
        emptyStars.map(x => x.classList = "fa fa-star-o text-secondary");
    }
}

const displayCollectionModal = (imdbid) => {
    let collectionButtons = document.querySelectorAll(".addToCollectionButton");

    for (button of collectionButtons) {
        let collectionId = button.dataset.collectionid;
        button.onclick = (event) => {addMovieToCollection(imdbid, collectionId, event)};

        button.innerHTML = "Add";
        button.classList = "btn btn-primary addToCollectionButton";
    }
}

const errorMessage = (message) => {
    displayMessage(message, "Error! ", "danger", "#alertBox");
}

const successMessage = (message) => {
    displayMessage(message, "Success! ", "success", "#alertBox");
}

const formErrorMessage = (message) => {
    displayMessage(message, "", "danger", "#formAlertBox");
}

const displayMessage = (message, keyword, bootstrapColor, outputBox) => {
    let alertLocation = document.querySelector(outputBox);
    alertLocation.innerHTML = "";

    let alertBox = document.createElement("div");
    alertBox.classList = "alert alert-" + bootstrapColor + " alert-dismissible fade show";

    let closeButton = document.createElement("button");
    closeButton.classList = "close";
    closeButton.setAttribute("data-dismiss", "alert");
    closeButton.type = "button";
    closeButton.innerHTML = "&times;";

    let boldText = document.createElement("strong");
    boldText.textContent = keyword;

    let errorMessage = document.createElement("span");
    errorMessage.textContent = message;

    alertBox.appendChild(closeButton);
    alertBox.appendChild(boldText);
    alertBox.appendChild(errorMessage);

    alertLocation.appendChild(alertBox);
}